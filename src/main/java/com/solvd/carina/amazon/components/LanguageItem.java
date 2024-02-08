package com.solvd.carina.amazon.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LanguageItem extends AbstractUIObject {
    @FindBy(xpath = ".//input[@type='radio']")
    private ExtendedWebElement input;
    @FindBy(xpath = ".//i")
    private ExtendedWebElement i;

    public LanguageItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public String getValue(){
        return input.getAttribute("value");
    }
    public void click(){
        i.click();
    }
}
