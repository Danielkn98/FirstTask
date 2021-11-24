import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class ThirdTestIE {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void thirdTestIE(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
