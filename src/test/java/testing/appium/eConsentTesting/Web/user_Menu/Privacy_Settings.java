package testing.appium.eConsentTesting.Web.user_Menu;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.*;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener.class)
public class Privacy_Settings extends BaseTestSetWeb {

    private int waitInterval = 15;
    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String testCaseName;
    private String tcId;
    private String originalWindow;


    @Parameters({"user"})
    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(String user){

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
        username = username(user,PLATFORM_PARAMETER);
        password = password(user, PLATFORM_PARAMETER);
        firstName = firstName(user, PLATFORM_PARAMETER);
        middleName = middleName(user, PLATFORM_PARAMETER);
        lastName = lastName(user, PLATFORM_PARAMETER);

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.clickOn_menuBtn();
        mp.clickOn_privacySettingsBtn();
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        switch (testCaseName) {
            case "participant_App_Mobile_User_Menu_Privacy_Settings_Privacy_Policy_Link":
                try {
                    switchingToOriginalTab(driver, originalWindow);
                } catch (Exception ignored) {}
                break;
            case "participant_App_Mobile_User_Menu_Privacy_Settings_Back_Button_with_no_changes_made":
                mp.clickOn_menuBtn();
                mp.clickOn_privacySettingsBtn();
            break;
            case "participant_App_Mobile_User_Menu_Privacy_Settings_Updated_Cancel":
                refreshPage_WebElement(driver);
                try {
                    switchToAlertMessage_WebElement(driver);
                    acceptAlertMessage_WebElement(driver, waitInterval);
                } catch (Exception ex) {
                    LoggerStep_Failed("No Alert Message: " , ex.getMessage(), false);
                }
                break;
//        TODO waiting for TC Data
            case "participant_App_Mobile_User_Menu_Privacy_Settings_Updated_Save":
                try {
                    psp.clickOn_trainingAndUpdatesTgl_Off();
                    psp.assert_saveBtn_isEnabled();
                    psp.clickOn_saveBtn();
                } catch (Exception ignored) {}
                break;
            default:
        }
            psp.assert_PrivacyPolicyPage_for_Postcondition();
    }


    @AfterClass(alwaysRun=true,
            description = "Log Out form Application")
    public void logOut(){

        mp.clickOn_menuBtn();
        mp.clickOn_logOutBtn();
        sip.assert_pageTitle();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2389")
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Privacy Settings Opened",
            description = "Test case checks if Privacy Settings Page opens",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Privacy_Settings_Opened(){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();

        psp.assert_pageTitle();
        psp.assert_saveBtn_isDisable();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4261")
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Privacy Settings Privacy Policy Link",
            description = "Test case checks if Privacy Settings Page, link Privacy Policy whether it works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Privacy_Settings_Privacy_Policy_Link(){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();

        originalWindow = assert_oneWindowTabIsPresent(driver);
        psp.clickOn_privacyPolicyBtn();
        switchingTabs(driver, waitInterval, originalWindow);
        pp.assert_pageTitle();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4264")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile User Menu Privacy Settings Back Button with no changes made",
            description = "Test case checks if Privacy Settings Back Button Works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Privacy_Settings_Back_Button_with_no_changes_made(String user){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();
        firstName = firstName(user, PLATFORM_PARAMETER);

        psp.clickOn_backBtn();
        mp.assert_WelcomeMsg(firstName);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4268")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile User Menu Privacy Settings Back Button with changes made",
            description = "Test case checks if Privacy Settings Back Button Works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Privacy_Settings_Back_Button_with_changes_made(String user) throws InterruptedException {
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();
        firstName = firstName(user, PLATFORM_PARAMETER);

        psp.clickOn_trainingAndUpdatesTgl_On();
        psp.clickOn_backBtn();
//        Handling Alert Message on different platforms
        if (PLATFORM_PARAMETER.equals("Android")) {
            switchToAlertMessage_WebElement(driver);
            assertAlertMessage_WebElement(driver, waitInterval, nativeBrowserDialog_backBtn);
            acceptAlertMessage_WebElement(driver, waitInterval);
        }else{
//        Wait for Switching Context and Auto Confirm Alert Message on iOS
            Thread.sleep(5000);
        }
        mp.assert_WelcomeMsg(firstName);
        mp.clickOn_menuBtn();
        mp.clickOn_privacySettingsBtn();
        psp.assert_trainingAndUpdatesTgl_On();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4275")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Privacy Settings Updated Reload",
            description = "Test case checks if Privacy Settings Reload Works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Privacy_Settings_Updated_Reload(String user){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();
        firstName = firstName(user, PLATFORM_PARAMETER);

        psp.assert_cookiesTgl_On();
        psp.assert_trainingAndUpdatesTgl_On();
        psp.clickOn_trainingAndUpdatesTgl_On();
        refreshPage_WebElement(driver);
        if (!isIOS(driver)) {
            switchToAlertMessage_WebElement(driver);
//        assertAlertMessage_WebElement(driver, waitInterval, nativeBrowserDialog_reload);
            acceptAlertMessage_WebElement(driver, waitInterval);}
        psp.assert_cookiesTgl_On();
        psp.assert_trainingAndUpdatesTgl_On();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2388")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Privacy Settings Updated",
            description = "Test case checks if Privacy Settings Update Status Works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Privacy_Settings_Updated_Save(String user){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();
        firstName = firstName(user, PLATFORM_PARAMETER);

        psp.assert_cookiesTgl_On();
        psp.assert_trainingAndUpdatesTgl_On();
        psp.clickOn_trainingAndUpdatesTgl_On();
        psp.assert_saveBtn_isEnabled();
        psp.clickOn_saveBtn();
        psp.assert_cookiesTgl_On();
        psp.assert_trainingAndUpdatesTgl_Off();
        psp.assert_saveBtn_isDisable();
    }

    //    @Bug(androidTicket ="null", iOSTicket="null")
//    @TcID(tcId = "")
//    @Parameters({"user"})
//    @Test(groups= {"SmokeTest", "Android", "iOS"},
//            testName = "Participant App Mobile - User Menu - Privacy Settings Updated Cancel",
//            description = "Test case checks if Privacy Settings Cancel Button Works",
//            retryAnalyzer = TcRetry.class)
//    public void participant_App_Mobile_User_Menu_Privacy_Settings_Updated_Cancel(String user){
//
//
//        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
//        firstName = firstName(user, PLATFORM_PARAMETER);
//
//        psp.clickOn_trainingAndUpdatesTgl();
//        psp.clickOn_backBtn();
//        switchToAlertMessage_WebElement(driver);
//        assertAlertMessage_WebElement(driver, waitInterval, nativeBrowserDialog_backBtn);
//        dismissAlertMessage_WebElement(driver, waitInterval);
//        psp.assert_pageTitle();
//    }

}
