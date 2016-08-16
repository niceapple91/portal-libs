package com.cartera.pages;

import com.cartera.actions.Action;
import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.*;
import com.cartera.elements.Error;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.util.*;

public class ManageBrandsPage extends BasePage {

    @FindBy(xpath = "//li[@class='last']/a")
    private Button logoutButton;
    @FindBy(xpath = "//*[@id='sf_admin_content']/form/div/table/tfoot/tr/th")
    private WebElement count;
    @FindBy(xpath = "//*[@id='sf_admin_content']/form/div/table/tbody/tr/td[@class='sf_admin_text sf_admin_list_td_display_name']")
    private List<TableItem> allDisplayNames;
    @FindBy(xpath = "//a/img[@src='/admin/images/next.png']/..")
    private Button nextPageButton;
    @FindBy(xpath = "//*[@id='sf_admin_content']/form/div/table/tbody")
    private TableBody tableBody;
    @FindBy(xpath = "(//*[@class='sf_admin_action_rebates']//a)[1]")
    private Link firstRebatesLink;
    @FindBy(xpath = "//*[@id='brand_filters_organization_fk']/option")
    private List<WebElement> allOrganizations;
    @FindBy(xpath = "//*[@id='brand_filters_organization_fk']/option[1]")
    private SelectBox orgDropDown;
    @FindBy(xpath = "//*[@id='brand_filters_organization_fk']")
    private SelectBox selectableOrgElement;
    @FindBy(xpath = "//input[@id='brand_filters_name']")
    private TextBox nameTextBox;
    @FindBy(xpath = "//div[@id='sf_admin_bar']//input[@type='submit']")
    private Button filterButton;
    @FindBy(xpath = "//input[@id='brand_filters_domain']")
    private TextBox domainTextBox;
    @FindBy(xpath = "//div[@class='sf_admin_list']/table/tbody/tr/td[@class='sf_admin_text sf_admin_list_td_domain_with_preview']")
    private List<TableItem> allDomains;
    @FindBy(xpath = "//*[@id='brand_filters_solutions_online']")
    private SelectBox onlineSolutionCheckBox;
    @FindBy(xpath = "//*[@id='brand_filters_solutions_browser_app']")
    private SelectBox browserAppSolutionCheckBox;
    @FindBy(xpath = "//*[@id='brand_filters_solutions_social_media']")
    private SelectBox socialMediaSolutionCheckBox;
    @FindBy(xpath = "//div[@class='sf_admin_list']/table/tbody/tr/td[@class='sf_admin_text sf_admin_list_td_solutions']")
    private List<TableItem> allSolutions;
    @FindBy(xpath = "//*[@id='brand_filters_post_date_to_month']")
    private SelectBox monthSelectBox;
    @FindBy(xpath = "//*[@id='brand_filters_post_date_to_day']")
    private SelectBox daySelectBox;
    @FindBy(xpath = "//*[@id='brand_filters_post_date_to_year']")
    private SelectBox yearSelectBox;
    @FindBy(xpath = "//div[@class='sf_admin_list']/table/tbody/tr/td[@class='sf_admin_date sf_admin_list_td_post_date']")
    private List<TableItem> allUpdatedDates;
    @FindBy(xpath = "//*[@id='brand_filters_content_actively_managed']/option")
    private List<WebElement> allBrandsToShowValues;
    @FindBy(xpath = "//a[@href='/brand/filter/action?_reset']")
    private Link resetLink;
    @FindBy(xpath = "//div[@class='sf_admin_list']/table/thead/tr/th")
    private List<TableItem> tableHeaders;
    @FindBy(xpath = "//input[@value='go']")
    private Button goButton;
    @FindBy(xpath = "//*[@name='batch_action']/option")
    private List<WebElement> allActions;
    @FindBy(xpath = "//*[@name='batch_action']")
    private SelectBox selectableActionElement;
    @FindBy(xpath = "//div[@class='error']")
    private Error errorMessageAfterDeleting;
    @FindBy(xpath = "//li[@class='sf_admin_action_show']//a")
    private List<Link> allShowLinks;
    @FindBy(xpath = "//li[@class='sf_admin_action_edit']//a")
    private List<Link> allEditLinks;
    @FindBy(xpath = "//li[@class='sf_admin_action_rebates']//a")
    private List<Link> allRebatesLinks;
    @FindBy(xpath = "//li[@class='sf_admin_action_editflags']//a")
    private List<Link> allEditFlagsLinks;
    @FindBy(xpath = "//li[@class='sf_admin_action_delete']/a")
    private List<Link> allDeleteLinks;
    @FindBy(xpath = "//span[@class='ui-button-text' and contains(.,'Edit')]")
    private Button editButton;
    @FindBy(xpath = "//span[@class='ui-button-text' and contains(.,'Deactivate')]")
    private Button deactivateButton;
    @FindBy(xpath = "//span[@class='ui-button-text' and contains(.,'Save')]")
    private Button saveButton;
    @FindBy(xpath = "//input[@id='sf_admin_list_batch_checkbox']")
    private CheckBox selectAllCheckBox;
    @FindBy(xpath = "//input[@class='sf_admin_batch_checkbox']")
    private List<CheckBox> allCheckBoxes;
    @FindBy(xpath = "//a[@href='/brand?page=3']")
    private Link page3Link;
    @FindBy(xpath = "//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')]/../td//li[@class='sf_admin_action_edit']//a")
    private List<Link> editLinksMnTestCash;
    @FindBy(xpath = "//*[@class='notice']")
    private Message noticeMessage;
    @FindBy(xpath = "//a[@href='http://301.201.preview.cartera.com/preview____.htm']")
    private Link previewAALink;
    @FindBy(xpath = "(//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')]/../td//li[@class='sf_admin_action_delete']/a)[1]")
    private Link deleteLink;

