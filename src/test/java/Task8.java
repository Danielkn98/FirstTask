import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task8 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void task8() throws Exception {
        driver.get("http://localhost/litecart");
        for (int a = 0; a < driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")).size(); a++){
            WebElement UnderVerification = driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")).get(a);
            if(UnderVerification.findElements(By.cssSelector("[class*=sticker]")).size() != 1){
                throw new Exception("На каждый товар не приходится по одному стикеру");
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
