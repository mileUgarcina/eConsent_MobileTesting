package testing.appium.eConsentTesting.Web.user_Menu;

import org.testng.ITestContext;
import org.testng.annotations.*;
import testing.appium.eConsentTesting.Web.BaseTestSetWeb;
import testing.appium.helpers.TcRetry;
import testing.appium.helpers.jiraTicket.Bug;
import testing.appium.helpers.testCaseId.TcID;
import testing.appium.runner.TestListener;

import static testing.appium.helpers.TCLogger.LoggerInformation;
import static testing.appium.helpers.Utils.refreshPage_WebElement;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.SIGN_IN_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;
import static testing.appium.runner.propertyFile.DataProvider.*;


@Listeners(TestListener.class)
public class Menu extends BaseTestSetWeb {

    static private final String appUrl = appEnvironment();
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String testCaseName;
    private String tcId;

    @Parameters({"user"})
    @BeforeClass(alwaysRun=true,
                  description = "Wait for the application to boot up")
    public void precondition(String user){

        sip.loadWebPage(appUrl + SIGN_IN_PAGE_LINK);
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        username = username(user,PLATFORM_PARAMETER);
        password = password(user, PLATFORM_PARAMETER);
        firstName = firstName(user, PLATFORM_PARAMETER);
        middleName = middleName(user, PLATFORM_PARAMETER);
        lastName = lastName(user, PLATFORM_PARAMETER);

        sip.logInProcedure(username, password);
        mp.assert_pageTitle();
        mp.assert_WelcomeMsg(firstName);
    }

    @Parameters({"browserName"})
    @AfterMethod(description = "Restore the application to the minimum common state for all Test Cases")
    public void postcondition(String browserName){

        LoggerInformation("Postcondition procedure for TC: " + testCaseName);
        refreshPage_WebElement(driver);
    }


    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4256")
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Dropdown Menu opened",
            description = "Test case checks does a dropdown menu open",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Dropdown_Menu_opened(){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();

        mp.clickOn_menuBtn();
        mp.assert_dropDownMenu_isPresent();
    }

    @Bug(androidTicket ="null", iOSTicket="null")
    @TcID(tcId = "EC-4257")
    @Test(groups= {"SmokeTest", "Android", "iOS"},
            testName = "Participant App Mobile - User Menu - Menu closed",
            description = "Test case checks does the drop down menu close",
            retryAnalyzer = TcRetry.class)
    public void participant_App_Mobile_User_Menu_Menu_closed(){
        testCaseName = new Object(){}.getClass().getEnclosingMethod().getName();
        tcId = new Object(){}.getClass().getEnclosingMethod().getAnnotation(TcID.class).tcId();

        mp.clickOn_menuBtn();
        mp.assert_dropDownMenu_isPresent();
        mp.clickOn_menuBtn();
        mp.assert_dropDownMenu_notPresent();
    }

}
