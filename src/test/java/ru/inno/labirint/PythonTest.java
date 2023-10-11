package ru.inno.labirint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class PythonTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "112.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "117 Chrome");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
        }});
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void pythonTest() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        driver.get("https://labirint.ru");
        driver.manage().addCookie(new Cookie("cookie_policy", "1"));

        driver.findElement(By.cssSelector("#search-field")).sendKeys("Python", Keys.RETURN);

        driver.findElement(By.cssSelector("span.sorting-items")).click();
        driver.findElement(By.cssSelector("[data-event-content='высокий рейтинг']")).click();

        WebElement loader = driver.findElement(By.cssSelector("div.loading-panel"));
        new WebDriverWait(driver, ofSeconds(10)).until(
                or(
                        stalenessOf(loader),
                        invisibilityOf(loader)
                )
        );

        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn-tocart.buy-link"));

        for (WebElement button : buttons) {
            button.click();
        }

        String sizeToBe = Integer.toString(buttons.size());

        new WebDriverWait(driver, ofSeconds(5)).until(textToBe(By.cssSelector(".basket-in-cart-a"), sizeToBe));

        String sizeAsIs = driver.findElement(By.cssSelector(".basket-in-cart-a")).getText();

        assertEquals(sizeToBe, sizeAsIs);
    }
}



