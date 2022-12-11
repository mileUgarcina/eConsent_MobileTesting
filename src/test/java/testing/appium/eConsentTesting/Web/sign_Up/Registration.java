package testing.appium.eConsentTesting.Web.sign_Up;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener.class)
public class Registration extends BaseTestSetWeb {
//        TODO Submit Button Tooltip Verification
//    static private final String appUrl = appEnvironment();
    static private final String appUrl = "https://us-participant-auth.se.qav2.researchbinders.com/#/sign-up?email=mobile.automation+active2_android@florencehc.com&inviteToken=IwrmUm8KObGzThJPaqN9kHQxvA1TMP2ffWnVik0WlVXCBFa81X5xwG%2F2ybn6WuQZ7rMfyCWr%2BbAmBrHO9DCacFdpTs7zYqM4GjB3Br9Krfw%3D&language=en";
    private String firstName = randomString(5);
    private String middleName = randomString(5);
    private String lastName = randomString(5);
    private String invalidPassword = randomString(5);
    private String password = randomString(10);

    private String testCaseName;
    private String originalWindow;



    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(){
//        TODO Waiting for TC Data
        sip.loadWebPage(appUrl);
    }


    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition() throws InterruptedException {
        switch (testCaseName) {
            case "participant_Auth_Mobile_Sign_Up_Register_With_Participant_not_Invited_to_the_Study":
            case "participant_Auth_Mobile_Sign_Up_Register_With_the_Expired_Token":
                openWebPage_WebElement(driver, appUrl);
                break;
            case "participant_Auth_Mobile_Sign_Up_Privacy_Policy_Action":
            case "participant_Auth_Mobile_Sign_Up_Terms_and_Conditions_Action":
                switchingToOriginalTab(driver, originalWindow);
                break;
        }
        refreshPage_WebElement(driver);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant Invited to the Study Info Message",
            description = "Test case checks if Info Message are displayed on Registration Page",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_Invited_to_the_Study_Info_Messages(String user, String platformParameter, ITestContext context) throws InterruptedException {
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_legalNameInfoBtn();
        rp.assert_legalNameTooltip(legalNameTooltipMsg);
        rp.clickOn_emailInfoBtn();
        rp.assert_emailTooltip(emailTooltipMsg);

//        TODO Submit Button Tooltip Verification for iOS
        Thread clickOn_submitBtn = new Thread(() -> rp.clickOn_submitBtn());
        Thread assert_submitTooltip = new Thread(() -> rp.assert_submitTooltip(signInBtnTooltipMsg));
        clickOn_submitBtn.start();
        assert_submitTooltip.start();
        clickOn_submitBtn.join();
        assert_submitTooltip.join();



    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant Invited to the Study Only First And Last Name",
            description = "Test case checks that the first and last name are not enough for registration",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_Invited_to_the_Study_Only_First_And_Last_Name(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_firstNameInputField();
        rp.insert_firstNameInputField(firstName);
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField(lastName);
        rp.assert_submitInBtn_isDisable();

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant Invited to the Study Invalid Password",
            description = "Test case checks error messages for wrong password format",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_Invited_to_the_Study_Invalid_Password(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(invalidPassword);
        rp.assert_passwordFieldColor(inputFieldBorderColorRedV2, "Red");
        rp.assert_passwordWarningMsg(errorMsg_passwordField_Incorrect);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant Invited to the Study Valid Password",
            description = "Test case checks for entering a valid password",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_Invited_to_the_Study_Valid_Password(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.assert_passwordFieldColor(inputFieldBorderColorBlue, "Blue");
        rp.assert_submitInBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant Invited to the Study Show Password",
            description = "Test case checks whether the password and Re-Password is visible when clicked on Show Password",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_Invited_to_the_Study_Show_Password(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(password);
        rp.clickOn_showPasswordChkBox();
        rp.assert_passwordInputField();
        rp.assert_rePasswordInputField();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Error States Mandatory Fields Empty",
            description = "Test case checks all mandatory fields",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Error_States_Mandatory_Fields_Empty(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_firstNameInputField();
        rp.clickOn_lastNameInputField();
        rp.clickOn_passwordInputField();
        rp.clickOn_rePasswordInputField();
        rp.clickOn_passwordInputField();
        rp.assert_firstNameFieldColor(inputFieldBorderColorRed,"Red");
        rp.assert_firstNameWarningMsg(errorMsg_mandatoryField_Incorrect);
        rp.assert_lastNameFieldColor(inputFieldBorderColorRed,"Red");
        rp.assert_lastNameWarningMsg(errorMsg_mandatoryField_Incorrect);
        rp.clickOn_passwordInputField();
        rp.assert_passwordFieldColor(inputFieldBorderColorRedV2, "Red");
        rp.assert_passwordWarningMsg(errorMsg_passwordField_Incorrect);
        rp.assert_rePasswordFieldColor(inputFieldBorderColorRed, "Red");
        rp.assert_rePasswordWarningMsg(errorMsg_rePasswordField_Incorrect);
        rp.assert_submitInBtn_isDisable();
    }

    @Bug(androidTicket ="EC-4185", iOSTicket="EC-418")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Error States Mandatory Fields Invalid Enter",
            description = "Test case checks all mandatory fields",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Error_States_Mandatory_Fields_Invalid_Enter(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_firstNameInputField();
        prosp.insert_firstNameInputField("   ");
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField("   ");
        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(invalidPassword);
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(invalidPassword);
        rp.assert_firstNameFieldColor(inputFieldBorderColorRed,"Red");
        rp.assert_firstNameWarningMsg(errorMsg_mandatoryField_Incorrect);
        rp.assert_lastNameFieldColor(inputFieldBorderColorRed,"Red");
        rp.assert_lastNameWarningMsg(errorMsg_mandatoryField_Incorrect);
        rp.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
        rp.assert_passwordWarningMsg(errorMsg_passwordField_Incorrect);
        rp.assert_rePasswordFieldColor(inputFieldBorderColorRed, "Red");
        rp.assert_rePasswordWarningMsg(errorMsg_rePasswordField_Incorrect);
        rp.assert_submitInBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Error States Mandatory Fields Clear",
            description = "Test case checks all mandatory fields fo Clear action",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Error_States_Mandatory_Fields_Clear(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_firstNameInputField();
        rp.insert_firstNameInputField(firstName);
        rp.clear_firstNameInputField();
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField(lastName);
        rp.clear_lastNameInputField();
        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.clear_passwordInputField();
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(password);
        rp.clear_rePasswordInputField();
        rp.assert_firstNameFieldColor(inputFieldBorderColorRed,"Red");
        rp.assert_firstNameWarningMsg(errorMsg_mandatoryField_Incorrect);
        rp.assert_lastNameFieldColor(inputFieldBorderColorRed,"Red");
        rp.assert_lastNameWarningMsg(errorMsg_mandatoryField_Incorrect);
        rp.assert_passwordFieldColor(inputFieldBorderColorRed, "Red");
        rp.assert_passwordWarningMsg(errorMsg_passwordField_Incorrect);
        rp.assert_rePasswordFieldColor(inputFieldBorderColorRedV2, "Red");
        rp.assert_rePasswordWarningMsg(errorMsg_rePasswordField_Incorrect);
        rp.assert_submitInBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Error States Wrong Confirmation Password",
            description = "Test case checks wrong entry on the second Password",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Error_States_Wrong_Confirmation_Password(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_firstNameInputField();
        rp.insert_firstNameInputField(firstName);
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField(lastName);
        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(invalidPassword);
        rp.clear_rePasswordInputField();
        rp.assert_rePasswordFieldColor(inputFieldBorderColorRedV2, "Red");
        rp.assert_rePasswordWarningMsg(errorMsg_rePasswordField_Incorrect);
        rp.assert_submitInBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2027")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Privacy Policy Action",
            description = "Test check if the Privacy Policy Action link works",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Privacy_Policy_Action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        originalWindow = assert_oneWindowTabIsPresent(driver);;
        rp.clickOn_privacyPolicyBtn();
        switchingTabs(driver, 10, originalWindow);
        pp.assert_pageTitle();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2028")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Terms and Conditions Action",
            description = "Test check if the Terms and Conditions Action link works",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Terms_and_Conditions_Action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        originalWindow = assert_oneWindowTabIsPresent(driver);;
        rp.clickOn_termsAndConditionsBtn();
        switchingTabs(driver, 10, originalWindow);
        tp.assert_pageTitle();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2014")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant not Invited to the Study",
            description = "Test case checks is it possible to register with an inactive user",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_not_Invited_to_the_Study(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        String wrongUrl =  appUrl.replace("@", "_wrong@");

        openWebPage_WebElement(driver, wrongUrl);
        rp.clickOn_firstNameInputField();
        rp.insert_firstNameInputField(firstName);
        rp.clickOn_middleNameInputField();
        rp.insert_middleNameInputField(middleName);
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField(lastName);
        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(password);
        rp.clickOn_submitBtn();
        rp.assert_toastMsg(errorMsg_wrong);

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2040")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With the Expired Token",
            description = "Test case checks is it possible to register with Expired Token",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_the_Expired_Token(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for TC Data
        String expiredUrl = "https://us-participant-auth.se.qav2.researchbinders.com/#/sign-up?email=mobile.automation+unactivated@florencehc.com&inviteToken=IwrmUm8KObGzThJPaqN9kDR8vOmqimAwVscU8xI6solbXd22flP6Ylpdth2RVXTywDe4ZTI4ims0wwjMszNLY4p1punc3N62%2FHUP0gK0LEw%3D&language=en";

        openWebPage_WebElement(driver, expiredUrl);
        rp.clickOn_firstNameInputField();
        rp.insert_firstNameInputField(firstName);
        rp.clickOn_middleNameInputField();
        rp.insert_middleNameInputField(middleName);
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField(lastName);
        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(password);
        rp.clickOn_submitBtn();
        rp.assert_toastMsg(errorMsg_Token);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-3401")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Additional Signer Creates Account",
            description = "Test case checks is it possible to register with Additional Signer",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Additional_Signer_Creates_Account(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for TC Data
        String additionalSigneUrl = "https://us-participant-auth.se.qav2.researchbinders.com/#/sign-up?email=mobile.automation+unactivated@florencehc.com&inviteToken=IwrmUm8KObGzThJPaqN9kDR8vOmqimAwVscU8xI6solbXd22flP6Ylpdth2RVXTywDe4ZTI4ims0wwjMszNLY4p1punc3N62%2FHUP0gK0LEw%3D&language=en";
        String userMail = "mobile.automation+unactivated@florencehc.com";

        openWebPage_WebElement(driver, additionalSigneUrl);
        rp.assert_pageTitle();
        rp.assert_emailInputField(userMail);
        rp.assert_submitInBtn_isDisable();

    }



    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - Sign Up - Register With Participant Invited to the Study Valid Password",
            description = "Test case checks for entering a valid password",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_Sign_Up_Register_With_Participant_Invited_to_the_Study(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        rp.clickOn_firstNameInputField();
        rp.insert_firstNameInputField(firstName);
        rp.clickOn_middleNameInputField();
        rp.insert_middleNameInputField(middleName);
        rp.clickOn_lastNameInputField();
        rp.insert_lastNameInputField(lastName);
        rp.clickOn_passwordInputField();
        rp.insert_passwordInputField(password);
        rp.clickOn_rePasswordInputField();
        rp.insert_rePasswordInputField(password);
//        TODO Waiting for TC Data
        rp.assert_submitInBtn_isEnabled();

    }




}
