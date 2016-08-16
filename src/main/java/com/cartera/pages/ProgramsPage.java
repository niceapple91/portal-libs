package com.cartera.pages;

import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.*;
import com.cartera.logger.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;

public class ProgramsPage extends BasePage {

    @FindBy(xpath = "//form[@name='update_program']/table/tbody/tr[@class='table_head']/td")
    private List<TableItem> commissionTableHeaders;
    @FindBy(xpath = "//form[@name='update_rebate']/table/tbody/tr/td[@class='td_head']")
    private List<TableItem> currentRebatesTableHeaders;
    @FindBy(xpath = "//form[@name='update_program']/table/tbody/tr[@class='table_item']/td[7]")
    private TableItem createDateElement;
    @FindBy(xpath = "//form[@name='update_program']/table/tbody/tr[@class='table_item']/td[3]")
    private TableItem commissionElement;
    @FindBy(xpath = "//form[@name='update_program']/table/tbody/tr[@class='table_item']/td[8]")
    private TableItem createdByElement;
    @FindBy(xpath = "//form[@name='update_program']//td[contains(.,'Change Type')]//a")
    private Link changeTypeLink;
    @FindBy(xpath = "//div[@class='breadcrumbs']")
    private Breadcrumb breadcrumb;
    @FindBy(xpath = "//form[@name='update_program']//td[contains(.,'Edit')]//a")
    private Link editLink;
    @FindBy(xpath = "//form[@name='update_program']//td[contains(.,'Deactivate')]//a")
    private Link deactivateLink;
    @FindBy(xpath = "//form[@name='update_rebate']/table/tbody/tr[@id='table_item_0']/td")
    private List<WebElement> itemsFromCurrentRebatesTable;
    @FindBy(xpath = "(//form[@name='update_program']/table/tbody/tr/td/input)[1]")
    private TextBox newRateTextBox;
    @FindBy(xpath = "//form[@name='update_program']/table/tbody/tr/td/input[@value='Update']")
    private Button updateButton;
    @FindBy(xpath = "//table/tbody/tr/td/input[@value='apply']")
    private Button applyButton;
    @FindBy(xpath = "//a[@href='logout.php']")
    private Link logoutLink;

    public ProgramsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void logout() {
        logoutLink.waitForElementPresent();
        logoutLink.click();
    }

    public List<String> getComissionProgramsTableHeaders() {
        List<String> headers = new LinkedList<String>();
        for (TableItem header : commissionTableHeaders) {
            headers.add(header.getText());
        }
        return headers;
    }

    public List<String> getCurrentRebatesTableHeaders() {
        List<String> headers = new LinkedList<String>();
        for (TableItem header : currentRebatesTableHeaders) {
            headers.add(header.getText());
        }
        return headers;
    }

    public List<String> getItemsFromCurrentRebatesTable() {
        List<String> items = new LinkedList<String>();
        for (WebElement element : itemsFromCurrentRebatesTable) {
            items.add(element.getText());
        }
        return items;
    }

    public String getCreateDate() {
        createDateElement.waitForElementPresent();
        String createDate = createDateElement.getText();
        return createDate;
    }

    public String getCommission() {
        commissionElement.waitForElementPresent();
        String commission = commissionElement.getText();
        return commission;
    }

    public String getCreatedBy() {
        createDateElement.waitForElementPresent();
        String createdBy = createdByElement.getText();
        return createdBy;
    }

    public void clickOnChangeType() {
        changeTypeLink.waitForElementPresent();
        Logger.logStep("Verify if Change Type link is available");
        Assert.assertTrue(changeTypeLink.isDisplayed(), "Change Type is not available");
        changeTypeLink.click();
    }

    public String getBreadcrumbs() {
        breadcrumb.waitForElementPresent();
        String breadcrumbText = breadcrumb.getText();
        return breadcrumbText;
    }

    public void clickOnEdit() {
        editLink.waitForElementPresent();
        Logger.logStep("Verify if Edit link is available");
        Assert.assertTrue(editLink.isDisplayed(), "Edit is not available");
        editLink.click();
    }

    public void clickOnDeactivate() {
        deactivateLink.waitForElementPresent();
        Logger.logStep("Verify if Deactivate link is available");
        Assert.assertTrue(deactivateLink.isDisplayed(), "Deactivate Link is not available");
        deactivateLink.click();
    }

    public void modifyNewRateAndClickUpdate(String value) {
        Logger.logStep("Modify New Rate to " + value);
        newRateTextBox.waitForElementPresent();
        newRateTextBox.setText(value);
        Logger.logStep("Click Update");
        updateButton.waitForElementPresent();
        updateButton.click();
    }
}
