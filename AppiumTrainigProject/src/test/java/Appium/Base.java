package Appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Base {

  public AndroidDriver driver;

  @BeforeTest
  public  void Intialize(){

      try{

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

          driver = new AndroidDriver(remoteUrl, desiredCapabilities);

      }
      catch (Exception ex)
      {
          ex.printStackTrace();
      }

  }

  @Test
  public void AcceptTerms ()
  {
      try {
          driver.findElement(By.id("terms_accept")).click();
      }
      catch (Exception ex)
      {
          ex.printStackTrace();
      }
  }


  @AfterTest
  public void TearDown(){
    if(driver!= null)
    {
        driver.quit();
    }
  }

}
