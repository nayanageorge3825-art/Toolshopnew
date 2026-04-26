package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CheckoutPage;

public class CheckoutTest {
    WebDriver driver;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // 1. Navigate and Add Item (Prevents the 'Empty Cart' error)
        driver.get("https://practicesoftwaretesting.com");
        Thread.sleep(3000); 
        
        driver.findElement(By.cssSelector(".card")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("btn-add-to-cart")).click();
        Thread.sleep(2000);
        
        // 2. Go to Checkout
        driver.get("https://practicesoftwaretesting.com/checkout");
        checkoutPage = new CheckoutPage(driver);
        Thread.sleep(2000);
    }

    @Test
    public void testCheckoutWorkflow() throws InterruptedException {
        // Check if items exist
        int itemCount = checkoutPage.getCartItems().size();
        System.out.println("Items found in cart: " + itemCount);
        
        if (itemCount > 0) {
            System.out.println("Cart is not empty, proceeding to checkout...");
            checkoutPage.clickProceed();
            
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("checkout"), "Navigation failed!");
        } else {
            System.out.println("Cart is empty, clicking Continue Shopping...");
            checkoutPage.clickContinueShopping();
            
            Thread.sleep(2000);
            Assert.assertTrue(driver.getCurrentUrl().equals("https://practicesoftwaretesting.com/"), 
                "Did not return to home page");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}