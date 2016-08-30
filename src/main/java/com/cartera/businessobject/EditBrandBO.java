package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class EditBrandBO {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ManageBrandsPage manageBrandsPage;
    private EditBrandPage editBrandPage;
    private BrandRebatesPage brandRebatesPage;
    private NewBrandPage newBrandPage;


    public EditBrandBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        editBrandPage = new EditBrandPage(driver);
        brandRebatesPage = new BrandRebatesPage(driver);
        newBrandPage = new NewBrandPage(driver);
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

    public void logout() {
        editBrandPage.logout();
    }

    public void loginAndNavigateToManageBrandsPage() {
        login();
        manageBrandsPage = homePage.navigateToManageBrandsPage();
        Logger.logStep("Manage Brands page is open ");
    }

    public void checkModifyMemberShare() {
        createBrand();
        homePage.open();
        manageBrandsPage = homePage.navigateToManageBrandsPage();
        manageBrandsPage.clickEditMnTestCash();
        Logger.logStep("Verify if breadcrumb is correct");
        Assert.assertTrue(editBrandPage.getHeaderData().contains("Edit"), "Wrong breadcrumb");
        editBrandPage.modifyMemberShare("10");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if alert is appeared");
        Assert.assertTrue(editBrandPage.isAlertDisplayed(), "Problems with alert");
    }


    public void checkModifyClientShare() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.modifyClientShare("10");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if alert is appeared");
        Assert.assertTrue(editBrandPage.isAlertDisplayed(), "Problems with alert");
    }

    public void checkModifyCarteraShare() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.modifyCarteraShare("10");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if alert is appeared");
        Assert.assertTrue(editBrandPage.isAlertDisplayed(), "Problems with alert");
    }

    public void checkModifyMemberClientCarteraShares() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.modifyMemberShare("50");
        editBrandPage.modifyCarteraShare("50");
        editBrandPage.modifyClientShare("50");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if alert is appeared");
        Assert.assertTrue(editBrandPage.isAlertDisplayed(), "Problems with alert");
    }

    public void checkRemoveNameField() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.removeNameField();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Required message is appeared");
        Assert.assertTrue(editBrandPage.isRequiredMessageDisplayed(), "Problems with required message");
    }

    public void checkRemoveDisplayName() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.removeDisplayNameField();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Required message is appeared");
        Assert.assertTrue(editBrandPage.isRequiredMessageDisplayed(), "Problems with required message");
    }

    public void checkRemoveMemberShare() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.removeMemberShareField();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if alert is appeared");
        Assert.assertTrue(editBrandPage.isAlertDisplayed(), "Problems with alert");
        Logger.logStep("Verify if Required message is appeared");
        Assert.assertTrue(editBrandPage.isRequiredMessageDisplayed(), "Problems with required message");
    }

    public void checkRemoveClientShare() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.removeClientShareField();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Required message(only) is appeared, because member share = 100% --> no alert message");
        Assert.assertTrue(editBrandPage.isRequiredMessageDisplayed(), "Problems with required message");
    }

    public void checkRemoveCarteraShare() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.removeCarteraShareField();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Required message(only) is appeared, because member share = 100% --> no alert message");
        Assert.assertTrue(editBrandPage.isRequiredMessageDisplayed(), "Problems with required message");
    }

    public void checkSetNewValuesWithCorrectData() {
        String brandName;
        manageBrandsPage.clickEditMnTestCash();
        brandName = editBrandPage.getBrandName();
        editBrandPage.modifyMemberShare("50");
        editBrandPage.modifyCarteraShare("25");
        editBrandPage.modifyClientShare("25");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Notice Message is correct");
        Assert.assertEquals(editBrandPage.getNoticeMessage(), "The item was updated successfully.");
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        brandRebatesPage.getBrandsForOrganization("Mn");
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput("autotest");
        brandRebatesPage.clickFilterButton();
        brandRebatesPage.selectBias("none");
        brandRebatesPage.clickResetRebates();
        Logger.logStep("Verify rebates after changing data");
        Assert.assertEquals(brandRebatesPage.getRebatesFromTable().get(0), getExpectedResult("rebate_update_share"), "Wrong Rebate");
    }

    public void checkSetNewValuesWithWrongData() {
        manageBrandsPage.clickEditMnTestCash();
        editBrandPage.modifyMemberShare("40");
        editBrandPage.modifyCarteraShare("25");
        editBrandPage.modifyClientShare("25");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if alert is appeared");
        Assert.assertTrue(editBrandPage.isAlertDisplayed(), "Problems with alert");
    }

    public void checkModifyPointsConversionRate() {
        String brandName;
        manageBrandsPage.clickEditMnTestCash();
        brandName = editBrandPage.getBrandName();
        Logger.logStep("Brand - " + brandName);
        editBrandPage.modifyPointsConversionRate("60");
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Notice Message is correct");
        Assert.assertEquals(editBrandPage.getNoticeMessage(), "The item was updated successfully.");
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        brandRebatesPage.getBrandsForOrganization("Mn");
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput("autotest");
        brandRebatesPage.clickFilterButton();
        brandRebatesPage.selectBias("none");
        brandRebatesPage.clickResetRebates();
        Logger.logStep("Verify rebates after changing data");
        Assert.assertEquals(brandRebatesPage.getRebatesFromTable().get(0), getExpectedResult("rebate_update_points_conversion"), "Wrong Rebate");
    }

    public void checkSetNewCurrency() {
        String brandName;
        manageBrandsPage.clickEditMnTestCash();
        brandName = editBrandPage.getBrandName();
        Logger.logStep("Brand - " + brandName);
        editBrandPage.modifyCurrency();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Notice Message is correct");
        Assert.assertEquals(editBrandPage.getNoticeMessage(), "The item was updated successfully.");
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        brandRebatesPage.getBrandsForOrganization("Mn");
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput("autotest");
        brandRebatesPage.clickFilterButton();
        brandRebatesPage.selectBias("none");
        brandRebatesPage.clickResetRebates();
        Logger.logStep("Verify rebates after changing data");
        Assert.assertEquals(brandRebatesPage.getRebatesFromTable().get(0), getExpectedResult("rebate_update_points_currency"), "Wrong Rebate");
    }

    public void checkModifyToInitialState() {
        String brandName;
        manageBrandsPage.clickEditMnTestCash();
        brandName = editBrandPage.getBrandName();
        Logger.logStep("Brand - " + brandName);
        editBrandPage.setValuesToinitialState();
        editBrandPage.clickSaveButton();
        Logger.logStep("Verify if Notice Message is correct");
        Assert.assertEquals(editBrandPage.getNoticeMessage(), "The item was updated successfully.");
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        brandRebatesPage.getBrandsForOrganization("Mn");
        brandRebatesPage.selectBrand(brandName);
        brandRebatesPage.fillMerchantInput("autotest");
        brandRebatesPage.clickFilterButton();
        brandRebatesPage.selectBias("none");
        brandRebatesPage.clickResetRebates();
        Logger.logStep("Verify rebates after changing data");
        Assert.assertEquals(brandRebatesPage.getRebatesFromTable().get(0), getExpectedResult("rebate_update_state"), "Wrong Rebate");
    }

    private void createBrand(){
        String cashBackBrand = "textCash" + System.currentTimeMillis() + 1;
        try {
            newBrandPage.open();
            Logger.logStep("Fill required inputs. Brand name: " + cashBackBrand);
            newBrandPage.fillCashBackRequiredFields(cashBackBrand);
            newBrandPage.submit();
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with brand creation.");
        }
    }

    private String getExpectedResult(String key){
        return Context.getTestData().getData(key);
    }
}
