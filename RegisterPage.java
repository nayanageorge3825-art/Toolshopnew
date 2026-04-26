package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Fields
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By email = By.id("email");
    By password = By.id("password");
    By registerBtn = By.xpath("//input[@value='Register']");



    public void open() {
        driver.get("https://practicesoftwaretesting.com/auth/register");
    }

    public void register(String f, String l, String e, String p) {

        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(f);

        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(l);

        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(e);

        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(p);

        driver.findElement(registerBtn).click();
    }

    // Validation checks
   
}