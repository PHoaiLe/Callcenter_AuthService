package com.callcenter.AuthService.Services.RegisterService.Strategies;

import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.DTO.Register.InternalInput.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Register.InternalOutput.EaPAccountRegisterStrategyResult;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import com.callcenter.AuthService.Support.Password.SupportPasswordProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EaPAccountRegisterStrategy extends RegisterStrategy<EaPAccountRegisterStrategyResult, EaPAccountRegisterInput>
{
    protected static final Class<EaPAccountRegisterInput> inputClassToHandle = EaPAccountRegisterInput.class;

    private AccountRepository accountRepository;
    private EmailPasswordRepository emailPasswordRepository;
    private SupportPasswordProvider supportPasswordProvider;

    @Autowired
    public EaPAccountRegisterStrategy(AccountRepository accountRepository, EmailPasswordRepository emailPasswordRepository,
                                      SupportPasswordProvider supportPasswordProvider)
    {
        super(inputClassToHandle.getName(), inputClassToHandle);
        this.accountRepository = accountRepository;
        this.emailPasswordRepository = emailPasswordRepository;
        this.supportPasswordProvider = supportPasswordProvider;
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
    public EaPAccountRegisterStrategyResult register(EaPAccountRegisterInput input) throws RegisterException
    {
        System.out.println("Email & Password Strategy");

        Optional<EmailPasswordAuthenticationEntity> optionalEmailPasswordAuthenticationEntity = emailPasswordRepository.findByEmail(input.getEmail());

        //the email has already been registered, force to use new email
        if(optionalEmailPasswordAuthenticationEntity.isEmpty() == false)
        {
            throw RegisterException.EMAIL_ALREADY_USED;
        }

        //encode the password
        String encodedPassword = this.supportPasswordProvider.encodeByBcrypt(input.getPassword());

        EmailPasswordAuthenticationEntity newEntityRecord = EmailPasswordAuthenticationEntity.getInstance(
                input.getEmail(), encodedPassword
        );

        EmailPasswordAuthenticationEntity createdRecord = emailPasswordRepository.save(newEntityRecord);

        EaPAccountRegisterStrategyResult result = new EaPAccountRegisterStrategyResult(createdRecord.getId());

        return result;
    }

    @Override
    public void rollback(String recordId)
    {
        emailPasswordRepository.deleteById(recordId);
    }

}
