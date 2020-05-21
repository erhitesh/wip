package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }
    By hamburgerIcon = By.id("com.amazon.mShop.android.shopping:id/chrome_action_bar_burger");
    By alreadyACustomerBnt = By.id("sign_in_button");
    By progressBar = By.id("apspinner_progressbar");
    By userEmail = By.id("ap_email_login");
    By userPassword = By.id("ap_password");
    By continueBtn = By.id("continue");
    By loginBtn = By.id("signInSubmit");


    /**
     * Used to do the login
     * @param userName String username
     * @param password String password
     */
    public void doLogin(String userName, String password) {
        if (!isElementPresent(hamburgerIcon)) {
            ExtentManager.logs(Status.INFO, "User need to enter valid credential");
            waitAndClick(alreadyACustomerBnt);
            ExtentManager.logs(Status.INFO, "Sign In Button Click Successfully");
            waitAndFindElement(userEmail).sendKeys(userName);
            ExtentManager.logs(Status.INFO, "User name enter successfully");
            waitAndFindElement(continueBtn).click();
            ExtentManager.logs(Status.INFO, "Continue button click successfully");
            waitAndFindElement(userPassword).sendKeys(password);
            ExtentManager.logs(Status.INFO, "User Password enter successfully");
            waitAndFindElement(loginBtn).click();
            ExtentManager.logs(Status.INFO, "Sign-In Button click successfully");
        } else{
            ExtentManager.logs(Status.INFO, "User Already Login With Valid Credential");
            logger.info("User Already Login");
        }
    }
}
