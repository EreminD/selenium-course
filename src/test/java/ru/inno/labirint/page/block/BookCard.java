package ru.inno.labirint.page.block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookCard {

    @FindBy(css = ".btn-tocart.buy-link")
    private WebElement buyButton;

    @FindBy(css = ".product-card__name")
    private WebElement bookTitle;

    public BookCard(WebElement element) {
        PageFactory.initElements(element, this);
    }

    public String getTitle() {
        return this.bookTitle.getText();
    }

    public void addToCart() {
        this.buyButton.click();
    }
}
