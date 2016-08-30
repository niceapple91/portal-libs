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

public class EnableTransformer implements IAnnotationTransformer, IAnnotationTransformer2 {

    private static TestData testData = new TestData();

    @Override
    public void transform(ITestAnnotation test, Class cls,
                          Constructor constructor, Method method)
    {
        boolean dissabledMethods = testData.isTestCaseDisabled("test." + method.getDeclaringClass().getSimpleName() + ".TestCases.dissabled", method.getName());
        if (dissabledMethods){
            test.setEnabled(false);
            Logger.logStep("Set TestCase to with name " + method.getName() + " dissabled.");
        }
    }

    @Override
    public void transform(IConfigurationAnnotation iConfigurationAnnotation, Class aClass, Constructor constructor, Method method) {
        boolean dissabledMethods = testData.isTestCaseDisabled("test." + method.getDeclaringClass().getSimpleName() + ".TestCases.dissabled", method.getName());
        if (dissabledMethods){
            iConfigurationAnnotation.setEnabled(false);
            Logger.logStep("Set TestCase to with name " + method.getName() + " dissabled.");
        }
    }

    @Override
    public void transform(IDataProviderAnnotation iDataProviderAnnotation, Method method) {

    }

    @Override
    public void transform(IFactoryAnnotation iFactoryAnnotation, Method method) {

    }
}
