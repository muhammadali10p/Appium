package Appium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.utilities.CustomException;

import java.net.URL;
import java.time.Duration;
public class Base {

    //Android driver instance
    public static AndroidDriver driver;
    public static  ExtentReports extentReports;
    public static ExtentSparkReporter sparkReporter;
    //elements Selectors are under this editor folder
    //<editor-fold desc="Elements">
    public By btnAccept = By.id("terms_accept");
    public By statisticCheckBox = By.id("send_report_checkbox");
    public By link_NoThanks = By.id("negative_button");
//</editor-fold>
    @BeforeClass
    public void initialize() {

        try {

            //<editor-folder desc="Capabilities">
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("appium:platformVersion", "10");
            desiredCapabilities.setCapability("appium:deviceName", "Device1");
            desiredCapabilities.setCapability("appium:appPackage", "com.android.chrome");
            desiredCapabilities.setCapability("appium:appActivity", "com.google.android.apps.chrome.Main");
            desiredCapabilities.setCapability("appium:automationName", "uiAutomator2");
            desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
            desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
            desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
            desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
            URL remoteUrl = new URL("http://0.0.0.0:4723/wd/hub");
            //</editor-folder>

            extentReports = new ExtentReports();
            sparkReporter = new ExtentSparkReporter("html.report");
            extentReports.attachReporter(sparkReporter);
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));


        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extentReports.flush();
        }
    }
    //This method will click on any element based on provided selectors
    public static void clickElement(By selector, String elementName) throws Exception {
        //Check if the desired element exists or not
        boolean isElementPresent = driver.findElement(selector).isDisplayed();

        if (isElementPresent) {

            //click on element if it exists
            driver.findElement(selector).click();
        } else {
            throw new CustomException("Unable to click on "+elementName + " Element not found");
        }
    }
    //This method will enter text to the fields based on provided selector
    public static void enterText(By selector, String text, String elementName) throws CustomException {

        boolean isElementExist = driver.findElement(selector).isDisplayed();

        if (isElementExist) {
            if (text != null)
                driver.findElement(selector).sendKeys(text);
        } else {
            throw new CustomException(elementName + " Is not found");
        }


    }
    //this method will return attributes of an element based on the arguments passed to the methods
    public static String getAttribute(By Selector, String atr, String elementName) throws CustomException {
        String attribute;
        boolean isElementPresent = driver.findElement(Selector).isDisplayed();
        if(isElementPresent)
        {
            attribute = driver.findElement(Selector).getAttribute(atr);
        }
        else{

            throw new CustomException(elementName + " Not found");
        }

        return attribute;
    }
}
