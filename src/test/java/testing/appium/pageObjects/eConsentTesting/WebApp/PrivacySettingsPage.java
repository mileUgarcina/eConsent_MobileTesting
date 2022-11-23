package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static testing.appium.helpers.TCLogger.*;
import static testing.appium.helpers.Utils.*;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.PRIVACY_SETTINGS_PAGE_LINK;
import static testing.appium.runner.propertyFile.DataProvider.environmentData.appEnvironment;


public class PrivacySettingsPage {

    static AppiumDriver<MobileElement> driver;

    private static final int waitInterval = 15;

    private final By backBtn = By.xpath("//i[@class='fas fa-arrow-left']");
    private static final By pageTitle = By.xpath("//p[normalize-space()='Privacy Policy']");
    private final By privacyPolicyBtn = By.cssSelector("a[href='https://florencehc.com/privacy-policy/']");
    private final By cookiesTgl = By.xpath("//app-toggle[@id='cookies']");
    private final By cookiesTgl_On = By.xpath("//app-toggle[@id='cookies']//i[@class='fas fa-toggle-on fa-2x toggle__icon-on']");
    private final By cookiesTgl_Off = By.xpath("//app-toggle[@id='cookies']//i[@class='fas fa-toggle-off fa-2x toggle__icon-off']");
    private final By trainingAndUpdatesTgl = By.xpath("//app-toggle[@id='training']");
    private final By trainingAndUpdatesTgl_On = By.xpath("//app-toggle[@id='training']//i[@class='fas fa-toggle-on fa-2x toggle__icon-on']");
    private final By trainingAndUpdatesTgl_Off = By.xpath("//app-toggle[@id='training']//i[@class='fas fa-toggle-off fa-2x toggle__icon-off']");
    private final By saveBtn = By.xpath("//button[normalize-space()='Save']");



    public PrivacySettingsPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_PrivacyPolicyPage_for_Postcondition( ) {

        try {
            LoggerWaiting("To be Visible element: \"" + "Privacy Policy Page Title" + "\"");
            Thread.sleep(1000);
            waitForElement(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageTitle));
            LoggerAssert_Passed("Verification successful - element: \"" + "Privacy Policy Page Title" + "\" is Present");
        } catch (Exception e) {
            LoggerAssert_Failed("Verification unsuccessful - element: \"" + "Privacy Policy Page Title" + "\" is not Present");
            String appUrl = appEnvironment();
            openWebPage_WebElement(driver, appUrl + PRIVACY_SETTINGS_PAGE_LINK);
        }
    }

//    /**
//     * How long it takes to load the page.
//     *
//     * @return duration of time to load the page
//     */
//    @SuppressWarnings("unchecked")
//    public Integer getPageLoadTime() {
//        HashMap<String, Object> metrics = new HashMap<>();
//        metrics.put("type", "sauce:performance");
//        Map<String, Object> perfMetrics = (Map<String, Object>) driver.executeScript("sauce:log", metrics);
//        return Integer.parseInt(perfMetrics.get("load").toString());
//    }


    public void clickOn_backBtn(){
        click_onWebElement_waitClickable(driver, backBtn, waitInterval,"Back Button");
    }

    public void clickOn_cookiesTgl(){
        click_onWebElement_waitClickable(driver, cookiesTgl, waitInterval,"Cookies Toggle Switch");
    }

    public void clickOn_trainingAndUpdatesTgl_On(){
        click_onWebElement_waitClickable(driver, trainingAndUpdatesTgl_On, waitInterval,"Training And Updates Toggle Switch");
    }

    public void clickOn_trainingAndUpdatesTgl_Off(){
        click_onWebElement_waitClickable(driver, trainingAndUpdatesTgl_Off, waitInterval,"Training And Updates Toggle Switch");
    }

    public void clickOn_saveBtn(){
        click_onWebElement_waitClickable(driver, saveBtn, waitInterval,"Save Button");
        wait_foreElementToBeDisabled(driver, saveBtn, waitInterval, "Save Button");
    }

    public void assert_saveBtn_isDisable(){
        assertElementIsDisable_WebElement(driver, saveBtn, waitInterval, "Save Button");
    }

    public void assert_saveBtn_isEnabled(){
        assertElementIsEnabled_WebElement(driver, saveBtn, waitInterval, "Save Button");
    }

    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Privacy Policy Page Title");
    }

    public void clickOn_privacyPolicyBtn(){
        click_onWebElement_waitClickable(driver, privacyPolicyBtn, waitInterval,"Privacy Policy");
    }

    public void assert_cookiesTgl_On(){
        assertElementPresence_WebElement(driver, cookiesTgl_On, waitInterval, "Cookies Toggle Switch is ON");
    }

    public void assert_cookiesTgl_Off(){
        assertElementPresence_WebElement(driver, cookiesTgl_Off, waitInterval, "Cookies Toggle Switch is OFF");
    }

    public void assert_trainingAndUpdatesTgl_On(){
        assertElementPresence_WebElement(driver, trainingAndUpdatesTgl_On, waitInterval, "Training And Updates Toggle Switch is ON");
    }

    public void assert_trainingAndUpdatesTgl_Off(){
        assertElementPresence_WebElement(driver, trainingAndUpdatesTgl_Off, waitInterval, "Training And Updates Toggle Switch is OFF");
    }





}



