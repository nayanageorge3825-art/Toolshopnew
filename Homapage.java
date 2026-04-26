package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Homapage {

    WebDriver driver;

    public Homapage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By searchBox = By.cssSelector("[data-test='search-query']");
    By searchBtn = By.cssSelector("[data-test='search-submit']");
    By sortDropdown = By.cssSelector("[data-test='sort']");
    By products = By.cssSelector(".card-title");
    By firstProduct = By.cssSelector(".card-title");
    By addToCartBtn = By.cssSelector("[data-test='add-to-cart']");

    // Search
    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
    }

    // Sorting (High to Low Price)
    public void sortHighToLow() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue("price,desc");   // IMPORTANT
    }

    // Click first product
    public void clickFirstProduct() {
        driver.findElements(firstProduct).get(0).click();
    }

    // Add to cart
    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }

    // Product count
    public int getProductCount() {
        return driver.findElements(products).size();
    }
}