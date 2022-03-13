package ru.redramka.zener.dev.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

public class ApplicationManager {
    WebDriver driver;
    private OrderHelper orderHelper;
    private ProductHelper productHelper;
    private CartHelper cartHelper;
    private ProfileHelper profileHelper;
    private OrderInfoHelper orderInfoHelper;
    private String browser;

    public ApplicationManager(String browser){
        this.browser = browser;
    }

    public void init(){
        if (browser == BrowserType.FIREFOX) {
            System.setProperty("webdriver.gecko.driver", "C:\\Geckodriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser == BrowserType.CHROME) {
            System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            driver = new InternetExplorerDriver();
        }

        driver.manage().window().maximize();
        driver.navigate().to("http://dev9.redramka.ru");
        orderHelper = new OrderHelper(driver);
        productHelper = new ProductHelper(driver);
        cartHelper = new CartHelper(driver);
        profileHelper = new ProfileHelper(driver);
        orderInfoHelper = new OrderInfoHelper(driver);

    }

    public void stop() {
        driver.quit();
    }

    public CartHelper getCartHelper() {
        return cartHelper;
    }

    public OrderHelper getOrderHelper() {
        return orderHelper;
    }

    public ProductHelper getProductHelper() {
        return productHelper;
    }

    public ProfileHelper getProfileHelper() {
        return profileHelper;
    }

    public OrderInfoHelper getOrderInfoHelper(){
        return orderInfoHelper;
    }
}
