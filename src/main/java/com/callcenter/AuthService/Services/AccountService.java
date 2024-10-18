package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountRoleEnum;
import com.callcenter.AuthService.Constants.AccountStatusEnum;
import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.Constants.SignIn.SignInException;
import com.callcenter.AuthService.DTO.JWT.JwtGeneratedToken;
import com.callcenter.AuthService.DTO.SignIn.ExternalInput.EaPSignInInfoRequest;
import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import com.callcenter.AuthService.DTO.Register.RegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.DTO.SignIn.SignInResult;
import com.callcenter.AuthService.Entities.AccountEntity;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import com.callcenter.AuthService.Services.Events.AccountEventPublisher;
import com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent.RefreshTokenEventProp;
import com.callcenter.AuthService.Services.JwtService.JwtService;
import com.callcenter.AuthService.Services.RegisterService.RegisterServiceProviderV2;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import com.callcenter.AuthService.Support.Password.SupportPasswordProvider;
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

    private AccountEventPublisher accountEventPublisher;

    public AccountService()
    {}

    @Autowired
    public AccountService(RegisterServiceProviderV2 registerServiceProviderV2, AccountRepository accountRepository,
                          AccountRoleService roleService, AccountRegisterTypeService accountRegisterTypeService,
                          AccountStatusService accountStatusService, EmailPasswordRepository emailPasswordRepository,
                          SupportPasswordProvider supportPasswordProvider, JwtService jwtService,
                          AccountEventPublisher accountEventPublisher)
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

//        System.out.println(Thread.currentThread());

        //publish an event to execute background task of recording refresh token
        RefreshTokenEventProp eventProp = new RefreshTokenEventProp(accountEntity.getId(), refreshToken.getToken(), refreshToken.getIssuedAt(), refreshToken.getExpiredAt());
        accountEventPublisher.publishEvent(eventProp);

        return serviceResult;
    }

}
