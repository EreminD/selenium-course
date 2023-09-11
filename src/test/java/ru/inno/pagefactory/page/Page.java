package ru.inno.pagefactory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.inno.pagefactory.block.Header;

public abstract class Page {
    protected final WebDriver driver;
    protected final String BASE_URL = "https://labirint.ru";
    protected Header header;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.header = PageFactory.initElements(driver, Header.class);
    }

    public Header getHeader() {
        return header;
    }

    public abstract void open();
}
