package com.solvd.carina.saucedemo.pages;

import com.solvd.carina.saucedemo.components.Header;
import com.solvd.carina.saucedemo.components.Product;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ProductsPage extends AbstractPage {

    @FindBy(id = "header_container")
    private Header header;

    @FindBy(className = "inventory_item")
    private List<Product> products;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public int countProducts(){
        return products.size();
    }
    public Product getProductByIndex(int index){
        return products.get(index);
    }
    public Product getRandomProduct(){
        Random r = new Random();
        return products.get(r.nextInt(countProducts()));
    }
    public CartPage clickOnCartPage(){
        return header.clickOnCartButton();
    }

}
