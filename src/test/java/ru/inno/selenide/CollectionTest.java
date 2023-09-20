package ru.inno.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static ru.inno.selenide.HasChildCondition.hasChildWithTag;

@ExtendWith(ScreenShooterExtension.class)
public class CollectionTest {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @Test
    public void test() {
        step("open page", () -> open("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html"));

        step("click dropdown", () -> $("#my-dropdown-1").click());

        ElementsCollection elements = $(".dropdown-menu.show").$$("li");

        step("dropdown has 'Another action' option", () -> elements
                .shouldHave(itemWithText("Another action"))
                .shouldHave(size(5))
        );

        step("dropdown has 4 links", () -> elements
                .filterBy(hasChildWithTag("a"))
                .shouldHave(size(5))
        );


        //        .stream().filter(se -> se.$("*").getTagName().equals("a")).count();
        //        assertEquals(4, counter);
    }
}
