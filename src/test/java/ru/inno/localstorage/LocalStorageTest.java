package ru.inno.localstorage;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.time.Duration.*;

@ExtendWith(SeleniumJupiter.class)
public class LocalStorageTest {

    @Test
    public void setBestScore(ChromeDriver driver){
        driver.manage().timeouts().implicitlyWait(ofSeconds(4));
        driver.get("https://www.2048.org/");
        driver.executeScript("localStorage['bestScore'] = 987654321");
        driver.navigate().refresh();
    }

}
