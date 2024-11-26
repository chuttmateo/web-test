package com.solvd.carina.luma.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartComponent extends AbstractUIObject {

    @FindBy(xpath = ".//li[@data-role='product-item']/div[@class='product']")
    private List<CartItem> items;

    public CartComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<CartItem> getItems() {
        return items;
    }
}
