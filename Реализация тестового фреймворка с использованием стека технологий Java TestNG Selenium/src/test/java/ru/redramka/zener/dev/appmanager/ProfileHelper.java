package ru.redramka.zener.dev.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileHelper extends HelperBase{

    public ProfileHelper(WebDriver driver) { super(driver);}
    WebDriverWait wait = new WebDriverWait(driver, 30);
    Actions action = new Actions(driver);
}
