package testing.appium.eConsentTesting.Web.user_Menu;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener_Xray;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.firstName;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener_Xray.class)
public class Profile_Settings extends BaseTestSetWeb {

    private int waitInterval = 15;
    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String testCaseName;
    private String originalWindow;


    @Parameters({"user","platformParameter"})
    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(String user, String platformParameter){

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
        username = username(user,platformParameter);
        password = password(user, platformParameter);

//        TODO Waiting TC Data
        sip.logInProcedure("mobile.automation+profile_android@florencehc.com", "Password123*");
        mp.assert_pageTitle();
        mp.clickOn_menuBtn();
        mp.clickOn_profileSettingsBtn();
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        switch (testCaseName) {
            case "participant_App_Mobile_User_Menu_Profile_Settings_Back_Button":
                try {
                    mp.clickOn_menuBtn();
                    mp.clickOn_profileSettingsBtn();
                } catch (Exception ignored) {}
                break;
            case "participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_fields_respond_to_valid_data":
            case "participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_fields_cleared":
            case "participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_Incorrect":
            case "participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_updated_with_the_same_input":
                try {
                    prosp.clickOn_xBtn();
                } catch (Exception ignored) {}
                break;
            default:
        }
        prosp.assert_ProfileSettingsPage_for_Postcondition();
    }

//    @AfterClass(alwaysRun=true,
//            description = "Log Out form Application")
//    public void logOut(){
//
//        prosp.clickOn_backBtn();
//        mp.clickOn_menuBtn();
//        mp.clickOn_logOutBtn();
//        sip.assert_pageTitle();
//    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4351")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Profile Settings option",
            description = "Test case checks if Profile Settings Page opens",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Profile_Settings_Opened(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        username = username(user,platformParameter);
        password = password(user, platformParameter);
        firstName = firstName(user, platformParameter);
        middleName = middleName(user, platformParameter);
        lastName = lastName(user, platformParameter);

        prosp.assert_pageTitle();
//        TODO Waiting TC Data
//        prosp.assert_profileName(firstName, middleName, lastName);
//        prosp.assert_emailAddress(username);
        prosp.assert_password(password);
        prosp.assert_Language("English");

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-3147")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile User Menu Profile Settings Back Button",
            description = "Test case checks if Profile Settings Back Button Works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Profile_Settings_Back_Button(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        prosp.clickOn_backBtn();
//        TODO Waiting TC Data
//        mp.assert_WelcomeMsg(firstName);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4356")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile - User Menu - Profile Settings - Profile Name fields respond to valid data",
            description = "Test case checks if Profile Settings Name Change whether the input fields are Highlighted when they are selected",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_fields_respond_to_valid_data(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        prosp.clickOn_updateProfileNameBtn();
        prosp.clickOn_firstNameInputField();
        prosp.assert_firstNameFieldColor(inputFieldBorderColorBlue, "Blue");
        prosp.clickOn_middleNameInputField();
        prosp.assert_middleNameFieldColor(inputFieldBorderColorBlue, "Blue");
        prosp.clickOn_lastNameInputField();
        prosp.assert_lastNameFieldColor(inputFieldBorderColorBlue, "Blue");


    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4357")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - User Menu - Profile Settings - Profile Name  error states - Remove First Name",
            description = "Test case checks if Profile Settings Changing the First name is allowed",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_fields_cleared(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        prosp.clickOn_updateProfileNameBtn();
        prosp.assert_pageTitleProfilePopUp();
        prosp.clickOn_firstNameInputField();
        prosp.clear_firstNameInputField();
        prosp.clickOn_lastNameInputField();
        prosp.clear_lastNameInputField();
        prosp.clickOn_middleNameInputField();
        prosp.assert_firstNameWarningMsg("Required");
        prosp.assert_firstNameWarningMsg("Required");
        prosp.assert_firstNameFieldColor(inputFieldBorderColorRed, "Red");
        prosp.assert_lastNameFieldColor(inputFieldBorderColorRed, "Red");
        prosp.assert_saveBtn_isDisable();
    }

//        TODO Waiting TC Data
    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-3181")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"Android", "iOS"},
            testName = "Participant Auth Mobile - User Menu - Profile Settings - Profile Name updated with the same input",
            description = "Test case checks if Profile Settings Changing the First name is allowed",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_updated_with_the_same_input(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        firstName = firstName(user, platformParameter);
        middleName = middleName(user, platformParameter);
        lastName = lastName(user, platformParameter);

        prosp.clickOn_updateProfileNameBtn();
        prosp.assert_pageTitleProfilePopUp();
        String bufferFirstName = prosp.buffer_firstNameInputField();
        String bufferMiddleName = prosp.buffer_middleNameInputField();
        String bufferLastName = prosp.buffer_lastNameInputField();
        prosp.clear_firstNameInputField();
        prosp.insert_firstNameInputField(bufferFirstName);
        prosp.clear_middleNameInputField();
        prosp.insert_middleNameInputField(bufferMiddleName);
        prosp.clear_lastNameInputField();
        prosp.insert_lastNameInputField(bufferLastName);
        prosp.assert_saveBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4354")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile - User Menu - Profile Settings - Profile error states changed - Canceled",
            description = "Test case checks if Profile Settings Cancel Button Works",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_Changed_Canceled(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        firstName = firstName(user, platformParameter);
        middleName = middleName(user, platformParameter);
        lastName = lastName(user, platformParameter);

        String newFirstName = randomString(5);
        String newMiddleName = randomString(5);
        String newLastName = randomString(5);

        prosp.clickOn_updateProfileNameBtn();
        prosp.assert_pageTitleProfilePopUp();
        prosp.clear_firstNameInputField();
        prosp.insert_firstNameInputField(newFirstName);
        prosp.clear_middleNameInputField();
        prosp.insert_middleNameInputField(newMiddleName);
        prosp.clear_lastNameInputField();
        prosp.insert_lastNameInputField(newLastName);
        prosp.clickOn_cancelBtn();
//        TODO Waiting TC Data
//        prosp.assert_profileName(firstName, middleName, lastName);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-3149")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile - User Menu - Profile Settings - Profile Name changed",
            description = "Test case checks if Profile Settings, can the user details be changed",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_User_Menu_Profile_Settings_Profile_Name_Changed(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();;
        String newFirstName = randomString(5);
        String newMiddleName = randomString(5);
        String newLastName = randomString(5);

        prosp.clickOn_updateProfileNameBtn();
        prosp.assert_pageTitleProfilePopUp();
        prosp.clear_firstNameInputField();
        prosp.insert_firstNameInputField(newFirstName);
        prosp.clear_middleNameInputField();
        prosp.insert_middleNameInputField(newMiddleName);
        prosp.clear_lastNameInputField();
        prosp.insert_lastNameInputField(newLastName);
//        TODO Waiting TC Data
        prosp.clickOn_saveBtn();
        prosp.assert_toastContainer(toastMessage_successfully);
        prosp.assert_profileName(newFirstName, newMiddleName, newLastName);

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-3410")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant Auth Mobile - User Menu - Profile Settings - Language changed",
            description = "Test case checks if Profile Settings, language change is possible",
            retryAnalyzer = TcRetry.class)
    public void participant_Auth_Mobile_User_Menu_Profile_Settings_Language_changed(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        prosp.clickOn_updateProfileLanguageBtn();
        prosp.clickOn_espRadioBtn();
//        TODO Waiting TC Data
//        prosp.clickOn_saveBtn();
//        prosp.assert_Language("Español");
    }

}
