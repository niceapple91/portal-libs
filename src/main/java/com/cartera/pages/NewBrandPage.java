package com.cartera.pages;

import com.cartera.actions.Action;
import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.Button;
import com.cartera.elements.SelectBox;
import com.cartera.elements.TextBox;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NewBrandPage extends BasePage {

    @FindBy(xpath = "//select[@name='organization']")
    private SelectBox orgSelectBox;
    @FindBy(xpath = "//input[@name='brand_name']")
    private TextBox brandNameTextBox;
    @FindBy(xpath = "//input[@name='brand_domain']")
    private TextBox domainTextBox;
    @FindBy(xpath = "//input[@name='rebate_action_pre']")
    private TextBox rActionPreTextBox;
    @FindBy(xpath = "//input[@name='rebate_action_post']")
    private TextBox rActionPostTextBox;
    @FindBy(xpath = "//input[@name='brand_display_name']")
    private TextBox bDisplayNameTextBox;
    @FindBy(xpath = "//input[@name='member_share_percent']")
    private TextBox memberSharePerTextBox;
    @FindBy(xpath = "//input[@name='client_share_percent']")
    private TextBox clientSharePerTextBox;
    @FindBy(xpath = "//input[@name='mn_share_percent']")
    private TextBox mnSharePerTextBox;
    @FindBy(xpath = "//input[@name='Submit']")
    private Button submitButton;

    @FindBy(xpath = "//input[@name='single_percent_equivalent_short']")
    private TextBox sPerEqShortTextBox;
    @FindBy(xpath = "//input[@name='single_dollar_equivalent_short']")
    private TextBox sDollarEqShortTextBox;
    @FindBy(xpath = "//input[@name='single_percent_equivalent_long']")
    private TextBox sPerEqLongTextBox;
    @FindBy(xpath = "//input[@name='single_dollar_equivalent_long']")
    private TextBox sDollarEqLongTextBox;
    @FindBy(xpath = "//input[@name='plural_percent_equivalent_short']")
    private TextBox pPerEqShortTextBox;
    @FindBy(xpath = "//input[@name='plural_dollar_equivalent_short']")
    private TextBox pDollarEqShortTextBox;
    @FindBy(xpath = "//input[@name='plural_percent_equivalent_long']")
    private TextBox pPerEqLongTextBox;
    @FindBy(xpath = "//input[@name='plural_dollar_equivalent_long']")
    private TextBox pDollarEqLongTextBox;
    @FindBy(xpath = "//input[@name='points_conversion_rate']")
    private TextBox pointsConvRateTextBox;


    public NewBrandPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        Logger.logStep("Navigate to New Brand page");
        String url = getBaseUrl() + "/brand_add_quick_form.php";
        navigateTo(url);
    }

    public void fillCashBackRequiredFields(String brandName) {
        Logger.logStep("Fill required inputs.");
        orgSelectBox.select("3");
        brandNameTextBox.setText(brandName);
        domainTextBox.setText(brandName);
        rActionPreTextBox.setText("Earn");
        rActionPostTextBox.setText("cash back");
        bDisplayNameTextBox.setText(brandName);
        memberSharePerTextBox.setText("100");
        clientSharePerTextBox.setText("0.00");
        mnSharePerTextBox.setText("0.00");
    }

    public void submit() {
        Logger.logStep("Submit form.");
        submitButton.waitForElementPresent();
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return submitButton.click();
            }
        });
    }

    public void submitButtonClick(){
        Logger.logStep("Submit form.");
        submitButton.waitForElementPresent();
        submitButton.click();
    }

    public void fillExtraPtRequiredFields(String brandName) {
        Logger.logStep("Fill required inputs.");
        orgSelectBox.waitForElement();
        orgSelectBox.waitForElementPresent();
        orgSelectBox.select("56");
        brandNameTextBox.setText(brandName);
        domainTextBox.setText(brandName);
        sPerEqShortTextBox.setText("extra pt/$");
        sDollarEqShortTextBox.setText("extra pt");
        sPerEqLongTextBox.setText("extra pt/$");
        sDollarEqLongTextBox.setText("extra pt");
        pPerEqShortTextBox.setText("extra pts/$");
        pDollarEqShortTextBox.setText("extra pts");
        pPerEqLongTextBox.setText("extra pts/$");
        pDollarEqLongTextBox.setText("extra points");
        rActionPostTextBox.setText("extra points");
        bDisplayNameTextBox.setText(brandName);
        pointsConvRateTextBox.setText("100");
        memberSharePerTextBox.setText("50");
        clientSharePerTextBox.setText("30");
        mnSharePerTextBox.setText("20");
    }

    public void fillMileRequiredFields(String brandName) {
        Logger.logStep("Fill required inputs.");
        orgSelectBox.select("56");
        brandNameTextBox.setText(brandName);
        domainTextBox.setText(brandName);
        sPerEqShortTextBox.setText("mile/$");
        sDollarEqShortTextBox.setText("mile");
        sPerEqLongTextBox.setText("mile/$");
        sDollarEqLongTextBox.setText("mile");
        pPerEqShortTextBox.setText("miles/$");
        pDollarEqShortTextBox.setText("miles");
        pPerEqLongTextBox.setText("miles/$");
        pDollarEqLongTextBox.setText("miles");
        rActionPreTextBox.setText("Earn");
        bDisplayNameTextBox.setText(brandName);
        pointsConvRateTextBox.setText("51.68");
        memberSharePerTextBox.setText("70");
        clientSharePerTextBox.setText("10");
        mnSharePerTextBox.setText("20");
    }

    public void fillMileRequiredFieldsForAlaska(String brandName) {
        Logger.logStep("Fill required inputs.");
        orgSelectBox.select("232");
        brandNameTextBox.setText(brandName);
        domainTextBox.setText(brandName);
        sPerEqShortTextBox.setText("mile/$");
        sDollarEqShortTextBox.setText("mile");
        sPerEqLongTextBox.setText("mile/$");
        sDollarEqLongTextBox.setText("mile");
        pPerEqShortTextBox.setText("miles/$");
        pDollarEqShortTextBox.setText("miles");
        pPerEqLongTextBox.setText("miles/$");
        pDollarEqLongTextBox.setText("miles");
        rActionPreTextBox.setText("Earn");
        bDisplayNameTextBox.setText(brandName);
        pointsConvRateTextBox.setText("51.68");
        memberSharePerTextBox.setText("70");
        clientSharePerTextBox.setText("10");
        mnSharePerTextBox.setText("20");
    }

    public String getUrl() {
        String protocol = testData.getData("merch_https").equals("yes") ? "https" : "http";
        String baseDomain = testData.getData("base_domain");

        String baseUrl = String.format("%s://%s", protocol, getPrefixForUrl() + baseDomain);
        return baseUrl + "/brand_add_quick_form.php";
    }

    public List<String> getErrorMessages() {
        List<String> errorMessages = new LinkedList<String>();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        String[] tmpArray = alertText.replace(" - ", "").split("\\n");
        errorMessages.addAll(Arrays.asList(tmpArray));
        errorMessages.remove(0);
        errorMessages.remove(errorMessages.size() - 1);
        alert.accept();
        driver.switchTo().defaultContent();
        return errorMessages;
    }
}
