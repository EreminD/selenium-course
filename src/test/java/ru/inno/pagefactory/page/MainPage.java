package ru.inno.pagefactory.page;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
    }
}
