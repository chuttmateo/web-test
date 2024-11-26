package com.solvd.carina.luma.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends AbstractPage {

    @FindBy(xpath = "//label[@for='email']/following-sibling::div/input") // i used xpath just for practicing
    private ExtendedWebElement emailInput;

    @FindBy(css = "#pass")
    private ExtendedWebElement passInput;

    @FindBy(xpath = "//span[text()='Sign In']/parent::button")
    private ExtendedWebElement signInButton;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email){
        emailInput.type(email);
    }

    public void typePass(String pass){
        passInput.type(pass);
    }

    public void clickOnSignInButton(){
        signInButton.click();
    }
}
