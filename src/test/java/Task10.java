import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


public class Task10 {

    private WebDriver driver;

    @Before
    public void start(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void productName() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String NameMP = product.findElement(By.cssSelector("div.name")).getText();
        product.click();
        String NamePP = driver.findElement(By.cssSelector("h1.title")).getText();
        if(NameMP.equals(NamePP)==false){
            throw new Exception("Названия на главной странице и на странице товара не совпадают");
        }
    }

    @Test
    public void productPrices() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        String RegPriceMP = product.findElement(By.cssSelector(".regular-price")).getText();
        String CamPriceMP = product.findElement(By.cssSelector(".campaign-price")).getText();
        product.click();
        String RegPricePP = driver.findElement(By.cssSelector("#box-product .regular-price")).getText();
        String CamPricePP = driver.findElement(By.cssSelector("#box-product .campaign-price")).getText();
        if(RegPriceMP.equals(RegPricePP)==false){
            throw new Exception("Цена без акции на главной странице и на странице товара не совпадают");
        }
        if(CamPriceMP.equals(CamPricePP)==false){
            throw new Exception("Акционная цена на главной странице и на странице товара не совпадают");
        }
    }

    @Test
    public void regPriceStyle() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement ProductPriceMP = driver.findElement(By.cssSelector("#box-campaigns li.product .regular-price"));
        String RegPriceColorMP = ProductPriceMP.getCssValue("color");
        String RegPriceColorMPPrecut = RegPriceColorMP.substring(RegPriceColorMP.indexOf("(")+1);
        String RegPriceColorMPCut = RegPriceColorMPPrecut.substring(0,RegPriceColorMPPrecut.length()-1);
        String[] RPC1 = RegPriceColorMPCut.split(", ");
        int b1 = Integer.valueOf(RPC1[0]);
        for(int a = 1; a < 3; a++){
            if (Integer.valueOf(RPC1[a]) != b1){
                throw new Exception("Цвет цены без акции на главной странице не серый");
            }
        }
        System.out.println(ProductPriceMP.getCssValue("text-decoration-line"));
        if (ProductPriceMP.getCssValue("text-decoration-line").equals("line-through") == false){
            throw new Exception("Цена без акции на главной странице не перечеркнута");
        }
        ProductPriceMP.click();
        WebElement ProductPricePP = driver.findElement(By.cssSelector("#box-product .regular-price"));
        String RegPriceColorPP = ProductPricePP.getCssValue("color");
        String RegPriceColorPPPrecut = RegPriceColorMP.substring(RegPriceColorPP.indexOf("(")+1);
        String RegPriceColorPPCut = RegPriceColorPPPrecut.substring(0,RegPriceColorPPPrecut.length()-1);
        String[] RPC2 = RegPriceColorPPCut.split(", ");
        int b2 = Integer.valueOf(RPC2[0]);
        for(int a = 1; a < 3; a++){
            if (Integer.valueOf(RPC1[a]) != b2){
                throw new Exception("Цвет цены без акции на странице товара не серый");
            }
        }
        if (ProductPricePP.getCssValue("text-decoration-line").equals("line-through") == false) {
            throw new Exception("Цена без акции на странице товара не перечеркнута");
        }
    }

    @Test
    public void camPriceStyle() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement ProductPriceMP = driver.findElement(By.cssSelector("#box-campaigns li.product .campaign-price"));
        String CamPriceColorMP = ProductPriceMP.getCssValue("color");
        String CamPriceColorMPPrecut = CamPriceColorMP.substring(CamPriceColorMP.indexOf("(")+1);
        String CamPriceColorMPCut = CamPriceColorMPPrecut.substring(0,CamPriceColorMPPrecut.length()-1);
        String[] CPC1 = CamPriceColorMPCut.split(", ");
        if (Integer.valueOf(CPC1[1]) != 0 || Integer.valueOf(CPC1[2]) != 0 ){
            throw new Exception("Цвет акционной цены на главной странице не красный");
        }
        String FontMP = ProductPriceMP.getCssValue("font-weight");
        if (FontMP.equals("700") == false && FontMP.equals("800") == false && FontMP.equals("900") == false){
            throw new Exception("Акционная цена на главной странице не выделена жирным");
        }
        ProductPriceMP.click();
        WebElement ProductPricePP = driver.findElement(By.cssSelector("#box-product .campaign-price"));
        String CamPriceColorPP = ProductPricePP.getCssValue("color");
        String CamPriceColorPPPrecut = CamPriceColorMP.substring(CamPriceColorPP.indexOf("(")+1);
        String CamPriceColorPPCut = CamPriceColorPPPrecut.substring(0,CamPriceColorPPPrecut.length()-1);
        String[] CPC2 = CamPriceColorPPCut.split(", ");
        if (Integer.valueOf(CPC2[1]) != 0 || Integer.valueOf(CPC2[2]) != 0 ){
            throw new Exception("Цвет акционной цены на странице товара не красный");
        }
        String FontPP = ProductPricePP.getCssValue("font-weight");
        if (FontPP.equals("700") == false && FontPP.equals("800") == false && FontPP.equals("900") == false) {
            throw new Exception("Акционная цена на странице товара не выделена жирным");
        }
    }

    @Test
    public void priceSizeComparison() throws Exception{
        driver.get("http://localhost/litecart");
        WebElement RegPriceMP = driver.findElement(By.cssSelector("#box-campaigns li.product .regular-price"));
        WebElement CamPriceMP = driver.findElement(By.cssSelector("#box-campaigns li.product .campaign-price"));;
        Dimension RegPriceMPD = RegPriceMP.getSize();
        Dimension CamPriceMPD = CamPriceMP.getSize();
        if(RegPriceMPD.getWidth() >= CamPriceMPD.getWidth() || RegPriceMPD.getHeight() >= CamPriceMPD.getHeight()){
            throw new Exception("На главной странице размер цены без акции больше или такой же как у акционной");
        }
        RegPriceMP.click();
        WebElement RegPricePP = driver.findElement(By.cssSelector("#box-product .regular-price"));
        WebElement CamPricePP = driver.findElement(By.cssSelector("#box-product .campaign-price"));
        Dimension RegPricePPD = RegPricePP.getSize();
        Dimension CamPricePPD = CamPricePP.getSize();
        if(RegPricePPD.getWidth() >= CamPricePPD.getWidth() || RegPricePPD.getHeight() >= CamPricePPD.getHeight()){
            throw new Exception("На странице товара размер цены без акции больше или такой же как у акционной");
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
