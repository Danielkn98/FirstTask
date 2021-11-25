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
        for (int a = 0; a < driver.findElements(cssSelector("#box-apps-menu #app-")).size(); a++){
            driver.findElements(cssSelector("#box-apps-menu #app-")).get(a).click();
            driver.findElement(By.cssSelector("h1"));
            if(driver.findElements(By.cssSelector("#box-apps-menu #app- .docs")).size()!=0){
                for(int b = 0; b < driver.findElements(By.cssSelector("#box-apps-menu #app- .docs li")).size(); b++){
                    driver.findElements(By.cssSelector("#box-apps-menu #app- .docs li")).get(b).click();
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
