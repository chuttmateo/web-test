package com.solvd.carina.saucedemo;

import com.solvd.carina.saucedemo.components.CartProduct;
import com.solvd.carina.saucedemo.components.Product;
import com.solvd.carina.saucedemo.pages.CartPage;
import com.solvd.carina.saucedemo.pages.LogInPage;
import com.solvd.carina.saucedemo.pages.ProductsPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTest extends AbstractTest {

    @Test
    @MethodOwner(owner = "mchutt")
    public void verifyProductInCartTest(){
        LogInPage page = new LogInPage(getDriver());
        //logIn
        page.open();
        page.typeUsername("standard_user");
        page.typePassword("secret_sauce");
        ProductsPage productsPage = page.clickOnLogInButton();

        //Get random product -> add to cart -> verify if is present in cartPage.
        Product product = productsPage.getRandomProduct();
        String productName = product.getProductName();
        product.clickOnAddToCartButton();
        CartPage cartPage = productsPage.clickOnCartPage();

        boolean isProductPresent = cartPage.isProductPresent(productName);
        Assert.assertTrue(isProductPresent, String.format("Product with name: %s is not present in cart page", productName));
    }
}
