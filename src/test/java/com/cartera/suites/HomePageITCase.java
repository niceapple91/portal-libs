package com.cartera.suites;

import com.cartera.businessobject.HomePageBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.launcher.Context;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageITCase extends BaseTestClass {

    private HomePageBO homePageBO;

    @BeforeClass
    public void setUp() {
        homePageBO = new HomePageBO(getDriver());
    }

    /**
     * Check if login prompt appears without login
     */
    @Test(timeOut = 300000, priority = 1)
    public void homePage_1() {
        homePageBO.clickLoginAndVerifyPrompt();
    }

    /**
     * Get version of portal
     */
    @Test(timeOut = 300000, priority = 1)
    public void homePage_2() {
        homePageBO.loginAndNavigateToHomePage();
        homePageBO.navigateToInfoPageAndPresentPortalVersion();
    }

    /**
     * Check Home Page Sections
     */
    @Test(timeOut = 300000, priority = 1)
    public void homePage_3() {
        homePageBO.loginAndNavigateToHomePage();
        homePageBO.verifyPageSections();
    }

    @AfterMethod
    public void finishMethod() {
        Context.getTestSession().clearCookies();
    }
}
