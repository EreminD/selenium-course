package ru.inno;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static java.time.Duration.ofSeconds;

public class WebDriverFactory {
    public WebDriver getDriver(DriverType name) {
        return getDriver(name, new String[]{});
    }

    public WebDriver getDriver(DriverType name, String... args) {
        WebDriver driver;
        switch (name) {
            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                for (String s : args) {
                    options.addArguments(s);
                }
                driver = new EdgeDriver(options);
            }
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                for (String s : args) {
                    options.addArguments(s);
                }
                driver = new ChromeDriver(options);
            }
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                for (String s : args) {
                    options.addArguments(s);
                }
                driver = new FirefoxDriver(options);
            }
            case SAFARI -> {
                SafariOptions options = new SafariOptions();
                for (String s : args) {
                    // logic
                }
                driver = new SafariDriver(options);
            }
            default -> throw new RuntimeException("Wrong WebDriver type!");
        }

        driver.manage().timeouts().implicitlyWait(ofSeconds(4)); // config
        driver.manage().window().maximize();
        return driver;
    }
}