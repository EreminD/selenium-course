package ru.inno.labirint.page;

import org.openqa.selenium.WebDriver;
import ru.inno.labirint.page.block.Header;

public abstract class Page {
    protected final WebDriver driver;
    protected Header header;

    protected Page(WebDriver driver) {
        this.driver = driver;
    }

    public Header getHeader() {
        return header;
    }
}
