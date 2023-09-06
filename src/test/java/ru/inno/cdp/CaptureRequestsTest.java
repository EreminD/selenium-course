package ru.inno.cdp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.BiDi;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v116.fetch.Fetch;
import org.openqa.selenium.devtools.v116.log.Log;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.devtools.v116.network.model.Request;
import org.openqa.selenium.devtools.v116.network.model.RequestId;
import org.openqa.selenium.devtools.v116.network.model.Response;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CaptureRequestsTest {

    private WebDriver driver;

    private Map<String, Request> requests;
    private Map<String, Response> responses;
    private Map<String, String> responseBodies;

    @BeforeEach
    public void setUp(){
        requests = new HashMap<>();
        responses = new HashMap<>();
        responseBodies = new HashMap<>();

        driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();

        devTools.send(Log.enable());
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestWillBeSent -> {
            String requestId = requestWillBeSent.getRequestId().toString();
            requests.put(requestId, requestWillBeSent.getRequest());
        });

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String requestId = responseReceived.getRequestId().toString();
            responses.put(requestId, responseReceived.getResponse());

            String body = devTools.send(Network.getResponseBody(new RequestId(requestId))).getBody();
            responseBodies.put(requestId, body);
        });
    }

    @AfterEach
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }

        for (String requestId : requests.keySet()) {
            Request request = requests.get(requestId);
            if (request.getUrl().startsWith("https://todo-app-sky")){
                Response response = responses.get(requestId);
                String body = responseBodies.get(requestId);
                System.out.println(request.getMethod() + " " + request.getUrl() + " " + request.getPostData().orElse("NO_DATA"));
                System.out.println(response.getStatus());
                System.out.println(body);
                System.out.println("--------------------------------------");
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        String url = "http://sky-todo-list.herokuapp.com";

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        List<WebElement> tasks = driver.findElements(By.cssSelector("table tr"));
        tasks.get(0).click();

        Thread.sleep(4000L);
        System.out.println(tasks.size());
    }
}
