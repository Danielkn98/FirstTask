package task19;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Tools {

    private static WebDriver driver;

    public static boolean isElementPresent(By locator){
        driver = Page.driver;
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }
}

