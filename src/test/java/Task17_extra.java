import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Task17_extra {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start(){

        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable("browser", Level.ALL);

        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void task17_extra(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("#box-login [name = username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("#box-login [name = password]")).sendKeys("admin");
        driver.findElement(By.cssSelector(".footer [name = login]")).click();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.manage().logs().get("browser").forEach(log -> System.out.println(log));
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='&product'][title]"));
        for(int a = 0; a < products.size(); a++){
            products.get(a).click();
            String ProductName = driver.findElement(By.cssSelector("input[name='name[en]']")).getAttribute("value");
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            if(logs.size()!=0){
                System.out.println("При открытии страницы редактирования товара "+ProductName+" в логе браузера появились следующие сообщения:");
                logs.forEach(log -> System.out.println(log));
            }
            driver.navigate().back();
            products = driver.findElements(By.cssSelector("a[href*='&product'][title]"));
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}


