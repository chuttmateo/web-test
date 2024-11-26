package com.solvd.carina.luma;

import com.solvd.carina.luma.components.CartComponent;
import com.solvd.carina.luma.pages.LumaHomePage;
import com.solvd.carina.luma.pages.ProductsDetailsPage;
import com.solvd.carina.luma.pages.SearchPage;
import com.solvd.carina.luma.pages.SignInPage;
import com.solvd.carina.luma.components.ProductsCard;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class WebLumaTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "mchutt")
    public void testSearchSomething(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();

        boolean pageOpened = page.isPageOpened();
        Assert.assertTrue(pageOpened, "Page is not opened");

        page.getHeaderComponent().typeInSearchInput("books");
        SearchPage searchPage = page.getHeaderComponent().pressEnterInSearchInput();
        String se = searchPage.getTextSearched();

        Assert.assertEquals(se, "Search results for: 'books'");
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testFilterByPrice(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();

        boolean pageOpened = page.isPageOpened();
        Assert.assertTrue(pageOpened, "Page is not opened");

        SearchPage searchPage = page.getARandomOfferCard().clickOnLink();
        searchPage.sortByPrice();

        List<ProductsCard> products = searchPage.getProducts();


        boolean isSorted = products.stream()
                .map(ProductsCard::getPrice)
                .sorted()
                .collect(Collectors.toList())
                .equals(products.stream()
                        .map(ProductsCard::getPrice)
                        .collect(Collectors.toList()));

        System.out.println(isSorted);

        Assert.assertTrue(isSorted, "Products aren't sorted by price");

    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testSingIn(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();

        String pass = "Pepepepe12";
        String email = "pepepepe@pepe.com";

        SignInPage signInPage = page.getHeaderComponent().clickOnSignInButton();
        signInPage.typeEmail(email);
        signInPage.typePass(pass);
        signInPage.clickOnSignInButton();


        String welcomeMessage = page.getHeaderComponent().getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, "Welcome, pepe pepe!");
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testSortProductsByName(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();

        SearchPage searchPage = page.getARandomOfferCard().clickOnLink();
        searchPage.sortByName();

        List<String> productsName = searchPage.getProducts().stream()
                .map(ProductsCard::getName)
                .collect(Collectors.toList());

        List<String> sortedByName = productsName.stream()
                .sorted()
                .collect(Collectors.toList());


        boolean equals = sortedByName.equals(productsName);

        Assert.assertTrue(equals, "The products aren't sorted by name");

    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testProductDetailsPage(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();

        SearchPage searchPage = page.getARandomOfferCard().clickOnLink();
        ProductsCard randomProduct = searchPage.getARandomProduct();
        String productName = randomProduct.getName();

        ProductsDetailsPage productsDetailsPage = randomProduct.clickOnDetails();

        boolean equals = productName.equals(productsDetailsPage.getProductName());

        Assert.assertTrue(equals,"product name doesn't match with the expected one");

    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void addProductsToCartTest(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();

        SearchPage searchPage = page.getARandomOfferCard().clickOnLink();
        ProductsCard productCard = searchPage.getProducts().get(0);
        String productName = productCard.getName();
        ProductsDetailsPage productsDetailsPage = productCard.clickOnDetails();

        productsDetailsPage.clickOnARandomProductSize();
        productsDetailsPage.clickOnARandomProductColor();
        productsDetailsPage.clickOnAddToCartButton();

        CartComponent cartComponent = productsDetailsPage.getHeaderComponent().clickOnOpenCartButton();
        String cartItemName = cartComponent.getItems().get(0).getItemName();


        Assert.assertEquals(cartItemName, productName, "The product added to the cart doesn't appears, or the one that appears doesn't match with the expected one");
    }

}