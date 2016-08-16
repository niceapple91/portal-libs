package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.logger.Logger.Level;
import com.cartera.pages.*;
import com.cartera.testdata.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;

public class BrandBO {

    private WebDriver driver;
    private String merchantName;
    private LoginPage loginPage;
    private HomePage homePage;
    private AddMerchantPage addMerchantPage;
    private EditBrandPage editBrandPage;
    private ManageBrandsPage manageBrandsPage;
    private NewBrandPage newBrandPage;
    private BrandRebatesPage brandRebatesPage;
    private TestData testData;


    public BrandBO(WebDriver driver) {
        this.driver = driver;
        testData = Context.getTestData();
        loginPage = new LoginPage(driver);
    }

    public void login(){
        loginPage.open();
        homePage = loginPage.login();
        if (homePage.getLoggedIn().isDisplayed()) {
            Logger.logHuman(Level.INFO, "User is logged", true);
        } else {
            Logger.logHuman(Level.WARNING, "User isn't logged. Something is wrong", true);
        }
    }

    public void loginAndNavigateToAddMerchantPage() {
        login();
        homePage.navigateToAddMerchantPage();
    }

    public void loginAndNavigateToNewBrandPage(){
        login();
        newBrandPage = new NewBrandPage(driver);
        homePage.navigateToNewBrandPage();
    }

    public void checkNewBrandPageUrl(){
        String currentUrl = getCurrentURL();
        Logger.logStep("Check if navigation to New Brand page is correct");
        Assert.assertEquals(currentUrl, newBrandPage.getUrl(), "Can't navigate to 'New Brand Page' from 'Home Page'!");
    }

    private String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void createMerchant() {
        Context.waitForPageLoaded(driver);
        Logger.logStep("Creation new merchant");
        try {
            addMerchantPage = new AddMerchantPage(driver);
            merchantName = "autotest" + System.currentTimeMillis() + "mer";
            Logger.logStep("Fill required inputs. Merchant name: " + merchantName);
            addMerchantPage.createNewGlobalMerchant(merchantName);

        } catch (Exception e) {
            Logger.logStep("Merchant didn't created. ");
            e.printStackTrace();
            Assert.assertTrue(false, e.getMessage());
        }
        try {
            if (addMerchantPage.getCreateMerchantError().isDisplayed()) {
                Logger.logStep("!!!Global merchant creation ERROR!!!");
            }
        } catch (Exception e) {
            Logger.logStep(e.getMessage());
        } finally {
            homePage.open();
            Logger.logStep("Merchant created.");
        }

    }

    public void createNewBrandAndVerifyOnCorrectness(String brandName) {
        List<String> errors = new LinkedList<String>();

        newBrandPage = homePage.navigateToNewBrandPage();
        newBrandPage.fillCashBackRequiredFields(brandName);
        newBrandPage.submit();
        editBrandPage = new EditBrandPage(driver);
        if (!editBrandPage.getHeaderData().contains(brandName)) {
            Logger.logStep("Fill required inputs.");
            errors.add("Newly created brand is not appear in 'Edit Brand Page' header!");
        }
        manageBrandsPage = new ManageBrandsPage(driver);
        manageBrandsPage.open();
        List<String> thy = manageBrandsPage.getBrandsDisplayNames();
        if (!thy.contains(brandName)) {
            Logger.logStep("Fill required inputs.");
            errors.add("Table on 'Manage Brands Page' do not contain newly created brand!");
        }
        brandRebatesPage = new BrandRebatesPage(driver);
        brandRebatesPage.open();
        if(!brandRebatesPage.getBrandsForOrganization("Mn").contains(brandName)){
            Logger.logStep("Fill required inputs.");
            errors.add("Brand is not available in filter on Brand Rebates page!");
        }
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput(merchantName);
        brandRebatesPage.clickFilterButton();

        List<String> rebates = brandRebatesPage.getRebatesFromTable();
        for (String rebate : rebates) {
            if (!rebate.trim().equals("10%")) {
                errors.add("Merchant " + merchantName + " with 10% rebates is not available on Brand Rebates page!");
            }
        }
        Logger.logStep("Verify if errors appeared on pages");
        Assert.assertTrue(errors.isEmpty(), errors.toString());

    }

