package ru.inno.labirint.page;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.inno.labirint.page.block.Header;


public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        this.header = PageFactory.initElements(driver, Header.class);
    }

    public MainPage get() {
        this.driver.get("https://www.labirint.ru");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
        return this;
    }

    public Header getHeader() {
        return header;
    }
}
