package com.solvd.carina.amazon.components;

import com.solvd.carina.amazon.pages.ChangeLanguagePage;
import com.solvd.carina.amazon.pages.ResultsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends AbstractUIObject {
    @FindBy(css = "#twotabsearchtextbox")
    private ExtendedWebElement searchInput;
    @FindBy(css = "#nav-search-submit-button")
    private ExtendedWebElement submitBtn;
    @FindBy(xpath = ".//span[@class='icp-nav-link-inner']")
    private ExtendedWebElement changeLanguageBtn;
    @FindBy(xpath = ".//span[@class='nav-line-2']//div")
    private ExtendedWebElement language;
    public HeaderComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
    public void typeSearch(String text){
        searchInput.type(text);
    }
    public ResultsPage clickOnSubmitBtn(){
        submitBtn.click();
        return new ResultsPage(getDriver());
    }
    public ChangeLanguagePage clickOnChangeLanguageBtn(){
        changeLanguageBtn.click();
        return new ChangeLanguagePage(getDriver());
    }
    public String getLanguage(){
        return language.getText();
    }

}
