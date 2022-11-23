package testing.appium.pageObjects.eConsentTesting.WebApp;

;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import static testing.appium.helpers.Utils.*;


public class GoogleHomePage {

    AppiumDriver<MobileElement> driver;

    private final int waitInterval = 30;

//    WebElement searchField = driver.findElement(By.name("q"));
    private static By searchField = By.name("q");
    private static By googleSearchButton = By.name("btnK");


    public GoogleHomePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
//        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }



    public Integer getPageLoadTime() {
        HashMap<String, Object> metrics = new HashMap<>();
        metrics.put("type", "sauce:performance");
        Map<String, Object> perfMetrics = (Map<String, Object>) driver.executeScript("sauce:log", metrics);
        return Integer.parseInt(perfMetrics.get("load").toString());
    }

    public void clickOnSearchField() {
        click_onWebElement_waitClickable(driver, searchField, waitInterval,"Search Field");
    }

   public void insertSearchField(String text) {
       sendKey_toWebElement(driver, searchField, waitInterval,"Search Field", text, true);
    }



}


