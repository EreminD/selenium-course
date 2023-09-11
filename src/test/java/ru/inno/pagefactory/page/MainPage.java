package ru.inno.pagefactory.page;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("открыть главную страницу")
    public void open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        getScreen();
    }

    @Attachment(value = "screenshot.png", type = "image/png")
    private byte[] getScreen(){
        return driver.findElement(By.cssSelector("body")).getScreenshotAs(OutputType.BYTES);
    }


}
