package com.cartera.transformer;

import com.cartera.logger.Logger;
import com.cartera.testdata.TestData;
import org.testng.IAnnotationTransformer;
import org.testng.IAnnotationTransformer2;
import org.testng.annotations.IConfigurationAnnotation;
import org.testng.annotations.IDataProviderAnnotation;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.ITestAnnotation;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TimeoutTransformer implements IAnnotationTransformer, IAnnotationTransformer2 {

    private static TestData testData = new TestData();

    @Override
    public void transform(ITestAnnotation test, Class cls,
                          Constructor constructor, Method method)
    {
        test.setTimeOut(Math.max(Long.parseLong(testData.getData("timeout")),test.getTimeOut()));
        Logger.logStep("Set timeout to " + test.getTimeOut());
    }

    @Override
    public void transform(IConfigurationAnnotation iConfigurationAnnotation, Class aClass, Constructor constructor, Method method) {
        iConfigurationAnnotation.setTimeOut(Math.max(Long.parseLong(testData.getData("timeout")),iConfigurationAnnotation.getTimeOut()));
        Logger.logStep("Set timeout to " + iConfigurationAnnotation.getTimeOut());
    }

    @Override
    public void transform(IDataProviderAnnotation iDataProviderAnnotation, Method method) {

    }

    @Override
    public void transform(IFactoryAnnotation iFactoryAnnotation, Method method) {

    }
}
