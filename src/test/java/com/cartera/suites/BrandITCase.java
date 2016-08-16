package com.cartera.suites;

import com.cartera.businessobject.BrandBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.launcher.Context;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BrandITCase extends BaseTestClass {

    private BrandBO brandBO;

    @BeforeClass
    public void setUp() {
        brandBO = new BrandBO(getDriver());
    }

    @Test(timeOut = 300000, priority = 3)
    public void createBrand_1() {
        brandBO.loginAndNavigateToNewBrandPage();
        brandBO.checkNewBrandPageUrl();
    }

    @Test(timeOut = 300000, priority = 3)
    public void createBrand_2() {
        brandBO.loginAndNavigateToNewBrandPage();
        brandBO.clickSubmitAndVerifyErrorMessages();
    }

    @Test(timeOut = 1200000, priority = 3)
    public void createBrand_3() {
        String testBrandName = "text" + System.currentTimeMillis();
        brandBO.loginAndNavigateToAddMerchantPage();
        brandBO.createMerchant();
        brandBO.createNewBrandAndVerifyOnCorrectness(testBrandName);
    }

    @Test(timeOut = 300000, priority = 3)
    public void createBrand_4() {
        String testBrandName = "text" + System.currentTimeMillis();
        brandBO.loginAndNavigateToAddMerchantPage();
        brandBO.createMerchant();
        brandBO.createNewBrandWithExtraPoints(testBrandName);
    }

    @Test(timeOut = 300000, priority = 3)
    public void createBrand_5() {
        String testBrandName = "text" + System.currentTimeMillis();
        brandBO.loginAndNavigateToAddMerchantPage();
        brandBO.createMerchant();
        brandBO.createNewBrandWithMile(testBrandName);
    }

    @AfterMethod
    public void finishMethod() {
        Context.getTestSession().clearCookies();
    }

}
