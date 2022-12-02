package testing.appium.eConsentTesting.Web.sign_In;

import org.apache.commons.codec.DecoderException;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.runner.TestListener_Xray;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.*;

import static testing.appium.runner.databaseSiding.eConsentAPI.SHAHashing;
import static testing.appium.runner.databaseSiding.eConsentAPI.seedData_API;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.*;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener_Xray.class)
public class Sign_In extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String testCaseName;
    private String originalWindow;


    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition() throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, DecoderException {


        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        switch (testCaseName) {
            case "participant_Auth_Mobile_Sign_in_with_participant":
            case "participant_Auth_Mobile_Sign_In_Additional_Signer":
                mp.clickOn_menuBtn();
                mp.clickOn_logOutBtn();
                sip.assert_pageTitle();
                break;
            case "participant_Auth_Mobile_Sign_In_Privacy_Policy_Link":
            case "participant_Auth_Mobile_Sign_In_Terms_And_Conditions_Link":
                switchingToOriginalTab(driver, originalWindow);
                break;
            case "participant_Auth_Mobile_Sign_In_Forgot_Password_Link":
                navigateBack_WebElement(driver);
                break;
            case "participant_Auth_Mobile_Sign_In_Forgot_password_action_with_populated_Email_field":
                navigateBack_WebElement(driver);
                refreshPage_WebElement(driver);
                break;
            default:
                refreshPage_WebElement(driver);
        }
    }


    @Bug(androidTicket ="EC-3751", iOSTicket="null")
    @TcID(tcId = "EC-2034")
    @Parameters({"user", "platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile - Sign in with participant",
            description = "Test case checks if logging into the application is possible",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_in_with_participant(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        username = username(user,platformParameter);
        password = password(user, platformParameter);
        firstName = firstName(user, platformParameter);

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-3414")
    @Parameters({"user", "platformParameter", "additional_LAR_Username", "additional_LAR_Password", "additional_LAR_FirstName", "additional_LAR_MiddleName", "additional_LAR_LastName"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile Sign In Login with Additional Signer",
            description = "Test case checks if logging into the application with Additional Signer is possible",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Additional_Signer(String user, String platformParameter, String additional_LAR_Username, String additional_LAR_Password, String additional_LAR_FirstName, String additional_LAR_MiddleName, String additional_LAR_LastName, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.logInProcedure(additional_LAR_Username, additional_LAR_Password);
        mp.assert_pageTitle();
        mp.assert_WelcomeMsg(additional_LAR_FirstName);
    }

    @Bug(androidTicket ="EC-3751", iOSTicket="EC-3751")
    @TcID(tcId = "EC-2035")
    @Parameters({"user","platformParameter", "unactivatedUsername", "unactivatedPassword"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign in with inactive participant",
            description = "Test case checks if login is possible with Unactivated Account",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_in_with_inactive_participant(String user, String platformParameter, String unactivatedUsername, String unactivatedPassword, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.logInProcedure(unactivatedUsername, unactivatedPassword);
        sip.assert_toastMsg(errorMsg_unactivatedUser);
        sip.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
    }

    @Bug(androidTicket ="EC-3751", iOSTicket="EC-3751")
    @TcID(tcId = "EC-4188")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign in with inactive Additional Signer",
            description = "Test case checks if login is possible with Unactivated Additional Signer Account",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_in_with_inactive_Additional_Signer(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

//        TODO waiting for TC Data
        String unactivatedLarUsername = "mobile.automation+unactivate_lar_android@florencehc.com";
        String unactivatedLarPassword = "Password123*";

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.logInProcedure(unactivatedLarUsername, unactivatedLarPassword);
        sip.assert_toastMsg(errorMsg_unactivatedUser);
        sip.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4192")
    @Parameters({"user","platformParameter", "incorrectUsername", "incorrectPassword"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign in with incorrect email",
            description = "Test case checks if login is possible with Incorrect Input",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_in_with_incorrect_email(String user, String platformParameter, String incorrectUsername, String incorrectPassword, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.insert_emailInputField(incorrectUsername);
        sip.insert_passwordInputField(incorrectPassword);
        sip.assert_signInBtn_isDisable();
        sip.assert_usernameFieldColor(inputFieldBorderColorRed, "Red");
        sip.assert_usernameWarningMsg(errorMsg_emailField_Required);
        sip.assert_passwordFieldColor(inputFieldBorderColorRedV2, "Red");




    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4189")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign In - Fields respond with valid data",
            description = "Test case checks if login is possible with Valid Input",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Fields_respond_with_valid_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        username = username(user,platformParameter);
        password = password(user, platformParameter);

        sip.clickOn_emailInputField();
        sip.insert_emailInputField(username);
        sip.assert_usernameFieldColor(inputFieldBorderColorBlue, "Blue");
        sip.clickOn_passwordInputField();
        sip.insert_passwordInputField(password);
        sip.assert_passwordFieldColor(inputFieldBorderColorBlue, "Blue");
        sip.assert_signInBtn_isEnabled();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2036")
    @Parameters({"user","platformParameter", "incorrectUsername", "incorrectPassword"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign In - Fields respond with invalid data",
            description = "Test case checks field response to invalid data input",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Fields_respond_with_invalid_data(String user, String platformParameter, String incorrectUsername, String incorrectPassword,ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        username = username(user,platformParameter);
        password = password(user, platformParameter);

        sip.clickOn_emailInputField();
        sip.insert_emailInputField(incorrectUsername);
        sip.clickOn_passwordInputField();
        sip.insert_passwordInputField(incorrectPassword);
        sip.assert_usernameFieldColor(inputFieldBorderColorRed, "Red");
        sip.assert_passwordFieldColor(inputFieldBorderColorRedV2, "Red");
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4190")
    @Parameters({"user","platformParameter", "incorrectUsername", "incorrectPassword"})
    @Test(groups= {"Android", "iOS"},
            testName = "participant_Auth_Mobile_Sign_In_Empty_Input_Field",
            description = "Test case checks if login is possible with Empty Input Field",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Fields_respond_with_cleared_data(String user, String platformParameter, String incorrectUsername, String incorrectPassword, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.insert_emailInputField(incorrectUsername);
//        TODO clear on iOS
        sip.insert_emailInputField("");
        sip.clear_emailInputField();
        sip.assert_signInBtn_isDisable();
        sip.assert_usernameFieldColor(inputFieldBorderColorRed, "Red");
        sip.assert_usernameWarningMsg(errorMsg_emailField_Required);
        sip.insert_passwordInputField(incorrectPassword);
//        TODO clear on iOS
        sip.clear_passwordInputField();
        sip.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
        sip.assert_signInBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4192")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign in with incorrect password",
            description = "Test case checks if login is possible with Incorrect Password",
            retryAnalyzer = TcRetry.class)
    public void Participant_Auth_Mobile_Sign_in_with_incorrect_password(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        username = username(user,platformParameter);
        String incorrectPassword = "Pass" + randomString(4) + "1*";;

        sip.insert_emailInputField(username);
        sip.insert_passwordInputField(incorrectPassword);
        sip.clickOn_signInBtn();
        sip.assert_toastMsg(errorMsg_IncorrectPassword);
        sip.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
        sip.assert_signInBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4191")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "participant_Auth_Mobile_Sign_In_Sign_in_eConsent_Show_Password",
            description = "Test case checks if check box Show Password works",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Show_Password(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        password = password(user, platformParameter);

        sip.insert_passwordInputField(password);
        sip.assert_passwordInputField("type", "password");
        sip.clickOn_showPasswordChkBox();
        sip.assert_passwordInputField("type", "text");
        String buffer_checkedPasswordField = sip.buffer_passwordInputField();
        assert_twoStrings(buffer_checkedPasswordField, password);
    }



    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2046")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "participant_Auth_Mobile_Sign_In_Sign_in_eConsent_Privacy_Link",
            description = "Test case checks if Privacy Policy link work",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Privacy_Policy_Link(String user, String platformParameter, ITestContext context) {
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.assert_pageTitle();
        originalWindow = assert_oneWindowTabIsPresent(driver);
        sip.clickOn_privacyPolicyBtn();
        switchingTabs(driver, 10, originalWindow);
        pp.assert_pageTitle();

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2047")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "participant_Auth_Mobile_Sign_In_Sign_in_eConsent_Terms_And_Conditions_Link",
            description = "Test case checks if Privacy Policy link work",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Terms_And_Conditions_Link(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.assert_pageTitle();
        originalWindow = assert_oneWindowTabIsPresent(driver);
        sip.clickOn_termsAndConditionsBtn();
        switchingTabs(driver, 10, originalWindow);
        tp.assert_pageTitle();

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4198")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "participant_Auth_Mobile_Sign_In_Sign_in_eConsent_Forgot_Password_Link",
            description = "Test case checks if Forgot Password link work",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Forgot_Password_Link(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        sip.clickOn_forgotPasswordBtn();
        fpp.assert_pageTitle();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4198")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign In - Forgot password action with populated Email field",
            description = "Test case checks if Forgot Password link work with inserted email",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_In_Forgot_password_action_with_populated_Email_field(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        username = username(user,platformParameter);

        sip.clickOn_emailInputField();
        sip.insert_emailInputField(username);
        sip.clickOn_forgotPasswordBtn();
        fpp.assert_pageTitle();
        fpp.assert_emailInputField(username);
    }

}
