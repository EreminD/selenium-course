package ru.inno.selenide;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.Har;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;
import static com.codeborne.selenide.proxy.RequestMatcher.HttpMethod.GET;
import static com.codeborne.selenide.proxy.RequestMatchers.urlContains;

public class ProxyTest {

    @BeforeAll
    public static void setUp() {
        Configuration.proxyEnabled = true;
    }

    @Test
    public void test() throws IOException {
        open();
        BrowserUpProxy proxy = getSelenideProxy().getProxy();
        proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.newHar();

        open("https://sky-todo-list.herokuapp.com/");
        $$("tr").shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));

        Har har = proxy.endHar();
        har.writeTo(Files.createFile(Path.of("./my.har")).toFile());
    }

    @Test
    public void yandexTest() {
        open();

        getSelenideProxy().responseMocker()
                .mockText(
                        "suggestions_mock",
                        urlContains(GET, "suggest"),
                        () -> getResponse("Hello world!")
                );

        open("https://yandex.ru/search/?lr=213&redircnt=1695219729.1");
        open("https://ya.ru/");
        $("#text").val("qwe");
        screenshot("page");
    }

    private String getResponse(String val) {
        return "[\n" +
                "    \"te\",\n" +
                "    [\n" +
                "        [\n" +
                "            \"\",\n" +
                "            \"" + val + "\",\n" +
                "            {\n" +
                "                \"pers\": 1,\n" +
                "                \"sg_weight\": 0,\n" +
                "                \"hl\": [\n" +
                "                    [\n" +
                "                        0,\n" +
                "                        2\n" +
                "                    ]\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        [\n" +
                "            \"\",\n" +
                "            \"telegram\",\n" +
                "            {\n" +
                "                \"sg_weight\": 0,\n" +
                "                \"hl\": [\n" +
                "                    [\n" +
                "                        0,\n" +
                "                        2\n" +
                "                    ]\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        [\n" +
                "            \"\",\n" +
                "            \"tele2\",\n" +
                "            {\n" +
                "                \"sg_weight\": 0,\n" +
                "                \"hl\": [\n" +
                "                    [\n" +
                "                        0,\n" +
                "                        2\n" +
                "                    ]\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        [\n" +
                "            \"\",\n" +
                "            \"telegram web online\",\n" +
                "            {\n" +
                "                \"sg_weight\": 0,\n" +
                "                \"hl\": [\n" +
                "                    [\n" +
                "                        0,\n" +
                "                        2\n" +
                "                    ]\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        [\n" +
                "            \"\",\n" +
                "            \"tevas\",\n" +
                "            {\n" +
                "                \"sg_weight\": 0,\n" +
                "                \"hl\": [\n" +
                "                    [\n" +
                "                        0,\n" +
                "                        2\n" +
                "                    ]\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        [\n" +
                "            \"\",\n" +
                "            \"teamviewer\",\n" +
                "            {\n" +
                "                \"sg_weight\": 0,\n" +
                "                \"hl\": [\n" +
                "                    [\n" +
                "                        0,\n" +
                "                        2\n" +
                "                    ]\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    ],\n" +
                "    {\n" +
                "        \"r\": 1,\n" +
                "        \"pers_options\": 1,\n" +
                "        \"log\": \"sgtype:PersBBBBB\",\n" +
                "        \"continue\": \"r\"\n" +
                "    }\n" +
                "]";
    }
}
