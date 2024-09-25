package com.callcenter.AuthService.Services.RegisterService;

import com.callcenter.AuthService.DTO.Register.RegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.Support.Package.PackageAccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Please access the link below for more detail
//https://www.baeldung.com/java-singleton-double-checked-locking
public class RegisterServiceProvider<R extends RegisterResult, I extends RegisterInput>
{
    private static final String packageOfStrategiesPath = "com.callcenter.AuthService.Services.RegisterService.Strategies";
    private  Map<Class<?>, RegisterStrategy<R,I>> strategyRegistry = null;

    //One thing to keep in mind with this pattern is that the field needs to be volatile to prevent cache incoherence issues.
    // In fact, the Java memory model allows the publication of partially initialized objects and this may lead in turn to subtle bugs.
    private static volatile RegisterServiceProvider instance = null;

    private RegisterServiceProvider()
    {
        this.strategyRegistry = new HashMap<>();
    }

    private RegisterServiceProvider(Map<Class<?>, RegisterStrategy<R,I>> strategyRegistry)
    {
        this.strategyRegistry = strategyRegistry;
    }

    public static RegisterServiceProvider getInstance()
    {
        if(instance == null)
        {
            synchronized (RegisterServiceProvider.class)
            {
                if(instance == null)
                {
                    PackageAccess packageAccess = new PackageAccess();
                    List<Class> listOfClasses = packageAccess.findAllClassesOfPackage(packageOfStrategiesPath);

                    Map<Class<?>, RegisterStrategy<?,?>> strategyMap = new HashMap<>();

                    for(int i = 0; i < listOfClasses.size(); i ++)
                    {
                        Class<RegisterStrategy<?,?>> strategyClass = listOfClasses.get(i);
//                            RegisterStrategy<?, ?> strategy = strategyClass.getDeclaredConstructor().newInstance();
//                            strategy.initializes();
//                        RegisterStrategy<?, ?> strategy = (RegisterStrategy<?, ?>) applicationContext.getBean(listOfClasses.get(i));
//
//                        strategyMap.put(strategy.getInputClassToHandle(), strategy);
                    }

                    instance = new RegisterServiceProvider(strategyMap);
                }
            }
        }

        return instance;
    }

    public RegisterStrategy<R,I> getRegisterStrategy(Class<I> inputClass)
    {
        return this.strategyRegistry.get(inputClass);
    }
}
