package ru.inno.cucumber;

import io.cucumber.java.*;
import io.cucumber.java.ru.Дано;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LabirintStepdefs {

    private WebDriver driver;

    private List<WebElement> cards;

    @Before
    public void beforeTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
    }

    @After
    public void afterTest(){
        if (driver != null){
            driver.quit();
        }
    }

    @Дано("установлены cookie {string}")
    public void setCookie(String keyValue){
        String[] pair = keyValue.split("=");
        driver.get("https://labirint.ru");
        driver.manage().addCookie(new Cookie(pair[0], pair[1]));
    }

    @Дано("пользователь зашел на главную страницу")
    public void openMainPage() {
        driver.get("https://labirint.ru");
    }

    @Дано("пользователь пишет в поиск слово {string}")
    public void search(String term) {
        driver.findElement(By.cssSelector("#search-field")).sendKeys(term);
    }

    @Дано("нажимает кнопку Поиск")
    public void clickSearch(){
        driver.findElement(By.cssSelector("#search-field")).sendKeys(Keys.RETURN);
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
