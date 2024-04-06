package com.solvd.carina.saucedemo.pages;

import com.solvd.carina.saucedemo.components.CartProduct;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {
    @FindBy(className = "cart_item")
    private List<CartProduct> products;
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public boolean isProductPresent(String productName){
         return products.stream()
                .anyMatch((product) -> product.getProductCartName().equals(productName));
    }
}
