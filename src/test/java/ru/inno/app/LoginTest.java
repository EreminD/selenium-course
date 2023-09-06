package ru.inno.app;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import ru.inno.app.page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class LoginTest {

    @Test
    public void loginTest(ChromeDriver driver) {
        LoginPage page = PageFactory.initElements(driver, LoginPage.class);
        String username = "super-user";

        page.open();
        page.authAs(username, "pwd");
        String text = page.getStatusText();
        assertEquals("Welcome, " + username + "!", text);
    }

    @Test
    public void loginFailedTest(ChromeDriver driver) {
        LoginPage page = PageFactory.initElements(driver, LoginPage.class);
        page.open();
        page.authAs("user123", "pwd1234r5");
        String status = page.getStatusText();
        assertEquals("Invalid username/password", status);
    }
}
