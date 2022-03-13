package ru.redramka.zener.dev.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.redramka.zener.dev.model.ProductData;
import ru.redramka.zener.dev.model.ProductParametrs;

import java.util.ArrayList;
import java.util.List;

public class ProductHelper extends HelperBase{

    public ProductHelper(WebDriver driver) { super(driver); }

    public void goToProductPage(String productName){
       driver.navigate().to("http://dev9.redramka.ru/shop/product/" + productName + "/");
    }

    WebDriverWait wait = new WebDriverWait(driver, 30);
    Actions action = new Actions(driver);


    public ProductParametrs getProductsParametrs(String productName){
        goToProductPage(productName);
        isFilterPresent();

            ProductParametrs params = new ProductParametrs();
            List<WebElement> filtrElements = driver.findElements(By.cssSelector("#j-avail-list label > input"));

            for (WebElement element: filtrElements ) {
                String manufacturer = element.getAttribute("data-manufacturer");
                String name = element.getAttribute("data-name");
                params.add(new ProductData()
                        .with_manufacturer(manufacturer)
                        .with_name(name));
            }
            return params;
    }


    public void isFilterPresent(){
        WebElement filter_list = driver.findElement(By.cssSelector("#j-avail-list ul"));
        wait.until(ExpectedConditions.visibilityOf(filter_list));
    }


    public void selectPointFromFilter(int filterElementNumber){
        isFilterPresent();
        WebElement filter_point = driver.findElement(By.cssSelector("#j-avail-list > ul > li:nth-child(" + filterElementNumber + ") > label > span.checkbox"));
        wait.until(ExpectedConditions.visibilityOf(filter_point));
        action.moveToElement(filter_point);
        if (!filter_point.isSelected())filter_point.click();

    }



    public void goToCart(){
        WebElement modal_window = driver.findElement(By.cssSelector("#modal-added"));
        boolean modal_present = modal_window.isDisplayed();
        Assert.assertTrue(modal_present,"Модальное окно не открылось после начатия кнопки 'Добавить в корзину'");
        if (modal_present){
            click(By.cssSelector("div#modal-added a.btn.btn-green")); }
    }

    public void continiueShoping(){
        WebElement modal_window = driver.findElement(By.cssSelector("#modal-added"));
        boolean modal_present = modal_window.isDisplayed();
        Assert.assertTrue(modal_present,"Модальное окно не открылось после начатия кнопки 'Добавить в корзину'");
        if (modal_present){
            click(By.cssSelector("div#modal-added .btn.btn-white.close-modal")); }
    }

    public void addProductToCart(){
        click(By.cssSelector(".btn.add_to_cart"));
    }

}
