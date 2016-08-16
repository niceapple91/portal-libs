package com.cartera.suites;

import com.cartera.businessobject.BrandRebatesBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.launcher.Context;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrandRebatesITCase extends BaseTestClass {

    BrandRebatesBO brandRebatesBO;

    @BeforeClass
    public void setUp() {
        brandRebatesBO = new BrandRebatesBO(getDriver());
    }

    /**
     * check Navigation To Manage Brands Page
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_1() {
        brandRebatesBO.loginAndNavigateToManageBrandsPage();
        brandRebatesBO.checkNavigationToManageBrandsPage();
        brandRebatesBO.logout();
    }

    /**
     * check Navigation To Rebates Page
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_2() {
        brandRebatesBO.loginAndNavigateToManageBrandsPage();
        brandRebatesBO.checkNavigationToRebatesPage();
        brandRebatesBO.logout();
    }

    /**
     * check Organization DropDown
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_3_4() {
        brandRebatesBO.login();
        brandRebatesBO.checkOrganizationAndBrandDropDown();
        brandRebatesBO.logout();
    }

    /**
     * check Merchant Suggestion (autocomplete textbox)
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_5() {
        brandRebatesBO.login();
        brandRebatesBO.checkMerchantAutocomplete();
        brandRebatesBO.logout();
    }

    /**
     * check Lock Rebates
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_6() {
        brandRebatesBO.login();
        brandRebatesBO.checkLockedRebates();
        brandRebatesBO.logout();
    }

    /**
     * check Expired Rebates
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_7() {
        brandRebatesBO.login();
        brandRebatesBO.checkExpiredRebates();
        brandRebatesBO.logout();
    }

    /**
     * check Inactive Rebates
     */
    @Test(timeOut = 800000, priority = 4)
    public void brandRebateUI_8() {
        brandRebatesBO.login();
        brandRebatesBO.checkShowInactiveMerchants();
        brandRebatesBO.logout();
    }

    /**
     * check Clear Button
     */
    @Test(timeOut = 300000, priority = 4)
    public void brandRebateUI_9() {
        brandRebatesBO.login();
        brandRebatesBO.checkClearLink();
        brandRebatesBO.logout();
    }

    /**
     * check Filter Button
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_10() {
        brandRebatesBO.login();
        brandRebatesBO.checkFilterButton();
        brandRebatesBO.logout();
    }

    /**
     * check Rounding Bias DropDown
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_11() {
        brandRebatesBO.login();
        brandRebatesBO.checkRoundingBiasDropDown();
        brandRebatesBO.logout();
    }

    /**
     * check Reset Rebates Button
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_12() {
        brandRebatesBO.login();
        brandRebatesBO.checkRebatesButton();
        brandRebatesBO.logout();
    }

    /**
     * check Update Button
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_13() {
        brandRebatesBO.login();
        brandRebatesBO.checkUpdateButton();
        brandRebatesBO.logout();
    }

    /**
     * check Lock checkbox and Rebates Updated Message
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_14_15() {
        brandRebatesBO.login();
        brandRebatesBO.checkLockCheckboxAndRebatesUpdatedMessage();
        brandRebatesBO.logout();
    }

    /**
     * check All Links Url
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_16() {
        brandRebatesBO.login();
        brandRebatesBO.checkDownloadAllLink();
        brandRebatesBO.logout();
    }

    /**
     * check Rebates Display Table
     */
    @Test(timeOut = 600000, priority = 5)
    public void brandRebateUI_17() {
        brandRebatesBO.login();
        brandRebatesBO.checkRebatesDisplayTable();
        brandRebatesBO.logout();
    }

    /**
     * check Rebates DropDown
     */
    @Test(timeOut = 300000, priority = 5)
    public void brandRebateUI_18() {
        brandRebatesBO.login();
        brandRebatesBO.checkRebatesDropDownWindow();
        brandRebatesBO.logout();
    }

}
