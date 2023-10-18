package ru.inno.cucumber;

import io.cucumber.java.*;
import io.cucumber.java.ru.Дано;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchResultsStepdefs {

    private WebDriver driver;

    private List<WebElement> cards;

    public SearchResultsStepdefs(DriverManager manager) {
        driver = manager.getDriver();
    }

    @Дано("отображается список книг")
    public void iCanSeeResults(){
        cards = driver.findElements(By.cssSelector(".product-card"));
        assertEquals(60, cards.size());
    }

    @Дано("у всех книг в названии есть слово {word}")
    public void cardsShouldContainSearchTerm(String term){
        long shouldBeZero = cards.stream()
                .map(card -> card.findElement(By.cssSelector(".product-card__name")).getText())
                .filter(title -> title.contains(term))
                .count();

        assertEquals(0, shouldBeZero);
    }

    @Дано("отображается сообщение с текстом {string}")
    public void errorTextShouldBeVisible(String message){
        String textAsIs = driver.findElement(By.cssSelector(".search-error h1")).getText();
        assertEquals(textAsIs, message);
    }
}
