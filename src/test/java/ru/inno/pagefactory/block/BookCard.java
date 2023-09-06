package ru.inno.pagefactory.block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookCard {
    @FindBy(css = ".btn-tocart.buy-link")
    private WebElement buyButton;

    @FindBy(css = ".product-card__name")
    private WebElement bookTitle;

    private final WebElement element;

    public BookCard(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }

    public String getTitle() {
        return bookTitle.getText();
    }

    public void addToCart() {
        buyButton.click();
    }
}
