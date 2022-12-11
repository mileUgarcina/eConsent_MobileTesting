package testing.appium.eConsentTesting.Web.landing_Page;

import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.tcData.declineStudyPopUpMsg;


@Listeners(TestListener.class)
public class Tasks extends BaseTestSetWeb {

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
    public void precondition(){
        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-944")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Home Page -  No Assigned Tasks",
            description = "Test case checks Home Page No Assigned Tasks",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Home_Page_No_Assigned_Tasks(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data Participant, 1 Study
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.assert_studyName("Appium Automation Study 2");
        mp.assert_studyStatus("1", "Approached");
        mp.clickOn_study("1", "Appium Automation Study 2");

//        Test
        sp.assert_pageTitle();
        sp.assert_studyTitle("Appium Automation Study 2");
        sp.assert_siteName("Site dalibor 2");
        sp.assert_formStatus("Approached");
        sp.assert_studyEmail("--");
        sp.assert_studyOfficePhoneNumber("--");
        sp.assert_formType("Other");
        sp.assert_formStatus("Not Signed");
        sp.clickOn_viewFormBtn();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-941")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Home Page -  View Tasks Action",
            description = "Test case checks Home Page View Tasks Action",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Home_Page_View_Tasks_Action(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data Participant, 1 Study, 1 Form
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.assert_studyTaskMsg("You have 1 study that needs attention");
        mp.assert_viewTasksBtn_isEnabled();
        mp.clickOn_viewTasksBtn();
        vt.assert_pageTitle();
        vt.assert_studyTitle("Appium Automation Study 2");
        vt.assert_siteName("Site dalibor 2");
        vt.assert_readForms("Read forms");
        vt.assert_readForms("Sign forms");
        vt.assert_viewTasksBtn_isEnabled();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-1434")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Home Page - Resume Tasks Action",
            description = "Test case checks Home Page Resume Tasks Action",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Home_Page_Resume_Tasks_Action(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data Participant, 1 Study, 1 Form - Resume Status
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.assert_studyTaskMsg("You have 1 study that needs attention");
        mp.assert_resumeTasksBtn_isEnabled();
        mp.clickOn_resumeTasksBtn();
        vt.assert_pageTitle();
        vt.assert_studyTitle("Appium Automation Study 2");
        vt.assert_siteName("Site dalibor 2");
        vt.assert_readForms("Read forms");
        vt.assert_resumeTasksBtn_isEnabled();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2137")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Home Page - Resume Tasks when Declined study is manually Activated",
            description = "Test case checks Home Page Resume Tasks when Declined study is manually Activated",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Home_Page_Resume_Tasks_when_Declined_study_is_manually_Activated(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.clickOn_viewTasksBtn();
        vt.assert_pageTitle();
        vt.clickOn_viewTasksBtn();
        vsp.clickOn_declineStudyBtn();
        vsp.assert_declineStudyPopUpTitle(declineStudyPopUpMsg);
        vsp.clickOn_declineStudyPopUpBtn();
        mp.assert_studyName("Appium Automation Study 2");
        mp.assert_studyStatus("1", "Declined");
//        TODO Waiting for Test Data, Status User - Approached, Form - Resend


    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-943")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Home Page - View All Action",
            description = "Test case checks Home Page View All Action",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Home_Page_View_All_Action(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data Participant, 2 Study, 2 Forms
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.clickOn_viewAllBtn();
        vt.assert_pageTitle();
        vt.assert_studyTitle("Appium Automation Study 2");
        vt.assert_siteName("Site dalibor 2");
        vt.assert_readForms("Number of forms:2");
        vt.assert_viewTasksBtn_isEnabled();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2706")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - tudy Details Page - View Tasks Action",
            description = "Test case checks tudy Details Page - View Tasks Action",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Study_Details_Page_View_Tasks_Action(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data Participant, 1 Study, 1 Forms
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
//        TODO Waiting for Test Data
//        mp.clickOn_viewTasksBtn();
        mp.clickOn_viewAllBtn();
        vt.assert_pageTitle();
        vt.clickOn_viewTasksBtn();
        vsp.assert_pageTitle();
        vsp.assert_formDoc();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2707")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - tudy Details Page - Resume Tasks Action",
            description = "Test case checks tudy Details Page - Resume Tasks Action",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Study_Details_Page_Resume_Tasks_Action(String user) {

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
//        TODO Waiting for Test Data Participant, 1 Study, 1 Forms
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";
        firstName = "Active";

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
//        TODO Waiting for Test Data
//        mp.clickOn_viewTasksBtn();
        mp.clickOn_viewAllBtn();
        vt.assert_pageTitle();
        vt.clickOn_resumeTasksBtn();
        vsp.assert_pageTitle();
        vsp.assert_formDoc();
    }


}



