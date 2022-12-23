package testing.appium.eConsentTesting.Web.user_Menu;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.firstName;
import static testing.appium.runner.propertyFile.DataProvider.*;


@Listeners(TestListener.class)
public class Log_Out extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();
    private String testCaseName;
    private String tcId;

    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(){

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-946")
    @Parameters({"user"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Log Out Action",
            description = "Test case checks if logging into the application is possible",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Log_Out_Action(String user){
        testCaseName = new Object() {}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();
        String username = username(user, PLATFORM_PARAMETER);
        String password = password(user, PLATFORM_PARAMETER);
        String firstName = firstName(user, PLATFORM_PARAMETER);

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.assert_WelcomeMsg(firstName);
        mp.clickOn_menuBtn();
        mp.clickOn_logOutBtn();
        sip.assert_pageTitle();
    }

}
