package com.cartera.suites;

import com.cartera.businessobject.MerchantBO;
import com.cartera.launcher.BaseTestClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MerchantITCase extends BaseTestClass {

    private MerchantBO merchantBO;

    @BeforeClass
    public void setUp() {
        merchantBO = new MerchantBO(getDriver());
    }

    /**
     * check Navigation To Add Merchant Page and creating new global merchant
     */
    @Test(timeOut = 300000, priority = 2)
    public void newMerch_1_2_3() {
        merchantBO.loginAndNavigateToAddMerchantPage();
        merchantBO.checkNavigationToAddMerchantPage();
        merchantBO.checkGlobalMerchantCreation();
    }

    /**
     * check New Merchant Creation
     */
    @Test(timeOut = 300000, priority = 2)
    public void newMerch_4() {
        merchantBO.checkNewMerchantCreation();
    }

    @Test(timeOut = 300000, priority = 2)
    public void newMerch_5() {
        merchantBO.loginAndNavigateToManageMerchantsPage();
        merchantBO.checkIfMerchantAvailableInManageMerchants();
        merchantBO.checkIsMerchantAvailableInAutoCompleteField();
        merchantBO.checkIsMerchantAvailableInRebatesList();
    }

}
