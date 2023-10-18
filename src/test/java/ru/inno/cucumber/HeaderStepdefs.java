package ru.inno.cucumber;

import io.cucumber.java.ru.Дано;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HeaderStepdefs {

    private WebDriver driver;

    public HeaderStepdefs(DriverManager manager) {
        driver = manager.getDriver();
    }
    @Дано("пользователь пишет в поиск слово {string}")
    public void search(String term) {
        driver.findElement(By.cssSelector("#search-field")).sendKeys(term);
    }

    @Дано("нажимает кнопку Поиск")
    public void clickSearch() {
        driver.findElement(By.cssSelector("#search-field")).sendKeys(Keys.RETURN);
    }

}
