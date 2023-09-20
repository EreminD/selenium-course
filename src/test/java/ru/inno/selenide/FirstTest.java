package ru.inno.selenide;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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

        // TODO: refresh browser on next test
        Configuration.holdBrowserOpen = true;
        WebDriverRunner.clearBrowserCache();

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


}
