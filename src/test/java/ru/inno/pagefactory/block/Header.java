package ru.inno.pagefactory.block;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.pagefactory.page.SearchResultPage;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Header {
    @FindBy(css = "#search-field")
    @CacheLookup
    private WebElement searchInput;
    @FindBy(css = ".basket-in-cart-a")
    private WebElement cartIcon;

    private final WebDriver driver;
    private By cartIconLocator = cssSelector(".basket-in-cart-a");;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Выполнить поиск по слову '{term}'")
    public SearchResultPage search(String term) {
        searchInput.clear();
        searchInput.sendKeys(term, Keys.RETURN);
        return new SearchResultPage(driver);
    }

    public Header awaitCartCounterToBe(int number) {
        new WebDriverWait(driver, ofSeconds(5)).until(textToBe(cartIconLocator, Integer.toString(number)));
        return this;
    }

    public int getCartCounter() {
        String text = cartIcon.getText();
        return Integer.parseInt(text);
    }
}
