package com.solvd.carina.luma.pages;

import com.solvd.carina.luma.components.WishListProductCard;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomerPage extends AbstractPage {

    @FindBy(xpath = "//form[@class='form-wishlist-items']//div[@class='product-item-info']")
    private List<WishListProductCard> wishListProducts;

    @FindBy(xpath = "//div[@class='messages']//div[contains(text(), 'has been removed from your Wish List.')]")
    private ExtendedWebElement productRemovedMessage;

    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    public List<WishListProductCard> getWishListProducts() {
        return wishListProducts;
    }

    public String getProductRemovedMessage(){
        return productRemovedMessage.getText();
    }
}
