package ru.inno.labirint;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.inno.labirint.page.MainPage;
import ru.inno.labirint.page.SearchResultPage;
import ru.inno.labirint.page.block.BookCard;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

@ExtendWith(SeleniumJupiter.class)
public class SearchTest {
    private MainPage mainPage;
    private SearchResultPage searchResultPage;
    private WebDriver browser;


    @BeforeEach
    public void setPages() {
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        mainPage = PageFactory.initElements(browser, MainPage.class);
        searchResultPage = PageFactory.initElements(browser, SearchResultPage.class);
    }

    @AfterEach
    public void tearDown() {
        if (browser != null) {
            browser.quit();
        }
    }

    @Test
    public void searchTest(ChromeDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(4));
        driver.get("https://www.labirint.ru");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);

        driver.findElement(By.cssSelector("#search-field")).sendKeys("Java", Keys.RETURN);

        driver.findElement(By.cssSelector("span.sorting-value.menu-open")).click();
        driver.findElement(By.cssSelector("a[data-event-content='высокий рейтинг']")).click();

        WebElement loader = driver.findElement(By.cssSelector("div.loading-panel"));
        new WebDriverWait(driver, ofSeconds(10)).until(stalenessOf(loader));

        List<WebElement> buttons = driver.findElements(By.cssSelector(".buy-link"));

        int counter = 0;
        for (WebElement button : buttons) {
            counter++;
            button.click();
        }

        new WebDriverWait(driver, ofSeconds(10))
                .until(textToBe(By.cssSelector(".basket-in-cart-a"), Integer.toString(counter)));

        String cart = driver.findElement(By.cssSelector(".basket-in-cart-a")).getText();

        System.out.println(counter);
        assertEquals(counter + "", cart);
    }

    @Test
    public void pageTest() {
        //зайти на главную
        mainPage.get();
        //найти java
        mainPage.getHeader().search("java");
        // по всем книгам прокликать в корзину
        searchResultPage.setSorting("высокий рейтинг");
        searchResultPage.clickChips("Нет в продаже");
        List<BookCard> books = searchResultPage.getResults();
        for (BookCard book : books) {
            System.out.println(book.getTitle());
            book.addToCart();
        }

        searchResultPage.getHeader().awaitCartHasNumber(books.size());
        assertEquals("60", searchResultPage.getHeader().getCartCounter());
    }
}
