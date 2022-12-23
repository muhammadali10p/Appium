package Appium;

import org.testng.annotations.Test;
import org.utilities.CustomException;

public class FirstTestCases extends Base {

    @Test
    public void continueWithoutLogin() {
        // extentTestCase = reporter.createTest("Continue to google without login");
        try {

            //variable to check static consent check box is checked or not;
            String isStaticChecked = getAttribute(statisticCheckBox,"checked","Statistic Checkbox");
            //if it is not checked enter the else block and check it before proceeding
            if (isStaticChecked == "true") {
                //click if the checkbox is not checked
                clickElement(statisticCheckBox,"Statistic Checkbox");
            }
            //Accept the terms and continue
            clickElement(btnAccept,"Accept button on initial screen");
            //Clicks on no thanks link
            clickElement(link_NoThanks,"No thanks button");

        }
        catch (CustomException ex)
        {
            //extentTestCase.log(Status.FAIL, ex.getMessage());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
