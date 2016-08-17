package com.cartera.pages;

import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.Breadcrumb;
import com.cartera.elements.Button;
import com.cartera.elements.Message;
import com.cartera.elements.TextBox;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditBrandPage extends BasePage {


    @FindBy(xpath = "//li[@class='last']/a")
    private Button logoutButton;
    @FindBy(xpath = "//*[@id='sf_admin_container']/h1")
    private Breadcrumb headerBreadcrumb;
    @FindBy(xpath = "//*[@id='brand_member_share_percent']")
    private TextBox memberShareTextBox;
    @FindBy(xpath = "//*[@id='brand_client_share_percent']")
    private TextBox clientShareTextBox;
    @FindBy(xpath = "//*[@id='brand_mn_share_percent']")
    private TextBox carteraShareTextBox;
    @FindBy(xpath = "//*[@id='brand_name_view']")
    private TextBox nameTextBox;
    @FindBy(xpath = "//*[@id='brand_display_name_view']")
    private TextBox displayNameTextBox;
    @FindBy(xpath = "//*[@id='brand_points_conversion_rate']")
    private TextBox pcrTextBox;


    @FindBy(xpath = "//*[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")
    private Button saveButton;
    @FindBy(xpath = "//*[@class='cc-ui-form-error_row ui-state-error ui-corner-all']")
    private Message alertMessage;
    @FindBy(xpath = "//*[@class='cc-ui-form-error' and contains(.,'Required.')]")
    private Message requiredMessage;
    @FindBy(xpath = "//*[@class='notice']")
    private Message noticeMessage;

    @FindBy(xpath = "//input[@id='brand_single_percent_equivalent_short']")
    private TextBox sPerEqShortTextBox;
    @FindBy(xpath = "//input[@id='brand_single_dollar_equivalent_short']")
    private TextBox sDollarEqShortTextBox;
    @FindBy(xpath = "//input[@id='brand_single_percent_equivalent_long']")
    private TextBox sPerEqLongTextBox;
    @FindBy(xpath = "//input[@id='brand_single_dollar_equivalent_long']")
    private TextBox sDollarEqLongTextBox;
    @FindBy(xpath = "//input[@id='brand_plural_percent_equivalent_short']")
    private TextBox pPerEqShortTextBox;
    @FindBy(xpath = "//input[@id='brand_plural_dollar_equivalent_short']")
    private TextBox pDollarEqShortTextBox;
    @FindBy(xpath = "//input[@id='brand_plural_percent_equivalent_long']")
    private TextBox pPerEqLongTextBox;
    @FindBy(xpath = "//input[@id='brand_plural_dollar_equivalent_long']")
    private TextBox pDollarEqLongTextBox;

    public EditBrandPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public String getHeaderData() {
        headerBreadcrumb.waitForElementPresent();
        return headerBreadcrumb.getText();
    }

    public void logout() {
        logoutButton.waitForElementPresent();
        logoutButton.click();
        Context.waitForPageLoaded(driver);
    }

    public void modifyMemberShare(String value) {
        Logger.logStep("Modify Member Share");
        memberShareTextBox.waitForElementPresent();
        memberShareTextBox.clear();
        memberShareTextBox.setText(value);
    }

    public void clickSaveButton() {
        Logger.logStep("Click Save Button");
        saveButton.waitForElementPresent();
        saveButton.click();
        Context.waitForPageLoaded(driver);
    }

    public boolean isAlertDisplayed() {
        alertMessage.waitForElementPresent();
        return alertMessage.isDisplayed();
    }

    public void modifyClientShare(String value) {
        Logger.logStep("Modify Client Share");
        clientShareTextBox.waitForElementPresent();
        clientShareTextBox.clear();
        clientShareTextBox.setText(value);
    }

    public void modifyCarteraShare(String value) {
        Logger.logStep("Modify Cartera Share");
        carteraShareTextBox.waitForElementPresent();
        carteraShareTextBox.clear();
        carteraShareTextBox.setText(value);
    }

    public void removeNameField() {
        Logger.logStep("Remove Name Field");
        nameTextBox.waitForElementPresent();
        nameTextBox.clear();
    }

    public boolean isRequiredMessageDisplayed() {
        requiredMessage.waitForElementPresent();
        return requiredMessage.isDisplayed();
    }

    public void removeDisplayNameField() {
        Logger.logStep("Remove Display Name Field");
        displayNameTextBox.waitForElementPresent();
        displayNameTextBox.clear();
    }

    public void removeMemberShareField() {
        Logger.logStep("Remove Member Share Field");
        memberShareTextBox.waitForElementPresent();
        memberShareTextBox.clear();
    }

    public void removeClientShareField() {
        Logger.logStep("Remove Client Share Field");
        clientShareTextBox.waitForElementPresent();
        clientShareTextBox.clear();
    }

    public void removeCarteraShareField() {
        Logger.logStep("Remove Cartera Share Field");
        carteraShareTextBox.waitForElementPresent();
        carteraShareTextBox.clear();
    }

    public String getBrandName() {
        nameTextBox.waitForElementPresent();
        return nameTextBox.getWebElement().getAttribute("value");
    }

    public String getNoticeMessage() {
        noticeMessage.waitForElementPresent();
        return noticeMessage.getText();
    }

    public void modifyPointsConversionRate(String value) {
        Logger.logStep("Modify Points Conversion Rate");
        pcrTextBox.waitForElementPresent();
        pcrTextBox.clear();
        pcrTextBox.setText(value);
    }

    public void modifyCurrency() {
        Logger.logStep("Modify currency");
        sPerEqShortTextBox.clear();
        sPerEqShortTextBox.setText("mile/$");
        sDollarEqShortTextBox.clear();
        sDollarEqShortTextBox.setText("mile");
        sPerEqLongTextBox.clear();
        sPerEqLongTextBox.setText("mile/$");
        sDollarEqLongTextBox.clear();
        sDollarEqLongTextBox.setText("mile");
        pPerEqShortTextBox.clear();
        pPerEqShortTextBox.setText("miles/$");
        pDollarEqShortTextBox.clear();
        pDollarEqShortTextBox.setText("miles");
        pPerEqLongTextBox.clear();
        pPerEqLongTextBox.setText("miles/$");
        pDollarEqLongTextBox.clear();
        pDollarEqLongTextBox.setText("miles");
    }

    public void setValuesToinitialState() {
        Logger.logStep("Set values to initial state");
        pcrTextBox.clear();
        sPerEqShortTextBox.clear();
        sDollarEqShortTextBox.clear();
        sPerEqLongTextBox.clear();
        sDollarEqLongTextBox.clear();
        pPerEqShortTextBox.clear();
        pDollarEqShortTextBox.clear();
        pPerEqLongTextBox.clear();
        pDollarEqLongTextBox.clear();
        memberShareTextBox.clear();
        memberShareTextBox.setText("100");
        clientShareTextBox.clear();
        clientShareTextBox.setText("0.00");
        carteraShareTextBox.clear();
        carteraShareTextBox.setText("0.00");
    }
}
