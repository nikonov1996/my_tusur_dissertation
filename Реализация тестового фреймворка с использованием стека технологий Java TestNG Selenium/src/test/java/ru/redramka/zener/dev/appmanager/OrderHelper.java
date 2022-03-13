package ru.redramka.zener.dev.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.redramka.zener.dev.model.UserProfilData;

public class OrderHelper extends HelperBase {
    public OrderHelper(WebDriver driver) { super(driver); }
    WebDriverWait wait = new WebDriverWait(driver, 30);
    Actions action = new Actions(driver);


    public void makeOrder(String payment,
                          String delivery,
                          UserProfilData user){
        if (payment.equals("bank_payment") && delivery.equals("delivery_courier")){
            fillUserForm(
                    user.getUserName(),
                    user.getUserEmail(),
                    user.getUserPhone());
            chooseCity(user.getUserCity());
            checkDeliveryCourier();
            fillAddressForm(
                    user.getUserMailIndex(),
                    user.getUserStreet(),
                    user.getUserHouseNumber(),
                    user.getUserRoomNumber());
            checkBankPayment();
            clickOrder();

        }else if (payment.equals("bank_payment") && delivery.equals("delivery_self")){
            fillUserForm(
                    user.getUserName(),
                    user.getUserEmail(),
                    user.getUserPhone());
            chooseCity(user.getUserCity());
            checkDeliverySelf();
            chooseDeliveryPoint();
            checkBankPayment();
            clickOrder();

        }else if (payment.equals("bill_payment") && delivery.equals("delivery_courier")){
            fillUserForm(
                    user.getUserName(),
                    user.getUserEmail(),
                    user.getUserPhone());
            chooseCity(user.getUserCity());
            checkDeliveryCourier();
            fillAddressForm(
                    user.getUserMailIndex(),
                    user.getUserStreet(),
                    user.getUserHouseNumber(),
                    user.getUserRoomNumber());
            checkBillPayment();
            fillBillForm(
                    user.getUserINN(),
                    user.getUserCompany());
            clickOrder();
        }else if (payment.equals("bill_payment") && delivery.equals("delivery_self")){
            fillUserForm(
                    user.getUserName(),
                    user.getUserEmail(),
                    user.getUserPhone());
            chooseCity(user.getUserCity());
            checkDeliverySelf();
            chooseDeliveryPoint();
            checkBillPayment();
            fillBillForm(
                    user.getUserINN(),
                    user.getUserCompany());
            clickOrder();
        }
    }

    public void chooseCity(String city){
        WebElement city_input = driver.findElement(By.cssSelector("#order_city"));
        if (!city_input.getText().equals(city)){
            city_input.clear();
            type(By.cssSelector("#order_city"),city);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//ul[@id='ui-id-1']/li")));
            action.moveToElement(driver.findElement(By.xpath(".//ul[@id='ui-id-1']/li")));
            click(By.xpath(".//ul[@id='ui-id-1']/li[1]"));
        }
    }

    public void checkDeliverySelf(){
        WebElement delivery_self_checkbox = driver.findElement(By.cssSelector(".sq-checks .check-delivery-self .checkbox"));
        if (!delivery_self_checkbox.isSelected()){
            delivery_self_checkbox.click();
        }
    }

    public void checkDeliveryCourier(){
        WebElement delivery_courier_checkbox = driver.findElement(By.cssSelector(".sq-checks .check-delivery-courier .checkbox"));
        if (!delivery_courier_checkbox.isSelected()){
            delivery_courier_checkbox.click();
        }
    }

    public void fillUserForm(String name,
                             String email,
                             String phone){
        type(By.cssSelector("input[name='full_name']"),name);
        type(By.cssSelector("input[name='email']"),email);
        type(By.cssSelector("input[name='phone']"),phone);
    }

    public void chooseDeliveryPoint(){
        WebElement bttn_change_point = driver.findElement(By.cssSelector(".map-point .j-choice-pvz"));
        if (bttn_change_point.getText().contains("Выбрать")){
            WebElement modal = driver.findElement(By.cssSelector("#modal-map-select"));
            click(By.cssSelector(".hold-name .btn"));
            wait.until(ExpectedConditions.visibilityOf(modal));
            click(By.cssSelector("#modal-map-select .btn.modal-save.btn-green"));
            wait.until(ExpectedConditions.invisibilityOf(modal));
        }
    }

    public void checkBankPayment(){
        WebElement bank_payment_checkbox = driver.findElement(By.cssSelector(".sq-checks .check-payment-card .checkbox"));
        if (!bank_payment_checkbox.isSelected()){
            bank_payment_checkbox.click();
        }
    }

    public void checkBillPayment(){
        WebElement bill_payment_checkbox = driver.findElement(By.cssSelector(".sq-checks .check-payment-jur .checkbox"));
        if (!bill_payment_checkbox.isSelected()){
            bill_payment_checkbox.click();
        }
    }

    public void fillBillForm(String inn,
                             String company){
        WebElement bill_form = driver.findElement(By.cssSelector(".wrap.check-payment-jur.contact-information__wrap--organization"));
        wait.until(ExpectedConditions.visibilityOf(bill_form));
        type(bill_form.findElement(By.cssSelector("input[name='orgname']")),company);
        type(bill_form.findElement(By.cssSelector("input[name='inn']")),inn);

    }

    public void fillAddressForm(String index,
                                String street,
                                String house,
                                String room){
        WebElement address_form = driver.findElement(By.cssSelector(".wrap.check-delivery-courier"));
        wait.until(ExpectedConditions.visibilityOf(address_form));
        type(address_form.findElement(By.cssSelector("input[name='zip_code']")),index);
        type(address_form.findElement(By.cssSelector("input[name='street']")),street);
        type(address_form.findElement(By.cssSelector("input[name='house']")),house);
        type(address_form.findElement(By.cssSelector("input[name='room']")),room);
    }

    public void clickOrder(){
        click(By.cssSelector(".total button"));
    }







}