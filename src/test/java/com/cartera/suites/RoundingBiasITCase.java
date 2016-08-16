package com.cartera.suites;

import com.cartera.businessobject.RoundingBiasBO;
import com.cartera.launcher.BaseTestClass;
import com.cartera.logger.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RoundingBiasITCase extends BaseTestClass {

    private RoundingBiasBO roundingBiasBO;

    @BeforeClass
    public void setUp() {
        roundingBiasBO = new RoundingBiasBO(getDriver());
        roundingBiasBO.createMerchantsAndBrands();
    }

    @Test(priority = 6)
    public void roundingBias_1() {
        roundingBiasBO.checkRoundingBiasForMN("none");
    }

    @Test(priority = 6)
    public void roundingBias_2() {
        roundingBiasBO.checkRoundingBiasForMN("+0.10");
    }

    @Test(priority = 6)
    public void roundingBias_3() {
        roundingBiasBO.checkRoundingBiasForMN("-0.25");
    }

    @Test(priority = 6)
    public void roundingBias_4() {
        roundingBiasBO.checkRoundingBiasForMN("+0.50");
    }

    @Test(priority = 6)
    public void roundingBias_5() {
        roundingBiasBO.checkRoundingBiasForMN("-1.00");
    }

    @Test(priority = 6)
    public void roundingBias_6() {
        roundingBiasBO.checkRoundingBiasForAlaska("-0.10 mile");
    }

    @Test(priority = 6)
    public void roundingBias_7() {
        roundingBiasBO.checkRoundingBiasForAlaska("+0.25 mile");
    }

    @Test(priority = 6)
    public void roundingBias_8() {
        roundingBiasBO.checkRoundingBiasForAlaska("-0.50 mile");
    }

    @Test(priority = 6)
    public void roundingBias_9() {
        roundingBiasBO.checkRoundingBiasForAlaska("+1.00 mile");
    }

    @Test(priority = 7)
    public void roundingBias_10() {
        roundingBiasBO.checkNoBiasCheckBox();
    }
    @Test(priority = 7)
    public void roundingBias_11() {
        Logger.logStep("Need explanation of this test case");
    }

}
