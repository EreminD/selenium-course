package ru.inno.internet;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ThreadGuard;

@ExtendWith(SeleniumJupiter.class)
public class ThreadGuardTest {

    @Test
    public void test(ChromeDriver driver) {
        WebDriver protectedDriver = ThreadGuard.protect(driver);
        protectedDriver.get("https://google.com");
        Runnable r1 = () -> {
            protectedDriver.get("https://selenium.dev");
        };
        Thread thr1 = new Thread(r1);
        thr1.start();
        protectedDriver.get("https://habr.com");
    }

}
