package tests;

import base.Basetest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Language;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Languagetest extends Basetest {

    @Test(description = "Verify language switching works for Bootstrap dropdown")
    public void testLanguageSwitching() {
        
        Language languagePage = new Language(driver);

        // Perform the switch
        languagePage.selectGermanLanguage();

        // Validation: Wait for URL to update to include 'de'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isGermanUrl = wait.until(ExpectedConditions.urlContains("de"));

        Assert.assertTrue(isGermanUrl, "URL did not update to German! Current URL: " + driver.getCurrentUrl());
        
        System.out.println("Test Passed: Language switched to German successfully.");
    }
}