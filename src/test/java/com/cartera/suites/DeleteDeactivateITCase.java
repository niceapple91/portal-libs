package com.cartera.suites;

import com.cartera.businessobject.DeleteDeactivateBO;
import com.cartera.launcher.BaseTestClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteDeactivateITCase extends BaseTestClass {

    private DeleteDeactivateBO deleteDeactivateBO;

    @BeforeClass
    public void setUp() {
        deleteDeactivateBO = new DeleteDeactivateBO(getDriver());
    }


    /**
     * checkMerchantDeactivation
     */
    @Test(timeOut = 300000, priority = 13)
    public void merchantDeactivate_1() {
        deleteDeactivateBO.checkDeactivateMerchant();
    }

    /**
     * check Show Inactive Merchants
     */
    @Test(timeOut = 300000, priority = 13)
    public void merchantDeactivate_2() {
        deleteDeactivateBO.checkShowInactiveMerchants();
    }

    /**
     * check delete brand
     */
    @Test(timeOut = 300000, priority = 13, enabled = false)
    public void deleteBrand_1() {
        deleteDeactivateBO.checkDeletingBrand();
    }

    /**
     * check delete multiple brands
     */
    @Test(timeOut = 300000, priority = 13)
    public void deleteBrand_2() {
        deleteDeactivateBO.checkDeletingMultipleBrands();
    }

}
