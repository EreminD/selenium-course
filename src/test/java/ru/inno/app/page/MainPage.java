package ru.inno.app.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver driver;

//    private final By loginInput = By.cssSelector("input[name='UserName']");
//    private final By passwordInput = By.cssSelector("input[name='Password']");
//    private final By loginButton = By.cssSelector("#login");
//    private final By loginStatus = By.cssSelector("#loginstatus");


    private WebElement UserName;

    private WebElement Password;

    private WebElement login;

    private WebElement loginstatus;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage get(){
        this.driver.get("http://uitestingplayground.com/sampleapp");
        return this;
    }

    public void setLogin(String login){
        UserName.clear();
        UserName.sendKeys(login);
    }

    public void setPassword(String password){
        Password.sendKeys(password);
    }

    public void clickLogin(){
        login.click();
    }

    public String getLoginStatus(){
        return loginstatus.getText();
    }
}
