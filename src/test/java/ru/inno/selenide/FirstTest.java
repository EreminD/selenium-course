package ru.inno.selenide;

import com.codeborne.selenide.*;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.codeborne.selenide.ClickOptions.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({SoftAssertsExtension.class})
public class FirstTest {

    @BeforeAll
    public static void setUp(){
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.browser = "chrome";
        Configuration.timeout = 2000;
        Configuration.screenshots = true;
        Configuration.headless = false;
        Configuration.assertionMode = AssertionMode.SOFT;
        Configuration.savePageSource = false;
    }

    @Test
    public void successLogin() {
        //1. зайти на страницу http://the-internet.herokuapp.com/login
        open("/login");

        //2. ввести логин tomsmith
        $("#username").val("tomsmith");


        //3. ввести пароль SuperSecretPassword!
        $("#password")
                .shouldHave(attribute("type", "password"))
                .val("SuperSecretPassword!");

        //4. нажать логин
        $("button[type=submit]").click(usingJavaScript());

        //5. проверить, что отображается зеленая плашка
        $("#flash").shouldHave(cssClass("success"));

        //6. проверить, что есть кнопка логаут
        $(".icon-signout").shouldHave(text("Logout"));
        System.out.println("Hi!");
    }

    // TODO: refresh browser on next test
}
