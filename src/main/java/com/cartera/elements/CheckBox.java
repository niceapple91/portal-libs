package com.cartera.elements;

import org.openqa.selenium.WebElement;

public class CheckBox extends BaseElement {
    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    public boolean isSelected() {
        return webElement.isSelected();
    }
}
