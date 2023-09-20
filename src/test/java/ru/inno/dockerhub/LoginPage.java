package ru.inno.dockerhub;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");

    public void open() {
        Selenide.open("https://login.docker.com/u/login/");
    }

    public SelenideElement setEmail(String email) {
        return usernameInput.val(email);
    }

    public SelenideElement setPassword(String password) {
        return passwordInput.val(password);
    }
}
