package com.callcenter.AuthService.Services.RegisterService;

import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import com.callcenter.AuthService.DTO.Register.RegisterInput;
import com.callcenter.AuthService.Support.ApplicationContext.ApplicationContextProvider;
import com.callcenter.AuthService.Support.Package.PackageAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceProviderV2<R extends RegisterStrategyResult, I extends RegisterInput>
{
    private static final String packageOfStrategiesPath = "com.callcenter.AuthService.Services.RegisterService.Strategies";
    private Map<Class<?>, RegisterStrategy<R,I>> strategyRegistry = null;

    private ApplicationContextProvider applicationContextProvider;

    public RegisterServiceProviderV2() {}

    @Autowired
    public RegisterServiceProviderV2(ApplicationContextProvider applicationContextProvider)
    {
        this.applicationContextProvider = applicationContextProvider;
        initialize();
    }

    private void initialize()
    {
        PackageAccess packageAccess = new PackageAccess();
        List<Class> listOfClasses = packageAccess.findAllClassesOfPackage(packageOfStrategiesPath);

        Map<Class<?>, RegisterStrategy<R,I>> strategyMap = new HashMap<>();

        ApplicationContext applicationContext = this.applicationContextProvider.getApplicationContext();

        for(int i = 0; i < listOfClasses.size(); i ++)
        {
            RegisterStrategy<R, I> strategy = (RegisterStrategy<R, I>) applicationContext.getBean(listOfClasses.get(i));

            strategyMap.put(strategy.getInputClassToHandle(), strategy);
        }

        this.strategyRegistry = strategyMap;
    }

    public RegisterStrategy<R,I> getRegisterStrategy(Class<I> inputClass)
    {
        return this.strategyRegistry.get(inputClass);
    }
}
