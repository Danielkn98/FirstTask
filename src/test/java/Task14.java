import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Task14 {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    private ExpectedCondition<String> newWindowOtherThan(Set<String> oldWindows){
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                Set<String> allWindows = driver.getWindowHandles();
                allWindows.removeAll(oldWindows);
                if(allWindows.size() > 0){
                    return allWindows.iterator().next();
                }
                else{
                    return null;
                }
            }
        };
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void task14(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("#box-login [name = username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("#box-login [name = password]")).sendKeys("admin");
        driver.findElement(By.cssSelector(".footer [name = login]")).click();
        assertTrue(isElementPresent(By.cssSelector("#sidebar")));
        driver.findElement(By.cssSelector("#sidebar [href*=countries]")).click();
        assertTrue(isElementPresent(By.cssSelector("form[name=countries_form]")));
        driver.findElement(By.cssSelector("a.button[href*=country]")).click();
        assertTrue(isElementPresent(By.cssSelector("#content form")));
        List<WebElement> Links = driver.findElements(By.cssSelector("i[class*=external-link]"));
        String SourceWindow = driver.getWindowHandle();
        Set <String> ExistingWindows = driver.getWindowHandles();
        for(int a = 0; a < Links.size(); a++){
            Links.get(a).click();
            String newWindow = wait.until(newWindowOtherThan(ExistingWindows));
            driver.switchTo().window(newWindow);
            assertTrue(isElementPresent(By.cssSelector("title")));
            System.out.println((a+1)+". "+driver.getTitle());
            driver.close();
            driver.switchTo().window(SourceWindow);
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
