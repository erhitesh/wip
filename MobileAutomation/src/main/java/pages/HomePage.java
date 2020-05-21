package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;

public class HomePage extends BasePage {

    public HomePage(AndroidDriver<MobileElement> driver) {
        super(driver);
    }
    By hamburgerIcon = By.id("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger");
    By settingIcon = By.xpath("//android.widget.LinearLayout[@resource-id='com.amazon.mShop.android.shopping:id/anp_drawer_item']//android.widget.TextView[@text='Settings']");
    By mainMenuIcon = By.xpath("//android.widget.TextView[@text='Main menu']");
    By countryAndLanguageIcon = By.xpath("//android.widget.TextView[@text='Country & Language']");
    By countryRegionLanguageView = By.xpath("//android.view.View[@text='Country/Region & Language']");

    By countryRegiontext = By.id("landing-countryButton-label");

    By countryRegionBtn = By.xpath("//android.widget.Button[contains(@text,'Country/Region')]");
    By countryRegionView = By.id("pref-page-title");
    String selectCountryValue = "//android.widget.RadioButton[contains(@text,'Replace')]";
    By doneBtn = By.xpath("//android.widget.Button[@text='Done']");

    /**
     * Click on the Hamberger Menu
     */
    public void clickOnHambergerMenu(){
        waitAndClick(hamburgerIcon);
    }

    /**
     * Used to select the Country value
     * @param expectedCountryValue String expected country value
     */
    public void selectCountryValue(String expectedCountryValue){
        String countryName = "";
        if (isElementPresent(hamburgerIcon)){
            clickOnHambergerMenu();
            click(settingIcon);
            waitVisibility(mainMenuIcon);
            click(countryAndLanguageIcon);
            waitVisibility(countryRegionLanguageView);

            countryName = getText(countryRegionBtn);
            if (countryName.contains(expectedCountryValue));
            else {
                waitAndClick(countryRegionBtn);
                waitVisibility(countryRegionView);
                waitAndClick(By.xpath(selectCountryValue.replace("Replace", expectedCountryValue)));
            }
            countryName = getText(countryRegionBtn);
            waitAndClick(doneBtn);
            ExtentManager.logs(Status.INFO, "Region is select as Australia..");
            logger.info("Region is select as Australia..");
        }
    }
}
