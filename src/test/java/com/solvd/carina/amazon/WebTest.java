package com.solvd.carina.amazon;

import com.solvd.carina.amazon.components.HeaderComponent;
import com.solvd.carina.amazon.pages.ChangeLanguagePage;
import com.solvd.carina.amazon.pages.HomePage;
import com.solvd.carina.amazon.pages.ResultsPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WebTest implements IAbstractTest {

    @DataProvider(name = "productsNameProvider")
    @MethodOwner(owner = "mchutt")
    public Object[][] productsNameProvider(){
        return new Object[][]{
                {"Iphone"},
                {"PC"}
        };
    }

    @Test(dataProvider = "productsNameProvider")
    @MethodOwner(owner = "mchutt")
    public void searchSomethingTest(String productName){
        HomePage page = new HomePage(getDriver());
        page.open();

        HeaderComponent headerComponent = page.getHeaderComponent();
        headerComponent.typeSearch(productName);
        ResultsPage resultsPage = headerComponent.clickOnSubmitBtn();
        boolean result = resultsPage.searchedTextEqualsToResultText(productName);

        Assert.assertTrue(result);
    }

    @DataProvider
    public Object[][] languagesProvider(){
        return new Object[][]{
                {"de_DE", "DE"},
                {"en_US", "EN"},
                {"es_US", "ES"},
        };
    }

    @Test(dataProvider = "languagesProvider")
    @MethodOwner(owner = "mchutt")
    public void changeLanguageTest(String languageLocale, String languageCode){
        HomePage page = new HomePage(getDriver());
        page.open();

        HeaderComponent headerComponent = page.getHeaderComponent();
        ChangeLanguagePage changeLanguagePage = headerComponent.clickOnChangeLanguageBtn();
        changeLanguagePage.clickOnValue(languageLocale);
        changeLanguagePage.clickOnSaveChangesBtn();

        Assert.assertEquals(headerComponent.getLanguage(), languageCode);
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void verifyTitleValueTest(){
        WebDriver driver = getDriver();
        HomePage page = new HomePage(driver);
        page.open();

        String title = page.getTitle();
        Assert.assertEquals(title,"Amazon.com");
    }
}
