package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Language {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    // 1. The main button you provided
    By languageButton = By.id("language"); 
    
    // 2. The German option inside the dropdown (Common Bootstrap pattern)
    // We search for the link that contains 'DE' or has a specific data-test attribute
    By germanOption = By.xpath("//a[contains(text(),'DE') or @data-test='nav-lang-de']");

    public Language(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void selectGermanLanguage() {
        // Step 1: Wait and scroll to the button
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(languageButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        // Step 2: Use Thread.sleep briefly if the site has heavy animations (as requested)
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        // Step 3: Click the button to open dropdown
        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        System.out.println("Language dropdown clicked.");

        // Step 4: Wait for German option to be visible and click it
        WebElement deOption = wait.until(ExpectedConditions.elementToBeClickable(germanOption));
        deOption.click();
        System.out.println("German (DE) language selected.");
    }
}