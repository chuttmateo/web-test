package com.solvd.carina.saucedemo.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractUIObject{

    @FindBy(xpath = ".//div[@class='inventory_item_label']//div[@class='inventory_item_name ']")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//img[@class='inventory_item_img']")
    private ExtendedWebElement productImage;

    @FindBy(xpath = ".//div[@class='pricebar']//button")
    private ExtendedWebElement addToCartButton;

    public Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName(){
        return productName.getText();
    }
    public String getProductImageUrl(){
        return productImage.getAttribute("src");
    }
    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }
}
