package ru.inno.pagefactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.inno.pagefactory.block.BookCard;
import ru.inno.pagefactory.block.Chips;
import ru.inno.pagefactory.page.MainPage;
import ru.inno.pagefactory.page.SearchResultPage;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Severity(SeverityLevel.BLOCKER)
@Tags({@Tag("Positive"), @Tag("Search"), @Tag("Filters")})
@ExtendWith(SeleniumJupiter.class)
@Epic("Каталог")
@Owner("EreminD")
public class LabirintTest {

    private WebDriver driver;
    private MainPage mainPage;
    private SearchResultPage searchResultPage;

    @BeforeEach
    public void setUp() {
        String hub = System.getProperty("hub", "http://localhost:4444");
        System.out.println(hub);
        step("Открыть драйвер", () -> {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("browserVersion", "117.0");
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("name", "Test badge...");
                put("sessionTimeout", "15m");
                put("env", new ArrayList<String>() {{
                    add("TZ=UTC");
                }});
                put("labels", new HashMap<String, Object>() {{
                    put("manual", "true");
                }});
                put("enableVideo", true);
            }});
            driver = new RemoteWebDriver(new URL(hub + "/wd/hub"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        });
        step("Инициализировать страницы", () -> {
            mainPage = PageFactory.initElements(driver, MainPage.class);
            searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        });
    }

    @AfterEach
    @Step("Закрыть драйвер")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Story("Как пользователь, я могу искать товары по названию")
    @Feature("Поиск по каталогу")
    @DisplayName("Ищем книги по названию java")
    @Description("Проверяем, что можем найти книги по искомому слову")
    public void searchPOMTest() {
        generateJson();
        mainPage.open();
        mainPage.getHeader().search("Java");

        List<BookCard> books = searchResultPage
                .setSortingType("высокий рейтинг")
                .closeChips(Chips.NOT_AVAILABLE)
                .closeChips(Chips.AWAITING)
                .getAllBooks();

        step("Добавить все книги в корзину", () -> {
            for (BookCard book : books) {
                System.out.println(book.getTitle());
                book.addToCart();
            }
        });

        int counter = searchResultPage.getHeader()
                .awaitCartCounterToBe(books.size())
                .getCartCounter();

        step("Ничего не делаем");
        step("Проверяем, что в корзине счетчик товаров показывает " + books.size(), () ->
                assertEquals(books.size(), counter, "Количество товаров в корзине не равно 60 => " + counter)
        );
    }

    @Attachment(value = "request-body.json", type = "application/json")
    private String generateJson() {
        return "{\"name\": \"tester\", \"age\":20}";
    }

    //    @Test
    @DisplayName("Page Object alternative")
    public void test(ChromeDriver driver) {
        step("Открыть страницу", () -> {
            driver.get("");
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
            driver.findElement(By.cssSelector("")).click();
            driver.findElement(By.cssSelector("")).click();
            driver.findElement(By.cssSelector("")).click();
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
        step("Нажать на кнопку.. ", () -> {
            driver.findElement(By.cssSelector("")).click();
        });
    }
}
