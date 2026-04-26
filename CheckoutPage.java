package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;

    // Locators
    private By cartRows = By.cssSelector("tr"); 
    private By proceedButton = By.cssSelector("[data-test='proceed-1']");
    private By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCartItems() throws InterruptedException {
        Thread.sleep(2000); // Wait for table to render
        return driver.findElements(cartRows);
    }

    public void clickProceed() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(proceedButton).click();
        Thread.sleep(2000); // Wait for navigation animation
    }

    public void clickContinueShopping() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(continueShoppingButton).click();
        Thread.sleep(2000);
    }
} 