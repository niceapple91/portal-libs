package com.cartera.pages;

import com.cartera.actions.Action;
import com.cartera.actions.DrawImage;
import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.*;
import com.cartera.elements.Error;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddMerchantPage extends BasePage {

    @FindBy(xpath = "//a[@alt=\"Create a new Global Merchant\"]")
    private Link createLink;
    @FindBy(id = "merchant_global_merchant_fk_modal")
    private ModalWindow createMerchantWindow;
    @FindBy(id = "global_merchant_name")
    private TextBox nameTextBox;
    @FindBy(id = "global_merchant_display_name")
    private TextBox displayNameTextBox;
    @FindBy(xpath = "//div[@id='merchant_global_merchant_fk_modal']//button")
    private Button saveButton;

    @FindBy(xpath = "//input[@id='autocomplete_merchant_global_merchant_fk']")
    private TextBox globalMerchantTextBox;
    @FindBy(id = "merchant_name")
    private TextBox merchantNameTextBox;

    @FindBy(id = "merchant_merch_name_text_25")
    private TextBox displayNameExternalTextBox;
    @FindBy(id = "merchant_merch_name_text_full")
    private TextBox merchNameTextFull;

    @FindBy(id = "merchant_description")
    private TextBox descriptionTextBox;
    @FindBy(id = "merchant_domain")
    private TextBox domainTextBox;
    @FindBy(id = "merchant_aggregator_fk")
    private SelectBox aggregatorSelectBox;
    @FindBy(id = "merchant_aggregator_merchant_id")
    private TextBox aggregatorIDTextBox;
    @FindBy(id = "unassociated_merchant_category_list")
    private SelectBox unassociatedSelectBox;
    @FindBy(xpath = "//div[@class='double_list']/div/a[1]")
    private Button moveToList;
    @FindBy(id = "merchant_stripname")
    private TextBox displayNameExTextBox;
    @FindBy(id = "merchant_Default_Program_fix_commission_percent")
    private TextBox commisionPercentTextBox;
    @FindBy(id = "merchant_Link_url")
    private TextBox urlTextBox;
    @FindBy(id = "merchant_88_x_31_Image_url")
    private TextBox image88x31TextBox;
    @FindBy(id = "merchant_120_x_60_Image_url")
    private TextBox image120x60TextBox;
    @FindBy(xpath = "html/body/div[@class='document']/div[@class='documentContent']/form/div[@class='mnPortalFormRow']/button[@type='submit']")
    private Button saveMerchButton;
    @FindBy(css = "span.cc-ui-form-error")
    private Error createMerchantError;

    @FindBy(css = "li.ac_even.ac_over")
    private AutocompleteWindow globalMAutocompleteWindow;
    @FindBy(xpath = "//div[@class='breadcrumbs']")
    private Breadcrumb merchantBreadcrumb;

    public AddMerchantPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        Logger.logStep("Navigate to New Merchant page");
        String url = getBaseUrl().replace("merchandising", "searchandising") + "/index.php/merchant/new.html";
        navigateTo(url);
        Context.waitForPageLoaded(driver);
    }

    public String getUrl() {
        return getBaseUrl().replace("merchandising", "searchandising") + "/index.php/merchant/new.html";
    }

    public void createNewGlobalMerchant(String merchantName) {
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return createLink.click();
            }
        });
        createMerchantWindow.waitForElement();
        createMerchantWindow.waitForElementPresent();
        Logger.logStep("Set merchant name.");
        nameTextBox.setText(merchantName);
        displayNameTextBox.setText(merchantName);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                saveButton.click();
                return true;
            }
        });
        fillAllInputs(merchantName);
    }

    public void createNewGlobalMerchantOnly(String merchantName) {
        createLink.click();
        createMerchantWindow.waitForElementPresent();
        nameTextBox.setText(merchantName);
        displayNameTextBox.setText(merchantName);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return saveButton.click();
            }
        });

    }

    public void fillAllInputs(String merchantName) {
        Logger.logStep("Try fill all inputs..");
        DrawImage drawImages = new DrawImage();
        drawImages.drawImage("test_image.png");
        File imageFile = new File("test_image.png");
        globalMerchantTextBox.waitForElement();
        globalMerchantTextBox.waitForElementPresent();
        merchantNameTextBox.setText(merchantName);
        globalMerchantTextBox.clearAndWriteText(merchantName);

        displayNameExternalTextBox.setText(merchantName);
        merchNameTextFull.setText(merchantName);

        descriptionTextBox.setText(merchantName);
        domainTextBox.setText(merchantName);
        aggregatorSelectBox.select(getRandomAggregatorValue());

        //verify if aggregatorID is required
        if(aggregatorIDTextBox.getWebElement().getAttribute("disabled") == null){
            aggregatorIDTextBox.setText(merchantName);
        }
        unassociatedSelectBox.select("1");
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return moveToList.click();
            }
        });
        displayNameExTextBox.clearAndWriteText(merchantName);
        commisionPercentTextBox.setText("10");
        urlTextBox.setText("http://autotest.com/");
        image88x31TextBox.setText(imageFile.getAbsolutePath());
        image120x60TextBox.setText(imageFile.getAbsolutePath());
        Logger.logStep("Save.. ");
        saveMerchButton.click();
        Context.waitForPageLoaded(driver);
        imageFile.delete();
    }


    public String getRandomAggregatorValue(){
        String randomValue;
        int randomIndex;
        List<String> allValues = new ArrayList<String>();
        List<WebElement> allAggregators = driver.findElements(By.xpath("//select[@id='merchant_aggregator_fk']/option"));

        for (WebElement element: allAggregators){
            if(!element.getText().isEmpty()){
                allValues.add(element.getAttribute("value"));
            }

        }
        randomIndex = new Random().nextInt(allValues.size());
        randomValue = allValues.get(randomIndex);
        Logger.logStep("Random value is - " + randomValue);
        return randomValue;
    }


    public void fillGlobalMerchantAutocomplete(String merchant) {
        globalMerchantTextBox.setText(merchant);
    }

    public void doubleClickOnAutocomplete() {
        globalMerchantTextBox.doubleClick();
    }

    public String getTextFromGlobalAutocomplete() {
        globalMAutocompleteWindow.waitForElementPresent();
        return globalMAutocompleteWindow.getText();
    }

    public Error getCreateMerchantError() {
        return createMerchantError;
    }

    public String getMerchantId() {
        String protocol = testData.getData("merch_https").equals("yes") ? "https" : "http";
        String baseDomain = testData.getData("base_domain");
        return Context.getCurrentWindowURL().replace(protocol + "://"+ baseDomain + "/view_merchant_programs.php?merchant_id=", "");
    }

    public boolean isMerchantCreated(String merchantId, String merchantName) {
        String programsName = merchantBreadcrumb.getText().replace("merchandising >  main >  manage merchant >  ", "").replace(" programs", "");
        if (merchantId != null && !merchantId.isEmpty() && programsName.equals(merchantName)) {
            return true;
        } else
            return false;
    }


}
