import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @Test
    public void firstTest(){
        driver.get("https://www.bing.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.id("search_icon")).click();
        wait.until(titleIs("webdriver - Поиск"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}




