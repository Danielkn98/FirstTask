package task19;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static task19.Tools.isElementPresent;

public class MainPage extends Page{

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public static String prodBox = "";

    public static void open() {
        driver.get("http://localhost/litecart");
    }

    public static void chooseProductCategory(String productCategory) throws Exception {

        if(productCategory.equals("Most Popular")){
            prodBox = "#box-most-popular ";
        }else {
            if (productCategory.equals("Campaigns")){
                prodBox = "#box-campaigns ";
            }
            else {
                if (productCategory.equals("Latest Products")){
                    prodBox = "#box-latest-products ";
                }else {
                    throw new Exception("Была неправильно введена категория товара!");
                }
            }
        }
    }

    public static void chooseTheFirstProduct() {
        List<WebElement> productsMP = driver.findElements(By.cssSelector(prodBox + "li"));
        productsMP.get(0).click();
        assertTrue(isElementPresent(By.cssSelector("#box-product")));
    }
}