import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Task9_2 {

    private WebDriver driver;

    private boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void start(){

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void task9_2() throws Exception{
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.cssSelector("#box-login [name = username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("#box-login [name = password]")).sendKeys("admin");
        driver.findElement(By.cssSelector(".footer [name = login]")).click();
        List<WebElement> countrynames = driver.findElements(By.cssSelector("[name=geo_zones_form] td a:not([title])"));
        for(int a = 0; a < countrynames.size(); a++){
            driver.findElements(By.cssSelector("[name=geo_zones_form] td a:not([title])")).get(a).click();
            assertTrue(isElementPresent(By.cssSelector("#table-zones")));
            List<WebElement> zonenames = driver.findElements(By.cssSelector("select[name*=zone_code]"));
            ArrayList<String> zonenamesString = new ArrayList<>();
            for (int b = 0; b < zonenames.size(); b++){
                zonenamesString.add(zonenames.get(b).getText());
            }
            ArrayList<String> zonenamesSorted = new ArrayList<>();
            zonenamesSorted = (ArrayList<String>) zonenamesString.clone();
            Collections.sort(zonenamesSorted);
            if(zonenamesString.equals(zonenamesSorted) == false){
                throw new Exception("Зоны расположены не в алфавитном порядке");
            }
            driver.navigate().back();
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
