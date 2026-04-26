package  tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class CategoryTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void navigateToCategory() throws InterruptedException {
        driver.get("https://practicesoftwaretesting.com/category/hand-tools");
        
    }

    @Test(description = "Validate sorting dropdown")
    public void testSortingDropdown() throws InterruptedException {
        WebElement sortDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='sort']")));
        Select select = new Select(sortDropdown);
       

        select.selectByVisibleText("Price (High - Low)");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Price (High - Low)");
        Thread.sleep(2000);
    }

    @Test(description = "Validate filters by category and brand")
    public void testFilters() throws InterruptedException {
        // Filter by Category (e.g., Hammer)
        WebElement hammerFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Hammer')]")));
        hammerFilter.click();
        Thread.sleep(2000);
        // Filter by Brand
        WebElement brandFilter = driver.findElement(By.xpath("//label[contains(text(),'ForgeFlex')]"));
        brandFilter.click();
        Thread.sleep(2000);
        // Wait for results to refresh (Spinner to disappear)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));
        
        List<WebElement> results = driver.findElements(By.cssSelector(".card"));
        Thread.sleep(2000);
        Assert.assertTrue(results.size() > 0, "No products found for selected filters!");
    }

    @Test(description = "Validate top-level navigation")
    public void testNavigation() throws InterruptedException {
        WebElement homeNav = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        homeNav.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicesoftwaretesting.com/");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        if (driver != null) {
        	Thread.sleep(3000);
            driver.quit();
        }
    }
}