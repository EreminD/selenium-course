package ru.inno.labirint.page.block;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Header {
    private final WebDriver driver;
    @FindBy(css = "#search-field")
    private WebElement searchInput;

    @FindBy(css = ".basket-in-cart-a")
    private WebElement cart;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public void search(String term) {
        this.searchInput.sendKeys(term, Keys.RETURN);
    }

    public void awaitCartHasNumber(int number) {
        new WebDriverWait(driver, ofSeconds(10))
                .until(textToBePresentInElement(cart, Integer.toString(number)));
    }

    public String getCartCounter() {
        return cart.getText();
    }
}
