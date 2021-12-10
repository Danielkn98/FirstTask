package task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static task19.Tools.isElementPresent;

public class CartPage extends Page{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public static void open() {
        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
        wait.until(titleIs("Checkout | My Store"));
    }

    public static void removeAllProducts() {
        WebElement CheckoutSummary = driver.findElement(By.cssSelector("#box-checkout-summary"));
        List<WebElement> CheckoutSummaryProducts = CheckoutSummary.findElements(By.cssSelector("td.item"));
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

}
