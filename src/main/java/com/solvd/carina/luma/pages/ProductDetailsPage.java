package com.solvd.carina.luma.pages;

import com.solvd.carina.luma.components.HeaderComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ProductDetailsPage extends AbstractPage {

    @FindBy(css = ".page-header")
    private HeaderComponent headerComponent;

    @FindBy(xpath = "//div[@attribute-code='size']/div/div")
    private List<ExtendedWebElement> productSizeList;

    @FindBy(xpath = "//div[@attribute-code='color']/div/div")
    private List<ExtendedWebElement> productColorList;

    @FindBy(css = "#product-addtocart-button")
    private ExtendedWebElement addToCartBtn;

    @FindBy(xpath = "//h1/span")
    private ExtendedWebElement productName;

    @FindBy(xpath = "//div[@class='product-info-main']//a[@data-action='add-to-wishlist']")
    private ExtendedWebElement addToWishListBtn;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(productName);
    }

    public String getProductName() {
        return productName.getText();
    }

    public void clickOnAddToCartButton() {
        addToCartBtn.scrollTo();
        addToCartBtn.hover();
        addToCartBtn.click();
    }

    public void clickOnARandomProductSize() {
        Random r = new Random();
        ExtendedWebElement extendedWebElement = productSizeList.get(r.nextInt(productSizeList.size()));
        extendedWebElement.click();
    }

    public void clickOnARandomProductColor() {
        Random r = new Random();
        ExtendedWebElement extendedWebElement = productColorList.get(r.nextInt(productColorList.size()));
        extendedWebElement.click();
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public CustomerPage clickOnAddToWishListButton(){
        addToWishListBtn.scrollTo();
        addToWishListBtn.hover();
        addToWishListBtn.click();
        return new CustomerPage(driver);
    }
}
