package TestCase;

import junit.framework.TestCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
public class MyFirstTest extends Base {

    //private WebDriver driver;
    //private WebDriverWait wait=new WebDriverWait(driver, 10);


    @Test
    public void OpenPage() {
        driver.get("http://google.com");
        driver.findElement(By.name ("q")).sendKeys("webdriver");
        driver.findElement(By.name ("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleIs("webdriver - Поиск в Google"));
    }


    @Test
    public void mySecondTest() {
        driver.navigate().to("http://google.com");
        driver.findElement(By.className("gb_b gb_bc")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleIs("hi"));
    }
    @Test
    public void myThirdTest() {
        driver.close();

    }
}
