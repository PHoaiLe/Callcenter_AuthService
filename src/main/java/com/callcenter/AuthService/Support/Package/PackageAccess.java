package com.callcenter.AuthService.Support.Package;

import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.List;
import java.util.stream.Collectors;

public class PackageAccess
{
    public List<Class> findAllClassesOfPackage(String packagePath)
    {
        /**
         * In this method, we're initiating the SubTypesScanner class and fetching all subtypes of the Object class.
         * Through this approach, we get more granularity when fetching the classes.
         */

        Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
        List<Class> listOfClasses = reflections.getSubTypesOf(RegisterStrategy.class).stream().collect(Collectors.toList());

        return listOfClasses;
    }

    public List<String> findAllNameOfClassesOfPackage(String packagePath)
    {
        Reflections reflections = new Reflections(packagePath, new SubTypesScanner(false));
        List<String> listOfClasses = reflections.getSubTypesOf(Object.class).stream().map((targetClass) -> targetClass.getName()).collect(Collectors.toList());

        return listOfClasses;
    }
}
