package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountRoleEnum;
import com.callcenter.AuthService.Constants.AccountStatusEnum;
import com.callcenter.AuthService.Constants.JWT.TokenVerificationException;
import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.Constants.SignIn.SignInException;
import com.callcenter.AuthService.DTO.Endpoint.APIEndPoint;
import com.callcenter.AuthService.DTO.JWT.JwtClaimsProvider;
import com.callcenter.AuthService.DTO.JWT.JwtFullClaims;
import com.callcenter.AuthService.DTO.JWT.JwtGeneratedToken;
import com.callcenter.AuthService.DTO.SignIn.ExternalInput.EaPSignInInfoRequest;
import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import com.callcenter.AuthService.DTO.Register.RegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.DTO.SignIn.SignInResult;
import com.callcenter.AuthService.DTO.TokenVerification.TokenVerificationResult;
import com.callcenter.AuthService.Entities.AccountEntity;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Entities.PermissionEntity;
import com.callcenter.AuthService.Entities.RoleEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import com.callcenter.AuthService.Services.Events.AccountEventPublisher;
import com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent.RefreshTokenEventProp;
import com.callcenter.AuthService.Services.JwtService.JwtService;
import com.callcenter.AuthService.Services.RegisterService.RegisterServiceProviderV2;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import com.callcenter.AuthService.Support.Password.SupportPasswordProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccountService
{

    private RegisterServiceProviderV2 registerServiceProviderV2;
    private AccountRepository accountRepository;
    private AccountRoleService roleService;
    private AccountRegisterTypeService accountRegisterTypeService;
    private AccountStatusService accountStatusService;
    private EmailPasswordRepository emailPasswordRepository;
    private SupportPasswordProvider supportPasswordProvider;
    private JwtService jwtService;
    private PermissionService permissionService;

    private AccountEventPublisher accountEventPublisher;

    public AccountService()
    {}

    @Autowired
    public AccountService(RegisterServiceProviderV2 registerServiceProviderV2, AccountRepository accountRepository,
                          AccountRoleService roleService, AccountRegisterTypeService accountRegisterTypeService,
                          AccountStatusService accountStatusService, EmailPasswordRepository emailPasswordRepository,
                          SupportPasswordProvider supportPasswordProvider, JwtService jwtService,
                          AccountEventPublisher accountEventPublisher,
                          PermissionService permissionService)
    {
        this.registerServiceProviderV2 = registerServiceProviderV2;
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.accountRegisterTypeService = accountRegisterTypeService;
        this.accountStatusService = accountStatusService;
        this.emailPasswordRepository = emailPasswordRepository;
        this.supportPasswordProvider = supportPasswordProvider;
        this.jwtService = jwtService;
        this.accountEventPublisher = accountEventPublisher;
        this.permissionService = permissionService;
    }

    protected AccountEntity createAccountRecord(
            @NonNull String authInfoId, @NonNull Integer role, @NonNull Integer status, @NonNull Integer registerType,@Nullable Date createdAt)
    {
        Date currentDate = new Date();
        if(createdAt != null)
        {
            currentDate = createdAt;
        }

        try {
            AccountEntity entity = AccountEntity.getInstance(authInfoId, status, registerType, currentDate, role);
            return accountRepository.save(entity);
        }
        catch (Exception exception)
        {
            return null;
        }
    }

    protected PermissionEntity getPermissionByEndpointAndRole(Integer roleId, String accountId, APIEndPoint endPoint) throws Exception
    {
        RoleEntity roleEntity = roleService.getRoleEntityById(roleId);
        if(roleEntity == null)
        {
            return null;
        }

        PermissionEntity permissionEntity = this.permissionService.verifyPermissionByRoleId(roleEntity.getRefer_table(), accountId, endPoint);

        System.err.println(permissionEntity);
        return permissionEntity;
    }

    public <RSR extends RegisterStrategyResult, I extends RegisterInput> ServiceResult create (I input) throws RegisterException
    {
        //get the role's id by using AccountRoleService
        Integer roleId = roleService.getRoleId(input.getRoleValue());
        if(roleId == null || roleId == roleService.getRoleId(AccountRoleEnum.ADMIN.getValue()))
        {
            throw RegisterException.UNRECOGNIZED_ROLE_VALUE;
        }

        //get id of the register type by AccountRegisterTypeService
        Integer registerTypeId = accountRegisterTypeService.getRegisterTypeId(input.getRegisterType());
        if(registerTypeId == null)
        {
            throw RegisterException.UNRECOGNIZED_REGISTER_TYPE;
        }

        RegisterStrategy<RSR, I> registerStrategy = registerServiceProviderV2.getRegisterStrategy(input.getClass());
        if(registerStrategy == null)
        {
            throw RegisterException.UNSUPPORTED_REGISTER_STRATEGY;
        }

        RegisterStrategyResult strategyResult = registerStrategy.register(input);

        Integer statusId = accountStatusService.getAccountStatusId(AccountStatusEnum.INITIALIZED);

        //create a record in Account Table
        String authInfoId = strategyResult.getRecordId();

        try
        {
            AccountEntity newAccountEntity = createAccountRecord(authInfoId, roleId, statusId, registerTypeId, null);
            ServiceResult<RegisterResult> serviceResult = new ServiceResult<>();
            serviceResult.setObject(new RegisterResult(newAccountEntity));
            serviceResult.setSuccess(true);

            return serviceResult;
        }
        catch (Exception exception)
        {
            System.out.println(exception);
            throw RegisterException.VIOLATION_IN_CREDENTIALS_TO_CREATE_ACCOUNT;
        }
    }

    public ServiceResult<SignInResult> signInByEmailPassword(EaPSignInInfoRequest request) throws SignInException
    {
        Optional< EmailPasswordAuthenticationEntity> optionalAuthenticationEntity = this.emailPasswordRepository.findByEmail(request.email());
        if(optionalAuthenticationEntity.isEmpty())
        {
            throw SignInException.INVALID_EMAIL_PASSWORD;
        }

        EmailPasswordAuthenticationEntity authenticationEntity = optionalAuthenticationEntity.get();

        //validate the password
        boolean isValidRawPassword = this.supportPasswordProvider.matchByBCrypt(authenticationEntity.getPassword(), request.password());
        if(isValidRawPassword == false)
        {
            throw SignInException.INVALID_EMAIL_PASSWORD;
        }

        //get account info by using authenticationEntity.getId()
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findByAuthInfoId(authenticationEntity.getId());
        if(optionalAccountEntity.isEmpty())
        {
            throw SignInException.ACCOUNT_NOT_FOUND;
        }

        AccountEntity accountEntity = optionalAccountEntity.get();
        JwtGeneratedToken accessToken = jwtService.generateAccessToken(accountEntity);
        JwtGeneratedToken refreshToken = jwtService.generateRefreshToken(accountEntity);

        SignInResult signInResult = SignInResult.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiration(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiration(refreshToken.getExpiredAt())
                .build();

        ServiceResult serviceResult = new ServiceResult<>(true, signInResult);

        //System.out.println(Thread.currentThread());

        //publish an event to execute background task of recording refresh token
        RefreshTokenEventProp eventProp = new RefreshTokenEventProp(accountEntity.getId(), refreshToken.getToken(), refreshToken.getIssuedAt(), refreshToken.getExpiredAt());
        accountEventPublisher.publishEvent(eventProp);

        return serviceResult;
    }

    public ServiceResult<TokenVerificationResult> verifyAccessToken(String token, Date requestTime, APIEndPoint endPoint) throws TokenVerificationException
    {
        JwtFullClaims fullClaims = null;

        //check whether the token is valid or not
        try
        {
            fullClaims = jwtService.getAccessTokenClaims(token, JwtClaimsProvider::toJwtFullClaims);
        }
        catch (ExpiredJwtException exception)
        {
            throw TokenVerificationException.EXPIRED_TOKEN;
        }
        catch (UnsupportedJwtException exception)
        {
            throw TokenVerificationException.UNSUPPORTED_JWT_TOKEN;
        }
        catch (MalformedJwtException exception)
        {
            //TODO: do security process here
            throw TokenVerificationException.MALFORMED_JWT;
        }
        catch (SignatureException exception)
        {
            //TODO: do security process here
            throw TokenVerificationException.INVALID_SIGNATURE;
        }
        catch (IllegalArgumentException exception)
        {
            //TODO: do security process here
            throw TokenVerificationException.ILLEGAL_ARGUMENTS;
        }

        //check the expiration of the token
        if(fullClaims.getExpiration().before(requestTime))
        {
            throw TokenVerificationException.EXPIRED_TOKEN;
        }

        String accountId = fullClaims.getKey();

        //get data from the database
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);
        if(optionalAccountEntity.isEmpty())
        {
            throw TokenVerificationException.INVALID_AUTHORIZATION_INFO;
        }

        AccountEntity accountEntity = optionalAccountEntity.get();
        if(accountEntity.getRole() != fullClaims.getRole())
        {
            //TODO: do security process here
            throw TokenVerificationException.ILLEGAL_ARGUMENTS;
        }

        //identify whether this user has the target permission accompanied by the role in the token
        TokenVerificationResult result = null;
        try
        {
            PermissionEntity permissionEntity = this.getPermissionByEndpointAndRole(accountEntity.getRole(), accountEntity.getId(), endPoint);
            if(permissionEntity == null)
            {
                throw TokenVerificationException.INVALID_AUTHORIZATION_INFO;
            }

            result = TokenVerificationResult.builder()
                    .targetEntity(accountEntity)
                    .targetPermission(permissionEntity.getValue())
                    .issuedAt(fullClaims.getIssuedAt())
                    .expiration(fullClaims.getExpiration())
                    .build();
        }
        catch (Exception exception) //usually happen by not-found-permission-record exception after mapping the permission value with banned permissions
        {
            System.err.println(exception);
            throw TokenVerificationException.FORBIDDEN_PERMISSION_ACCESS;
        }

        ServiceResult<TokenVerificationResult> serviceResult = new ServiceResult<>(true, result);
        return serviceResult;
    }

}
