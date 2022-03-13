package ru.redramka.zener.dev.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelperBase {

    protected WebDriver driver;
    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }



    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void type(WebElement element, String text){
        element.click();
        if (text != null) {
            String existingText = element.getAttribute("value");
            if (! text.equals(existingText)) {
                element.clear();
                element.sendKeys(text);
            }
        }
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isElementPresent( By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
