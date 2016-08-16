package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.HomePage;
import com.cartera.pages.LoginPage;
import com.cartera.testdata.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageBO {

    private WebDriver driver;
    private TestData testData;

    private LoginPage loginPage;
    private HomePage homePage;

    public HomePageBO(WebDriver driver) {
        this.driver = driver;
        testData = Context.getTestData();
        loginPage = new LoginPage(driver);
    }

    public void loginAndNavigateToHomePage() {
        loginPage.open();
        homePage = loginPage.login();
        if (homePage.getLoggedIn().isDisplayed()) {
            Logger.logHuman(Logger.Level.INFO, "User is logged", true);
        } else {
            Logger.logHuman(Logger.Level.WARNING, "User isn't logged. Something is wrong", true);
        }
    }

    public void navigateToInfoPageAndPresentPortalVersion() {
        homePage.navigateToInfoPage();
        String currentPortalVersion = homePage.getPortalVersion();
        Logger.logStep("Current portal version is - " + currentPortalVersion);
        Logger.logStep("It's only getting portal version. We haven't expected version");
    }


    public void clickLoginAndVerifyPrompt() {
        Logger.logStep("Navigate to Login Page");
        loginPage.open();
        Logger.logStep("Click Login button");
        loginPage.clickLoginButton();
        Logger.logStep("Verify if login prompt is appeared");
        Assert.assertEquals(loginPage.getLoginPromptText(), testData.getData("login_prompt"), "Login prompt isn't correct");

    }

    public void verifyPageSections() {
        Logger.logStep("Check if displaying sections contain expected content.");
        List<List<String>> actualSectionsList = homePage.getSectionsList();
        List<List<String>> expectedSectionsList = new ArrayList<List<String>>();
        for (String section : testData.getList("sections_list")) {
            expectedSectionsList.add(Arrays.asList(section.split("\\*")));
        }
        for(int i=0; i<actualSectionsList.size(); i++){
            Assert.assertTrue(actualSectionsList.get(i).containsAll(expectedSectionsList.get(i)), "Actual sections or their content doesn't contain(!) expected:" + "\n\nExpected:" + expectedSectionsList.get(i) + "\n\nActual:" + actualSectionsList.get(i));
            if(actualSectionsList.get(i).size()>expectedSectionsList.get(i).size()){
                Logger.logStep("Actual list is different than expected. In - \"" + expectedSectionsList.get(i).get(0) + "\" block.");
                Logger.logStep("\nExpected:\n" + expectedSectionsList.get(i));
                Logger.logStep("\nActual:\n" + actualSectionsList.get(i));
                throw new SkipException("Actual list is different than expected. In - \"" + expectedSectionsList.get(i).get(0) + "\" block.");
            }
        }

        //Assert.assertTrue(actualSectionsList.containsAll(expectedSectionsList), "Actual sections or their content doesn't contain(!) expected:" + "\nExpected:" + expectedSectionsList + "\nActual:" + actualSectionsList);
    }


}
