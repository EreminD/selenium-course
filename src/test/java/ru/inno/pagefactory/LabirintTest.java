package ru.inno.pagefactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ru.inno.pagefactory.block.BookCard;
import ru.inno.pagefactory.block.Chips;
import ru.inno.pagefactory.page.MainPage;
import ru.inno.pagefactory.page.SearchResultPage;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Поиск по каталогу")
@Feature("Поиск по названию")
@ExtendWith(SeleniumJupiter.class)

public class LabirintTest {
    @Test
    @Tag("Positive")
    @Story("Поиск в хедере")
    @Issue("bug-01")
    @TmsLink("test-123")
    @Link(url="https://ya.ru", name = "Click me!")
    @Owner("EreminD")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Поиск на сайте")
    public void searchPOMTest(ChromeDriver driver) {
        generateJson();
        generateSQL();
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);

        mainPage.open();
        mainPage.getHeader().search("Java");

        List<BookCard> books = searchResultPage
                .setSortingType("высокий рейтинг")
                .closeChips(Chips.NOT_AVAILABLE)
                .closeChips(Chips.AWAITING)
                .getAllBooks();

        step("Положить все книги в корзину", () -> {
            for (BookCard book : books) {
                System.out.println(book.getTitle());
                book.addToCart();
            }
        });

        int counter = searchResultPage.getHeader()
                .awaitCartCounterToBe(books.size())
                .getCartCounter();

        step("проверить, что в корзине стоит число " + books.size(), () -> {
            assertEquals(books.size(), counter);
        });
    }

    @Attachment(value = "request-body-json", type = "application/json" )
    private String generateJson(){
        return "{\"name\": \"Tester\", \"age\": 20}";
    }
    @Attachment(value = "sql", type = "text/plain" )
    private String generateSQL(){
        return "SELECT * FROM COMPANIES WHERE ID > 10";
    }
}
