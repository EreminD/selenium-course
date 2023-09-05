package ru.inno.labirint.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.labirint.block.BookCard;
import ru.inno.labirint.block.Chips;

import java.util.LinkedList;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class SearchResultPage extends Page {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/search");
    }

    public SearchResultPage setSortingType(String sortType) {
        driver.findElement(cssSelector("span.sorting-items")).click();
        driver.findElement(cssSelector("[data-event-content='" + sortType + "']")).click();
        waitLoader();
        return this;
    }

    public SearchResultPage closeChips(Chips label) {
        List<WebElement> chips = driver.findElements(cssSelector(".filter-reset"));
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

    public List<BookCard> getAllBooks() {
        List<BookCard> books = new LinkedList<>();
        List<WebElement> cards = driver.findElements(cssSelector(".product-card"));
        for (WebElement element : cards) {
            BookCard book = new BookCard(element);
            books.add(book);
        }
        return books;
    }

    private void waitLoader() {
        WebElement loader = driver.findElement(cssSelector("div.loading-panel"));
        new WebDriverWait(driver, ofSeconds(10)).until(stalenessOf(loader));
    }
}
