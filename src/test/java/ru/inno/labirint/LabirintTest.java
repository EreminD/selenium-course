package ru.inno.labirint;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.labirint.block.BookCard;
import ru.inno.labirint.block.Chips;
import ru.inno.labirint.page.MainPage;
import ru.inno.labirint.page.SearchResultPage;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

@ExtendWith(SeleniumJupiter.class)
public class LabirintTest {

    @Test
    public void searchTest(ChromeDriver driver) {
        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(70, -1100));
        driver.get("https://labirint.ru");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));

        driver.findElement(cssSelector("#search-field")).sendKeys("Java", Keys.RETURN);

        driver.findElement(cssSelector("span.sorting-items")).click();
        driver.findElement(cssSelector("[data-event-content='высокий рейтинг']")).click();

        WebElement loader = driver.findElement(cssSelector("div.loading-panel"));
        new WebDriverWait(driver, ofSeconds(10)).until(stalenessOf(loader));

        List<WebElement> buttons = driver.findElements(cssSelector(".btn-tocart.buy-link"));

        for (WebElement button : buttons) {
            button.click();
        }

        String sizeToBe = Integer.toString(buttons.size());

        new WebDriverWait(driver, ofSeconds(5))
                .until(textToBe(cssSelector(".basket-in-cart-a"), sizeToBe));

        String sizeAsIs = driver.findElement(cssSelector(".basket-in-cart-a")).getText();

        assertEquals(sizeToBe, sizeAsIs);
    }

    @Test
    public void searchPOMTest(ChromeDriver driver) {
        MainPage mainPage = new MainPage(driver);
        SearchResultPage searchResultPage;

        mainPage.open();
        searchResultPage = mainPage.getHeader().search("Java");

        List<BookCard> books = searchResultPage
                .setSortingType("высокий рейтинг")
                .closeChips(Chips.NOT_AVAILABLE)
                .getAllBooks();

        for (BookCard book : books) {
            System.out.println(book.getTitle());
            book.addToCart();
        }

        int counter = searchResultPage.getHeader()
                .awaitCartCounterToBe(books.size())
                .getCartCounter();

        assertEquals(books.size(), counter);
    }
}
