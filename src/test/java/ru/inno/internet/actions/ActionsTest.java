package ru.inno.internet.actions;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v116.log.Log;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.devtools.v116.network.model.Request;
import org.openqa.selenium.devtools.v116.network.model.RequestId;
import org.openqa.selenium.devtools.v116.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SeleniumJupiter.class)
public class ActionsTest {

    @Test
    public void sliderTest(ChromeDriver driver) {
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");

        WebElement range = driver.findElement(By.cssSelector("input[type=range]"));

        Actions actions = new Actions(driver);
        actions.clickAndHold(range);
        actions.moveByOffset(-25, 0);
        actions.release();
        actions.perform();

        String value = driver.findElement(By.cssSelector("#range")).getText();

        assertEquals("1.5", value);
    }

    @Test
    public void dragAndDropTest(ChromeDriver driver) {
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");

        WebElement elementToMove = driver.findElement(By.cssSelector("#draggable"));
        WebElement target = driver.findElement(By.cssSelector("#droppable"));
        new Actions(driver).dragAndDrop(elementToMove, target).perform();

        assertEquals("Dropped!", target.getText());
    }

    @Test
    public void contextClick(ChromeDriver driver) {
        driver.get("http://the-internet.herokuapp.com/context_menu");

        WebElement hotspot = driver.findElement(By.cssSelector("#hot-spot"));
        new Actions(driver).contextClick(hotspot).perform();

        Alert alert = driver.switchTo().alert();
        assertEquals("You selected a context menu", alert.getText());
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
    public void executeJSTEst(ChromeDriver driver) {
        String removeTitle = "document.getElementsByClassName('dfp_label')[2].remove()";
        String removeAd = "document.getElementsByClassName('adfox_banner')[2].remove()";

        driver.get("https://qna.habr.com");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".adfox_banner"), 3));

        int sizeBefore = driver.findElements(By.cssSelector(".adfox_banner")).size();

        driver.executeScript(removeTitle);
        driver.executeScript(removeAd);

        int sizeAfter = driver.findElements(By.cssSelector(".adfox_banner")).size();

        assertEquals(sizeBefore - 1, sizeAfter);
    }

    Map<String, Request> reqs;
    Map<String, Response> resps;
    Map<String, String> respBodies;

    WebDriver driver;

    DevTools devTools;


    @BeforeEach
    public void createStorage() {
        driver = new ChromeDriver();
        reqs = new HashMap<>();
        resps = new HashMap<>();
        respBodies = new HashMap<>();

        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());

        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestSent -> reqs.put(requestSent.getRequestId().toString(), requestSent.getRequest()));

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String reqId = responseReceived.getRequestId().toString();
            resps.put(reqId, responseReceived.getResponse());
            String body = devTools.send(Network.getResponseBody(new RequestId(reqId))).getBody();
            respBodies.put(reqId, body);

        });
    }

    @AfterEach
    public void buildNetworkReport() {
        if (driver != null) {
            devTools.disconnectSession();
            driver.quit();
        }


        for (String requestId : reqs.keySet()) {
            Request request = reqs.get(requestId);
            if (request.getUrl().startsWith("https://todo")) {
                Response response = resps.get(requestId);
                String body = respBodies.get(requestId);
                System.out.println(request.getMethod() + " " + request.getUrl() + " " + request.getPostData().orElse("NO_DATA"));
                System.out.println(response.getStatus());
                System.out.println(body);
                System.out.println("--------------------------------------");
            }

        }
    }

    @Test
    public void test() {
        String url = "http://sky-todo-list.herokuapp.com";

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> taskList = driver.findElements(By.cssSelector("table tr"));
    }

}
