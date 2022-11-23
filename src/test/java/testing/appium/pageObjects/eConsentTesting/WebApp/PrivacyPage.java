package testing.appium.pageObjects.eConsentTesting.WebApp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import static testing.appium.helpers.Utils.*;


public class PrivacyPage {

    static AppiumDriver<MobileElement> driver;

    private final int waitInterval = 30;

    private final By pageTitle = By.xpath("//h1[normalize-space()='Privacy']");



    public PrivacyPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }


    public void assert_pageTitle(){
        assertElementPresence_WebElement(driver, pageTitle, waitInterval, "Privacy Page Title");
    }



}



