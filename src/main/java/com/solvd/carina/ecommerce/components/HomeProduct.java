package com.solvd.carina.ecommerce.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Objects;

public class HomeProduct extends AbstractUIObject {


    @FindBy(xpath = ".//button")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = ".//p")
    private ExtendedWebElement productName;
    public HomeProduct(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public String getProductName(){
        return productName.getText();
    }
    public void clickOnAddToCart(){
        addToCartButton.click();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeProduct that = (HomeProduct) o;
        return Objects.equals(productName.getText(), that.productName.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName.getText());
    }
}
