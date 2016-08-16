package com.cartera.pages;

import com.cartera.actions.Action;
import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.*;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class ManageMerchantsPage extends BasePage {

    @FindBy(xpath = "//table[@class='index_list-table']")
    private TableBody merchantTable;
    @FindBy(xpath = "//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']/span[contains(.,'Ok')]")
    private Button okButton;
    @FindBy(xpath = "//button[@class='btnSave ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']")
    private Button deactivateButton;
    @FindBy(xpath = "//*[@id='merchant_view_detail_table']/tbody/tr[3]/td")
    private TableItem statusTableItem;
    @FindBy(xpath = "//div[@class='ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-dialog-buttons']")
    private ModalWindow confirmModalWindow;
    @FindBy(css = "div.mnPortalFormRow")
    private ModalWindow deactivateMerchModalWindow;
    @FindBy(xpath = "//input[@id='active_ind']")
    private CheckBox showInactiveCheckBox;

    @FindBy(xpath = "//tr[@class='table_item']")
    private TableItem someTableItem;
    @FindBy(xpath = "(//tr[@class='inactive' and ./td[contains(.,'autotest')]]/td/a[@title='Programs'])[1]")
    private Link inactiveProgramsLink;

    public ManageMerchantsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        Logger.logStep("Navigate to Manage Merchants page");
        String url = getBaseUrl().replace("merchandising", "searchandising") + "/index.php/merchant.html";
        navigateTo(url);
    }

    public boolean isMerchantPresent(String merchantId, String merchantName) {
        String merchantRowSelector = "//table[@class='index_list-table']//tr[@id='merchant_" + merchantId + "']";
        String merchantStatusSelector = "//tr[@id='merchant_" + merchantId + "']/td[3]";
        char merchantNameFirstLetter = merchantName.toCharArray()[0];
        List<WebElement> webElements = driver.findElements(By.cssSelector(".cc-ui-loadscreen"));
        for (final WebElement webElement : webElements) {
            if (webElement.getText().equalsIgnoreCase(Character.toString(merchantNameFirstLetter))) {
                Context.ajaxWait(new Action() {
                    @Override
                    public boolean run() {
                        webElement.click();
                        return true;
                    }
                });
                break;
            }
            ;
        }
        merchantTable.waitForElementPresent();
        if (driver.findElement(By.xpath(merchantRowSelector)).isDisplayed() && driver.findElement(By.xpath(merchantStatusSelector)).getText().equals("Active"))
            return true;
        else return false;
    }

    public void openProgramsForMerchant(final String merchantId) {
        Logger.logStep("Filter by 'A'");
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                driver.findElement(By.cssSelector("#merchant_index_form")).findElement(By.linkText("A")).click();
                return true;
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String merchantRowSelector = "table.index_list-table>tbody>tr#merchant_" + merchantId;
        Context.waitForCondition(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return driver.findElement(By.cssSelector(merchantRowSelector)).isDisplayed();
            }
        }, "");

        WebElement root = driver.findElement(By.cssSelector(merchantRowSelector));
        WebElement programs = root.findElement(By.linkText("Programs"));
        programs.click();
        Context.waitForPageLoaded(driver);
    }

    public void clickOkButton() {
        confirmModalWindow.waitForElementPresent();
        if (confirmModalWindow.isDisplayed()) {
            Logger.logStep("Confirm Deactivation");
            okButton.waitForElementPresent();
            okButton.click();
        }
    }

    public void clickDeactivateButton() {
        Logger.logStep("Click Deactivate button");
        deactivateButton.waitForElementPresent();
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return deactivateButton.click();
            }
        });
    }

    public String getMerchantStatus() {
        statusTableItem.waitForElementPresent();
        String status = statusTableItem.getText();
        return status;
    }

    public void deactivateMerchant(String merchantId) {
        Logger.logStep("Filter by 'A'");
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                driver.findElement(By.cssSelector("#merchant_index_form")).findElement(By.linkText("A")).click();
                return true;
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger.logStep("Click Deactivate link");
        String merchantRowSelector = "table.index_list-table>tbody>tr# merchant_" + merchantId;
        WebElement root = driver.findElement(By.cssSelector(merchantRowSelector));
        WebElement deact = root.findElement(By.linkText("Deactivate Merchant"));
        deact.click();
        if (driver.findElement(By.cssSelector("div.ui-dialog-buttonset")).isDisplayed()) {
            driver.findElement(By.xpath("//div[@class='ui-dialog-buttonset']/button[1]")).click();
        } else Logger.logStep("ERRORA1");
        deactivateButton.waitForElementPresent();
        deactivateButton.click();
    }

    public void filterMerchantsByLetter(final String letter) {
        Logger.logStep("Filter by '" + letter + "'");

        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                driver.findElement(By.cssSelector("#merchant_index_form")).findElement(By.linkText(letter)).click();
                return true;
            }
        });
    }

    public void clickShowInactiveMerchants() {
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                driver.findElement(By.xpath("//input[@id='active_ind']")).click();
                return true;
            }
        });
    }

    public void clickOnProgramsOfInactiveAutotest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inactiveProgramsLink.waitForElementPresent();
        Logger.logStep("Click on Programs link of inactive autotest");
        inactiveProgramsLink.click();
        Context.waitForPageLoaded(driver);
    }

    public int isDisplayedProgramsWithNoData() {
        return driver.findElements(By.xpath("//form[@name='update_program']/table/tbody/tr")).size();
    }
}
