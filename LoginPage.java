package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By email = By.id("email");
    By password = By.id("password");
    By loginBtn = By.xpath("//input[@value='Login']");

    // Actions
    public void openLoginPage() {
        driver.get("https://practicesoftwaretesting.com/auth/login");
    }

    public void login(String user, String pass) {
        driver.findElement(email).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }
}