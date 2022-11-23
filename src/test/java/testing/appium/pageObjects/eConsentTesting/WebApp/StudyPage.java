package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static testing.appium.helpers.Utils.*;


public class StudyPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 20;

    private final By backBtn = By.xpath("//img[@alt='arrow-left']");
    private final By pageTitle = By.xpath("//h3[normalize-space()='Study']");
    private final By studyContainer = By.xpath("(//div[@class='studies-details-wrapper u-margin-b-3'])[1]");

    private final By viewFormBtn = By.xpath("//div[@class='study-details__forms-item-content--action']");
    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");



    public StudyPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Study Page Title");
    }


    public void assert_studyTitle(String studyName){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Study Name", studyName);
    }

    public void assert_siteName(String siteName){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Site Name", siteName);
    }

    public void assert_studyOfficePhoneNumber(String phone){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Office Phone Number", phone);
    }

    public void assert_studyEmail(String studyEmail){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Email", studyEmail);
    }

    public void assert_studyStatus(String studyStatus){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Study Status", studyStatus);
    }

    public void assert_formType(String formType){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Form Type", formType);
    }

    public void assert_formStatus(String formStatus){
        assert_getText_WebElement(driver, studyContainer, waitInterval, "Form Type", formStatus);
    }

    public void clickOn_viewFormBtn(){
        click_onWebElement_waitClickable(driver, viewFormBtn, waitInterval,"View Tasks Button");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy Button");
    }

}



