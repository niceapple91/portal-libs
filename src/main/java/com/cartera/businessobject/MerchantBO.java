package com.cartera.businessobject;

import com.cartera.actions.Action;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.*;
import com.cartera.testdata.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MerchantBO {

    private WebDriver driver;
    private TestData testData;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddMerchantPage addMerchantPage;
    private ManageMerchantsPage manageMerchantsPage;
    private BrandRebatesPage brandRebatesPage;
    private String merchantId;

    private final String MERCHANT_NAME = "autotest" + System.currentTimeMillis();

    public MerchantBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        testData = Context.getTestData();
    }

    public void login() {
        Context.getTestSession().clearCookies();
        loginPage.open();
        Logger.logStep("Try login..");
        homePage = loginPage.login();
        if (homePage.getLoggedIn().isDisplayed()) {
            Logger.logHuman(Logger.Level.INFO, "User is logged", true);
        } else {
            Logger.logHuman(Logger.Level.WARNING, "User isn't logged. Something is wrong", true);
        }
    }

    public void loginAndNavigateToAddMerchantPage() {
        login();
        homePage.navigateToAddMerchantPage();
        addMerchantPage = new AddMerchantPage(driver);
    }

    public void loginAndNavigateToManageMerchantsPage() {
        login();
        manageMerchantsPage = homePage.navigateToManageMerchantsPage();

    }

    public void checkNavigationToAddMerchantPage() {
        Logger.logStep("newMerch_1 Navigate to Add Merchant page");
        String currentURL = Context.getCurrentWindowURL();
        Assert.assertEquals(currentURL, addMerchantPage.getUrl(), "Can't navigate to 'Add Merchant Page' from 'Home Page'!");
    }

    public void checkGlobalMerchantCreation() {
        Logger.logStep("newMerch_2 Select one of suggested global merchants");
        addMerchantPage.fillGlobalMerchantAutocomplete(MERCHANT_NAME);
        Logger.logStep("newMerch_3 Global Merchant does not exist --> Creating new Global merchant");
        addMerchantPage.createNewGlobalMerchantOnly(MERCHANT_NAME);
        Logger.logStep("Verify if Global merchant created correctly");
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                addMerchantPage.doubleClickOnAutocomplete();
                return true;
            }
        });

        Assert.assertEquals(addMerchantPage.getTextFromGlobalAutocomplete(), MERCHANT_NAME, "Error with creating Global Merchant");

    }

    public void checkNewMerchantCreation() {
        loginAndNavigateToAddMerchantPage();
        Context.waitForPageLoaded(driver);
        addMerchantPage.createNewGlobalMerchant(MERCHANT_NAME);
        merchantId = addMerchantPage.getMerchantId();
        Logger.logStep("merchantId " + merchantId);
        Logger.logStep("Check is merchant created correctly.");
        boolean isMerchantCreated = addMerchantPage.isMerchantCreated(merchantId, MERCHANT_NAME);
        Assert.assertTrue(isMerchantCreated, "Problems with merchant creation.");
    }

    public void checkIfMerchantAvailableInManageMerchants() {
        Logger.logStep("Check is created merchant " + MERCHANT_NAME + " is active and available in manage merchants section.");
        Context.waitForPageLoaded(driver);
        boolean isMerchantPresent = manageMerchantsPage.isMerchantPresent(merchantId, MERCHANT_NAME);
        Assert.assertTrue(isMerchantPresent, "Created merchant is not active or not available in manage merchants section.");
    }

    public void checkIsMerchantAvailableInAutoCompleteField() {
        addMerchantPage.open();
        Logger.logStep("Check is created merchant " + MERCHANT_NAME + " available in 'merchant auto-complete' field.");
        addMerchantPage.fillGlobalMerchantAutocomplete(MERCHANT_NAME);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                addMerchantPage.doubleClickOnAutocomplete();
                return true;
            }
        });

        Assert.assertEquals(addMerchantPage.getTextFromGlobalAutocomplete(), MERCHANT_NAME, "Merchant is not exist in autocomplete");
    }

    public void checkIsMerchantAvailableInRebatesList() {
        brandRebatesPage = new BrandRebatesPage(driver);
        brandRebatesPage.open();
        Logger.logStep("Check is created " + MERCHANT_NAME + " available on the list of rebates for all active brands.");
        boolean isCorrectSuggestionB = brandRebatesPage.fillMerchantInputAndCheckSuggestions(MERCHANT_NAME);
        Assert.assertTrue(isCorrectSuggestionB, "Created merchant is not available on the list of rebates for all active brands.");
    }


}
