package com.callcenter.AuthService.Services.RegisterService.Strategies;

import com.callcenter.AuthService.Constants.Register.RegisterStatusConstants;
import com.callcenter.AuthService.DTO.Register.InternalInput.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Register.InternalOutput.EaPAccountRegisterStrategyResult;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EaPAccountRegisterStrategy extends RegisterStrategy<EaPAccountRegisterStrategyResult, EaPAccountRegisterInput>
{
    protected static final Class<EaPAccountRegisterInput> inputClassToHandle = EaPAccountRegisterInput.class;

    private AccountRepository accountRepository;
    private EmailPasswordRepository emailPasswordRepository;

    @Autowired
    public EaPAccountRegisterStrategy(AccountRepository accountRepository, EmailPasswordRepository emailPasswordRepository)
    {
        super(inputClassToHandle.getName(), inputClassToHandle);
        this.accountRepository = accountRepository;
        this.emailPasswordRepository = emailPasswordRepository;
    }

    @Override
    public String getKey() {
        return super.getKey();
    }

    @Override
    public Class<EaPAccountRegisterInput> getInputClassToHandle() {
        return inputClassToHandle;
    }

    @Override
    public EaPAccountRegisterStrategy initializes() {
        super.setKey(EaPAccountRegisterStrategy.inputClassToHandle.getName());
        super.setInputClassToHandle(EaPAccountRegisterStrategy.inputClassToHandle);

        return this;
    }

    @Override
    public EaPAccountRegisterStrategyResult register(EaPAccountRegisterInput input)
    {
        System.out.println("Email & Password Strategy");
        EaPAccountRegisterStrategyResult result = new EaPAccountRegisterStrategyResult();

        Optional<EmailPasswordAuthenticationEntity> optionalEmailPasswordAuthenticationEntity = emailPasswordRepository.findByEmail(input.getEmail());

        //the email has already been registered, force to use new email
        if(optionalEmailPasswordAuthenticationEntity.isEmpty() == false)
        {
            result.setRecordId(null);
            result.setSuccess(false);
            result.setHttpResult(RegisterStatusConstants.EMAIL_ALREADY_USED);
            return result;
        }

        EmailPasswordAuthenticationEntity newEntityRecord = EmailPasswordAuthenticationEntity.getInstance(
                input.getEmail(), input.getPassword()
        );

        EmailPasswordAuthenticationEntity createdRecord = emailPasswordRepository.save(newEntityRecord);

        result.setRecordId(createdRecord.getId());
        result.setEmail(newEntityRecord.getEmail());
        result.setPassword(newEntityRecord.getPassword());
        result.setSuccess(true);
        result.setHttpResult(RegisterStatusConstants.SUCCESS);

        return result;
    }

    @Override
    public void rollback(String recordId)
    {
        emailPasswordRepository.deleteById(recordId);
    }

}