    public ManageBrandsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        Logger.logStep("Navigate to Manage Brands page");
        String url = getBaseUrl().replace("merchandising", "searchandising") + "/index.php/brand.html?page=1";
        navigateTo(url);
    }

    public void logout() {
        logoutButton.waitForElementPresent();
        logoutButton.click();
    }

    public String getUrl() {
        return getBaseUrl().replace("merchandising", "searchandising") + "/index.php/brand.html";
    }

    public List<String> getBrandsDisplayNames() {
        Logger.logStep("Getting all display Brands Names");
        int tablePages = Integer.parseInt(count.getText().split("page")[1].split("/")[1].replace(")", "").trim());
        List<String> brandsDisplayNames = new LinkedList<String>();
        List<TableItem> tmp0 = allDisplayNames;

        for (TableItem name : tmp0) {
            brandsDisplayNames.add(name.getText());
        }
        for (int i = 0; i < tablePages - 1; i++) {
            nextPageButton.click();
            Logger.logStep("-----------NEXT PAGE CLICK-------------");
            tableBody.waitForElementPresent();
            List<TableItem> tmp1 = allDisplayNames;
            for (TableItem name : tmp1) {
                brandsDisplayNames.add(name.getText());
            }
        }
        return brandsDisplayNames;
    }

    public BrandRebatesPage clickOnRebatesNewUI() {
        firstRebatesLink.waitForElementPresent();
        firstRebatesLink.click();
        return new BrandRebatesPage(driver);
    }

    public List<String> getOrganizationDropDownContent() {
        List<String> organizations = new LinkedList<String>();
        for (WebElement webElement : allOrganizations) {
            organizations.add(webElement.getText());
        }
        return organizations;
    }

    public void selectOrganization(String organization) {
        orgDropDown.waitForElementPresent();
        Map<String, String> valuesMap = new LinkedHashMap<String, String>();
        for (WebElement webElement : allOrganizations) {
            valuesMap.put(webElement.getText(), webElement.getAttribute("value"));
        }
        final String value = valuesMap.get(organization);
        selectableOrgElement.select(value);
    }

    public void fillNameInput(String name) {
        nameTextBox.waitForElementPresent();
        nameTextBox.setText(name);
    }

    public void clickFilterButton() {
        Logger.logStep("Click Filter Button");
        filterButton.waitForElementPresent();
        filterButton.click();
        Context.waitForPageLoaded(driver);
        Context.ajaxWait(new Action() {
            @Override
            public boolean run() {
                return true;
            }
        });
    }

    public List<String> getBrandsNamesFromTable() {
        List<String> brandsNames = new LinkedList<String>();
        for (TableItem displayName : allDisplayNames) {
            brandsNames.add(displayName.getText());
        }
        return brandsNames;

    }

    public void fillDomainInput(String domain) {
        domainTextBox.waitForElementPresent();
        domainTextBox.setText(domain);
    }

    public List<String> getBrandsDomainsFromTable() {
        List<String> brandsDomains = new LinkedList<String>();
        for (TableItem domain : allDomains) {
            brandsDomains.add(domain.getText());
        }
        return brandsDomains;
    }

    public void selectOnlineSolutionCheckBox() {
        Logger.logStep("select online Solution Check Box");
        onlineSolutionCheckBox.waitForElementPresent();
        onlineSolutionCheckBox.click();
    }

    public void selectBrowserAppSolutionCheckBox() {
        Logger.logStep("select browser App Solution Check Box");
        browserAppSolutionCheckBox.waitForElementPresent();
        browserAppSolutionCheckBox.click();
    }

    public void selectSocialMediaSolutionCheckBox() {
        Logger.logStep("select social Media Solution Check Box");
        socialMediaSolutionCheckBox.waitForElementPresent();
        socialMediaSolutionCheckBox.click();
    }

    public List<String> getBrandsSolutionsFromTable() {
        List<String> brandsSolutions = new LinkedList<String>();
        for (TableItem brandSolution : allSolutions) {
            brandsSolutions.add(brandSolution.getText());
        }
        return brandsSolutions;

    }

    public void fillUpdatedToField(String date) {
        String[] tmp = date.split("/");
        monthSelectBox.select(tmp[0].replace("0", ""));
        daySelectBox.select(tmp[1].replace("0", ""));
        yearSelectBox.select(tmp[2]);
    }

    public List<GregorianCalendar> getUpdatedToDates() {
        Integer s = allUpdatedDates.size();
        List<GregorianCalendar> dates = new LinkedList<GregorianCalendar>();
        for (TableItem updateDate : allUpdatedDates) {
            String[] values = updateDate.getText().split("/");
            dates.add(new GregorianCalendar(Integer.parseInt("20" + values[2]), Integer.parseInt(values[1]), Integer.parseInt(values[0])));
        }
        return dates;
    }

    public List<String> getBrandsToShowContent() {
        List<String> values = new LinkedList<String>();
        for (WebElement webElement : allBrandsToShowValues) {
            values.add(webElement.getText());
        }
        return values;
    }

    public void clickReset() {
        resetLink.waitForElementPresent();
        resetLink.click();
    }

    public boolean isOnlineSolutionCheckBoxSelected() {
        onlineSolutionCheckBox.waitForElementPresent();
        return onlineSolutionCheckBox.isSelected();
    }

    public boolean isBrowserAppSolutionCheckBoxSelected() {
        browserAppSolutionCheckBox.waitForElementPresent();
        return browserAppSolutionCheckBox.isSelected();
    }

    public boolean isSocialMediaSolutionCheckBoxSelected() {
        socialMediaSolutionCheckBox.waitForElementPresent();
        return socialMediaSolutionCheckBox.isSelected();
    }

    public List<String> getBrandTableHeaders() {
        List<String> rebatesTexts = new LinkedList<String>();
        for (TableItem header : tableHeaders) {
            rebatesTexts.add(header.getText());
        }
        return rebatesTexts;
    }

    public void clickGo() {
        Logger.logStep("Click Go button");
        goButton.waitForElementPresent();
        goButton.click();
    }

    public void clickChooseAnAction(String action) {
        Map<String, String> valuesMap = new LinkedHashMap<String, String>();
        for (WebElement webElement : allActions) {
            valuesMap.put(webElement.getText(), webElement.getAttribute("value"));
        }
        final String value = valuesMap.get(action);
        selectableActionElement.select(value);
    }

    public String getErrorAfterChooseAction() {
        errorMessageAfterDeleting.waitForElementPresent();
        return errorMessageAfterDeleting.getText();
    }

    public String clickShowLink() {
        String rebNum = allShowLinks.get(1).getWebElement().getAttribute("href");
        allShowLinks.get(1).click();
        return rebNum;
    }

    public String clickEditLink() {
        String rebNum = allEditLinks.get(1).getWebElement().getAttribute("href");
        allEditLinks.get(1).click();
        return rebNum;
    }

    public boolean isEditButtonAvailable() {
        editButton.waitForElementPresent();
        return editButton.isDisplayed();
    }

    public boolean isDeactivateButtonAvailable() {
        deactivateButton.waitForElementPresent();
        return deactivateButton.isDisplayed();
    }

    public boolean isSaveButtonAvailable() {
        saveButton.waitForElementPresent();
        return saveButton.isDisplayed();
    }

    public String clickRebatesLink() {
        String rebNum = allRebatesLinks.get(0).getWebElement().getAttribute("href");
        allRebatesLinks.get(0).click();
        return rebNum;
    }

    public String clickEditFlagsLink() {
        String rebNum = allEditFlagsLinks.get(0).getWebElement().getAttribute("href");
        allEditFlagsLinks.get(0).click();
        return rebNum;
    }

    public String clickDeleteLink() {
        List<WebElement> webElements = driver.findElements(By.cssSelector("li.sf_admin_action_delete a"));
        webElements.get(1).click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
        return alertText;
    }


    public void clickSelectAllCheckBox() {
        selectAllCheckBox.waitForElementPresent();
        selectAllCheckBox.click();
    }

    public List<Boolean> getAllCheckBoxesBool() {
        List<Boolean> all = new LinkedList<Boolean>();

        for (CheckBox element : allCheckBoxes) {
            all.add(element.isSelected());
        }
        return all;
    }

    private void selectMNFilter() {
        Logger.logStep("Select MN organization and filter");
        selectOrganization("Mn");
        clickFilterButton();
    }

    public void clickEditMnTestCash() {
        selectMNFilter();
        Logger.logStep("Click on random Edit link (Mn-textCash) and navigate to Edit Page");
        editLinksMnTestCash.get(0).click();
        Context.waitForPageLoaded(driver);
    }

    public void deleteTestCashMultipleBrands() {
        Logger.logStep("Select MN organization and filter");
        selectOrganization("Mn");
        clickFilterButton();
        Logger.logStep("Click on 2 checkBox (Mn-textCash) to delete");
        driver.findElement(By.xpath("(//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')]/../td//input[@class='sf_admin_batch_checkbox'])[1]")).click();
        driver.findElement(By.xpath("(//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')]/../td//input[@class='sf_admin_batch_checkbox'])[2]")).click();
        String brandName1 = driver.findElement(By.xpath("(//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')])[1]/../td[@class='sf_admin_text sf_admin_list_td_display_name']")).getText();
        String brandName2 = driver.findElement(By.xpath("(//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')])[2]/../td[@class='sf_admin_text sf_admin_list_td_display_name']")).getText();
        Logger.logStep("Choose action: delete " + brandName1 + " and " + brandName2 + " and verify if deleting is correct");
        clickChooseAnAction("Delete");
        clickGo();
        Assert.assertEquals(getNoticeMessage(), "The selected items have been deleted successfully.", "Smth wrong with delete");
        List allBrandsFromPage = getBrandsNamesFromTable();
        Logger.logStep("Verify if brands are not displayed on Manage Brands page");
        Assert.assertTrue(!allBrandsFromPage.contains(brandName1), "Warning. Brand " + brandName1 + " is displayed on page");
        Assert.assertTrue(!allBrandsFromPage.contains(brandName2), "Warning. Brand " + brandName2 + " is displayed on page");
        BrandRebatesPage brandRebatesPage = new BrandRebatesPage(driver);
        brandRebatesPage.open();
        List<String> brands = brandRebatesPage.getBrandsForOrganization("Mn");
        Logger.logStep("Verify if brands are not available on Brand Rebates page");
        Assert.assertTrue(!brands.contains(brandName1), "Smth wrong with deleting " + brandName1 + " brand");
        Assert.assertTrue(!brands.contains(brandName2), "Smth wrong with deleting " + brandName2 + " brand");
    }

    public String getNoticeMessage() {
        return noticeMessage.getText();
    }


    public void clickPreviewLink() {
        Logger.logStep("Click on preview link (americanAirLines)");
        previewAALink.waitForElementPresent();
        previewAALink.click();
        Context.waitForPageLoaded(driver);
    }

    public void deleteBrand() {
        Logger.logStep("Select MN organization and filter");
        selectOrganization("Mn");
        clickFilterButton();
        deleteLink.waitForElementPresent();
        String brandName = driver.findElement(By.xpath("(//td[@class='sf_admin_text sf_admin_list_td_Organization' and contains(.,'Mn')]/../td[@class='sf_admin_text sf_admin_list_td_display_name' and contains(.,'textCash')])[1]/../td[@class='sf_admin_text sf_admin_list_td_display_name']")).getText();
        Logger.logStep("Delete " + brandName + " brand");
        deleteLink.click();

        driver.switchTo().alert().accept();
        List allBrandsFromPage = getBrandsNamesFromTable();
        Logger.logStep("Verify if brand is not displayed on Manage Brands page");
        Assert.assertTrue(!allBrandsFromPage.contains(brandName), "Warning. Brand " + brandName + " is displayed on page");

        BrandRebatesPage brandRebatesPage = new BrandRebatesPage(driver);
        brandRebatesPage.open();
        List<String> brands = brandRebatesPage.getBrandsForOrganization("Mn");
        Logger.logStep("Verify if brand is not available on Brand Rebates page");
        Assert.assertTrue(!brands.contains(brandName), "Smth wrong with deleting " + brandName + " brand");
    }
}
