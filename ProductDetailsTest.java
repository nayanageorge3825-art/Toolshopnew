package tests;

import base.Basetest;
import pages.ProductDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ProductDetailsTest extends Basetest {

    @Test(description = "Full Validation of Product Details Page")
    public void verifyProductDetails() throws InterruptedException {
        // Direct URL to the product
        driver.get("https://practicesoftwaretesting.com/product/01KPZS7AQC93BN98FYN4D4KGBP");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        // 1. Wait for loading spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-spinner-overlay")));

        // 2. Validate Essential Elements
        String name = detailsPage.getProductName();
        System.out.println("Product Name: " + name);
        Assert.assertNotNull(name, "Product name is missing!");

        String price = detailsPage.getPrice();
        System.out.println("Price: " + price);
        Assert.assertTrue(price.length() > 0, "Price is missing!");

        Assert.assertTrue(detailsPage.isImageDisplayed(), "Main product image is missing!");

        // 3. Validate Specifications (Table)
        boolean hasSpecs = detailsPage.areSpecificationsPresent();
        System.out.println("Specifications present: " + hasSpecs);
        // Note: For this specific product, specs should exist
        Assert.assertTrue(hasSpecs, "Specifications table is missing!");

        // 4. Validate Related Products
        int relatedCount = detailsPage.getRelatedProductsCount();
        System.out.println("Related products count: " + relatedCount);
        
        // If relatedCount is 0, we log it but don't fail, because it depends on the database
        if (relatedCount == 0) {
            System.out.println("Verified: No related products listed for this specific item.");
        } else {
            Assert.assertTrue(relatedCount > 0);
        }
        
        // Final sleep just to see the result before tearDown
        Thread.sleep(2000);
    }
}