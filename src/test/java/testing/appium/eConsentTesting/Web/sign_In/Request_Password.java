package testing.appium.eConsentTesting.Web.sign_In;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener_Xray;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.randomString;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;
import static testing.appium.runner.propertyFile.DataProvider.username;


@Listeners(TestListener_Xray.class)
public class Request_Password extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();

    private String email_valid = "email." + randomString(4) + "@" + randomString(4) + ".com";
    private String email_invalid = randomString(6);
    private String testCaseName;
    private String originalWindow;


    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(){

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
        sip.clickOn_forgotPasswordBtn();
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        refreshPage_WebElement(driver);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4200")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Request Password - Email field responds with valid data",
            description = "Test case checks whether the email input field responds to valid input",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Request_Password_Email_field_responds_with_valid_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        fpp.clickOn_emailInputField();
        fpp.insert_emailInputField(email_valid);
        fpp.assert_emailFieldColor(inputFieldBorderColorBlue,"Blue");
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4201")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Request Password - Email field responds with invalid data",
            description = "Test case checks whether the email input field responds to invalid input",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Request_Password_Email_field_responds_with_invalid_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        fpp.clickOn_emailInputField();
        fpp.insert_emailInputField(email_invalid);
        fpp.assert_emailFieldColor(inputFieldBorderColorRedV2,"Red");
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4202")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Request Password - Email field responds with cleared data",
            description = "Test case checks whether the email input field responds to cleared data",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Request_Password_Email_field_responds_with_cleared_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        fpp.clickOn_emailInputField();
        fpp.insert_emailInputField("a");
        fpp.clear_emailInputField();
        fpp.assert_emailFieldColor(inputFieldBorderColorRedV2,"Red");
        fpp.assert_emailWarningMsg(errorMsg_emailField_Required);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4204")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Request Password - Password Reset Action",
            description = "Test case checks Password Reset Action",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Request_Password_Request_password_reset_action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO waiting for TC Data
        String email = username(user,platformParameter);

        fpp.clickOn_emailInputField();
        fpp.insert_emailInputField(email);
        fpp.clickOn_requestPasswordResetBtn();
        fpp.assert_toastMsg(toastMessage_email_successfully);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2302")
    @Parameters({"user","platformParameter"})
    @Test(groups= { "Android", "iOS"},
            testName = "Participant Auth Mobile - Request Password - Sign in action",
            description = "Test case checks Sign in Link",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Request_Sign_in_action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        fpp.clickOn_signInBtnBtn();
        sip.assert_pageTitle();

    }

}
