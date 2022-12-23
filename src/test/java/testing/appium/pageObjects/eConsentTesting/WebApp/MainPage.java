package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;


public class MainPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 20;

    private final By pageImage = By.xpath("//img[@alt='clipboard-icon']");
    private final By welcomeMsg= By.xpath("//div[@class='landing-header__title-container']");
    private final By menuBtn = By.xpath("//i[@class='fas fa-user-circle fa-lg header__nav-icon']");
    private final By dropDownMenu = By.cssSelector("#dropdown-menu");
    private final By profileSettingsBtn = By.xpath("//a[normalize-space()='Profile Settings']");
    private final By privacySettingsBtn = By.xpath("//a[normalize-space()='Privacy Settings']");
    private final By logOutBtn = By.xpath("//a[normalize-space()='Log Out']");
    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");
    private By study(String studyNumber){ return By.xpath("(//li[@class='landing-studies-list__list-item'])[" + studyNumber + "]");}
    private By studyName(String studyName){ return By.xpath("//span[normalize-space()='" + studyName + "']");}
    private By studyStatus(String studyStatus, String studyNumber){ return By.xpath("(//span[@class='landing-studies-list__list-item-status'][normalize-space()='" + studyStatus + "'])[" + studyNumber + "]");}
    private final By studyTaskMsg = By.xpath("//div[@class='landing-header__title-container']//div");
    private final By viewTasksBtn = By.xpath("//button[contains(text(), 'View tasks')]");
    private final By resumeTasksBtn = By.xpath("//button[contains(text(), 'Resume tasks')]");
    private final By viewAllBtn = By.xpath("//button[contains(text(), 'View all')]");

//Inactivity Warning Modal
    private final By inactivityWarningPopUpTitle = By.xpath("//div[@class='modal-header']");
    private final By inactivityWarningPopUpBody = By.xpath("//div[@class='modal-body']");

//   "As a security measure, you will be logged out within 2 minutes unless you return."



    public MainPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageImage, waitInterval, "Main Page Image");
    }

    public void clickOn_menuBtn(){
            click_onWebElement_waitClickable(driver, menuBtn, waitInterval,"Menu Button");
    }

    public void clickOn_profileSettingsBtn(){
        click_onWebElement_waitClickable(driver, profileSettingsBtn, waitInterval,"Profile Settings Button");
    }

    public void clickOn_privacySettingsBtn(){
        click_onWebElement_waitClickable(driver, privacySettingsBtn, waitInterval,"Privacy Settings Button");
    }

    public void clickOn_logOutBtn(){
        click_onWebElement_waitClickable(driver, logOutBtn, waitInterval,"Log Out Button");
    }

    public void assert_WelcomeMsg(String firstName){
        assert_getText_WebElement(driver, welcomeMsg, waitInterval, "Welcome Message", firstName);
    }

    public void assert_dropDownMenu_isPresent(){
        assertElementPresence_WebElement(driver, dropDownMenu, waitInterval, "Drop Down Menu");
    }

    public void assert_dropDownMenu_notPresent(){
        assertElementNotPresent_WebElement(driver, dropDownMenu, 5, "Drop Down Menu");
    }

    public void clickOn_study(String studyNumber, String studyName){
        click_onWebElement_waitClickable(driver, study(studyNumber), waitInterval,studyName);
    }

    public void assert_studyName( String studyName){
        assert_getText_WebElement(driver, studyName(studyName), waitInterval, "Study Title", studyName);
    }

    public void assert_studyStatus(String studyNumber, String studyStatus){
        String upperCase_studyStatus = studyStatus.toUpperCase();
        assert_getText_WebElement(driver, studyStatus(studyStatus, studyNumber), waitInterval, "Study Status", upperCase_studyStatus);
    }

    public void assert_studyTaskMsg(String studyTaskMsgTxt){
        assert_getText_WebElement(driver, studyTaskMsg, waitInterval, "Study Task Message", studyTaskMsgTxt);
    }

    public void clickOn_viewTasksBtn(){
        click_onWebElement_waitClickable(driver, viewTasksBtn, waitInterval,"View Tasks Button");
    }

    public void assert_viewTasksBtn_notPresent(){
        assertElementNotPresent_WebElement(driver, viewTasksBtn, 5, "View Tasks Button");
    }

    public void assert_viewTasksBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, viewTasksBtn, waitInterval, "View Tasks Button");
    }

    public void clickOn_resumeTasksBtn(){
        click_onWebElement_waitClickable(driver, resumeTasksBtn, waitInterval,"Resume Tasks Button");
    }

    public void assert_resumeTasksBtn_notPresent(){
        assertElementNotPresent_WebElement(driver, resumeTasksBtn, 5, "Resume Tasks Button");
    }

    public void assert_resumeTasksBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, resumeTasksBtn, waitInterval, "Resume Tasks Button");
    }

    public void clickOn_viewAllBtn(){
        click_onWebElement_waitClickable(driver, viewAllBtn, waitInterval,"View All Button");
    }



    public void assert_viewAllBtn_notPresent(){
        assertElementNotPresent_WebElement(driver, viewAllBtn, 5, "View All Button");
    }

    public void assert_viewAllBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, viewAllBtn, waitInterval, "View All Button");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy Button");
    }

    public void clickOn_inactivityWarningPopUpTitle(){
        click_onWebElement_waitClickable(driver, inactivityWarningPopUpTitle, waitInterval,"Inactivity Warning PopUp");
    }

    public void assert_inactivityWarningPopUpTitle(String inactivityWarningTitleTxt){
        assert_getText_WebElement(driver, inactivityWarningPopUpTitle, 90, "Inactivity Warning PopUp Message", inactivityWarningTitleTxt);
    }

    public void assert_inactivityWarningPopUpBody(String assert_inactivityWarningBodyTxt){
        assert_getText_WebElement(driver, inactivityWarningPopUpBody, 90, "Inactivity Warning PopUp Message", assert_inactivityWarningBodyTxt);
    }

}



