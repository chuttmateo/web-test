package com.solvd.carina.saucedemo.components;

import com.solvd.carina.saucedemo.pages.CartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject {

    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement cartLink;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CartPage clickOnCartButton(){
        cartLink.click();
        return new CartPage(getDriver());
    }
}
