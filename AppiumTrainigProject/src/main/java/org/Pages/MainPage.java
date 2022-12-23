package org.Pages;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.utilities.CustomException;

public class MainPage {

    private static AndroidDriver _driver;
    public static By txtSearchBox = By.id("search_box_text");
    public static By btnAccept = By.id("terms_accept");
    public static By statisticCheckBox = By.id("send_report_checkbox");
    public static By link_NoThanks = By.id("negative_button");
    public static By webView = By.id("//android.widget.FrameLayout[@content-desc='Web View']");
    public static By urlBar = By.id("url_bar");

    public MainPage(AndroidDriver driver)
    {
        _driver = driver;
    }



    //This method will click on any element based on provided selectors
    public void clickElement(By selector, String elementName) throws Exception {
        //Check if the desired element exists or not
        boolean isElementPresent = isElementAvailable(selector);

        if (isElementPresent) {

            //click on element if it exists
            _driver.findElement(selector).click();
        } else {
            throw new CustomException("Unable to click on "+elementName + " Element not found");
        }
    }
    //This method will enter text to the fields based on provided selector
    public void enterText(By selector, String text, String elementName) throws CustomException {

        boolean isElementExist = isElementAvailable(selector);

        if (isElementExist) {
            if (text != null)
                _driver.findElement(selector).sendKeys(text);
        } else {
            throw new CustomException(elementName + " Is not found");
        }


    }
    //this method will return attributes of an element based on the arguments passed to the methods
    public String getAttribute(By selector, String atr, String elementName) throws CustomException {
        String attribute;
        boolean isElementPresent = isElementAvailable(selector);
        if(isElementPresent)
        {
            attribute = _driver.findElement(selector).getAttribute(atr);
        }
        else{

            throw new CustomException(elementName + " Not found");
        }

        return attribute;
    }
    //this method will check and return boolean value for the availability of elements
    public boolean isElementAvailable(By selector)
    {

        return  _driver.findElement(selector).isDisplayed();
    }

}
