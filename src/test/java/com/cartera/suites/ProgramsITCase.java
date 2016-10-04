package com.cartera.suites;

import com.cartera.businessobject.ProgramsBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.launcher.Context;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProgramsITCase extends BaseTestClass {

    private ProgramsBO programsBO;


    @BeforeClass
    public void setUp() {
        programsBO = new ProgramsBO(getDriver());
    }


    @Test(timeOut = 300000, priority = 12)
    public void programs_1() {
        programsBO.checkNavigationToMerchantsPage();
    }


    @Test(timeOut = 300000, priority = 12)
    public void programs_2() {
        programsBO.checkCommissionPrograms();
    }

    @Test(timeOut = 300000, priority = 12)
    public void programs_3() {
        programsBO.checkNavigationToChangeType();
    }

    @Test(timeOut = 300000, priority = 12)
    public void programs_4() {
        programsBO.checkNavigationToEditLink();
    }

    @Test(timeOut = 300000, priority = 12)
    public void programs_5() {
        programsBO.checkNavigationToDeactivate();
    }

    @Test(timeOut = 300000, priority = 12)
    public void programs_6() {
        programsBO.checkCurrentRebatesTable();
    }

    @Test(timeOut = 300000, priority = 12)
    public void programs_7() {
        programsBO.checkModifyCommission();
    }


    @AfterMethod
    public void afterMethod() {
        Context.getTestSession().clearCookies();
    }
}
