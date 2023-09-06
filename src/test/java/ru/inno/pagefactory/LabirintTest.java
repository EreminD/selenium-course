package ru.inno.pagefactory;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ru.inno.pagefactory.block.BookCard;
import ru.inno.pagefactory.block.Chips;
import ru.inno.pagefactory.page.MainPage;
import ru.inno.pagefactory.page.SearchResultPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class LabirintTest {
    @Test
    public void searchPOMTest(ChromeDriver driver) {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);

        mainPage.open();
        mainPage.getHeader().search("Java");

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
