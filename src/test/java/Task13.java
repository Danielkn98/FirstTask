import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class Task13 {

    private WebDriver driver;
    private WebDriverWait wait;

    public static final String USERNAME = "bsuser_92bISj";
    public static final String AUTOMATE_KEY = "QdbzETDDgoFuDnCRQwLX";
    public static final String MyURL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

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
    public void start() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "FireFox");
        capabilities.setCapability("browserVersion", "latest");

        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");

        capabilities.setCapability("bstack:options", browserstackOptions);

        driver = new RemoteWebDriver(new URL(MyURL), new DesiredCapabilities(capabilities));
        wait = new WebDriverWait(driver,10);
    }

    @Test
    public void task13(){
        driver.get("https://litecart.stqa.ru");
        int numberOfProducts = 3;
        for (int a = 0; a<numberOfProducts; a++) {
            List<WebElement> productsMP = driver.findElements(By.cssSelector("#box-most-popular li"));
            productsMP.get(0).click();
            WebElement quantityInCart = driver.findElement(By.cssSelector("#cart .quantity"));
            String quantity = quantityInCart.getAttribute("textContent");
            int intQ = Integer.valueOf(quantity);
            if(isElementPresent(By.cssSelector("[name='options[Size]']"))){
                driver.findElement(By.cssSelector("[name='options[Size]']")).click();
                driver.findElement(By.cssSelector("[name='options[Size]'] [value=Medium]")).click();
            }
            driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
            wait.until(ExpectedConditions.attributeContains(quantityInCart, "textContent", Integer.toString(intQ + 1)));
            if(a==numberOfProducts-1){
                driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
            }else{
                driver.navigate().back();
            }
        }
        wait.until(titleIs("Checkout | My Store"));
        WebElement CheckoutSummary = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List <WebElement> CheckoutSummaryProducts = CheckoutSummary.findElements(By.cssSelector("td.item"));
        int typesOfProductsInCart = CheckoutSummaryProducts.size();
        while (true){
            if(isElementPresent(By.cssSelector("button[value=Remove]"))){
                WebElement removeButton = driver.findElement(By.cssSelector("button[value=Remove]"));
                wait.until(visibilityOf(removeButton));
                removeButton.click();
                wait.until(numberOfElementsToBe(By.cssSelector("td.item"), typesOfProductsInCart-1));
                typesOfProductsInCart--;
                continue;
            }
            else{
                wait.until(ExpectedConditions.stalenessOf(CheckoutSummary));
                break;
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
