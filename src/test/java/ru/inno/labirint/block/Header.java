package ru.inno.labirint.block;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.labirint.page.SearchResultPage;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Header {
    private final static By searchInput = cssSelector("#search-field");
    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public SearchResultPage search(String term) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(term, Keys.RETURN);
        return new SearchResultPage(driver);
    }

    public Header awaitCartCounterToBe(int number) {
        new WebDriverWait(driver, ofSeconds(5))
                .until(textToBe(cssSelector(".basket-in-cart-a"), Integer.toString(number)));
        return this;
    }

    public int getCartCounter() {
        String text = driver.findElement(cssSelector(".basket-in-cart-a")).getText();
        return Integer.parseInt(text);
    }
}
