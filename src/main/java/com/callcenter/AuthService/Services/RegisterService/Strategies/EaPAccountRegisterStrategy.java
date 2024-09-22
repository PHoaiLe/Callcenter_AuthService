package com.callcenter.AuthService.Services.RegisterService.Strategies;

import com.callcenter.AuthService.DTO.Internal.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Internal.EaPAccountRegisterResult;
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

    public EaPAccountRegisterStrategy()
    {
        super(inputClassToHandle.getName(), inputClassToHandle);
    }

//    @Autowired
//    public void setAccountRepository(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    @Autowired
//    public void setEmailPasswordRepository(EmailPasswordRepository emailPasswordRepository) {
//        this.emailPasswordRepository = emailPasswordRepository;
//    }

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

        EmailPasswordAuthenticationEntity emailPasswordAuthenticationEntity = EmailPasswordAuthenticationEntity.getInstance(
                input.getEmail(), input.getPassword()
        );

        Optional<EmailPasswordAuthenticationEntity> optionalEmailPasswordAuthenticationEntity = emailPasswordRepository.findByEmail(input.getEmail());

        System.out.println("Existed Account: ");
        System.out.println(optionalEmailPasswordAuthenticationEntity.get());

        if(optionalEmailPasswordAuthenticationEntity.isEmpty() == false)
        {
            System.out.println("Account has already existed");
            return null;
        }

        return null;
    }
}
