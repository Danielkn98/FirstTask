import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
        List<WebElement> goods = driver.findElements(By.cssSelector("li.product"));
        for (int a = 0; a < goods.size(); a++){
            WebElement UnderVerification = goods.get(a);
            if(UnderVerification.findElements(By.cssSelector(".sticker")).size() != 1){
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
