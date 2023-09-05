package ru.inno.labirint.page;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        // TODO: move to driver config
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
    }
}
