package com.solvd.carina.luma.pages;

import com.solvd.carina.luma.components.HeaderComponent;
import com.solvd.carina.luma.components.OfferCard;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class LumaHomePage extends AbstractPage {
    @FindBy(css = ".page-header")
    private HeaderComponent headerComponent;

    @FindBy(xpath = "//span[contains(text(), 'Shop ')]/ancestor::a") //
    private List<OfferCard> offerCards;

    public LumaHomePage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public OfferCard getARandomOfferCard(){
        Random random = new Random();
        return offerCards.get(random.nextInt(offerCards.size()));
    }
}
