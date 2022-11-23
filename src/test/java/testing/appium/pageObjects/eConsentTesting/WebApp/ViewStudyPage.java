package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static testing.appium.helpers.Utils.*;


public class ViewStudyPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 20;


    private final By formDoc = By.xpath("//lib-content[@class='ng-star-inserted']");
    private final By backBtn = By.xpath("//i[@class='fas fa-fw fa-angle-left fa-lg']");
    private final By infoBtn = By.xpath("(//fa-icon[@class='ng-fa-icon'])[1]");
    private final By downloadBtn = By.xpath("(//lib-download-button[@class='ng-star-inserted'])[1]");
    private final By formTypeTitle = By.xpath("(//lib-header[@class='ng-star-inserted'])[1]");
    private final By previousBtn = By.xpath("(//lib-previous-form-button[@class='ng-star-inserted'])[1]");
    private final By nextBtn = By.xpath("((//div[contains(text(),'Next form')])[1]");
    private final By zoomBtn = By.xpath("(//lib-zoom-button[@class='ng-star-inserted'])[1]");
    private final By plusZoomBtn = By.xpath("(//button[@class='generic-button'])[4]");
    private final By minusZoomBtn = By.xpath("(//button[@class='generic-button'])[5]");
    private final By declineStudyBtn = By.xpath("(//button[normalize-space()='Decline study'])[1]");
    private final By privacyPolicyBtn = By.xpath("//a[normalize-space()='Privacy Policy']");
//    Info PopUp Msg
    private final By popUpMsgTitle = By.xpath("(//div[@class='info-modal-header']");
    private final By popUpMsgTxt = By.xpath("//div[@class='info-modal-body']");
    private final By closeInfoPopUpMsgBtn = By.xpath("//div[contains(text(),'Close')]");

//    Decline Study PopUp Msg

    private final By declineStudyPopUpTitle = By.xpath("(//div[@class='modal-header'])[1]");
    private final By declineStudyPopUpBtn = By.xpath("(//button[@class='c-btn--default--outline'])[1]");
    private final By cancelDeclineStudyPopBtn = By.xpath("(//button[@class='c-btn--danger'])[1]");


    public ViewStudyPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, downloadBtn, waitInterval, "View Study Page Title");
    }

    public void assert_formDoc(){
        assertElementPresence_WebElement(driver, formDoc, waitInterval, "Form Document");
    }

    public void clickOn_backBtn(){
        click_onWebElement_waitClickable(driver, backBtn, waitInterval,"Back Button");
    }

    public void clickOn_infoBtn(){
        click_onWebElement_waitClickable(driver, infoBtn, waitInterval,"Info Button");
    }

    public void clickOn_downloadBtn(){
        click_onWebElement_waitClickable(driver, downloadBtn, waitInterval,"Download Button");
    }

    public void clickOn_previousBtn(){
        click_onWebElement_waitClickable(driver, previousBtn, waitInterval,"Previous Button");
    }

    public void clickOn_nextBtn(){
        click_onWebElement_waitClickable(driver, nextBtn, waitInterval,"Next Button");
    }

    public void clickOn_zoomBtn(){
        click_onWebElement_waitClickable(driver, zoomBtn, waitInterval,"Zoom Button");
    }

    public void clickOn_plusZoomBtn(){
        click_onWebElement_waitClickable(driver, plusZoomBtn, waitInterval,"Plus Zoom Button");
    }

    public void clickOn_minusZoomBtn(){
        click_onWebElement_waitClickable(driver, minusZoomBtn, waitInterval,"Minus Zoom Button");
    }

    public void clickOn_declineStudyBtn(){
        click_onWebElement_waitClickable(driver, declineStudyBtn, waitInterval,"Decline Study Button");
    }

    public void assert_formTypeTitle(String formType){
        assert_getText_WebElement(driver, formTypeTitle, waitInterval, "Study Name", formType);
    }

    public void assert_infoPopUpMsgTitle(String infoPopUpMsg){
        assert_getText_WebElement(driver, popUpMsgTitle, waitInterval, "Info PopUp Message Title", infoPopUpMsg);
    }

    public void clickOn_closeInfoPopUpMsgBtn(){
        click_onWebElement_waitClickable(driver, closeInfoPopUpMsgBtn, waitInterval,"Close Info PopUp Message Button");
    }

    public void assert_declineStudyPopUpTitle(String declineStudyPopUpMsg){
        assert_getText_WebElement(driver, declineStudyPopUpTitle, waitInterval, "Decline Study PopUp Title", declineStudyPopUpMsg);
    }

    public void clickOn_declineStudyPopUpBtn(){
        click_onWebElement_waitClickable(driver, declineStudyPopUpBtn, waitInterval,"Decline Study PopUp Button");
    }

    public void clickOn_cancelDeclineStudyPopUpBtn(){
        click_onWebElement_waitClickable(driver, cancelDeclineStudyPopBtn, waitInterval,"Cancel Decline Study PopUp Button");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy Button");
    }


}



