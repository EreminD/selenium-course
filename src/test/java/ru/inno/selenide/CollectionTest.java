package ru.inno.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

@ExtendWith(ScreenShooterExtension.class)
public class CollectionTest {
    @Test
    public void test() {
        open("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        $("#my-dropdown-1").click();
        ElementsCollection elements = $(".dropdown-menu.show").$$("li");

        elements
                .shouldHave(itemWithText("Another action"))
                .shouldHave(size(5));

        screenshot("test-screen");

    }

    // TODO: Elements collection filter by childs attribute
}
