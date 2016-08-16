package com.cartera.suites;

import com.cartera.businessobject.ManageBrandsBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.launcher.Context;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManageBrandsITCase extends BaseTestClass {

    private ManageBrandsBO manageBrandsBO;

    @BeforeClass
    public void setUp() {
        manageBrandsBO = new ManageBrandsBO(getDriver());
    }


    /**
     * checkNavigationToManageBrandsPage
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_1() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkNavigationToManageBrandsPage();
    }

    /**
     * checkOrganizationDropDown
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_2() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkOrganizationDropDown();
    }

    /**
     * checkNameField
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_3() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkNameField();
    }

    /**
     * checkDomainField
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_4() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkDomainField();
    }

    /**
     * checkSolutionCheckBox
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_5() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkSolutionCheckBoxes();
    }

    /**
     * checkUpdateDate
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_6() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkUpdateDate();
    }

    /**
     * checkBrandsToShowDropDown
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_7() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkBrandsToShowDropDown();
    }

    /**
     * checkResetLink
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_8() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkResetLink();
    }

    /**
     * checkFilterButton
     */
    @Test(timeOut = 300000, priority = 8)
    public void manageBrands_9() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkFilterButton();
    }

    /**
     * check Select All checkbox
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_10() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkSelectAllCheckBox();
    }

    /**
     * checkBrandsDisplayTable
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_11() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkBrandsDisplayTable();
    }

    /**
     * checkChooseAnAction
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_12() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkChooseAnAction();
    }


    /**
     * checkSetOfResults
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_13() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkSetOfResults();
    }
    /**
     * checkPreviewLink
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_14() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkPreviewLink();
    }
    /**
     * checkShowLink
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_15() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkShowLink();
        manageBrandsBO.checkEditAndDeactivateButtons();
    }

    /**
     * checkEditLink
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_16() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkEditLink();
        manageBrandsBO.checkSaveButton();
    }

    /**
     * checkRebatesLink
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_17() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkRebatesLink();
    }

    /**
     * checkEditFlagsLink
     */
    @Test(timeOut = 300000, priority = 9)
    public void manageBrands_18() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkEditFlagsLink();
    }

    /**
     * checkDeleteLink
     */
    @Test(timeOut = 300000, priority = 9, enabled = false)
    public void manageBrands_19() {
        manageBrandsBO.loginAndNavigateToManageBrandsPage();
        manageBrandsBO.checkDeleteLink();
    }

    @AfterMethod
    public void afterMethod() {
        Context.getTestSession().clearCookies();
        manageBrandsBO.logout();
    }


}
