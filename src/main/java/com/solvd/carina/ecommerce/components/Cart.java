package com.solvd.carina.ecommerce.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Cart extends AbstractUIObject {
    @FindBy(xpath = ".//button[.//span[text() = 'X' ]]")
    private ExtendedWebElement closeCartButton;
    @FindBy(xpath = ".//button[@title='remove product from cart']/parent::div")
    private List<CartProduct> products;
    @FindBy(xpath = ".//button[text() = 'Checkout']")
    private ExtendedWebElement checkoutButton;
    public Cart(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public List<CartProduct> getAllProducts(){
        return products;
    }
    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }
    public void clickOnCloseCartButton(){
        closeCartButton.click();
    }
}
