package com.solvd.carina.amazon.pages;

import com.solvd.carina.amazon.components.HeaderComponent;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//header")
    private HeaderComponent headerComponent;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeaderComponent(){
        return headerComponent;
    }
}
