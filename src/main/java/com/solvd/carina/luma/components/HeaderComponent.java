package com.solvd.carina.luma.components;

import com.solvd.carina.luma.pages.ProductsPage;
import com.solvd.carina.luma.pages.SignInPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(xpath = "//div[@class='minicart-wrapper']/a")
    private ExtendedWebElement cartButton;

    @FindBy(id = "search")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = ".//li[@class='authorization-link']/a")
    private ExtendedWebElement signInButton;

    @FindBy(css = ".logged-in")
    private ExtendedWebElement welcomeMessage;

    @FindBy(css = "#minicart-content-wrapper")
    private CartComponent cart;

    @FindBy(css = ".counter-number")
    private ExtendedWebElement itemCounter;

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeInSearchInput(String text) {
        searchInput.type(text);
    }

    public ProductsPage pressEnterInSearchInput() {
        searchInput.sendKeys(Keys.ENTER);
        return new ProductsPage(getDriver());
    }

    public SignInPage clickOnSignInButton() {
        signInButton.scrollTo();
        signInButton.hover();
        signInButton.clickByJs();
        return new SignInPage(getDriver());
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public CartComponent clickOnOpenCartButton() {
        ExpectedCondition<Boolean> condition = (c) -> {
            if (itemCounter.getText().isEmpty()) return false;
            return !itemCounter.getText().equals("0"); //Wait until cart counter turns 1 or more
        };
        waitUntil(condition, 5);
        cartButton.click();
        return cart;
    }
}
