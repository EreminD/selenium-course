package ru.inno.pagefactory.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.pagefactory.block.BookCard;
import ru.inno.pagefactory.block.Chips;

import java.util.LinkedList;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class SearchResultPage extends Page {

    @FindBy(css = ".product-card")
    private List<WebElement> cards;
    @FindBy(css = "span.sorting-value.menu-open")
    private WebElement sortMenu;
    @FindBy(css = "div.loading-panel")
    private WebElement loader;
    @FindBy(css = ".filter-reset")
    private List<WebElement> chips;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу поиска")
    public void open() {
        driver.get(BASE_URL + "/search");
    }

    @Step("Установить сортировку товаров по '{sortType}'")
    public SearchResultPage setSortingType(String sortType) {
        sortMenu.click();
        driver.findElement(cssSelector("[data-event-content='" + sortType + "']")).click();
        waitLoader();
        return this;
    }

    @Step("Удалить фильтр '{label.text}'")
    public SearchResultPage closeChips(Chips label) {
        for (WebElement chip : chips) {
            String txt = chip.getText();
            if (txt.equalsIgnoreCase(label.getText())) {
                chip.findElement(cssSelector(".filter-reset__icon")).click();
                waitLoader();
                break;
            }
        }
        return this;
    }

    @Step("Взять все книги на странице")
    public List<BookCard> getAllBooks() {
        List<BookCard> books = new LinkedList<>();
        for (WebElement element : cards) {
            BookCard book = new BookCard(element);
            books.add(book);
        }
        return books;
    }

    @Step("Дождаться, когда пропадет спиннер")
    private void waitLoader() {
        new WebDriverWait(driver, ofSeconds(10)).until(invisibilityOf(loader));
    }
}
