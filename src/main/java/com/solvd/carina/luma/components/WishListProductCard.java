package com.solvd.carina.luma.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class WishListProductCard extends AbstractUIObject {

    @FindBy(xpath = ".//strong[@class='product-item-name']/a")
    private ExtendedWebElement productName;

    @FindBy(xpath = ".//a[@data-role='remove']")
    private ExtendedWebElement removeFromWishListButton;

    public WishListProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName() {
        return productName.getText();
    }
    public void clickOnRemoveButton(){
        getRootExtendedElement().hover();
        removeFromWishListButton.click();
    }
}
