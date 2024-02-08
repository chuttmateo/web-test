package com.solvd.carina.amazon.pages;

import com.solvd.carina.amazon.components.LanguageItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;


public class ChangeLanguagePage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'a-row a-spacing-mini')][*]")
    private List<LanguageItem> languageItems;
    @FindBy(xpath = "//input[@class='a-button-input' and @type='submit']")
    private ExtendedWebElement saveChangesBtn;
    public ChangeLanguagePage(WebDriver driver) {
        super(driver);
    }
    public void clickAll(){
        languageItems.forEach(LanguageItem::clickOnItemButton);
    }
    public void clickOnValue(String value){
        Optional<LanguageItem> divToClick = languageItems.stream()
                .filter(div -> div.getValue().equals(value))
                .findAny();
        divToClick.ifPresent(LanguageItem::clickOnItemButton);
    }
    public void clickOnSaveChangesBtn(){
        saveChangesBtn.click();
    }
}
