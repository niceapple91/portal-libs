package com.cartera.suites;

import com.cartera.businessobject.LogoutBO;
import com.cartera.launcher.BaseTestClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogoutITCase extends BaseTestClass {

    private LogoutBO logoutBO;

    @BeforeClass
    public void setUp() {
        logoutBO = new LogoutBO(getDriver());
    }

    /**
     * Check Logout functionality
     */
    @Test(timeOut = 300000, priority = 14)
    public void logOut_1() {
        logoutBO.checkLogoutFunctionality();
    }

}
