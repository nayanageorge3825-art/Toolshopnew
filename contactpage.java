package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class contactpage {

    WebDriver driver;
    WebDriverWait wait;

    public contactpage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By email = By.id("email");
    By subject = By.id("subject");
    By message = By.id("message");
    By fileUpload = By.cssSelector("input[type='file']");
    By submitBtn = By.cssSelector("button[type='submit']");

    public void open() {
        driver.get("https://practicesoftwaretesting.com/contact");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
    }

    public void fillForm(String fn, String ln, String mail, String sub, String msg) {

        driver.findElement(firstName).sendKeys(fn);
        driver.findElement(lastName).sendKeys(ln);
        driver.findElement(email).sendKeys(mail);

        // ✅ Correct dropdown handling
        Select dropdown = new Select(driver.findElement(subject));
        dropdown.selectByVisibleText(sub);

        driver.findElement(message).sendKeys("msg");
    }

    public void uploadFile(String path) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileUpload));
        driver.findElement(fileUpload).sendKeys(path);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.getPageSource().contains("success");
    }
    
}