    public void createNewBrandWithExtraPoints(String brandName) {
        List<String> errors = new LinkedList<String>();

        newBrandPage = homePage.navigateToNewBrandPage();
        newBrandPage.fillExtraPtRequiredFields(brandName);
        newBrandPage.submit();
        editBrandPage = new EditBrandPage(driver);
        if (!editBrandPage.getHeaderData().contains(brandName)) {
            Logger.logStep("Fill required inputs.");
            errors.add("Newly created brand is not appear in 'Edit Brand Page' header!");
        }
        manageBrandsPage = new ManageBrandsPage(driver);
        manageBrandsPage.open();

        Logger.logStep("TEST_BRAND: " + brandName);
        List<String> thy = manageBrandsPage.getBrandsDisplayNames();
        if (!thy.contains(brandName)) {
            Logger.logStep("Fill required inputs.");
            errors.add("Table on 'Manage Brands Page' do not contain newly created brand!");
        }
        brandRebatesPage = new BrandRebatesPage(driver);
        brandRebatesPage.open();
        if(!brandRebatesPage.getBrandsForOrganization("Chase Lsm").contains(brandName)){
            Logger.logStep("Fill required inputs.");
            errors.add("Brand is not available in filter on Brand Rebates page!");
        }
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput(merchantName);
        brandRebatesPage.clickFilterButton();

        List<String> rebates = brandRebatesPage.getRebatesFromTable();
        for (String rebate : rebates) {
            if (!rebate.trim().equals("5 extra pts/$")) {
                errors.add("Merchant " + merchantName + " with 5 extra points/$ rebate is not available on Brand Rebates page!");
            }
        }
        Logger.logStep("Verify if errors appeared on pages");
        Assert.assertTrue(errors.isEmpty(), errors.toString());

    }

    public void createNewBrandWithMile(String brandName) {
        List<String> errors = new LinkedList<String>();

        newBrandPage = homePage.navigateToNewBrandPage();
        newBrandPage.fillMileRequiredFields(brandName);
        newBrandPage.submit();
        editBrandPage = new EditBrandPage(driver);
        if (!editBrandPage.getHeaderData().contains(brandName)) {
            Logger.logStep("Fill required inputs.");
            errors.add("Newly created brand is not appear in 'Edit Brand Page' header!");
        }
        manageBrandsPage = new ManageBrandsPage(driver);
        manageBrandsPage.open();

        Logger.logStep("TEST_BRAND: " + brandName);
        List<String> thy = manageBrandsPage.getBrandsDisplayNames();
        if (!thy.contains(brandName)) {
            Logger.logStep("Fill required inputs.");
            errors.add("Table on 'Manage Brands Page' do not contain newly created brand!");
        }
        brandRebatesPage = new BrandRebatesPage(driver);
        brandRebatesPage.open();
        if(!brandRebatesPage.getBrandsForOrganization("Chase Lsm").contains(brandName)){
            Logger.logStep("Fill required inputs.");
            errors.add("Brand is not available in filter on Brand Rebates page!");
        }
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput(merchantName.replace("merch", ""));
        brandRebatesPage.clickFilterButton();

        List<String> rebates = brandRebatesPage.getRebatesFromTable();
        for (String rebate : rebates) {
            if (!rebate.trim().equals("4 miles/$")) {
                errors.add("Merchant " + merchantName + " with 4 miles/$ rebate is not available on Brand Rebates page!");
            }
        }
        Logger.logStep("Verify if errors appeared on pages");
        Assert.assertTrue(errors.isEmpty(), errors.toString());
    }

    public void clickSubmitAndVerifyErrorMessages() {
        newBrandPage.submitButtonClick();
        List<String> actualErrorMessages = newBrandPage.getErrorMessages();
        List<String> expectedErrorMessages = testData.getList("new_brand_fields_errors");
        Logger.logStep("Verify if error messages appeared on the page");
        Assert.assertEquals(actualErrorMessages, expectedErrorMessages, "Error messages is different that expected!");
    }
}