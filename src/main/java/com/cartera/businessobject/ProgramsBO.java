package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class ProgramsBO {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ManageMerchantsPage manageMerchantsPage;
    private ProgramsPage programsPage;
    private AddMerchantPage addMerchantPage;

    private String merchantId;
    private String merchantName;

    public ProgramsBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        manageMerchantsPage = new ManageMerchantsPage(driver);
        programsPage = new ProgramsPage(driver);
        addMerchantPage = new AddMerchantPage(driver);
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
        programsPage.logout();
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

    private void createMerchantOnly() {
        try {
            addMerchantPage.open();
            merchantName = "autotest" + System.currentTimeMillis();
            Logger.logStep("Fill required inputs. Merchant name: " + merchantName);
            addMerchantPage.createNewGlobalMerchant(merchantName);
            merchantId = addMerchantPage.getMerchantId();
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with merchant creation.");
        }
    }

    public void checkNavigationToMerchantsPage() {
        login();
        createMerchant();
        manageMerchantsPage.open();
        Logger.logStep("Navigate to Programs");
        manageMerchantsPage.openProgramsForMerchant(merchantId);
        String programsURL = Context.getCurrentWindowURL();
        String programsEndPoint = "/view_merchant_programs.php?merchant_id=" + merchantId;
        Logger.logStep("Verify navigation to Programs");
        Assert.assertTrue(programsURL.contains(programsEndPoint), "Problems with navigation to programs page");
    }


    public void checkCommissionPrograms() {
        List<String> errors = new LinkedList<String>();
        List<String> expectedTableHeaders = new LinkedList<String>();
        expectedTableHeaders.add("Organization");
        expectedTableHeaders.add("Brand");
        expectedTableHeaders.add("Commission");
        expectedTableHeaders.add("New Rate");
        expectedTableHeaders.add("Start Date");
        expectedTableHeaders.add("Stop Date");
        expectedTableHeaders.add("Create Date");
        expectedTableHeaders.add("Create By");
        expectedTableHeaders.add("Change type");
        expectedTableHeaders.add("Edit Program");
        expectedTableHeaders.add("Deactivate Program");

        Logger.logStep("Login to 'Merchandising Portal' and navigate to 'Home Page'.");
        login();
        createMerchant();
        manageMerchantsPage.open();
        Logger.logStep("Navigate to Programs");
        manageMerchantsPage.openProgramsForMerchant(merchantId);

        List<String> actualTableHeaders = programsPage.getComissionProgramsTableHeaders();

        if (!actualTableHeaders.equals(expectedTableHeaders)) {
            errors.add("Table headers different that expected");
        }

        String createDateStr = programsPage.getCreateDate();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        String commission = programsPage.getCommission();
        String expCommission = "Fixed: 10% ";
        if (!commission.equals(expCommission)) {
            errors.add("Commission for Autotest merchant is incorrect");
        }

        String createdBy = programsPage.getCreatedBy();

        if (createdBy.isEmpty()) {
            errors.add("Created by column is not populated");
        }

        try {
            dateFormatter.parse(createDateStr);
        } catch (ParseException e) {
            errors.add("Create date format is wrong");
        }
        Logger.logStep("Verify commission programs table");
        Assert.assertTrue(errors.isEmpty(), errors.toString());
    }

    public void checkNavigationToChangeType() {
        login();
        createMerchantOnly();
        Logger.logStep("Navigate to Programs");
        programsPage.clickOnChangeType();
        Logger.logStep("Verify redirecting to Change Type");
        Assert.assertEquals(programsPage.getBreadcrumbs(), Context.getTestData().getData("change_type_breadcrumbs"));
    }

    public void checkNavigationToEditLink() {
        login();
        createMerchantOnly();
        Logger.logStep("Navigate to Programs");
        programsPage.clickOnEdit();
        Logger.logStep("Verify redirecting to Edit");
        Assert.assertEquals(programsPage.getBreadcrumbs(), Context.getTestData().getData("edit_breadcrumbs"));
    }

    public void checkNavigationToDeactivate() {
        login();
        createMerchantOnly();
        Logger.logStep("Navigate to Programs");
        programsPage.clickOnDeactivate();
        Logger.logStep("Verify redirecting to Deactivate");
        Assert.assertEquals(programsPage.getBreadcrumbs(), Context.getTestData().getData("deactivate_breadcrumbs"));
    }

    public void checkCurrentRebatesTable() {
        List<String> expectedTableHeaders = new LinkedList<String>();
        List<String> errors = new LinkedList<String>();

        expectedTableHeaders.add("Organization");
        expectedTableHeaders.add("MS");
        expectedTableHeaders.add("Brand");
        expectedTableHeaders.add("PCR");
        expectedTableHeaders.add("Rate");
        expectedTableHeaders.add("New Rate");
        expectedTableHeaders.add("Locked");
        expectedTableHeaders.add("Elevated Offer");
        expectedTableHeaders.add("Start Date");
        expectedTableHeaders.add("Stop Date");
        expectedTableHeaders.add("Created By");
        expectedTableHeaders.add("Create Date");

        login();
        createMerchantOnly();
        Logger.logStep("Navigate to Programs");
        List<String> actualTableHeaders = programsPage.getCurrentRebatesTableHeaders();
        if (!actualTableHeaders.equals(expectedTableHeaders)) {
            errors.add("Table headers different that expected");
        }

        List<String> actualTableItems = programsPage.getItemsFromCurrentRebatesTable();
        if (actualTableItems.get(0).isEmpty()) {
            errors.add("Organization item is empty");
        }
        if (actualTableItems.get(1).isEmpty()) {
            errors.add("MS item is empty");
        }
        if (actualTableItems.get(2).isEmpty()) {
            errors.add("Brand item is empty");
        }
        if (actualTableItems.get(4).isEmpty()) {
            errors.add("Rate item is empty");
        }
        if (actualTableItems.get(5).isEmpty()) {
            errors.add("New Rate item is empty");
        }
        if (actualTableItems.get(10).isEmpty()) {
            errors.add("Created By item is empty");
        }
        if (actualTableItems.get(11).isEmpty()) {
            errors.add("Create Date item is empty");
        }
        Logger.logStep("Verify Current Rebates table");
        Assert.assertTrue(errors.isEmpty(), errors.toString());

    }

    public void checkModifyCommission() {
        login();
        createMerchantOnly();
        Logger.logStep("Navigate to Programs");
        programsPage.modifyNewRateAndClickUpdate("5");
        String commAfterUpdate = programsPage.getCommission();
        Logger.logStep("Verify updating after modify commission");
        Assert.assertEquals(commAfterUpdate, "Fixed: 5% ");
        programsPage.modifyNewRateAndClickUpdate("10");
        String initialState = programsPage.getCommission();
        Logger.logStep("Verify updating after initial");
        Assert.assertEquals(initialState, "Fixed: 10% ");
    }
}
