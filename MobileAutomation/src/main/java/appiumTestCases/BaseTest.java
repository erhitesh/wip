package appiumTestCases;

import driverSession.DriverSession;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.enums.PlatformTypes;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.*;
import utils.RestUtils;

import javax.naming.directory.SearchResult;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public AndroidDriver driver;
    public WebDriverWait wait;
    protected SearchResult searchResult = null;
    protected LoginPage loginPage = null;
    protected HomePage homePage = null;
    protected AmazonSearchPage amazonSearchPage = null;
    protected CartPage cartPage = null;
    protected DeliveryAddressPage deliveryAddressPage = null;
    protected PaymentPage paymentPage = null;
    protected PlaceYourOrder placeYourOrder = null;

    @BeforeMethod
    @Parameters({"deviceInfo"})
    public void getDriverInstance(String deviceInfo) {
        PlatformTypes platformType = PlatformTypes.ANDROID;
        try {
            driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), Capabilities.getDesiredCapability(deviceInfo));
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

            // First Set the driver instance
            DriverSession.setLastExecutionDriver(driver);
            wait = new WebDriverWait(DriverSession.getLastExecutionDriver(), 10);
            loginPage = new LoginPage(DriverSession.getLastExecutionDriver());
            homePage = new HomePage(DriverSession.getLastExecutionDriver());
            amazonSearchPage = new AmazonSearchPage(DriverSession.getLastExecutionDriver());
            cartPage = new CartPage(DriverSession.getLastExecutionDriver());
            deliveryAddressPage = new DeliveryAddressPage(DriverSession.getLastExecutionDriver());
            paymentPage = new PaymentPage(DriverSession.getLastExecutionDriver());
            placeYourOrder = new PlaceYourOrder(DriverSession.getLastExecutionDriver());
        } catch (MalformedURLException e) {
            e.getMessage();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                RestUtils.captureScreenshot(DriverSession.getLastExecutionDriver(), result.getMethod().getMethodName());

            } else if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("TC PASSED");

            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("TC SKIPPED");
            }

            if (DriverSession.getLastExecutionDriver() != null) {
                driver = null;
                DriverSession.getLastExecutionDriver().closeApp();
            }
        } catch (Exception e) {
            if (DriverSession.getLastExecutionDriver() != null) {
                DriverSession.setLastExecutionDriver(null);
            }
        }
    }
}
