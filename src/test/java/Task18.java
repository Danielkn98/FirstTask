import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.BrowserUpProxyServer;
import com.browserup.bup.client.ClientUtil;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.Har;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task18 {

    public WebDriver driver;
    public WebDriverWait wait;
    public BrowserUpProxy proxy;

    @Before
    public void start(){
        proxy = new BrowserUpProxyServer();
        proxy.start();
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(586856, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    }

    @Test
    public void task18(){
        proxy.newHar();
        driver.get("http://www.google.com/");
        driver.findElement(By.cssSelector("input[title='Поиск']")).sendKeys("ABRACADABRA" + Keys.ENTER);
        wait.until(titleIs("ABRACADABRA - Поиск в Google"));
        Har har = proxy.getHar();
        har.getLog().getEntries().forEach(e -> System.out.println(e.getResponse().getStatus()+" : "+e.getRequest().getUrl()));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
