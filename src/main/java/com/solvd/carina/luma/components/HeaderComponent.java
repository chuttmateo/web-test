package com.solvd.carina.luma.components;

import com.google.common.base.Function;
import com.solvd.carina.luma.pages.SearchPage;
import com.solvd.carina.luma.pages.SignInPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class HeaderComponent extends AbstractUIObject {

    @FindBy(xpath = "//div[@class='minicart-wrapper']/a")
    private ExtendedWebElement cartButton;

    @FindBy(id = "search")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = "//li/a")
    private ExtendedWebElement signInButton;

    @FindBy(css = ".logged-in")
    private ExtendedWebElement welcomeMessage;

    @FindBy(css = "#minicart-content-wrapper")
    private CartComponent cart;

    /*@FindBy(css = ".counter-number")
    private ExtendedWebElement itemCounter;*/

    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeInSearchInput(String text) {
        searchInput.type(text);
    }

    public SearchPage pressEnterInSearchInput() {
        searchInput.sendKeys(Keys.ENTER);
        return new SearchPage(getDriver());
    }

    public SignInPage clickOnSignInButton() {
        signInButton.click();
        return new SignInPage(getDriver());
    }

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public CartComponent clickOnOpenCartButton() {
        pause(2);
        cartButton.click();
        return cart;
    }
}
