package com.solvd.carina.amazon.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ResultsPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='sg-col-inner']//div//span[3]")
    private ExtendedWebElement resultsText;
    public ResultsPage(WebDriver driver) {
        super(driver);
    }
    public String getSearchedText(){
        return cleanText(resultsText.getText());
    }

    /**
     *
     * @param text sample input "Iphone"
     * @return sample output Iphone without ""
     */
    public String cleanText(String text){
        char[] charArray = text.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < charArray.length -1; i++) {
            result.append(charArray[i]);
        }
        return result.toString();
    }
}
