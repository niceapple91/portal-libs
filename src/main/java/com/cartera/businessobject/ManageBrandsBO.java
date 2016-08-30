package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.BrandRebatesPage;
import com.cartera.pages.HomePage;
import com.cartera.pages.LoginPage;
import com.cartera.pages.ManageBrandsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ManageBrandsBO {

    private WebDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;
    private ManageBrandsPage manageBrandsPage;
    private BrandRebatesPage brandRebatesPage;


    public ManageBrandsBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
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
        manageBrandsPage.logout();
    }

    public void loginAndNavigateToManageBrandsPage() {
        login();
        manageBrandsPage = homePage.navigateToManageBrandsPage();
        Context.waitForPageLoaded(driver);
    }


    public void checkNavigationToManageBrandsPage() {
        Logger.logStep("check Navigation To Manage Brands Page");
        String currentURL = Context.getCurrentWindowURL();
        Assert.assertEquals(currentURL, manageBrandsPage.getUrl(), "Can't navigate to 'Manage Brands Page' from 'Home Page'!");
    }

    public void checkOrganizationDropDown() {
        Logger.logStep("check Organization DropDown");
        List<String> organizations = manageBrandsPage.getOrganizationDropDownContent();
        Assert.assertTrue(organizations.size() > 1, "Problems with 'Organization' drop-down");
    }

    public void checkNameField() {
        final String name = "Cartera";
        Logger.logStep("check Name Field");
        manageBrandsPage.selectOrganization("Mn");
        manageBrandsPage.fillNameInput(name + "*");
        manageBrandsPage.clickFilterButton();
        boolean mark = false;
        List<String> brandNames = manageBrandsPage.getBrandsNamesFromTable();
        for (String brandName : brandNames) {
            if (brandName.contains(name)) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        Assert.assertTrue(mark, "Problems with 'Name' filtering.");
    }

    public void checkDomainField() {
        final String domain = "emall.mallnetworks.com";
        Logger.logStep("check Domain Field");
        manageBrandsPage.selectOrganization("Mn");
        manageBrandsPage.fillDomainInput(domain);
        manageBrandsPage.clickFilterButton();
        boolean mark = false;
        List<String> brandsDomains = manageBrandsPage.getBrandsDomainsFromTable();
        for (String brandDomain : brandsDomains) {
            if (brandDomain.contains(domain)) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        Assert.assertTrue(mark, "Problems with 'Domain' filtering.");
    }

    public void checkSolutionCheckBoxes() {
        final String solutions = "online,browser_app,social_media";
        manageBrandsPage.selectOnlineSolutionCheckBox();
        manageBrandsPage.selectBrowserAppSolutionCheckBox();
        manageBrandsPage.selectSocialMediaSolutionCheckBox();
        manageBrandsPage.clickFilterButton();
        Logger.logStep("check Solution CheckBoxes");
        boolean mark = false;
        List<String> brandsSolutions = manageBrandsPage.getBrandsSolutionsFromTable();
        for (String brandSolution : brandsSolutions) {
            if (brandSolution.trim().equals(solutions)) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        if(!mark){
            Logger.logStep(brandsSolutions.toString());
        }
        Assert.assertTrue(mark, "Problems with 'Solutions' filtering. All must be -> " + solutions);
    }

    public void checkUpdateDate() {
        final String date = "3/3/2014";
        GregorianCalendar oDate = new GregorianCalendar(2014, 1, 1);
        Logger.logStep("check Update Date");
        manageBrandsPage.selectOrganization("Mn");
        manageBrandsPage.fillUpdatedToField(date);
        manageBrandsPage.clickFilterButton();
        boolean mark = false;
        List<GregorianCalendar> dates = manageBrandsPage.getUpdatedToDates();
        for (GregorianCalendar aDate : dates) {
            if (aDate.after(oDate)) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        Assert.assertTrue(mark, "Problems with 'UpdateDate' filtering.");
    }

    public void checkBrandsToShowDropDown() {
        Logger.logStep("check Brands To Show DropDown");
        List<String> organizations = manageBrandsPage.getBrandsToShowContent();
        Assert.assertTrue(organizations.size() > 1, "Problems with 'Brands To Show' drop-down.");
    }

    public void checkResetLink() {
        manageBrandsPage.selectOnlineSolutionCheckBox();
        manageBrandsPage.selectBrowserAppSolutionCheckBox();
        manageBrandsPage.selectSocialMediaSolutionCheckBox();
        Logger.logStep("check Reset Link");
        manageBrandsPage.clickReset();
        boolean mark = true;
        if (manageBrandsPage.isOnlineSolutionCheckBoxSelected()) mark = false;
        else if (manageBrandsPage.isBrowserAppSolutionCheckBoxSelected()) mark = false;
        else if (manageBrandsPage.isSocialMediaSolutionCheckBoxSelected()) mark = false;
        Assert.assertTrue(mark, "Problems with 'Reset' button.");
    }

    public void checkFilterButton() {
        final String date = "3/3/2014";
        GregorianCalendar oDate = new GregorianCalendar(2014, 1, 1);
        Logger.logStep("check Filter Button");
        manageBrandsPage.selectOrganization("Mn");
        manageBrandsPage.fillUpdatedToField(date);
        manageBrandsPage.clickFilterButton();
        boolean mark = false;
        List<GregorianCalendar> dates = manageBrandsPage.getUpdatedToDates();
        for (GregorianCalendar aDate : dates) {
            if (aDate.after(oDate)) {
                mark = true;
            } else {
                mark = false;
                break;
            }
        }
        Assert.assertTrue(mark, "Problems with 'Filter' button.");
    }

    public void checkBrandsDisplayTable() {
        List<String> expectedRebatesDisplayTableHeaders = new LinkedList<String>();
        expectedRebatesDisplayTableHeaders.add("");
        expectedRebatesDisplayTableHeaders.add("Organization");
        expectedRebatesDisplayTableHeaders.add("Display name");
        expectedRebatesDisplayTableHeaders.add("Domain");
        expectedRebatesDisplayTableHeaders.add("Solutions");
        expectedRebatesDisplayTableHeaders.add("Content actively managed");
        expectedRebatesDisplayTableHeaders.add("Active Merchant Count");
        expectedRebatesDisplayTableHeaders.add("Created");
        expectedRebatesDisplayTableHeaders.add("Updated");
        expectedRebatesDisplayTableHeaders.add("Actions");

        Logger.logStep("check Brands Display Table");
        manageBrandsPage.selectOrganization("Mn");
        manageBrandsPage.clickFilterButton();
        List<String> actualRebatesDisplayTableHeaders = manageBrandsPage.getBrandTableHeaders();
        Assert.assertEquals(actualRebatesDisplayTableHeaders, expectedRebatesDisplayTableHeaders, "Problem with display table.");
    }

    public void checkChooseAnAction() {
        Logger.logStep("Choose action 'delete' and click 'go'");
        manageBrandsPage.clickChooseAnAction("Delete");
        Logger.logStep("Verify if select is correct");
        manageBrandsPage.clickGo();
        Assert.assertEquals(manageBrandsPage.getErrorAfterChooseAction(), "You must at least select one item.", "smth wrong with 'ChooseAction'");
    }

    public void checkSetOfResults() {
        Logger.logStep("check Set Of Results");
        List<String> actualRebatesRecords = manageBrandsPage.getBrandsDomainsFromTable();
        Assert.assertTrue(actualRebatesRecords.size() == 100, "Problem with set of results, real size is: " + actualRebatesRecords.size());
    }

    public void checkShowLink() {
        Logger.logStep("check Show Link");
        String rebUrl = manageBrandsPage.clickShowLink();
        String url = Context.getCurrentWindowURL();
        Assert.assertTrue(url.equals(rebUrl), "Problem with show link");
    }

    public void checkEditLink() {
        Logger.logStep("check Edit Link");
        String rebUrl = manageBrandsPage.clickEditLink();
        String url = Context.getCurrentWindowURL();
        Assert.assertTrue(url.equals(rebUrl), "Problem with edit link");
    }

    public void checkEditAndDeactivateButtons() {
        Logger.logStep("Verify if Edit and Deactivate buttons are available");
        Assert.assertTrue(manageBrandsPage.isEditButtonAvailable(), "Smth wrong with Edit Button");
        Assert.assertTrue(manageBrandsPage.isDeactivateButtonAvailable(), "Smth wrong with Deactivate Button");
    }

    public void checkSaveButton() {
        Logger.logStep("Verify if Save button is available");
        Assert.assertTrue(manageBrandsPage.isSaveButtonAvailable(), "Smth wrong with Save Button");
    }

    public void checkRebatesLink() {
        Logger.logStep("check Rebates Link");
        String rebUrl = manageBrandsPage.clickRebatesLink();
        String url = Context.getCurrentWindowURL();
        Assert.assertTrue(url.equals(rebUrl), "Problem with Rebates link");
    }

    public void checkEditFlagsLink() {
        Logger.logStep("check Edit Flags Link");
        String rebUrl = manageBrandsPage.clickEditFlagsLink();
        String url = Context.getCurrentWindowURL();
        Assert.assertTrue(url.equals(rebUrl), "Problem with Edit Flags link");
    }

    public void checkDeleteLink() {
        Logger.logStep("check Delete Link");
        String alertTxt = manageBrandsPage.clickDeleteLink();
        Assert.assertTrue(alertTxt.contains("Are you sure"), "Problem with delete link");
    }

    public void checkSelectAllCheckBox() {
        Logger.logStep("Verify SelectAll CheckBox");
        manageBrandsPage.clickSelectAllCheckBox();
        List<Boolean> allCheckBoxes = manageBrandsPage.getAllCheckBoxesBool();

        for (Boolean value : allCheckBoxes) {
            Assert.assertTrue(value, "Smth wrong with SelectAll check box");
        }
    }

    public void checkPreviewLink() {
        manageBrandsPage.clickPreviewLink();
        String expectedUrlAAPreview = Context.getTestData().getData("url_preview_link_aa");
        String actualUrlAAPreview = new String();
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                actualUrlAAPreview = Context.getCurrentWindowURL();
                driver.close();
                driver.switchTo().window(parentWindow);
            }
        }
        Logger.logStep("Verify if Preview Link works correct");
        Assert.assertTrue(expectedUrlAAPreview.contains(actualUrlAAPreview), "Wrong work Preview link");
    }
}
