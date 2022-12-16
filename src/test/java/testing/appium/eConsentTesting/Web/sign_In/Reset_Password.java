package testing.appium.eConsentTesting.Web.sign_In;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.password;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener.class)
public class Reset_Password extends BaseTestSetWeb {

//        TODO waiting for TC Data
    private final String appUrl = "https://us-participant-auth.se.qav2.researchbinders.com/#/reset-password?resetPrinciple=mobile.automation+active_android@florencehc.com&resetToken=IwrmUm8KObGzThJPaqN9kFMx8g7CxaZF%2FpEqJ7WEXte%2Bth1RuDRImJ6oeunZs6o7%2F40cFmSPPjkYwxmPUuV38iTXYlnbhHuuvtdnc8DMZ4A%3D&resetType=email&language=en";
    private final String email_valid = "email." + randomString(4) + "@" + randomString(4) + ".com";
    private final String email_invalid = randomString(6);
    private final String password_valid = "Pass" + randomString(4) + "1*";
    private final String password_invalid =  randomString(6);

    private String testCaseName;



    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(){
        rypp.loadWebPage(appUrl);
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        refreshPage_WebElement(driver);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2301")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Reset password Input fields respond to invalid data",
            description = "Test case checks Input fields respond to invalid data",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Reset_password_Input_fields_respond_to_invalid_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rypp.clickOn_emailInputField();
        rypp.clear_emailInputField();
        rypp.clickOn_emailInputField();
        rypp.clear_emailInputField();
        rypp.clickOn_emailInputField();
        rypp.clear_emailInputField();
        rypp.clickOn_emailInputField();
        rypp.clear_emailInputField();
        rypp.insert_emailInputField(email_invalid);
        rypp.insert_passwordInputField(password_invalid);
        rypp.insert_rePasswordInputField(password_invalid);
        rypp.assert_emailFieldColor(inputFieldBorderColorRed, "Red");
        rypp.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
        rypp.assert_rePasswordFieldColor(inputFieldBorderColorRedV2,"Red");
        rypp.assert_rePasswordWarningMsg(errorMsg_rePassword);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4207")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Reset password Input fields respond to valid data",
            description = "Test case checks Input fields respond to valid data",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Reset_password_Input_fields_respond_to_valid_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rypp.clickOn_emailInputField();
        rypp.assert_emailFieldColor(inputFieldBorderColorBlue, "Blue");
        rypp.insert_passwordInputField(password_valid);
        rypp.assert_passwordFieldColor(inputFieldBorderColorBlue, "Blue");
        rypp.insert_rePasswordInputField(password_valid);
        rypp.assert_rePasswordFieldColor(inputFieldBorderColorBlue,"Blue");
    }

    @Bug(androidTicket ="EC-3751", iOSTicket="null")
    @TcID(tcId = "EC-4206")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Reset password Input fields respond with cleared data",
            description = "Test case checks Input fields respond to cleared data",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Reset_password_Input_fields_respond_to_clear_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rypp.clear_emailInputField();
//        TODO clear_emailInputField
//        rypp.assert_emailFieldColor(inputFieldBorderColorRedV2, "Red");
        rypp.insert_passwordInputField("a");
        rypp.clear_passwordInputField();
        rypp.insert_rePasswordInputField("a");
        rypp.clear_rePasswordInputField();
        rypp.assert_passwordFieldColor(inputFieldBorderColorRed,"Red");
        rypp.clickOn_passwordInputField();
        rypp.assert_rePasswordFieldColor(inputFieldBorderColorRed,"Red");
        rypp.assert_rePasswordWarningMsg(errorMsg_rePassword);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4205")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Reset password Reset password with invalid email",
            description = "Test case checks is it possible to reset password with wrong email",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Reset_password_Reset_password_with_invalid_email(String user, String platformParameter, ITestContext context) throws InterruptedException {
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

//        TODO clear_emailInputField
        rypp.clear_emailInputField();
        rypp.insert_passwordInputField(password_valid);
        rypp.insert_rePasswordInputField(password_valid);
//        rypp.clickOn_emailInputField();
        tapOnScreen(driver, 1, 1);
        rypp.clickOn_resetPasswordBtn();
        rypp.assert_toastMsg(toastMessage_email_error);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4208")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Reset password Reset password with an old password",
            description = "Test case checks is it possible to reset password with an old password",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Reset_password_Reset_password_with_an_old_password(String user, String platformParameter, ITestContext context) {
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

//        TODO waiting for TC Data
        String password = password(user,platformParameter);

        rypp.insert_passwordInputField(password);
        rypp.insert_rePasswordInputField(password);
        tapOnScreen(driver, 1, 1);
        rypp.clickOn_resetPasswordBtn();
        rypp.assert_toastMsg(toastMessage_old_password_error);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2299")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Reset password action",
            description = "Test case checks whether the user can change the password",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Reset_password_action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rypp.insert_passwordInputField(password_valid);
        rypp.insert_rePasswordInputField(password_valid);
        tapOnScreen(driver, 1, 1);
//        TODO waiting for TC Data
//        rypp.clickOn_resetPasswordBtn();
//        rypp.assert_toastMsg(toastMessage_password_successfully);
    }


}
