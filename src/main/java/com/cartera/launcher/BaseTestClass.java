package com.cartera.launcher;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public abstract class BaseTestClass {

    public WebDriver getDriver(){
        return Context.getTestSession().getDriver();
    }

}
