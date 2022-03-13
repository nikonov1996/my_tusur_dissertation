package ru.redramka.zener.dev.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.interactions.SourceType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.redramka.zener.dev.model.ProductData;
import ru.redramka.zener.dev.model.ProductParametrs;
import ru.redramka.zener.dev.model.UserList;
import ru.redramka.zener.dev.model.UserProfilData;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CheckProductParamsTests extends TestBase {

    private UserProfilData testUserCreate(){
        return new UserProfilData()
                .with_name("tester")
                .with_email("test@test.ru")
                .with_phone("99999999999")
                .with_city("Томск")
                .with_mail_index("630000")
                .with_steet("testStreet")
                .with_house_number("99")
                .with_room_number("55")
                .with_company("testCompany")
                .with_inn("1234567890");

    }

    @DataProvider(name = "productList") // ручной ввод тестовых данных
    public Object[][] productList() throws IOException {
        return new Object[][]{{"1735"},{"F-331-1"}};
    };

    @Test(enabled = true , dataProvider = "productList")
    public void OrderWithBankPayAndPickUp(String productName) throws ParseException {
       UserProfilData user = testUserCreate();
       ProductParametrs productListBeforeOrder = app.getProductHelper().getProductsParametrs(productName);
       ProductParametrs productListAfterOrder = new ProductParametrs();
       ArrayList orderNumbers = new ArrayList();

        for (int i = 0; i < productListBeforeOrder.size(); i++) {
            int filterpointnumber = i+1;
            app.getProductHelper().goToProductPage(productName);
            app.getProductHelper().selectPointFromFilter(filterpointnumber);
            app.getProductHelper().addProductToCart();
            app.getProductHelper().goToCart();
            app.getCartHelper().goToOrder();
            app.getOrderHelper().makeOrder(
                    "bank_payment",
                    "delivery_self",
                    user);
            app.getOrderHelper().clickOrder();
            int order_num = app.getOrderInfoHelper().getOrderNumber();
            orderNumbers.add(order_num);
            Assert.assertTrue(app.getOrderInfoHelper().getOrderResult(order_num), "Ошибка формирования заказа");
            ProductData productItemsBeforeOrder = productListBeforeOrder.get(i);
            ProductData productItemsAfterOrder = app.getOrderInfoHelper().getProductData(order_num);
            productListAfterOrder.add(productItemsAfterOrder);
           // Assert.assertEquals(productItemsAfterOrder,productItemsBeforeOrder,
              //      "Ошибка:\nСравнение параметров товара "+ productName+" из склада фильтра №" + filterpointnumber+"\n:");


        }
        Assert.assertEquals(productListAfterOrder,productListBeforeOrder,
                "Сравнение списков параметров товарa [ "+productName+" ] из каждого склада в фильтре:\nОшибка:Параметры товаров не соответствуют:\n");

    }

    @Test(enabled = true , dataProvider = "productList")
    public void OrderWithBankPayAndCourierDelivery(String productName) throws ParseException {
        UserProfilData user = testUserCreate();
        ProductParametrs productListBeforeOrder = app.getProductHelper().getProductsParametrs(productName);
        ProductParametrs productListAfterOrder = new ProductParametrs();
        ArrayList orderNumbers = new ArrayList();

        for (int i = 0; i < productListBeforeOrder.size(); i++) {
            int filterpointnumber = i+1;
            app.getProductHelper().goToProductPage(productName);
            app.getProductHelper().selectPointFromFilter(filterpointnumber);
            app.getProductHelper().addProductToCart();
            app.getProductHelper().goToCart();
            app.getCartHelper().goToOrder();
            app.getOrderHelper().makeOrder(
                    "bank_payment",
                    "delivery_courier",
                    user);
            app.getOrderHelper().clickOrder();
            int order_num = app.getOrderInfoHelper().getOrderNumber();
            orderNumbers.add(order_num);
            Assert.assertTrue(app.getOrderInfoHelper().getOrderResult(order_num), "Ошибка формирования заказа");
            ProductData productItemsBeforeOrder = productListBeforeOrder.get(i);
            ProductData productItemsAfterOrder = app.getOrderInfoHelper().getProductData(order_num);
            productListAfterOrder.add(productItemsAfterOrder);
            // Assert.assertEquals(productItemsAfterOrder,productItemsBeforeOrder,
            //      "Ошибка:\nСравнение параметров товара "+ productName+" из склада фильтра №" + filterpointnumber+"\n:");


        }
        Assert.assertEquals(productListAfterOrder,productListBeforeOrder,
                "Сравнение списков параметров товарa [ "+productName+" ] из каждого " +
                        "склада в фильтре:\nОшибка:Параметры товаров не соответствуют:\n");

    }

    @Test(enabled = true , dataProvider = "productList")
    public void OrderWithJuridicalBillAndPickUp(String productName) throws ParseException {
        UserProfilData user = testUserCreate();
        ProductParametrs productListBeforeOrder = app.getProductHelper().getProductsParametrs(productName);
        ProductParametrs productListAfterOrder = new ProductParametrs();
        ArrayList orderNumbers = new ArrayList();

        for (int i = 0; i < productListBeforeOrder.size(); i++) {
            int filterpointnumber = i+1;
            app.getProductHelper().goToProductPage(productName);
            app.getProductHelper().selectPointFromFilter(filterpointnumber);
            app.getProductHelper().addProductToCart();
            app.getProductHelper().goToCart();
            app.getCartHelper().goToOrder();
            app.getOrderHelper().makeOrder(
                    "bill_payment",
                    "delivery_self",
                    user);
            app.getOrderHelper().clickOrder();
            int order_num = app.getOrderInfoHelper().getOrderNumber();
            orderNumbers.add(order_num);
            Assert.assertTrue(app.getOrderInfoHelper().getOrderResult(order_num), "Ошибка формирования заказа");
            ProductData productItemsBeforeOrder = productListBeforeOrder.get(i);
            ProductData productItemsAfterOrder = app.getOrderInfoHelper().getProductData(order_num);
            productListAfterOrder.add(productItemsAfterOrder);
            // Assert.assertEquals(productItemsAfterOrder,productItemsBeforeOrder,
            //      "Ошибка:\nСравнение параметров товара "+ productName+" из склада фильтра №" + filterpointnumber+"\n:");


        }
        Assert.assertEquals(productListAfterOrder,productListBeforeOrder,
                "Сравнение списков параметров товарa [ "+productName+" ] из каждого склада в фильтре:\nОшибка:Параметры товаров не соответствуют:\n");

    }

    @Test(enabled = true , dataProvider = "productList")
    public void OrderWithJuridicalBillAndCourierDelivery(String productName) throws ParseException {
        UserProfilData user = testUserCreate();
        ProductParametrs productListBeforeOrder = app.getProductHelper().getProductsParametrs(productName);
        ProductParametrs productListAfterOrder = new ProductParametrs();
        ArrayList orderNumbers = new ArrayList();

        for (int i = 0; i < productListBeforeOrder.size(); i++) {
            int filterpointnumber = i+1;
            app.getProductHelper().goToProductPage(productName);
            app.getProductHelper().selectPointFromFilter(filterpointnumber);
            app.getProductHelper().addProductToCart();
            app.getProductHelper().goToCart();
            app.getCartHelper().goToOrder();
            app.getOrderHelper().makeOrder(
                    "bill_payment",
                    "delivery_courier",
                    user);
            app.getOrderHelper().clickOrder();
            int order_num = app.getOrderInfoHelper().getOrderNumber();
            orderNumbers.add(order_num);
            Assert.assertTrue(app.getOrderInfoHelper().getOrderResult(order_num), "Ошибка формирования заказа");
            ProductData productItemsBeforeOrder = productListBeforeOrder.get(i);
            ProductData productItemsAfterOrder = app.getOrderInfoHelper().getProductData(order_num);
            productListAfterOrder.add(productItemsAfterOrder);
            // Assert.assertEquals(productItemsAfterOrder,productItemsBeforeOrder,
            //      "Ошибка:\nСравнение параметров товара "+ productName+" из склада фильтра №" + filterpointnumber+"\n:");


        }
        Assert.assertEquals(productListAfterOrder,productListBeforeOrder,
                "Сравнение списков параметров товарa [ "+productName+" ] из каждого склада в фильтре:\nОшибка:Параметры товаров не соответствуют:\n");

    }



}
