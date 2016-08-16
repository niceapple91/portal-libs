package com.cartera.pages;

import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.Button;
import com.cartera.elements.Message;
import com.cartera.elements.TextBox;
import com.cartera.logger.Logger;
import com.cartera.logger.Logger.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private TextBox userNameTBox;
    @FindBy(name = "password")
    private TextBox passwordTBox;
    @FindBy(name = "submit")
    private Button loginButton;
    @FindBy(xpath = "//font[@color='red']")
    private Message loginPrompt;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        navigateTo(getBaseUrl());
    }

    public TextBox getUserNameTBox() {
        return userNameTBox;
    }

    public TextBox getPasswordTBox() {
        return passwordTBox;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public void clearFields() {
        userNameTBox.waitForElementPresent();
        userNameTBox.clear();
        passwordTBox.waitForElementPresent();
        passwordTBox.clear();
    }

    public HomePage login() {
        if(userIsLogged()){
            Logger.logStep("User is already logged.");
            (new HomePage(driver)).logout();
        }
        Logger.logStep("User is not logged, try login..");
        clearFields();
        Logger.logHuman(Level.INFO, "Start filling fields", true);
        getUserNameTBox().waitForElement();
        getUserNameTBox().setText(testData.getData("uname"));
        getPasswordTBox().setText(testData.getData("password"));
        getLoginButton().click();
        return new HomePage(driver);
    }

    public void clickLoginButton() {
        getLoginButton().waitForElement();
        getLoginButton().click();
    }

    public String getLoginPromptText() {
        loginPrompt.waitForElement();
        return loginPrompt.getText();
    }

    private boolean userIsLogged(){
        String currURL = driver.getCurrentUrl();
        if(currURL.contains("login.php") || currURL.contains("cbUrl")){
            return false;
        } else {
            return true;
        }
    }

}
