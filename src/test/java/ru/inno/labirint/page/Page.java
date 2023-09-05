package ru.inno.labirint.page;

import org.openqa.selenium.WebDriver;
import ru.inno.labirint.block.Header;

public abstract class Page {
    protected final WebDriver driver;
    protected final String BASE_URL = "https://labirint.ru";
    protected Header header;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.header = new Header(driver);
    }

    public Header getHeader() {
        return header;
    }

    public abstract void  open();
}
