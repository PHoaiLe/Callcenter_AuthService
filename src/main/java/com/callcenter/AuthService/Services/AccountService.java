package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountRoleEnum;
import com.callcenter.AuthService.Constants.AccountStatusEnum;
import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.DTO.Login.ExternalInput.EaPSignInInfoRequest;
import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import com.callcenter.AuthService.DTO.Register.RegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.Entities.AccountEntity;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import com.callcenter.AuthService.Services.AccountRegisterType.AccountRegisterTypeService;
import com.callcenter.AuthService.Services.AccountStatusService.AccountStatusService;
import com.callcenter.AuthService.Services.RegisterService.RegisterServiceProviderV2;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import com.callcenter.AuthService.Services.AccountRoleService.AccountRoleService;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.apache.logging.log4j.Logger;
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
    private Logger logger;


    public AccountService()
    {}

    @Autowired
    public AccountService(RegisterServiceProviderV2 registerServiceProviderV2, AccountRepository accountRepository,
                          AccountRoleService roleService, AccountRegisterTypeService accountRegisterTypeService,
                          AccountStatusService accountStatusService, EmailPasswordRepository emailPasswordRepository,
                          Logger logger)
    {
        this.registerServiceProviderV2 = registerServiceProviderV2;
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.accountRegisterTypeService = accountRegisterTypeService;
        this.accountStatusService = accountStatusService;
        this.emailPasswordRepository = emailPasswordRepository;
        this.logger = logger;
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
            AccountEntity entity = AccountEntity.getInstance(authInfoId, status, registerType, currentDate);
            return accountRepository.save(entity);
        }
        catch (Exception exception)
        {
            return null;
        }
    }

    public <SR extends ServiceResult, RSR extends RegisterStrategyResult, I extends RegisterInput> RegisterResult create (I input) throws RegisterException
    {
        RegisterResult finalResult = new RegisterResult();

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
            finalResult.setSuccess(true);
            finalResult.setObject(newAccountEntity);
        }
        catch (Exception exception)
        {
            logger.error(exception.getMessage());
            throw RegisterException.VIOLATION_IN_CREDENTIALS_TO_CREATE_ACCOUNT;
        }

        return finalResult;
    }

    public void signInByEmailPassword(EaPSignInInfoRequest request)
    {
        Optional< EmailPasswordAuthenticationEntity> authenticationEntity = this.emailPasswordRepository.findByEmail(request.email());
//        if(authenticationEntity.isEmpty())
//        {
//            throw
//        }
    }

}
