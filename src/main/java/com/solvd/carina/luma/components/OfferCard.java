package com.solvd.carina.luma.components;

import com.solvd.carina.luma.pages.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class OfferCard extends AbstractUIObject {

    @FindBy(xpath = ".")
    private ExtendedWebElement offerLink;

    public OfferCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public SearchPage clickOnLink(){
        offerLink.clickIfPresent();
        return new SearchPage(getDriver());
    }
}
