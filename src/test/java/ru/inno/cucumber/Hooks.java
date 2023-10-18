package ru.inno.cucumber;

import io.cucumber.java.After;

import static java.time.Duration.ofSeconds;

public class Hooks {

    private DriverManager manager;

    public Hooks(DriverManager manager) {
        this.manager = manager;
    }

    @After
    public void afterTest() {
        manager.quitDriver();
    }
}
