package com.solvd.carina.ecommerce;

import com.solvd.carina.ecommerce.components.Cart;
import com.solvd.carina.ecommerce.components.CartProduct;
import com.solvd.carina.ecommerce.components.HomeProduct;
import com.solvd.carina.ecommerce.pages.HomePage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WebTest implements IAbstractTest {
    @Test
    @MethodOwner(owner = "mchutt")
    public void verifyCheckoutTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        HomeProduct p1 = homePage.getRandomProduct();
        p1.clickOnAddToCart();


        Cart cart = homePage.getCart();
        List<CartProduct> cartProducts = cart.getAllProducts();
        boolean isProductPresentInCart = cartProducts.stream()
                .anyMatch(cartProduct -> cartProduct.getProductName().equals(p1.getProductName()));


        Assert.assertTrue(isProductPresentInCart, String.format("Product: %s is not present in the cart", p1.getProductName()));
    }
}
