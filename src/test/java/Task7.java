import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.By.*;

public class Task7 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void task7(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("#box-login [name = username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("#box-login [name = password]")).sendKeys("admin");
        driver.findElement(By.cssSelector(".footer [name = login]")).click();
        List<WebElement> appRows = driver.findElements(cssSelector("#box-apps-menu #app-"));
        for (int a = appRows.size(), b = 0; a > 0; a--, b++){
            driver.findElements(cssSelector("#box-apps-menu #app-")).get(b).click();
            driver.findElement(By.cssSelector("h1"));
            if(driver.findElements(By.cssSelector("#box-apps-menu #app- .docs"))!=null){
                for(int c = driver.findElements(By.cssSelector("#box-apps-menu #app- .docs")).size(), d = 0; c > 0; c--, d++ ){
                    driver.findElements(By.cssSelector("#box-apps-menu #app- .docs")).get(d).click();
                    driver.findElement(By.cssSelector("h1"));
                }
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
