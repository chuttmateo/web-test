package com.solvd.carina.luma.components;

import com.solvd.carina.luma.pages.ProductDetailsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {

    @FindBy(xpath = ".//span[@class='price']/parent::span")
    private ExtendedWebElement price;

    @FindBy(css = ".product-item-link")
    private ExtendedWebElement name;

    @FindBy(xpath = ".//button[@title='Add to Cart']")
    private ExtendedWebElement addToCartButton;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public Double getPrice(){
        String attribute = price.getAttribute("data-price-amount"); //getting price value from an attribute to avoid the $ symbol
        return Double.valueOf(attribute);
    }

    public String getName(){
        return name.getText();
    }

    public ProductDetailsPage clickOnDetails(){
        name.scrollTo();
        name.hover();
        name.click();
        return new ProductDetailsPage(driver);
    }
    private void clickOnAddToCartBtn(){
        //TODO turn it as a public method and fix 'element click intercepted'
        addToCartButton.clickIfPresent();
    }
}
