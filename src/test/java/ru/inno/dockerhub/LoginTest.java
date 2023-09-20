package ru.inno.dockerhub;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.SelenidePageFactory;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(BrowserPerTestStrategyExtension.class)
public class LoginTest {
    public static final String EMAIL = "roltelespi@gufum.com";
    public static final String PASSWORD = "QWERTY123456";
    public static final String LOGIN = "roltelespi";


    @Test
    @Order(1)
    public void test() {
        LoginPage loginPage = new SelenidePageFactory().page(WebDriverRunner.driver(), LoginPage.class);
//        LoginPage loginPage = new LoginPage();
        loginPage.open();
        loginPage.setEmail(EMAIL).pressEnter();
        loginPage.setPassword(PASSWORD).pressEnter();

        new MainPage().usernameShouldBe(LOGIN);
    }

    @Test
    @Order(2)
    public void test_failed() {
        open("https://login.docker.com/u/login/");
        $("#username").val(LOGIN).pressEnter();
        $("#password").val("wrongPass").pressEnter();
        $("#error-element-password").shouldHave(text("Incorrect authentication credentials"));
    }
}
