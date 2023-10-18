package ru.inno.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.time.Duration.ofSeconds;

public class MainPageStepdefs {

    private WebDriver driver;

    public MainPageStepdefs(DriverManager manager) {
        driver = manager.getDriver();
    }

    @Дано("установлены cookie {string}")
    public void setCookie(String keyValue) {
        String[] pair = keyValue.split("=");
        driver.get("https://labirint.ru");
        driver.manage().addCookie(new Cookie(pair[0], pair[1]));
    }

    @Дано("пользователь зашел на главную страницу")
    public void openMainPage() {
        driver.get("https://labirint.ru");
    }

}
