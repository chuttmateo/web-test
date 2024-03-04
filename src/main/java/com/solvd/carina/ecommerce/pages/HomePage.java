package com.solvd.carina.ecommerce.pages;

import com.solvd.carina.ecommerce.components.Cart;
import com.solvd.carina.ecommerce.components.HomeProduct;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//button[text() = 'Add to cart']/ancestor::div[@tabindex = '1']")
    private List<HomeProduct> products;

    @FindBy(xpath = "//div[@title = 'Products in cart quantity']/../..")
    private ExtendedWebElement openCartButton;
    @FindBy(xpath = "//button[text() = 'Checkout']/ancestor::div[.//span[text()='X']][1]")
    private Cart cart;
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public HomeProduct getRandomProduct(){
        FluentWait<List<HomeProduct>> waiter = new FluentWait<>(products)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300));

        waiter.until(products -> !products.isEmpty());
        return products.get(new Random().nextInt(products.size()));
    }
    public Cart getCart() {
        return cart;
    }
    public Cart clickOnOpenCartButton(){
        openCartButton.click();
        return cart;
    }
}
