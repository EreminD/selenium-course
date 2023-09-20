package ru.inno.dockerhub;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public void usernameShouldBe(String textToBe){
        $("[data-testid='navBarUsernameDropdown']").shouldHave(Condition.text(textToBe));
    }
}

