package com.cartera.businessobject;

import com.cartera.actions.Action;
import com.cartera.launcher.Context;
import com.cartera.logger.Logger;
import com.cartera.pages.*;
import com.cartera.testdata.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.LinkedList;
import java.util.List;

public class RoundingBiasBO {

    private WebDriver driver;
    private TestData testData;

    private String merchantName;
    private String cashBackBrand;
    private String forMileBrand;
    private LoginPage loginPage;
    private HomePage homePage;
    private ManageBrandsPage manageBrandsPage;
    private BrandRebatesPage brandRebatesPage;
    private AddMerchantPage addMerchantPage;
    private NewBrandPage newBrandPage;
    private List<String> merchantsList;


    public RoundingBiasBO(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        testData = Context.getTestData();
    }

    public void login() {
        loginPage.open();
        homePage = loginPage.login();
        if (homePage.getLoggedIn().isDisplayed()) {
            Logger.logHuman(Logger.Level.INFO, "User is logged", true);
        } else {
            Logger.logHuman(Logger.Level.WARNING, "User isn't logged. Something is wrong", true);
        }
    }


    public void initPages() {
        manageBrandsPage = new ManageBrandsPage(driver);
        brandRebatesPage = new BrandRebatesPage(driver);
        newBrandPage = new NewBrandPage(driver);
        addMerchantPage = new AddMerchantPage(driver);
    }

    public void createMerchantsAndBrands() {
        initPages();
        login();
        createMerchant();
        createCashBrand();
        createMileBrand();
        brandRebatesPage.open();
        merchantsList = new LinkedList<String>();
        merchantsList.add(merchantName);
        merchantsList.add("AT\u0026T U-verse");
        merchantsList.add("Apple Store");
        merchantsList.add("CheapCaribbean");
        merchantsList.add("FedEx, a USAA Alliance Offer");
    }

    public void checkRoundingBiasForMN(String bias) {
        List<String> expectedNoneBias = testData.getList("rounding_bias_" + bias);
        List<String> actualNoneBias = getMNRoundingBias(bias);
        Assert.assertEquals(actualNoneBias, expectedNoneBias, "Wrong results. Rounding bias problems");
    }

    public void checkRoundingBiasForAlaska(String bias) {
        List<String> expectedNoneBias = testData.getList("rounding_bias_" + bias.substring(0, 5));
        List<String> actualNoneBias = getAlaskaRoundingBias(bias);
        Assert.assertEquals(actualNoneBias, expectedNoneBias, "Wrong results. Rounding bias problems");
    }

    public List<String> getMNRoundingBias(String bias) {
        List<String> actualBias = new LinkedList<String>();
        Logger.logStep("Brand: " + cashBackBrand + " Bias: " + bias);
        for (String merchant : merchantsList) {
            brandRebatesPage.clickClear();
            brandRebatesPage.getBrandsForOrganization("Mn");
            brandRebatesPage.selectBrand(cashBackBrand);
            brandRebatesPage.fillMerchantInput(merchant);
            brandRebatesPage.clickFilterButton();
            brandRebatesPage.selectBias(bias);
            brandRebatesPage.clickResetRebates();
            List<String> tmp = brandRebatesPage.getRebatesFromTable();
            actualBias.addAll(tmp);
            Logger.logStep("Merchant: " + merchant + tmp.toString());
        }
        return actualBias;
    }

    private List<String> getAlaskaRoundingBias(String bias) {
        List<String> actualBias = new LinkedList<String>();
        Logger.logStep("Brand: " + forMileBrand + " Bias: " + bias);
        for (String merchant : merchantsList) {
            brandRebatesPage.clickClear();
            brandRebatesPage.getBrandsForOrganization("Alaskaair");
            brandRebatesPage.selectBrand(forMileBrand);
            brandRebatesPage.fillMerchantInput(merchant);
            brandRebatesPage.clickFilterButton();
            brandRebatesPage.selectBias(bias);
            brandRebatesPage.clickResetRebates();
            List<String> tmp = brandRebatesPage.getRebatesFromTable();
            actualBias.addAll(tmp);
            Logger.logStep("Merchant: " + merchant + tmp.toString());
        }
        return actualBias;
    }


    private void createMerchant() {
        try {
            addMerchantPage.open();
            merchantName = "autotest" + System.currentTimeMillis();
            Logger.logStep("Fill required inputs. Merchant name: " + merchantName);
            addMerchantPage.createNewGlobalMerchant(merchantName);
            homePage.open();
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with merchant creation.");
        }
    }

    private void createCashBrand() {
        cashBackBrand = "textCash" + System.currentTimeMillis() + 1;
        try {
            newBrandPage.open();
            Logger.logStep("Fill required inputs. Brand name: " + cashBackBrand);
            newBrandPage.fillCashBackRequiredFields(cashBackBrand);
            Context.ajaxWait(new Action() {
                @Override
                public boolean run() {
                    newBrandPage.submit();
                    return true;
                }
            });
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with cash brand creation.");
        }
    }

    private void createMileBrand() {
        forMileBrand = "textMile" + System.currentTimeMillis() + 3;
        try {
            newBrandPage.open();
            Logger.logStep("Fill required inputs. Brand name: " + forMileBrand);
            newBrandPage.fillMileRequiredFieldsForAlaska(forMileBrand);
            Context.ajaxWait(new Action() {
                @Override
                public boolean run() {
                    newBrandPage.submit();
                    return true;
                }
            });
        } catch (Exception e) {
            Assert.assertTrue(false, "Problems with mile brand creation.");
        }
    }


    public void checkNoBiasCheckBox() {
        brandRebatesPage.clickClear();
        brandRebatesPage.getBrandsForOrganization("Mn");
        brandRebatesPage.selectBrand(cashBackBrand);
        brandRebatesPage.fillMerchantInput(merchantName);
        brandRebatesPage.clickFilterButton();
        brandRebatesPage.selectBias("none");
        brandRebatesPage.clickResetRebates();
        brandRebatesPage.checkNoBiasCheckBox();
        brandRebatesPage.clickUpdateButton();
        Logger.logStep("Verify NoBias checkbox");
        Assert.assertEquals(brandRebatesPage.getNoBiasCheckMessage(),merchantName+" [10%] rounding bias disabled","Smth Wrong NoBias checkBox");
        brandRebatesPage.checkNoBiasCheckBox();
        brandRebatesPage.clickUpdateButton();
        Logger.logStep("Verify return to initial state");
        Assert.assertEquals(brandRebatesPage.getNoBiasCheckMessage(),merchantName+" [10%] rounding bias enabled","Smth Wrong NoBias checkBox");
    }
}
