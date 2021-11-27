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

public class Task9_1 {

    private WebDriver driver;

    private boolean isElementPresent(By locator) {
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
    public void task9_1() throws Exception{
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("#box-login [name = username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("#box-login [name = password]")).sendKeys("admin");
        driver.findElement(By.cssSelector(".footer [name = login]")).click();
        List<WebElement> countrynames = driver.findElements(By.cssSelector("tr.row a:not([title])"));
        ArrayList <String> countrynamesString = new ArrayList<>();
        for (int a = 0; a < countrynames.size(); a++){
            countrynamesString.add(a, countrynames.get(a).getText());
        }
        ArrayList <String> countrynamessorted = new ArrayList<>();
        countrynamessorted = (ArrayList<String>) countrynamesString.clone();
        Collections.sort(countrynamessorted);
        if (countrynamesString.equals(countrynamessorted) == false){
            throw new Exception("Страны расположены не в алфавитном порядке");
        }
        List <WebElement> countries = driver.findElements(By.cssSelector("tr.row"));
        for(int b = 0; b < countries.size(); b++){
            if(countries.get(b).getAttribute("textContent").contains("0") == false){
                countries.get(b).findElement(By.cssSelector("a:not([title])")).click();
                assertTrue(isElementPresent(By.cssSelector("#table-zones")));
                List <WebElement> zonenames = driver.findElements(By.cssSelector("td [name*=name][name*=zones]"));
                ArrayList <String> zonenamesString = new ArrayList<>();
                for(int c = 0; c < zonenames.size(); c++){
                    zonenamesString.add(c, zonenames.get(c).getText());
                }
                ArrayList <String> zonenamessorted = new ArrayList<>();
                zonenamessorted = (ArrayList<String>) zonenamesString.clone();
                Collections.sort(zonenamessorted);
                if(zonenamesString.equals(zonenamessorted) == false){
                    throw new Exception("Зоны расположены не в алфавитном порядке");
                }
                driver.navigate().back();
                countries = driver.findElements(By.cssSelector("tr.row"));
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}
