package ru.inno.selenide;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class HasChildCondition extends Condition {

    private final String childTagName;

    private HasChildCondition(String tagName) {
        super(String.format("has child with tag " + tagName));
        this.childTagName = tagName;
    }

    // static factory method
    public static Condition hasChildWithTag(String tagName) {
        return new HasChildCondition(tagName);
    }

    @Nonnull
    @Override
    public CheckResult check(Driver driver, WebElement element) {
        try {
            WebElement child = element.findElement(By.cssSelector("*"));
            String tag = child.getTagName();
            System.out.println(tag);
            return new CheckResult(tag.equalsIgnoreCase(childTagName), "Имя тега не равно " + childTagName + " [" + tag + "]");
        } catch (NoSuchElementException nosuch) {
            return CheckResult.rejected("No such element exception", null);
        }
    }

}
