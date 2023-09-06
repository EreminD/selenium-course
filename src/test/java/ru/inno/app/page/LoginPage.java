package ru.inno.app.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    private final WebDriver driver;
    @FindBy(how = How.CSS, using = "[name='UserName']")
    private WebElement usernameInput;
    @FindBy(css = "[name='Password']")
    private WebElement passwordInput;
    @FindBy(css = "#loginstatus")
    private WebElement loginStatusText;
    @FindBy(css = "#login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setLogin(String username) {
        usernameInput.sendKeys(username);
    }

    public String getStatusText() {
        return loginStatusText.getText();
    }

    public void setPassword(String pass) {
        passwordInput.sendKeys(pass);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void authAs(String username, String pass) {
        this.setLogin(username);
        this.setPassword(pass);
        this.clickLogin();
    }

    public void open() {
        driver.manage().window().maximize();
        driver.get("http://uitestingplayground.com/sampleapp");
    }
}
