package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.BrandRebatesPage;
import com.cartera.pages.HomePage;
import com.cartera.pages.LoginPage;
import com.cartera.pages.ManageBrandsPage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.SkipException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BrandRebatesBO {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ManageBrandsPage manageBrandsPage;
    private BrandRebatesPage brandRebatesPage;
    private DateFormat dateFormatter;
    private Date currentDate;

    public BrandRebatesBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        brandRebatesPage = new BrandRebatesPage(driver);
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

    public void loginAndNavigateToManageBrandsPage() {
        login();
        manageBrandsPage = homePage.navigateToManageBrandsPage();

    }

    public void checkNavigationToManageBrandsPage() {
        String currentURL = Context.getCurrentWindowURL();
        Logger.logStep("check Navigation To Manage Brands Page");
        Assert.assertEquals(currentURL, manageBrandsPage.getUrl(), "Can't navigate to 'Manage Brands Page' from 'Home Page'!");
    }

    public void checkNavigationToRebatesPage() {
        Logger.logStep("Click on RebatesUI link");
        brandRebatesPage = manageBrandsPage.clickOnRebatesNewUI();
        boolean condition = Context.getCurrentWindowURL().endsWith("/rebates");
        Logger.logStep("check Navigation To Rebates Page");
        Assert.assertTrue(condition, "Problems with navigation for Rebates link!");

    }

    public void checkOrganizationAndBrandDropDown() {
        brandRebatesPage.open();
        List<String> brands = new ArrayList<String>();
        Logger.logStep("brandRebateUI-3 check Organization DropDown");
        brands.addAll(brandRebatesPage.getBrandsForOrganization("Mn"));
        Assert.assertTrue(!brands.isEmpty(), "Problem with organization drop down");
        Logger.logStep("brandRebateUI-4 check Brand DropDown");
        int randomNum = (int) (Math.random() * brands.size());
        if(randomNum==0){
            randomNum+=1;
        }
        String randomBrand = brands.get(randomNum);
        Logger.logStep("Select random brand \'" + randomBrand + "\'");
        brandRebatesPage.selectBrand(randomBrand);
        brandRebatesPage.clickFilterButton();
        List<String> brandsFromTable = brandRebatesPage.getBrandsFromTable();
        boolean mark = true;
        for (String tmp : brandsFromTable) {
            if (tmp.equals(randomBrand)) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        if(!mark){
            Logger.logStep(brandsFromTable.toString());
        }
        Assert.assertTrue(mark, "Problem with brands filtering");
    }

    public void checkMerchantAutocomplete() {
        brandRebatesPage.open();
        Logger.logStep("check Merchant autocomplete");
        boolean isCorrectSuggestionB = brandRebatesPage.fillMerchantInputAndCheckSuggestions("autotest");
        Assert.assertTrue(isCorrectSuggestionB, "Problem with merchant autocomplete");
    }

    public void checkLockedRebates() {
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        brandRebatesPage.checkLockedRebates();
        brandRebatesPage.clickFilterButton();
        Logger.logStep("check Show All Locked Rebates");
        List<Boolean> isLockedList = brandRebatesPage.getLocksFromTable();
        boolean mark = true;
        for (Boolean isLocked : isLockedList) {
            if (isLocked) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        Assert.assertTrue(mark, "Problem with locking");
    }

    public void checkExpiredRebates() {
        brandRebatesPage.open();
        Logger.logStep("check Expired Rebates");
        brandRebatesPage.clickClear();
        brandRebatesPage.checkExpiredRebates();
        brandRebatesPage.clickFilterButton();
        List<String> rebatesList = brandRebatesPage.getInnactiveAndExpiredRebatesFromTable();
        boolean mark = true;
        dateFormatter = new SimpleDateFormat("MM/dd/yy");
        currentDate = new Date();
        for (String isInactive : rebatesList) {
            Date endDate = null;
            try {
                endDate = dateFormatter.parse(isInactive);
            } catch (ParseException e) {
                Logger.logTechnical(e.toString());
            }
            if (endDate != null) {
                if (endDate.before(currentDate)) {
                    mark = true;
                } else {
                    mark = false;
                    break;
                }
            }
        }
        Assert.assertTrue(mark, "Problem with expired filtering");
    }

    public void checkShowInactiveMerchants() {
        brandRebatesPage.open();
        Logger.logStep("check Show Inactive Merchants");
        brandRebatesPage.clickClear();
        brandRebatesPage.checkInactiveRebates();
        brandRebatesPage.clickFilterButton();
        List<String> rebatesList = brandRebatesPage.getInnactiveAndExpiredRebatesFromTable();
        boolean mark = true;
        dateFormatter = new SimpleDateFormat("MM/dd/yy");
        currentDate = new Date();
        for (String isInactive : rebatesList) {
            if (!isInactive.isEmpty()) {
                Date endDate = null;
                try {
                    endDate = dateFormatter.parse(isInactive);
                } catch (ParseException e) {
                    Logger.logTechnical(e.toString());
                }
                if (endDate != null) {
                    if (endDate.before(currentDate)) {
                        mark = true;
                    } else {
                        mark = false;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue(mark, "Problem with inactive filtering");
    }

    public void checkClearLink() {
        brandRebatesPage.open();
        brandRebatesPage.clickClear();//todo
        brandRebatesPage.checkExpiredRebates();
        brandRebatesPage.checkLockedRebates();
        brandRebatesPage.checkInactiveRebates();
        Logger.logStep("check Clear Button");
        brandRebatesPage.clickClear();
        Assert.assertTrue(!brandRebatesPage.isExpiredChecked(), "Problems with Clear button");
        Assert.assertTrue(!brandRebatesPage.isLockedChecked(), "Problems with Clear button");
        Assert.assertTrue(!brandRebatesPage.isInactiveChecked(), "Problems with Clear button");
    }

    public void checkFilterButton() {
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        brandRebatesPage.checkLockedRebates();
        Logger.logStep("check Filter Button");
        brandRebatesPage.clickFilterButton();
        List<Boolean> isLockedList = brandRebatesPage.getLocksFromTable();
        boolean mark = true;
        for (Boolean isLocked : isLockedList) {
            if (isLocked) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        Assert.assertTrue(mark, "Problem with locking");
    }

    public void checkRoundingBiasDropDown() {
        brandRebatesPage.open();
        List<String> brands = new ArrayList<String>();
        brands.addAll(brandRebatesPage.getBrandsForOrganization("Mn"));
        int rnd = new Random().nextInt(brands.size());
        if (rnd==0){rnd++;}
        String randomBrand = brands.get(rnd);
        brandRebatesPage.selectBrand(randomBrand);
        brandRebatesPage.clickFilterButton();
        Logger.logStep("check Rounding Bias DropDown");
        List<String> actual = brandRebatesPage.getBiasDropDownData();
        List<String> expected = new LinkedList<String>();
        expected.add("+1.00");
        expected.add("+0.50");
        expected.add("+0.25");
        expected.add("+0.10");
        expected.add("none");
        expected.add("-0.10");
        expected.add("-0.25");
        expected.add("-0.50");
        expected.add("-1.00");
        Assert.assertEquals(actual, expected, "Rounding bias problems");
    }

    public void checkRebatesButton() {
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        List<String> brands = new ArrayList<String>();
        brands.addAll(brandRebatesPage.getBrandsForOrganization("Mn"));
        int randomNum = (int) (Math.random() * brands.size());
        String randomBrand = brands.get(randomNum);
        brandRebatesPage.selectBrand(randomBrand);
        brandRebatesPage.fillMerchantInput("autotest");
        brandRebatesPage.clickFilterButton();
        Logger.logStep("check Reset Rebates button");
        brandRebatesPage.clickResetRebates();
        String rebateMessage = brandRebatesPage.getTextFromRebatesMessage();
        Assert.assertTrue(rebateMessage.contains("ebates"), "Smth wrong with Reset Rebates Button");
    }


    public void checkUpdateButton() {
        brandRebatesPage.open();
        List<String> brands = new ArrayList<String>();
        brands.addAll(brandRebatesPage.getBrandsForOrganization("Mn"));
        int randomNum = (int) (Math.random() * brands.size());
        String randomBrand = brands.get(randomNum);
        brandRebatesPage.selectBrand(randomBrand);
        brandRebatesPage.clickFilterButton();
        brandRebatesPage.lockAllRebates();
        Logger.logStep("check Update Button");
        brandRebatesPage.clickUpdateButton();
        boolean isUpdated = brandRebatesPage.isRebatesUpdated();
        Assert.assertTrue(isUpdated, "Problems with update");
        brandRebatesPage.lockAllRebates();
        Logger.logStep("unlock rebates");
        brandRebatesPage.clickUpdateButton();
    }

    public void logout() {
        brandRebatesPage.logout();
    }

    public void checkLockCheckboxAndRebatesUpdatedMessage() {
        brandRebatesPage.open();
        brandRebatesPage.clickClear();
        List<String> brands = new ArrayList<String>();
        brands.addAll(brandRebatesPage.getBrandsForOrganization("Mn"));
        int randomNum = (int) (Math.random() * brands.size());
        if(randomNum==0){randomNum+=1;}
        String randomBrand = brands.get(randomNum);
        brandRebatesPage.selectBrand(randomBrand);
        brandRebatesPage.fillMerchantInput("autotest");
        brandRebatesPage.clickFilterButton();
        try {
            if (brandRebatesPage.isLockCheckBoxHighlighted()) {
                throw new SkipException("CheckBox is already highlighted");
            }
        } catch (org.openqa.selenium.NoSuchElementException e){
            Logger.logStep("Element is not highlighted");
        }
        Logger.logStep("click Lock checkBox");
        if(!brandRebatesPage.clickLockCheckBox()){
            throw new SkipException("Didn't click on checkbox");
        }
        Logger.logStep("click UpdateSelected button");
        brandRebatesPage.clickUpdateButton();
        Logger.logStep("brandRebateUI-14 Verify if Lock checkBox is highlighted");
        Assert.assertTrue(brandRebatesPage.isLockCheckBoxHighlighted(), "Problems with Lock checkBox");
        brandRebatesPage.clickLockCheckBox();
        brandRebatesPage.clickUpdateButton();
        Logger.logStep("brandRebateUI-15 Verify if information/error message is correct");
        Assert.assertTrue(brandRebatesPage.getRebatesMessage().isDisplayed(), "Error with information/error message");
    }

    public void checkDownloadAllLink() {
        brandRebatesPage.open();
        List<String> brands = new ArrayList<String>();
        brands.addAll(brandRebatesPage.getBrandsForOrganization("Mn"));
        int randomNum = (int) (Math.random() * brands.size());
        String randomBrand = brands.get(randomNum);
        brandRebatesPage.selectBrand(randomBrand);
        brandRebatesPage.clickFilterButton();
        Logger.logStep("check All Links Url");
        String allLinksUrl = brandRebatesPage.getAllLinksUrl();
        Assert.assertTrue(allLinksUrl.endsWith("rebate_file.xls"), "Problems with update");
    }

    public void checkRebatesDisplayTable() {
        List<String> expectedRebatesDisplayTableHeaders = new LinkedList<String>();
        expectedRebatesDisplayTableHeaders.add("Brand");
        expectedRebatesDisplayTableHeaders.add("Merchant");
        expectedRebatesDisplayTableHeaders.add("No Bias");
        expectedRebatesDisplayTableHeaders.add("Rebate");
        expectedRebatesDisplayTableHeaders.add("New");
        expectedRebatesDisplayTableHeaders.add("Lock");
        expectedRebatesDisplayTableHeaders.add("Activate Date");
        expectedRebatesDisplayTableHeaders.add("Start Date");
        expectedRebatesDisplayTableHeaders.add("End Date");
        expectedRebatesDisplayTableHeaders.add("Created By");
        brandRebatesPage.open();
        Logger.logStep("check Rebates Display Table");
        brandRebatesPage.clickClear();
        brandRebatesPage.checkExpiredRebates();
        brandRebatesPage.clickFilterButton();
        List<String> actualRebatesDisplayTableHeaders = brandRebatesPage.getRebatesTableHeaders();
        int rebatesCount = brandRebatesPage.getAllRebates().size();
        Assert.assertEquals(actualRebatesDisplayTableHeaders, expectedRebatesDisplayTableHeaders, "Problems with rebates table headers");
        Assert.assertEquals(rebatesCount, 1000, "Problems with rebates counts in table");
    }

    public void checkRebatesDropDownWindow() {
        brandRebatesPage.open();
        Logger.logStep("check Rebates DropDown");
        List<String> actual = brandRebatesPage.getRebatesDropDownData();
        List<String> expected = new LinkedList<String>();
        expected.add("Show All");
        expected.add("Only Base Offers");
        expected.add("Only Elevated Offers");
        Assert.assertEquals(actual, expected, "Rebates dropdown problems");
    }
}
