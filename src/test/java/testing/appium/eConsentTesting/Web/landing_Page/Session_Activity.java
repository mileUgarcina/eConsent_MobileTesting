package testing.appium.eConsentTesting.Web.landing_Page;

import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.printingTheRemainingMinutes;
import static testing.appium.helpers.Utils.refreshPage_WebElement;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.tcData.*;


@Listeners(TestListener.class)
public class Session_Activity extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String testCaseName;
    private String tcId;


    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(){

//        TODO Waiting for Test Data Participant, 1 Study
        username = "mobile.automation+active_android@florencehc.com";
        password = "Password123*";

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK );
        sip.logInProcedure(username, password);
        mp.assert_pageTitle();

    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        refreshPage_WebElement(driver);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2365")
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Participant Session Expires 13 min",
            description = "Test case checks does the 2 minute warning go out after 13 minutes of inactivity",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Participant_Session_Expires_13min() throws InterruptedException {
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();

        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        printingTheRemainingMinutes(60000, 13);
        mp.assert_inactivityWarningPopUpTitle(inactivityWarningTitle);
        mp.assert_inactivityWarningPopUpBody(inactivityWarningBody);
        mp.clickOn_inactivityWarningPopUpTitle();
        mp.assert_pageTitle();

    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-2365")
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Participant Session Expires 15 min",
            description = "Test case checks does Session Expires after 15 min",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Participant_Session_Expires_15min() throws InterruptedException {
        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();

        printingTheRemainingMinutes(60000, 15);
        sip.assert_pageTitle();

    }






}



