package ru.inno.labirint.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Chip {
   @FindBy(css = ".filter-reset__icon")
    private WebElement crossButton;

   @FindBy(css = ".filter-reset__content")
    private WebElement contentPlace;

    public Chip(WebElement element) {
        PageFactory.initElements(element, this);
    }

    public void clickCross(){
        crossButton.click();
    }

    public String getLabel(){
        return contentPlace.getText();
    }

}
