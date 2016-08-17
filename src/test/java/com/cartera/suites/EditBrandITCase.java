package com.cartera.suites;

import com.cartera.businessobject.EditBrandBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditBrandITCase extends BaseTestClass {

    private EditBrandBO editBrandBO;

    @BeforeClass
    public void setUp() {
        editBrandBO = new EditBrandBO(getDriver());
    }


    @Test(timeOut = 300000, priority = 10)
    public void editBrand_1() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkModifyMemberShare();

    }

    @Test(timeOut = 300000, priority = 10)
    public void editBrand_2() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkModifyClientShare();
    }

    @Test(timeOut = 300000, priority = 10)
    public void editBrand_3() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkModifyCarteraShare();
    }

    @Test(timeOut = 300000, priority = 10)
    public void editBrand_4() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkModifyMemberClientCarteraShares();
    }

    @Test(timeOut = 300000, priority = 10, enabled = false)
    public void editBrand_5() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkRemoveNameField();
    }

    @Test(timeOut = 300000, priority = 10, enabled = false)
    public void editBrand_6() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkRemoveDisplayName();
    }

    @Test(timeOut = 300000, priority = 10)
    public void editBrand_7() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkRemoveMemberShare();
    }

    @Test(timeOut = 300000, priority = 10)
    public void editBrand_8() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkRemoveClientShare();
    }

    @Test(timeOut = 300000, priority = 10)
    public void editBrand_9() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkRemoveCarteraShare();
    }

    @Test(timeOut = 300000, priority = 11)
    public void editBrand_10() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkSetNewValuesWithCorrectData();
    }

    @Test(timeOut = 300000, priority = 11)
    public void editBrand_11() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkSetNewValuesWithWrongData();
    }

    @Test(timeOut = 300000, priority = 11)
    public void editBrand_12() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkModifyPointsConversionRate();
    }

    @Test(timeOut = 300000, priority = 11)
    public void editBrand_13() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkSetNewCurrency();
    }

    @Test(timeOut = 300000, priority = 11)
    public void editBrand_14() {
        editBrandBO.loginAndNavigateToManageBrandsPage();
        editBrandBO.checkModifyToInitialState();
    }

    @AfterMethod
    public void afterMethod() {
        Context.getTestSession().clearCookies();
        try {
            editBrandBO.logout();
        } catch(Exception e){
            Logger.logStep(e.toString());
        }
    }

}
