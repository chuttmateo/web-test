package com.solvd.carina.luma;

import com.solvd.carina.luma.components.CartComponent;
import com.solvd.carina.luma.components.WishListProductCard;
import com.solvd.carina.luma.pages.*;
import com.solvd.carina.luma.components.ProductCard;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.apache.commons.collections.CollectionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WebLumaTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "mchutt")
    public void testSearchSomething(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "LumaHomePage is not opened!");

        page.getHeaderComponent().typeInSearchInput("books");
        ProductsPage productsPage = page.getHeaderComponent().pressEnterInSearchInput();
        String se = productsPage.getTextSearched();

        Assert.assertEquals(se, "Search results for: 'books'");
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testFilterByPrice(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "LumaHomePage is not opened!");

        boolean pageOpened = page.isPageOpened();
        Assert.assertTrue(pageOpened, "Page is not opened");

        ProductsPage productsPage = page.getARandomOfferCard().clickOnLink();
        productsPage.sortByPrice();

        List<ProductCard> products = productsPage.getProducts();


        boolean isSorted = products.stream()
                .map(ProductCard::getPrice)
                .sorted()
                .collect(Collectors.toList())
                .equals(products.stream()
                        .map(ProductCard::getPrice)
                        .collect(Collectors.toList()));

        System.out.println(isSorted);

        Assert.assertTrue(isSorted, "Products aren't sorted by price");

    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testSingIn(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "LumaHomePage is not opened!");

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
        Assert.assertTrue(page.isPageOpened(), "LumaHomePage is not opened!");

        ProductsPage productsPage = page.getARandomOfferCard().clickOnLink();
        productsPage.sortByName();

        List<String> productsName = productsPage.getProducts().stream()
                .map(ProductCard::getName)
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

        ProductsPage productsPage = page.getARandomOfferCard().clickOnLink();
        ProductCard randomProduct = productsPage.getARandomProduct();
        String productName = randomProduct.getName();

        ProductDetailsPage productDetailsPage = randomProduct.clickOnDetails();

        boolean equals = productName.equals(productDetailsPage.getProductName());

        Assert.assertTrue(equals,"product name doesn't match with the expected one");

    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void addProductsToCartTest(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "LumaHomePage is not opened!");

        ProductsPage productsPage = page.getARandomOfferCard().clickOnLink();
        List<ProductCard> products = productsPage.getProducts();
        Assert.assertFalse(CollectionUtils.isEmpty(products), "Products not found!");

        //Get a random product and open its details page
        ProductCard productCard = products.get(0);
        String productName = productCard.getName();
        ProductDetailsPage productDetailsPage = productCard.clickOnDetails();
        //pause(3);
        Assert.assertTrue(productDetailsPage.isPageOpened(), "Product details page is no opened!");

        //Add product to the cart
        productDetailsPage.clickOnARandomProductSize();
        productDetailsPage.clickOnARandomProductColor();
        productDetailsPage.clickOnAddToCartButton();

        //Open cart and verify that the product was added correctly
        CartComponent cartComponent = productDetailsPage.getHeaderComponent().clickOnOpenCartButton();
        String cartItemName = cartComponent.getItems().get(0).getItemName();


        Assert.assertEquals(cartItemName, productName, "The product added to the cart doesn't appears, or the one that appears doesn't match with the expected one");
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void testAddProductToWishList(){
        LumaHomePage page = new LumaHomePage(getDriver());
        page.open();
        Assert.assertTrue(page.isPageOpened(), "LumaHomePage is not opened!");

        String pass = "Pepepepe12";
        String email = "pepepepe@pepe.com";

        SignInPage signInPage = page.getHeaderComponent().clickOnSignInButton();
        signInPage.typeEmail(email);
        signInPage.typePass(pass);
        signInPage.clickOnSignInButton();

        String productName = "Hero Hoodie";
        page.getHeaderComponent().typeInSearchInput(productName);
        ProductsPage productsPage = page.getHeaderComponent().pressEnterInSearchInput();

        //Add product to wish list
        ProductDetailsPage productDetailsPage = productsPage.getProducts().get(0).clickOnDetails();
        CustomerPage customerPage = productDetailsPage.clickOnAddToWishListButton();

        Optional<WishListProductCard> product = customerPage.getWishListProducts().stream()
                .filter(p -> p.getProductName().equals(productName))
                .findFirst();

        Assert.assertTrue(product.isPresent(), "Product is not present in the wish list");

        //remove product from wish list
        product.get().clickOnRemoveButton();
        String productRemovedMessage = customerPage.getProductRemovedMessage();

        Assert.assertEquals(productRemovedMessage, productName + " has been removed from your Wish List.", "Product removed and alert message do not match");
    }

}