package com.solvd.carina.luma.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartItem extends AbstractUIObject {

    @FindBy(css = ".price")
    private ExtendedWebElement price;

    @FindBy(xpath = ".//strong[@class='product-item-name']/a")
    private ExtendedWebElement name;

    public CartItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public Double getDoublePrice(){
        String substring = getRawPrice().substring(1);
        return Double.valueOf(substring);
    }
    public String getRawPrice(){
        return price.getText();
    }

    public String getItemName(){
        return name.getText();
    }

}
