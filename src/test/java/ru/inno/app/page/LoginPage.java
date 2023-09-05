package ru.inno.app.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setLogin(String username) {
        driver.findElement(By.cssSelector("[name='UserName']")).sendKeys(username);
    }

    public String getStatusText() {
        return driver.findElement(By.cssSelector("#loginstatus")).getText();
    }

    public void setPassword(String pass) {
        driver.findElement(By.cssSelector("[name='Password']")).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(By.cssSelector("#login")).click();
    }

    public void authAs(String username, String pass) {
        this.setLogin(username);
        this.setPassword(pass);
        this.clickLogin();
    }

    public void open() {
        driver.manage().window().setPosition(new Point(70, -1100));
        driver.manage().window().maximize();
        driver.get("http://uitestingplayground.com/sampleapp");
    }
}
