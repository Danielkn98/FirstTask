package task19;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertTrue;
import static task19.Tools.isElementPresent;


public class ProductPage extends Page {

    public static int quantityToAdd;

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public static void chooseQuantity(int quantity) {
        quantityToAdd = quantity;
        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("[name=quantity]"))).click()
                .keyDown(Keys.CONTROL).sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(Integer.toString(quantityToAdd))
                .perform();
    }

    public static void chooseSize(String sizeIfPossible) {
        if(isElementPresent(By.cssSelector("[name='options[Size]']"))){
            driver.findElement(By.cssSelector("[name='options[Size]']")).click();
            driver.findElement(By.cssSelector("[name='options[Size]'] [value="+sizeIfPossible+"]")).click();
        }
    }

    public static void pressAddtoCart() {
        CartConditionIndicator.getCurrentAmount();
        driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
        assertTrue(CartConditionIndicator.confirmAddingAProducts());
    }
}