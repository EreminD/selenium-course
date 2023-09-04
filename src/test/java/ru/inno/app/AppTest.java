package ru.inno.app;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chromium.ChromiumDriverLogLevel;
import org.openqa.selenium.support.PageFactory;
import ru.inno.app.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class AppTest {

    @Test
    public void appTest() {

        ChromeDriverService service = new ChromeDriverService.Builder()
                .withLogLevel(ChromiumDriverLogLevel.INFO)
                .withLogOutput(System.out).build();

        WebDriver browser = new ChromeDriver(service);
        String username = "user";
        MainPage app = PageFactory.initElements(browser, MainPage.class);
        app.get();
        app.setLogin(username);
        app.setPassword("pwd");
        app.clickLogin();
        String status = app.getLoginStatus();

        String textToBe = "Welcome, " + username + "!";
        assertEquals(textToBe, status);
        browser.quit();
    }


}
