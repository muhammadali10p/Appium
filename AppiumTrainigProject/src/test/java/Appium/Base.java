package Appium;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.Pages.MainPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.net.URL;
import java.time.Duration;
public class Base {

    //Android driver instance
    public static AndroidDriver driver;
    public static  ExtentReports extentReports;
    public static final Logger logger = LogManager.getLogger("Log4j2properties");
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest extentTestCase;
    public static MainPage mainPage;


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

            sparkReporter = new ExtentSparkReporter("Report.html");
            extentReports.attachReporter(sparkReporter);
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            mainPage = new MainPage(driver);
        } catch (Exception ex) {

            logger.error(ex.getMessage());
        }

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            extentReports.flush();
        }
    }

}
