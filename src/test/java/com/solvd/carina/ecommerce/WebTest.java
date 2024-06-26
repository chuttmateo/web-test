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
import java.util.Set;
import java.util.stream.Collectors;

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
        cart.clickOnCheckoutButton();
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void verifyCheckoutTwoProductsTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        Set<HomeProduct> products = homePage.getRandomProducts(2);
        //add products to cart and collect names in a set
        Set<String> homeProductNames = products.stream()
                .peek(homeProduct -> {
                    homeProduct.clickOnAddToCart();
                    //closing cart after add a product in order to solve this error "element click intercepted: Element <button tabindex="-1" class="sc-124al1g-0 jCsgpZ">...</button> is not clickable at point (1152, 594). Other element would receive the click: <div class="sc-1h98xa9-12 fqwdnu">...</div>"
                    homePage.getCart().clickOnCloseCartButton();
                })
                .map(HomeProduct::getProductName)
                .collect(Collectors.toSet());

        //get cart products and collect their names in a set
        Cart cart = homePage.clickOnOpenCartButton();
        Set<String> cartProductName = cart.getAllProducts().stream()
                .map(CartProduct::getProductName)
                .collect(Collectors.toSet());

        //verify if all products are present in cart Component
        Assert.assertEquals(cartProductName, homeProductNames, "Products in cart do not match products added to cart");
    }

    @Test
    @MethodOwner(owner = "mchutt")
    public void verifyAddTheSameProductTwiceTest(){
        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        //Add the same product twice into the cart
        HomeProduct randomProduct = homePage.getRandomProduct();
        Assert.assertNotNull(randomProduct, "No product is present on the home page");

        randomProduct.clickOnAddToCart();
        homePage.getCart().clickOnCloseCartButton();
        randomProduct.clickOnAddToCart();

        //Verify quantity
        List<CartProduct> allProducts = homePage.getCart().getAllProducts();
        CartProduct cartProduct = allProducts.get(0);
        Assert.assertNotNull(cartProduct, "Product is not present into the cart");
        boolean endsWithTwo = cartProduct.getProductQuantity().endsWith("2");
        Assert.assertTrue(endsWithTwo, "Product does not have two for quantity");
    }

}
