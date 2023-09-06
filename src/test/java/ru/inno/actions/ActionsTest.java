package ru.inno.actions;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class ActionsTest {

    @Test
    public void rangeTest(ChromeDriver driver) {
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");

        WebElement range = driver.findElement(By.cssSelector("input[type=range]"));

        Actions actions = new Actions(driver);
        actions
                .clickAndHold(range)
                .moveByOffset(-24, 0)
                .release()
                .perform();

        String value = driver.findElement(By.cssSelector("#range")).getText();
        assertEquals("1.5", value);
    }

    @Test
    public void dragAndDropTest(ChromeDriver driver) {
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");

        WebElement smallBox = driver.findElement(By.cssSelector("#draggable"));
        WebElement bigBox = driver.findElement(By.cssSelector("#droppable"));
        new Actions(driver).dragAndDrop(smallBox, bigBox).pause(Duration.ofSeconds(3)).perform();

        assertEquals("Dropped!", bigBox.getText());
    }

    @Test
    public void contextClickTest(ChromeDriver driver) {
        driver.get("http://the-internet.herokuapp.com/context_menu");

        WebElement hotspot = driver.findElement(By.cssSelector("#hot-spot"));
        new Actions(driver).contextClick(hotspot).perform();

        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        assertEquals("You selected a context menu", text);
        alert.accept();
    }


    @Test
    public void canvasTest(ChromeDriver driver) {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/draw-in-canvas.html");

        WebElement canvas = driver.findElement(By.cssSelector("#my-canvas"));
        new Actions(driver)
                .moveToElement(canvas)
                .clickAndHold(canvas)
                .moveByOffset(20, 20)
                .release(canvas).perform();
    }

    @Test
    public void executeJsTest(ChromeDriver driver) {
        String removeTitleScript = "document.getElementsByClassName('dfp_label')[2].remove()";
        String removeBannerScript = "document.getElementsByClassName('adfox_banner')[2].remove()";

        driver.get("https://qna.habr.com");
        int sizeBefore = driver.findElements(By.cssSelector(".adfox_banner")).size(); //3

        driver.executeScript(removeTitleScript);
        driver.executeScript(removeBannerScript);

        int sizeAfter = driver.findElements(By.cssSelector(".adfox_banner")).size(); //2

        assertEquals(sizeBefore - 1, sizeAfter);
    }
}
