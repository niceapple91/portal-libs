package com.cartera.pages;

import com.cartera.launcher.Context;
import com.cartera.testdata.TestData;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected TestData testData;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        testData = Context.getTestData();
    }

    public void open(String relative_url) {
        String url = getBaseUrl() + relative_url;
        navigateTo(url);
    }

    public String getBaseUrl() {
        String protocol = testData.getData("merch_https").equals("yes") ? "https" : "http";
        String baseDomain = testData.getData("base_domain");

        String baseUrl = String.format("%s://%s", protocol, getPrefixForUrl() + baseDomain);
        return baseUrl;
    }

    public String getPrefixForUrl() {
        String prefix = System.getenv("PREFIX");

        if (prefix != null && !prefix.equals("") && !prefix.equals("null")) {
            return prefix.replace(";", "").trim() + ".";
        } else {
            return "";
        }
    }

    public String getPrefix() {
        String prefix = System.getenv("PREFIX");
        if (prefix != null && !prefix.equals("") && !prefix.equals("null")) {
            return prefix.replace(";", "").trim();
        } else {
            return "";
        }
    }

    public void navigateTo(String url) {
        driver.get(url);
        Context.waitForPageLoaded(driver);
    }

}
