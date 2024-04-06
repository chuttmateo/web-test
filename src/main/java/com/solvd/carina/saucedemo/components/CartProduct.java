package com.solvd.carina.saucedemo.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartProduct extends AbstractUIObject {

    @FindBy(className = "cart_quantity")
    private ExtendedWebElement quantity;

    @FindBy(className = "inventory_item_name")
    private ExtendedWebElement name;

    @FindBy(id = "remove-sauce-labs-backpack")
    private ExtendedWebElement removeButton;

    public CartProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public String getQuantity(){
        return quantity.getText();
    }
    public void clickOnRemoveButton(){
        removeButton.click();
    }
    public String getProductCartName(){
        return name.getText();
    }
}
