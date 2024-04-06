package com.solvd.carina.saucedemo.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPage {
    @FindBy(id = "password")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "user-name")
    private ExtendedWebElement usernameInput;

    @FindBy(id = "login-button")
    private ExtendedWebElement logInButton;
    public LogInPage(WebDriver driver) {
        super(driver);
    }
    public void typePassword(String password){
        passwordInput.type(password);
    }
    public void typeUsername(String username){
        usernameInput.type(username);
    }
    public ProductsPage clickOnLogInButton(){
        logInButton.click();
        return new ProductsPage(getDriver());
    }
}