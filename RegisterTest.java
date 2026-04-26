package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTest {

    @Test
    public void registerCustomer() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://practicesoftwaretesting.com/auth/register");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));

        // Fill form
        driver.findElement(By.id("first_name")).sendKeys("John");
        driver.findElement(By.id("last_name")).sendKeys("Doe");
        driver.findElement(By.id("dob")).sendKeys("1995-03-02");

        String email = "user" + System.currentTimeMillis() + "@test.com";
        driver.findElement(By.id("email")).sendKeys(email);

        driver.findElement(By.id("street")).sendKeys("Kochi");
        driver.findElement(By.id("postal_code")).sendKeys("682020");
        driver.findElement(By.id("city")).sendKeys("Ernakulam");
        driver.findElement(By.id("state")).sendKeys("Kerala");
        driver.findElement(By.id("phone")).sendKeys("9876543210");

        // ✅ COUNTRY DROPDOWN (correct way)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("country")));
        Select country = new Select(driver.findElement(By.id("country")));
        country.selectByVisibleText("India");

        // Password
        driver.findElement(By.id("password")).sendKeys("Pass@1234");

        // ✅ Register button (correct locator)
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".btnSubmit"))).click();

        // ✅ Validation
        wait.until(ExpectedConditions.urlContains("login"));

        System.out.println("✅ Registration Successful");

        driver.quit();
    }
}