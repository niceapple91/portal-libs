package com.cartera.businessobject;

import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.HomePage;
import com.cartera.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LogoutBO {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;


    public LogoutBO(WebDriver driver) {
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

    public void checkLogoutFunctionality() {
        login();
        homePage.logout();
        Logger.logStep("Check if correct goodbye message appears after user logout.");
        Assert.assertEquals(loginPage.getLoginPromptText(), "You are logged out. Have a nice day.");
    }
}
