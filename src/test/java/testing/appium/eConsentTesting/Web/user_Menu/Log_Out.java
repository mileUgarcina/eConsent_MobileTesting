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

//    @Parameters({"browserName"})
//    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
//    public void postcondition(String browserName){
//
//        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
//        switch (testCaseName) {
//            case "participant_App_Mobile_User_Menu_Log_Out_Action":
//
//            break;
//            default:
//                refreshPage_WebElement(driver);
//        }
//    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-946")
    @Parameters({"user","platformParameter"})
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Log Out Action",
            description = "Test case checks if logging into the application is possible",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Log_Out_Action(String user, String platformParameter, ITestContext context){
        context.setAttribute("platformParameter", platformParameter);

        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        username = username(user,platformParameter);
        password = password(user, platformParameter);
        firstName = firstName(user, platformParameter);
        middleName = middleName(user, platformParameter);
        lastName = lastName(user, platformParameter);

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.assert_WelcomeMsg(firstName);
        mp.clickOn_menuBtn();
        mp.clickOn_logOutBtn();
        sip.assert_pageTitle();
    }

}
