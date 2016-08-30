package com.cartera.pages;

import com.cartera.actions.Action;
import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.*;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BrandRebatesPage extends BasePage {

    @FindBy(xpath = "//li[@class='last']/a")
    private Button logoutButton;
    @FindBy(xpath = "//*[@id='organization_fk']/option[2]")
    private SelectBox orgDropdown;
    @FindBy(xpath = "//*[@id='brand_fk']/option[1]")
    private SelectBox brandDropdown;
    @FindBy(xpath = "//*[@id='organization_fk']/option")
    private List<WebElement> organizations;
    @FindBy(xpath = "//*[@id='brand_fk']/option")
    private List<WebElement> brands;
    @FindBy(xpath = "//*[@id='organization_fk']")
    private SelectBox selectableOrgElement;
    @FindBy(xpath = "//*[@id='brand_fk']")
    private SelectBox selectableBrandElement;
    @FindBy(id = "autocomplete_merchant_fk")
    private TextBox merchantTextBox;
    @FindBy(css = "div.ac_results")
    private AutocompleteWindow popupMerchant;
    @FindBy(xpath = "//div[@class='ac_results']/ul/li[1]")
    private Link autoLinkMerchant;
    @FindBy(xpath = "//div[@class='ac_results']/ul/li")
    private List<WebElement> currMerchants;
    @FindBy(xpath = "//*[@id='ccRebateSearchFormDiv']/div[@class='mnPortalFormRow']/button")
    private Button filterButton;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/table/tbody/tr/td[4]")
    private List<TableItem> rebatesList;
    @FindBy(css = "li.ac_even.ac_over")
    private AutocompleteWindow merchantRAutocompleteWindow;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/table/tbody/tr/td[1]")
    private List<TableItem> brandsFromTable;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/table/tbody/tr/td/input[@class='ccRebateUpdateLockedOrBiasCell']")
    private List<CheckBox> lockBrandsFromTable;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/table/tbody/tr[@class='ccRebateUpdateInactiveRow']/td[9]")
    private List<TableItem> expiredBrandsFromTable;
    @FindBy(xpath = "//div[@id='ccRebateSearchFormDiv']/div[@class='mnPortalFormRow']/a")
    private Button clearButton;
    @FindBy(xpath = "//*[@id='user_locked']")
    private CheckBox allLockedCheckBox;
    @FindBy(xpath = "//*[@id='show_expired']")
    private CheckBox expiredRebatesCheckBox;
    @FindBy(xpath = "//*[@id='active_ind']")
    private CheckBox inactiveRebatesCheckBox;
    @FindBy(xpath = "//*[@id='brand_rebate_rounding_bias']/option")
    private List<WebElement> biasDropDownData;
    @FindBy(xpath = "//*[@id='ccRebateRoundingBiasFormDiv']/div[@class='mnPortalFormRow']/button")
    private Button resetRebatesButton;
    @FindBy(xpath = "//div[@id='mnRebateUIStatusSection']/ul[contains(.,'ebates')]")
    private Message rebatesMessage;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']//div[@class='mnPortalFormRow'][1]//button")
    private Button updateButton;
    @FindBy(xpath = "//*[@id='ccBatchRebateLockCheckbox']")
    private CheckBox lockAllCheckBox;
    @FindBy(xpath = "//*[@id='mnRebateUIStatusSection']/ul")
    private Message rebatesUpdateMessage;
    @FindBy(xpath = "//*[@class='ccRebateLockCheckbox']")
    private CheckBox lockCheckBox;
    @FindBy(xpath = "//*[@class='ccRebateUpdateLockedOrBiasCell']")
    private CheckBox lockedCheckBox;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/div[@class='mnPortalFormRow']/a")
    private Link downloadAllLink;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/table/thead/tr/th")
    private List<TableItem> rebatesTableHeaders;
    @FindBy(xpath = "//*[@id='rebate_index_form_list']/form/table/tbody/tr")
    private List<TableItem> allRebates;
    @FindBy(xpath = "//*[@id='rebates_to_show']/option")
    private List<WebElement> rebatesDropDownOptions;
    @FindBy(xpath = "//*[@id='brand_rebate_rounding_bias']/option[1]")
    private SelectBox biasDropdown;
    @FindBy(xpath = "//*[@id='brand_rebate_rounding_bias']/option")
    private List<WebElement> allBias;
    @FindBy(xpath = "//*[@id='brand_rebate_rounding_bias']")
    private SelectBox selectableBiasElement;
    @FindBy(xpath = "(//table//input)[2]")
    private CheckBox noBiasCheckBox;
    @FindBy(xpath = "//div[@id='mnRebateUIStatusSection']/ul/li")
    private Message noBiasCheckMessage;


    public BrandRebatesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        Logger.logStep("Navigate to Brands Rebates page");
        String url = getBaseUrl().replace("merchandising", "searchandising") + "/rebate";
        navigateTo(url);

    }

    public void logout() {
        logoutButton.waitForElementPresent();
        logoutButton.click();
    }

    public String getUrl() {
        return getBaseUrl().replace("merchandising", "searchandising") + "/rebate";
    }

    public void clickClear() {
        clearButton.waitForElementPresent();
        clearButton.click();
    }

    public void checkNoBiasCheckBox(){
        Logger.logStep("Click NoBias checkbox");
        noBiasCheckBox.waitForElementPresent();
        noBiasCheckBox.click();
    }

    public void checkLockedRebates() {
        allLockedCheckBox.waitForElementPresent();
        allLockedCheckBox.click();
    }

    public void checkExpiredRebates() {
        expiredRebatesCheckBox.waitForElementPresent();
        expiredRebatesCheckBox.click();
    }

    public void checkInactiveRebates() {
        inactiveRebatesCheckBox.waitForElementPresent();
        inactiveRebatesCheckBox.click();
    }

    public boolean isLockedChecked() {
        return allLockedCheckBox.isSelected();
    }

    public boolean isExpiredChecked() {
        return expiredRebatesCheckBox.isSelected();
    }

    public boolean isInactiveChecked() {
        return inactiveRebatesCheckBox.isSelected();
    }


    public List<String> getBrandsForOrganization(String organization) {
        orgDropdown.waitForElementPresent();

        List<String> brandsForOrganization = new LinkedList<String>();
        Map<String, String> valuesMap = new LinkedHashMap<String, String>();

        for (WebElement webElement : organizations) {
            valuesMap.put(webElement.getText(), webElement.getAttribute("value"));
        }
        final String value = valuesMap.get(organization);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                selectableOrgElement.select(value);
                return true;
            }
        });
        try {Thread.sleep(5000);}
        catch (InterruptedException e) {e.printStackTrace();}
        orgDropdown.waitForElementPresent();
        for (WebElement webElement : brands) {
            brandsForOrganization.add(webElement.getText());
        }
        return brandsForOrganization;
    }

    public void selectBrand(String brand) {
        brandDropdown.waitForElementPresent();
        Map<String, String> valuesMap = new LinkedHashMap<String, String>();
        for (WebElement webElement : brands) {
            valuesMap.put(webElement.getText(), webElement.getAttribute("value"));
        }
        final String value = valuesMap.get(brand);
        selectableBrandElement.select(value);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return true;
            }
        });
    }

    public void selectBias(String bias) {
        biasDropdown.waitForElementPresent();
        Map<String, String> valuesMap = new LinkedHashMap<String, String>();
        for (WebElement webElement : allBias) {
            valuesMap.put(webElement.getText(), webElement.getAttribute("value"));
        }
        final String value = valuesMap.get(bias);
        selectableBiasElement.select(value);
    }


    public void fillMerchantInput(String inputValue) {
        merchantTextBox.waitForElementPresent();
        merchantTextBox.setText(inputValue);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript(String.format("$('#autocomplete_merchant_fk').val('%s');", inputValue));
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                merchantTextBox.doubleClick();
                return true;
            }
        });

