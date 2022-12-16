package testing.appium.eConsentTesting.Web.footer_Actions;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.*;


@Listeners(TestListener.class)
public class Privacy_Policy extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String testCaseName;
    private String originalWindow;


    @Parameters({"user","platformParameter"})
    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(String user, String platformParameter){

        username = username(user,platformParameter);
        password = password(user, platformParameter);

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
        sip.logInProcedure(username, password);
        mp.assert_pageTitle();

    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        switchingToOriginalTab(driver, originalWindow);
        mp.clickOn_menuBtn();
        mp.clickOn_logOutBtn();
        sip.assert_pageTitle();
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-951")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - Privacy Policy Action",
            description = "Test case checks if Privacy Policy link works",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_Privacy_Policy_Action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();

        originalWindow = assert_oneWindowTabIsPresent(driver);
        mp.clickOn_privacyPolicyBtn();
        switchingTabs(driver, 10, originalWindow);
        pp.assert_pageTitle();
    }


}
