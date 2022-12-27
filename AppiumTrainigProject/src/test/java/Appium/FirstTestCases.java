package Appium;
import com.aventstack.extentreports.Status;
import org.Pages.MainPage;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.utilities.CustomException;

public class FirstTestCases extends Base {




    @Test
    public void continueWithoutLogin(){
        extentTestCase = extentReports.createTest("Continue to google without login");
        try {

            //variable to check static consent check box is checked or not;
            String isStaticChecked = mainPage.getAttribute(MainPage.statisticCheckBox,"checked","Statistic Checkbox");
            //if it is not checked enter the else block and check it before proceeding
            if (isStaticChecked.equals("false")) {
                //click if the checkbox is not checked
                mainPage.clickElement(MainPage.statisticCheckBox,"Statistic Checkbox");
            }
            //Accept the terms and continue
            mainPage.clickElement(MainPage.btnAccept,"Accept button on initial screen");
            //Clicks on No_Thanks link
            mainPage.clickElement(MainPage.link_NoThanks,"No thanks button");

            boolean isElementAvailable = mainPage.isElementAvailable(MainPage.txtSearchBox);
            if(isElementAvailable)
            {
                extentTestCase.log(Status.PASS,"Test case passed");
            }
            else
            {
                extentTestCase.log(Status.FAIL,"Search text box not found");
            }

        }
        catch (CustomException ex)
        {
            extentTestCase.log(Status.FAIL, ex.getMessage());
        }
        catch (Exception ex) {
            extentTestCase.log(Status.FAIL,"Ops! Something went wrong please contact QA automation team");
            logger.error(ex.getMessage());
        }
    }

    @Test
    public void searchInChrome()
    {
        extentTestCase = extentReports.createTest("Search text in chrome");
        try
        {
            mainPage.enterText(MainPage.txtSearchBox,"Orange Automation","Global Search box");
            driver.findElement(MainPage.urlBar).sendKeys(Keys.ENTER);

            if(mainPage.isElementAvailable(MainPage.webView))
            {
                extentTestCase.log(Status.PASS,"Search Text Test case is passed");
            }
            else
            {
                extentTestCase.log(Status.FAIL,"Could not search for provided text");
            }
        }
        catch (CustomException ex)
        {
            extentTestCase.log(Status.FAIL, ex.getMessage());
        }
        catch (Exception ex) {
            extentTestCase.log(Status.FAIL,"Ops! Something went wrong please contact QA automation team");
            logger.info(ex.getMessage());
        }

    }


}
