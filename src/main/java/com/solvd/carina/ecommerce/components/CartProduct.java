package com.solvd.carina.ecommerce.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartProduct extends AbstractUIObject {

    @FindBy(xpath = ".//p")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//p[text() = 'Quantity: ']")
    private ExtendedWebElement productQuantityElement;
    public CartProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public String getProductName(){
        return productName.getText();
    }
    public String getProductQuantity(){
        return productQuantityElement.getText();
    }
}
