package TestCase;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Base {
    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }



        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + "found");

        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
          File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
          File screen = new File("screen-" + System.currentTimeMillis() + ".png");
            try {
                com.google.common.io.Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(screen);
        }
    }



    public static ThreadLocal<EventFiringWebDriver> tlDriver = new ThreadLocal<>();
    public  EventFiringWebDriver driver;
    public  WebDriverWait wait;

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10); //много потоковый запуск тестов
            return;
        }

        driver =new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 10);

        Runtime.getRuntime().addShutdownHook(
                new Thread(()-> { driver.quit(); driver = null; }));
    }

    @After
    public void stop() {
        //driver.quit();
        //driver = null
    }

}
