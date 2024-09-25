package com.callcenter.AuthService.Services.RegisterService.Strategies;

import com.callcenter.AuthService.Constants.Register.RegisterStatusConstants;
import com.callcenter.AuthService.DTO.Register.Internal.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Register.Internal.EaPAccountRegisterResult;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EaPAccountRegisterStrategy extends RegisterStrategy<EaPAccountRegisterResult, EaPAccountRegisterInput>
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
    public EaPAccountRegisterResult register(EaPAccountRegisterInput input)
    {
        System.out.println("Email & Password Strategy");
        EaPAccountRegisterResult result = new EaPAccountRegisterResult();

        Optional<EmailPasswordAuthenticationEntity> optionalEmailPasswordAuthenticationEntity = emailPasswordRepository.findByEmail(input.getEmail());

        if(optionalEmailPasswordAuthenticationEntity.isEmpty() == false)
        {
            result.setSuccess(false);
            result.setMessage(RegisterStatusConstants.EMAIL_ALREADY_USED.message());
            result.setStatusCode(RegisterStatusConstants.EMAIL_ALREADY_USED.statusCode());
            return result;
        }

        EmailPasswordAuthenticationEntity newEntityRecord = EmailPasswordAuthenticationEntity.getInstance(
                input.getEmail(), input.getPassword()
        );

        EmailPasswordAuthenticationEntity createdRecord = emailPasswordRepository.save(newEntityRecord);

        result.setId(createdRecord.getId());
        result.setSuccess(true);
        result.setMessage(RegisterStatusConstants.SUCCESS.message());
        result.setStatusCode(RegisterStatusConstants.SUCCESS.statusCode());

        return result;
    }
}
