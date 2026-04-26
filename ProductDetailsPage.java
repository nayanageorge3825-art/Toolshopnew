package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By productName = By.cssSelector("[data-test='product-name']");
    private By productPrice = By.cssSelector("[data-test='unit-price']");
    private By productDescription = By.cssSelector("[data-test='product-description']");
    private By productImage = By.cssSelector("img.figure-img");
    
    // Specifications (Product Information Table)
    private By specTable = By.cssSelector(".table"); 
    
    // Related Products - Using a more reliable XPath
    private By relatedHeader = By.xpath("//h2[contains(text(),'Related products')]");
    private By relatedCards = By.xpath("//h2[contains(text(),'Related products')]/following-sibling::div//a");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    public String getPrice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice)).getText();
    }

    public String getDescription() {
        return driver.findElement(productDescription).getText();
    }

    public boolean isImageDisplayed() {
        try {
            WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated(productImage));
            return img.isDisplayed() && !img.getAttribute("src").contains("placeholder");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areSpecificationsPresent() {
        return driver.findElements(specTable).size() > 0;
    }

    public int getRelatedProductsCount() {
        try {
            // Using a shorter wait for the optional related products section
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.presenceOfElementLocated(relatedHeader));
            return driver.findElements(relatedCards).size();
        } catch (TimeoutException e) {
            return 0; // Return 0 instead of failing if not present
        }
    }
}