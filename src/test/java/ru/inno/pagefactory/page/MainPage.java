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

    @Step("Открыть главную страницу")
    public void open() {
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));
        getScreenshot();
    }

    @Attachment(value = "screen.png", type = "image/png")
    private byte[] getScreenshot(){
        return driver.findElement(By.cssSelector("body")).getScreenshotAs(OutputType.BYTES);
    }
}
