package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountRoleEnum;
import com.callcenter.AuthService.Constants.AccountStatusEnum;
import com.callcenter.AuthService.Constants.Register.RegisterStatusConstants;
import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import com.callcenter.AuthService.DTO.Register.RegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.Entities.AccountEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Services.AccountRegisterType.AccountRegisterTypeService;
import com.callcenter.AuthService.Services.AccountStatusService.AccountStatusService;
import com.callcenter.AuthService.Services.RegisterService.RegisterServiceProviderV2;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import com.callcenter.AuthService.Services.AccountRoleService.AccountRoleService;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AccountService
{

    private RegisterServiceProviderV2 registerServiceProviderV2;
    private AccountRepository accountRepository;
    private AccountRoleService roleService;
    private AccountRegisterTypeService accountRegisterTypeService;
    private AccountStatusService accountStatusService;


    public AccountService()
    {}

    @Autowired
    public AccountService(RegisterServiceProviderV2 registerServiceProviderV2, AccountRepository accountRepository,
                          AccountRoleService roleService, AccountRegisterTypeService accountRegisterTypeService,
                          AccountStatusService accountStatusService)
    {
        this.registerServiceProviderV2 = registerServiceProviderV2;
        this.accountRepository = accountRepository;
        this.roleService = roleService;
        this.accountRegisterTypeService = accountRegisterTypeService;
        this.accountStatusService = accountStatusService;
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
            AccountEntity entity = AccountEntity.getInstance(authInfoId, role, status, registerType, currentDate);
            return accountRepository.save(entity);
        }
        catch (Exception exception)
        {
            return null;
        }
    }

    public <SR extends ServiceResult, RSR extends RegisterStrategyResult, I extends RegisterInput> RegisterResult create (I input)
    {
        RegisterResult finalResult = new RegisterResult();

        //get the role's id by using AccountRoleService
        Integer roleId = roleService.getRoleId(input.getRoleValue());
        if(roleId == null || roleId == roleService.getRoleId(AccountRoleEnum.ADMIN.getValue()))
        {
            finalResult.setSuccess(false);
            finalResult.setHttpResult(RegisterStatusConstants.UNRECOGNIZED_ROLE_VALUE);
            return finalResult;
        }

        //get id of the register type by AccountRegisterTypeService
        Integer registerTypeId = accountRegisterTypeService.getRegisterTypeId(input.getRegisterType());
        if(registerTypeId == null)
        {
            finalResult.setSuccess(false);
            finalResult.setHttpResult(RegisterStatusConstants.UNRECOGNIZED_REGISTER_TYPE);
            return finalResult;
        }

        RegisterStrategy<RSR, I> registerStrategy = registerServiceProviderV2.getRegisterStrategy(input.getClass());
        if(registerStrategy == null)
        {
            System.out.println(new NoSuchMethodException("Register strategy is null, check if the strategy has no target type of input to handle"));
            return null;
        }

        RegisterStrategyResult strategyResult = registerStrategy.register(input);
        if(strategyResult.isSuccess() == false)
        {
            finalResult.setSuccess(strategyResult.isSuccess());
            finalResult.setStatusCode(strategyResult.getStatusCode());
            finalResult.setMessage(strategyResult.getMessage());
            return finalResult;
        }

        Integer statusId = accountStatusService.getAccountStatusId(AccountStatusEnum.INITIALIZED);

        //create a record in Account Table
        String authInfoId = strategyResult.getRecordId();

        try
        {
            AccountEntity newAccountEntity = createAccountRecord(authInfoId, roleId, statusId, registerTypeId, null);
        }
        catch (Exception exception)
        {
            System.out.println(exception);
            finalResult.setSuccess(false);
            finalResult.setHttpResult(RegisterStatusConstants.VIOLATION_IN_CREDENTIALS_TO_CREATE_ACCOUNT);
            registerStrategy.rollback(strategyResult.getRecordId());
            return finalResult;
        }

        finalResult.setSuccess(true);
        finalResult.setHttpResult(RegisterStatusConstants.SUCCESS);

        return finalResult;
    }

}
