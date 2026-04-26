package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.*;

public class CategoryPage {

    WebDriver driver;
    WebDriverWait wait;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ✅ OPEN PAGE
    public void open() {
        driver.get("https://practicesoftwaretesting.com/category/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));
    }

    // 🔹 LOCATORS
    By products = By.cssSelector(".card");
    By productNames = By.cssSelector(".card-title");
    By prices = By.cssSelector(".card-text");

    By sortDropdown = By.cssSelector("[data-test='sort']");

    // 🔥 FIXED CATEGORY LOCATOR
    By categoryList = By.xpath("//a[contains(@href,'/category/')]");

    By brandCheckbox = By.xpath("//input[@type='checkbox']");

    By nextBtn = By.xpath("//button[@aria-label='Next']");

    // 🔹 PRODUCT COUNT
    public int getProductCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(products)).size();
    }

    // 🔹 SORT
    public void sortBy(String value) {

        Select select = new Select(wait.until(
                ExpectedConditions.elementToBeClickable(sortDropdown)));

        select.selectByValue(value);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(products));
    }

    // 🔹 GET PRICES
    public List<Double> getPrices() {

        List<WebElement> priceList = driver.findElements(prices);
        List<Double> values = new ArrayList<>();

        for (WebElement el : priceList) {
            String text = el.getText().replaceAll("[^0-9.]", "");
            if (!text.isEmpty()) {
                values.add(Double.parseDouble(text));
            }
        }
        return values;
    }

    // 🔹 SORT VALIDATION
    public boolean isSortedAscending(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) return false;
        }
        return true;
    }

    // 🔹 CATEGORY FILTER (FIXED)
    public void selectCategory(int index) {

        List<WebElement> categories = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(categoryList));

        if (categories.size() == 0) {
            throw new RuntimeException("No categories found");
        }

        WebElement category = categories.get(index);

        String beforeUrl = driver.getCurrentUrl();

        category.click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlToBe(beforeUrl)));

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(products));
    }

    // 🔹 BRAND FILTER
    public void selectBrand(int index) {

        List<WebElement> brands = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(brandCheckbox));

        WebElement checkbox = brands.get(index);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(products));
    }

    // 🔹 PAGINATION
    public void nextPage() {

        List<WebElement> before = driver.findElements(products);
        String first = before.get(0).getText();

        driver.findElement(nextBtn).click();

        wait.until(ExpectedConditions.stalenessOf(before.get(0)));

        List<WebElement> after = driver.findElements(products);

        if (after.get(0).getText().equals(first)) {
            throw new RuntimeException("Pagination failed");
        }
    }
}