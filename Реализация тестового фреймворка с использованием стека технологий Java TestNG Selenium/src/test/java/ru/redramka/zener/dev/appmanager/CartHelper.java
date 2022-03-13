package ru.redramka.zener.dev.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartHelper extends HelperBase{

    public CartHelper(WebDriver driver){super(driver);}

    public void goToOrder(){
        click(By.cssSelector("div.total .btn.btn-green.btn-rarr"));
    }

}
