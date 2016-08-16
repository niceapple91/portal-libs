package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DeleteDeactivateBO {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ManageMerchantsPage manageMerchantsPage;
    private BrandRebatesPage brandRebatesPage;
    private AddMerchantPage addMerchantPage;
    private NewBrandPage newBrandPage;
    private ManageBrandsPage manageBrandsPage;

    private String merchantName;
    private String merchantId;
    private String cashBackBrand;

    public DeleteDeactivateBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        manageMerchantsPage = new ManageMerchantsPage(driver);
        brandRebatesPage = new BrandRebatesPage(driver);
        addMerchantPage = new AddMerchantPage(driver);
        newBrandPage = new NewBrandPage(driver);
        manageBrandsPage = new ManageBrandsPage(driver);
    }

    public void login() {
        Context.getTestSession().clearCookies();
        loginPage.open();
        homePage = loginPage.login();
        if (homePage.getLoggedIn().isDisplayed()) {
            Logger.logHuman(Logger.Level.INFO, "User is logged", true);
        } else {
            Logger.logHuman(Logger.Level.WARNING, "User isn't logged. Something is wrong", true);
        }
    }

    private void createMerchant() {
        try {
            addMerchantPage.open();
            merchantName = "autotest" + System.currentTimeMillis();
            Logger.logStep("Fill required inputs. Merchant name: " + merchantName);
            addMerchantPage.createNewGlobalMerchant(merchantName);
            merchantId = addMerchantPage.getMerchantId();
            homePage.open();
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with merchant creation.");
        }
    }

    private void createBrand() {
        cashBackBrand = "textCash" + System.currentTimeMillis() + 1;
        try {
            newBrandPage.open();
            Logger.logStep("Fill required inputs. Brand name: " + cashBackBrand);
            newBrandPage.fillCashBackRequiredFields(cashBackBrand);
            newBrandPage.submit();
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with brand creation.");
        }
    }


    public void checkDeactivateMerchant() {
        login();
        createMerchant();
        manageMerchantsPage.open();
        manageMerchantsPage.deactivateMerchant(merchantId);
        Logger.logStep("Verify if merchant is deactivated");
        Assert.assertEquals(manageMerchantsPage.getMerchantStatus(), "Inactive", "Merchant status is different that expected");
    }

    public void checkShowInactiveMerchants() {
        login();
        manageMerchantsPage.open();
        Logger.logStep("Click on Show Inactive Merchants");
        manageMerchantsPage.clickShowInactiveMerchants();
        manageMerchantsPage.filterMerchantsByLetter("A");
        manageMerchantsPage.clickOnProgramsOfInactiveAutotest();
        Logger.logStep("Verify if programs page displayed with no data");
        Assert.assertEquals(manageMerchantsPage.isDisplayedProgramsWithNoData(), 2, "smth worng with data in table");
    }

    public void checkDeletingMultipleBrands() {
        login();
        createBrand();
        createBrand();
        manageBrandsPage.open();
        manageBrandsPage.deleteTestCashMultipleBrands();
    }

    public void checkDeletingBrand() {
        login();
        createBrand();
        manageBrandsPage.open();
        manageBrandsPage.deleteBrand();
    }

    public void logout() {
        manageBrandsPage.logout();
    }
}
