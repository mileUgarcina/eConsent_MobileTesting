package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static testing.appium.helpers.Utils.*;


public class ViewTasks {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 20;

    private final By backBtn = By.xpath("//img[@alt='arrow-left']");
    private final By pageTitle = By.xpath("//h3[normalize-space()='Study']");
    private final By taskContainer = By.xpath("//div[@class='ng-star-inserted']");
    private final By viewTasksBtn = By.xpath("//button[contains(text(), 'View tasks')]");
    private final By resumeTasksBtn = By.xpath("//button[contains(text(), 'Resume tasks')]");
    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");



    public ViewTasks(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void clickOn_backBtn(){
        click_onWebElement_waitClickable(driver, viewTasksBtn, waitInterval,"View Tasks Button");
    }

    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Main Page Title");
    }

    public void assert_studyTitle(String studyName){
        assert_getText_WebElement(driver, taskContainer, waitInterval, "Study Name", studyName);
    }

    public void assert_siteName(String siteName){
        assert_getText_WebElement(driver, taskContainer, waitInterval, "Site Name", siteName);
    }

    public void assert_readForms(String readForms){
        assert_getText_WebElement(driver, taskContainer, waitInterval, "Read Forms", readForms);
    }

    public void assert_signForms(String signForms){
        assert_getText_WebElement(driver, taskContainer, waitInterval, "Read Forms", signForms);
    }

    public void clickOn_viewTasksBtn(){
        click_onWebElement_waitClickable(driver, viewTasksBtn, waitInterval,"View Tasks Button");
    }

    public void assert_viewTasksBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, viewTasksBtn, waitInterval, "View Tasks Button");
    }

    public void assert_viewTasksBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, viewTasksBtn, waitInterval, "View Tasks Button");
    }

    public void clickOn_resumeTasksBtn(){
        click_onWebElement_waitClickable(driver, resumeTasksBtn, waitInterval,"Resume Tasks Button");
    }

    public void assert_resumeTasksBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, resumeTasksBtn, waitInterval, "Resume Tasks Button");
    }

    public void assert_resumeTasksBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, resumeTasksBtn, waitInterval, "Resume Tasks Button");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy Button");
        }

    }



