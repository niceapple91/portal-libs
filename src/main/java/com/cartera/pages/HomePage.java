package com.cartera.pages;

import com.cartera.decorator.CustomFieldDecorator;
import com.cartera.elements.Breadcrumb;
import com.cartera.elements.Link;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='link_menu_item']//a[contains(.,'Add Merchant')]")
    private Link addMerchantLink;
    @FindBy(xpath = "//div[@class='link_menu_item']//a[contains(.,'Add a Brand')]")
    private Link newBrandLink;
    @FindBy(xpath = "//div[@class='link_menu_item']//a[contains(.,'Manage Brands')]")
    private Link manageBrandsLink;
    @FindBy(xpath = "//div[@class='link_menu_item']//a[contains(.,'Manage Merchants')]")
    private Link manageMerchantsLink;
    @FindBy(xpath = "//a[@href='/logout.php']")
    private Link logoutLink;

    @FindBy(xpath = "//b[contains(text(),'ogged in as:')]")
    private Breadcrumb loggedIn;
    @FindBy(xpath = "//body")
    private WebElement infoPageBody;
    @FindBy(xpath = "//td[@valign='top']/div/ul/li/../../..")
    private List<Link> allHomePageSectionsList;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public void open() {
        Logger.logStep("Navigate to Home Page page");
        open("/index.php");
        Context.waitForPageLoaded(driver);
    }

    public Breadcrumb getLoggedIn() {
        loggedIn.waitForElementPresent();
        return loggedIn;
    }

    public void navigateToAddMerchantPage() {
        Logger.logStep("Navigate to Add Merchant page");
        addMerchantLink.click();
    }

    public NewBrandPage navigateToNewBrandPage() {
        Logger.logStep("Navigate to New Brand page");
        newBrandLink.click();
        Context.waitForPageLoaded(driver);
        return new NewBrandPage(driver);
    }

    public ManageBrandsPage navigateToManageBrandsPage() {
        Logger.logStep("Navigate to Manage Brands page");
        manageBrandsLink.click();
        Context.waitForPageLoaded(driver);
        return new ManageBrandsPage(driver);
    }

    public ManageMerchantsPage navigateToManageMerchantsPage() {
        Logger.logStep("Navigate to Manage Merchants page");
        manageMerchantsLink.click();
        return new ManageMerchantsPage(driver);
    }

    public void navigateToInfoPage() {
        Logger.logStep("Navigate to Info page");
        open("/diag/info.php");
    }

    public String getPortalVersion() {
        Matcher matcher = Pattern.compile("Portal version:\\s*(.*?.*)").matcher(infoPageBody.getText());
        matcher.find();
        return matcher.group(1);
    }

    public List<List<String>> getSectionsList() {
        List<List<String>> sectionsContentList = new LinkedList<List<String>>();
        for (Link link : allHomePageSectionsList) {
            List<String> contentList = new LinkedList<String>(Arrays.asList(link.getText().split("\\n")));
            for (Iterator<String> iterator = contentList.iterator(); iterator.hasNext(); ) {
                String str = iterator.next();
                if (str.isEmpty()) {
                    iterator.remove();
                }
            }
            sectionsContentList.add(contentList);
        }
        return sectionsContentList;
    }

    public void logout() {
        Logger.logStep("Logout");
        logoutLink.waitForElement();
        logoutLink.click();
    }
}
