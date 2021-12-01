import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task12 {

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
    private boolean Checked(By locator){
        WebElement checkbox = driver.findElement(locator);
        if(checkbox.getAttribute("checked")==null){
            return false;
        }
        else{
            if(checkbox.getAttribute("checked").contains("true")) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,30);
    }

    @Test
    public void task12() throws IOException {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("#box-login [name = username]")).sendKeys("admin");
        driver.findElement(By.cssSelector("#box-login [name = password]")).sendKeys("admin");
        driver.findElement(By.cssSelector(".footer [name = login]")).click();

        assertTrue(isElementPresent(By.cssSelector("#sidebar")));
        driver.findElement(By.cssSelector("#sidebar [href*=catalog]")).click();
        driver.findElement(By.cssSelector("[href*=edit_product]")).click();

        assertTrue(isElementPresent(By.cssSelector("#tab-general")));
        WebElement generalSet = driver.findElement(By.cssSelector("#tab-general"));
        generalSet.findElement(By.cssSelector("[name=status][value='1']")).click();
        generalSet.findElement(By.cssSelector("[name='name[en]']")).sendKeys("Black Duck");
        generalSet.findElement(By.cssSelector("[name=code]")).sendKeys("RD911");

        if(Checked(By.cssSelector("[data-name='Root']"))==false){
            generalSet.findElement(By.cssSelector("[data-name=Root]")).click();
        }
        if(Checked(By.cssSelector("[data-name='Rubber Ducks']"))==false){
            generalSet.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();
        }
        if(Checked(By.cssSelector("[data-name=Subcategory]"))==true){
            generalSet.findElement(By.cssSelector("[data-name=Subcategory]")).click();
        }
        /* Далее, в разделе "для кого товар", хочу отметить все чекбоксы, проверяю их значения прямо в цикле, т.к
         поиск по локатору внутри уже не осуществляется и так проще */
        List<WebElement> ProdGroups = generalSet.findElements(By.cssSelector("[name='product_groups[]']"));
        for(int a = 0; a < ProdGroups.size(); a++){
            if(ProdGroups.get(a).getAttribute("checked")==null || ProdGroups.get(a).getAttribute("checked").contains("true")==false){
                ProdGroups.get(a).click();
            }
        }

        new Actions(driver)
                .moveToElement(generalSet.findElement(By.cssSelector("[name=quantity]")))
                .click()
                        .keyDown(Keys.CONTROL).sendKeys("a")
                        .keyUp(Keys.CONTROL)
                                .sendKeys(Keys.DELETE)
                                        .sendKeys("10")
                                                .perform();
        generalSet.findElement(By.cssSelector("[name=quantity_unit_id] option[value='1']")).click();
        generalSet.findElement(By.cssSelector("[name=delivery_status_id] option[value='1']")).click();
        generalSet.findElement(By.cssSelector("[name=sold_out_status_id] option[value='2']")).click();

        File b = new File("../FirstTask/Upload/Black-Rubber-Duck.jpg");
        String absolute = b.getCanonicalPath();
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(absolute);

        generalSet.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("02122021");
        generalSet.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("25022022");

        driver.findElement(By.cssSelector("[href='#tab-information']")).click();
        assertTrue(isElementPresent(By.cssSelector("#tab-information")));
        WebElement informationSet = driver.findElement(By.cssSelector("#tab-information"));
        informationSet.findElement(By.cssSelector("[name=manufacturer_id] option[value='1']")).click();
        informationSet.findElement(By.cssSelector("[name=supplier_id] option[value]")).click(); // Этот клик можно не делать на данный момент
        informationSet.findElement(By.cssSelector("[name=keywords]")).sendKeys("Duck Black Rubber");
        informationSet.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("Black rubber duck for your bathroom");
        informationSet.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Wonderful black rubber duck for you to swim with while taking bath. It will never be out of style and even your grandchildren will be able to enjoy its company. Made of high-quality materials, dermatology tested");
        informationSet.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys("Black_duck");
        informationSet.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys("Brand new black rubber duck for your family");

        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();
        assertTrue(isElementPresent(By.cssSelector("#tab-prices")));
        WebElement priceSet = driver.findElement(By.cssSelector("#tab-prices"));
        new Actions(driver)
                .moveToElement(priceSet.findElement(By.cssSelector("[name=purchase_price]"))).click()
                        .keyDown(Keys.CONTROL).sendKeys("a")
                        .keyUp(Keys.CONTROL)
                                .sendKeys(Keys.DELETE)
                                        .sendKeys("14")
                                                .perform();
        priceSet.findElement(By.cssSelector("[name*='currency_code'] option[value='USD']")).click();
        priceSet.findElement(By.cssSelector("[name='tax_class_id'] option[value]")).click();
        new Actions(driver)
                .moveToElement(priceSet.findElement(By.cssSelector("[name='gross_prices[USD]']"))).click()
                        .keyDown(Keys.CONTROL).sendKeys("a")
                        .keyUp(Keys.CONTROL)
                                .sendKeys(Keys.DELETE)
                                        .sendKeys("25")
                                                .perform();
        new Actions(driver)
                .moveToElement(priceSet.findElement(By.cssSelector("[name='gross_prices[EUR]']"))).click()
                        .keyDown(Keys.CONTROL).sendKeys("a")
                        .keyUp(Keys.CONTROL)
                                .sendKeys(Keys.DELETE)
                                        .sendKeys("22,09")
                                                .perform();

        driver.findElement(By.cssSelector("[name=save]")).click();
        assertTrue(isElementPresent(By.cssSelector("[name=catalog_form]")));
        List<WebElement> categories = driver.findElements(By.cssSelector("a[href*='category']"));
        for(int a = 0; a < categories.size(); a++){
            if(categories.get(a).getAttribute("textContent")!=null && categories.get(a).getAttribute("textContent").contains("Rubber ducks")){
                categories.get(a).click();
            }
        }
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='&product']"));
        for(int a = 0; a < products.size(); a++){
            if(products.get(a).getAttribute("textContent")!=null && products.get(a).getAttribute("textContent").contains("Black Duck")){
                products.get(a).click();
                break;
            }
        }
        wait.until(titleIs("Edit Product: Black Duck | My Store"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
