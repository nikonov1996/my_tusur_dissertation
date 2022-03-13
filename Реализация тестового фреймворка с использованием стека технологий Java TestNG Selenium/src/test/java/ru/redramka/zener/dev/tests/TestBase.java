package ru.redramka.zener.dev.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.redramka.zener.dev.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void setUp() throws IOException{
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown(){
       // app.stop();
    }

}
