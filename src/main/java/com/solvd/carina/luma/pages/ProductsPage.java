package com.solvd.carina.luma.pages;

import com.solvd.carina.luma.components.HeaderComponent;
import com.solvd.carina.luma.components.ProductCard;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class ProductsPage extends AbstractPage {
    @FindBy(css = ".page-header")
    private HeaderComponent headerComponent;

    @FindBy(css = ".base")
    private ExtendedWebElement textSearched;

    @FindBy(xpath = "//div[@class='product-item-info']")
    private List<ProductCard> products;

    @FindBy(css = "#sorter")
    private ExtendedWebElement sorterSelect;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTextSearched(){
        return textSearched.getText();
    }
    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }
    public List<ProductCard> getProducts(){
        return products;
    }
    public void sortByPrice(){
        sorterSelect.select("Price");
    }
    public void sortByName(){
        sorterSelect.select("Product Name");
    }
    public ProductCard getARandomProduct(){
        Random r = new Random();
        return products.get(r.nextInt(products.size()));
    }
}