//        popupMerchant.waitForElementPresent();
        autoLinkMerchant.waitForElementPresent();
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                Logger.logStep("Click on merchant: " + currMerchants.get(0).getText());
                currMerchants.get(0).click();
                return true;
            }
        });

    }

    public void clickFilterButton() {
        filterButton.waitForElementPresent();

        filterButton.click();
        Logger.logStep("Click on filter button.");
        Context.waitForPageLoaded(driver);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return true;
            }
        });
    }

    public List<String> getRebatesFromTable() {

        List<String> rebatesTexts = new LinkedList<String>();
        for (TableItem rebate : rebatesList) {
            rebatesTexts.add(rebate.getText());
        }
        return rebatesTexts;
    }

    public boolean fillMerchantInputAndCheckSuggestions(String inputValue) {
        boolean marker = false;
        merchantTextBox.waitForElementPresent();
        merchantTextBox.setText(inputValue);
        merchantTextBox.click();
        popupMerchant.waitForElementPresent();

        if (popupMerchant.getText().contains(inputValue)) {
            marker = true;
        } else {
            return marker;
        }

        return marker;
    }

    public List<String> getBrandsFromTable() {
        List<String> brands = new LinkedList<String>();
        for (TableItem brand : brandsFromTable) {
            brands.add(brand.getText());
        }
        return brands;
    }

    public List<Boolean> getLocksFromTable() {
        List<Boolean> isCheckedList = new LinkedList<Boolean>();
        for (CheckBox lock : lockBrandsFromTable) {
            isCheckedList.add(lock.isSelected());
        }
        return isCheckedList;
    }

    public List<String> getInnactiveAndExpiredRebatesFromTable() {
        List<String> rebatesTexts = new LinkedList<String>();
        for (TableItem rebate : expiredBrandsFromTable) {
            rebatesTexts.add(rebate.getText());
        }
        return rebatesTexts;

    }

    public List<String> getBiasDropDownData() {
        List<String> options = new LinkedList<String>();
        for (WebElement webElement : biasDropDownData) {
            options.add(webElement.getText());
        }
        return options;
    }

    public void clickResetRebates() {
        resetRebatesButton.waitForElementPresent();
        resetRebatesButton.click();
        Context.waitForPageLoaded(driver);
    }

    public String getTextFromRebatesMessage() {
        rebatesMessage.waitForElementPresent();
        String message = rebatesMessage.getText();
        return message;
    }

    public void clickUpdateButton() {
        updateButton.waitForElementPresent();
        updateButton.click();
        Context.waitForPageLoaded(driver);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return true;
            }
        });
    }

    public void lockAllRebates() {
        Logger.logStep("Lock all click");
        lockAllCheckBox.waitForElementPresent();
        lockAllCheckBox.click();
    }

    public boolean isRebatesUpdated() {
        return rebatesUpdateMessage.getText().startsWith("Rebates Updated:");
    }

    public boolean clickLockCheckBox() {
        Logger.logStep("Click Lock checkBox");
        lockCheckBox.waitForElementPresent();
        return lockCheckBox.click();
    }

    public boolean isLockCheckBoxHighlighted() {
        Context.waitForPageLoaded(driver);
        return lockedCheckBox.isDisplayed();
    }

    public Message getRebatesMessage() {
        return rebatesMessage;
    }

    public String getAllLinksUrl() {
        downloadAllLink.waitForElementPresent();
        return downloadAllLink.getWebElement().getAttribute("href");
    }

    public List<String> getRebatesTableHeaders() {
        List<String> rebatesTexts = new LinkedList<String>();
        for (TableItem header : rebatesTableHeaders) {
            rebatesTexts.add(header.getText());
        }
        return rebatesTexts;
    }

    public List<String> getAllRebates() {
        List<String> rebatesTexts = new LinkedList<String>();
        for (TableItem rebate : allRebates) {
            rebatesTexts.add(rebate.getText());
        }
        return rebatesTexts;
    }

    public List<String> getRebatesDropDownData() {
        List<String> options = new LinkedList<String>();
        for (WebElement webElement : rebatesDropDownOptions) {
            options.add(webElement.getText());
        }
        return options;
    }
    public String getNoBiasCheckMessage() {
        noBiasCheckMessage.waitForElementPresent();
        return noBiasCheckMessage.getText();
    }

}
