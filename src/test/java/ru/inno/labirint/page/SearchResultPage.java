package ru.inno.labirint.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.labirint.elements.Chip;
import ru.inno.labirint.page.block.BookCard;
import ru.inno.labirint.page.block.Header;

import java.util.LinkedList;
import java.util.List;

import static java.time.Duration.ofSeconds;
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
        this.header = PageFactory.initElements(driver, Header.class);
    }

    public List<BookCard> getResults() {
        List<BookCard> results = new LinkedList<>();
        for (WebElement card : this.cards) {
            results.add(new BookCard(card));
        }
        return results;
    }

    public void setSorting(String sortType) {
        sortMenu.click();
        driver.findElement(By.cssSelector("a[data-event-content='" + sortType + "']")).click();
        waitLoader();
    }

    public void clickChips(String text) {
        for (WebElement element : chips) {
            Chip chip = new Chip(element);
            String label = chip.getLabel();
            System.out.println(label);
            if (label.equalsIgnoreCase(text)) {
                chip.clickCross();
                waitLoader();
                break;
            }
        }
    }

    private void waitLoader() {
        new WebDriverWait(driver, ofSeconds(10)).until(invisibilityOf(loader));
    }
}
