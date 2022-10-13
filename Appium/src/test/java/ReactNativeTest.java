import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Anitha Thummalapally
 * @since 12/10/22
 */

public class ReactNativeTest {
    public AppiumDriver driver;
    public WebDriverWait wait;

    public static final String appLocation = "https://github.com/anitha-thummalapally/Apps/releases/download/rnfiles/RNAutomationdevelopment_0.0.1.apk";
    public static final String appPackage = "com.wavemaker.rnautomation";
    public static final String appActivity = "com.wavemaker.rnautomation.MainActivity";

    @BeforeMethod
    public void setup() throws MalformedURLException, UnexpectedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", appLocation);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("deviceName","Pixel_3_XL_API_28");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("showChromedriverLog", true);
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("uiautomator2ServerLaunchTimeout", "50000");
        capabilities.setCapability("--session-override", true);
        capabilities.setCapability("noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        wait = new WebDriverWait(driver, 1000);
    }

    @Test
    public void logintesting() throws InterruptedException {

//        //Switching context from NATIVE_APP to WEB_VIEW
//        Set<String> contexts = driver.getContextHandles();
//        for (String context : contexts) {
//            System.out.println(context);
//        }
//        driver.context("WEBVIEW_" + appPackage);

        //Locators
        By userNameTxt = By.xpath("//*[@class='android.widget.EditText' and @text='Enter username']");
        By passwordTxt = By.xpath("//*[@class='android.widget.EditText' and @text='Enter password']");
        By loginButton = By.xpath("//android.view.ViewGroup[@index=4]");
        By passwordTxt1 = By.xpath("//android.widget.EditText[@index=3]");
        By labellocator = By.xpath("//*[@class='android.widget.TextView' and @text='Hello! admin']");

        //Functionality
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameTxt)).click();
        driver.findElement(userNameTxt).sendKeys("admin");
        driver.findElement(passwordTxt).sendKeys("admin");
        driver.findElement(passwordTxt1).click();
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).click();
        if(driver.findElement(loginButton).isDisplayed()){
            System.out.println("login button displayed");
        }
        Thread.sleep(30000);
        Assert.assertTrue(driver.findElement(labellocator).isDisplayed());

    }

    @AfterMethod
    public void tearDown() throws Exception {

        //Gets browser logs if available.
        driver.quit();
    }
}
