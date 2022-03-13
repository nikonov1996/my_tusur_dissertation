package ru.redramka.zener.dev.appmanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.redramka.zener.dev.model.ProductData;
import ru.redramka.zener.dev.model.ProductParametrs;


public class OrderInfoHelper extends HelperBase {
    public OrderInfoHelper(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, 30);
    Actions action = new Actions(driver);


    public String getOrderInfo(int orderNumber) {
        driver.navigate().to("http://dev9.redramka.ru/rest/orders.php?ID_FROM=" + orderNumber + "&ID_TO=" + orderNumber);
        WebElement content = driver.findElement(By.cssSelector("pre"));
        wait.until(ExpectedConditions.visibilityOf(content));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector("pre"), "null"));

        return content.getText();
    }

    public int getOrderNumber() {
        wait.until(ExpectedConditions.titleContains("Ваш заказ успешно оформлен"));

        String text_with_ordernumber = driver.findElement(By.cssSelector("h2")).getText();
        String[] chars_of_order_number = text_with_ordernumber.split("[^0-9]");
        String order_number = "";
        for (String num : chars_of_order_number) {
            order_number += num;
        }

        return Integer.parseInt(order_number);
    }

    public ProductData getProductData(int orderNumber) throws ParseException {
        ProductData product_items = new ProductData();
        String orderInformation = getOrderInfo(orderNumber);

        JSONObject orderinfo = (JSONObject) JSONValue.parseWithException(orderInformation);
        JSONObject once_order = (JSONObject) orderinfo.get(String.valueOf(orderNumber));
        JSONArray order_items = (JSONArray) once_order.get("ITEMS");
        JSONObject items = (JSONObject) order_items.get(0);

        String manufacturer = items.get("MANUFACTURER").toString();
        String name = items.get("NAME").toString();

        return product_items
                .with_manufacturer(manufacturer)
                .with_name(name);
    }

    public boolean getOrderResult(int order_number) {
        wait.until(ExpectedConditions.urlContains(String.valueOf(order_number)));
        boolean result = driver.getTitle().contains("Ваш заказ успешно оформлен");
        return result;
    }
}
