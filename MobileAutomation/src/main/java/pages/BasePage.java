package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    public static Logger logger;
    private static final int TIMEOUT = 5;
    private static final int POLLING = 100;

    protected BasePage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        logger = Logger.getLogger("MobileAutomation");
        PropertyConfigurator.configure("Log4j.properties");
    }

    /**
     * This method is used to get Webelement
     * @param by : locator type
     * @return : Webelement
     */
    protected WebElement getAndroidElement(By by) {
        WebElement element = null;
        element = driver.findElement(by);

        return element;
    }

    /**
     * This method is used to get list of Webelement
     * @param by : locator type
     * @return : list of Webelement
     */
    protected List<AndroidElement> getAndroidListElement(By by) {
        List<AndroidElement> elementList = null;
        elementList = (List<AndroidElement>) driver.findElements(by);

        return elementList;
    }

    /**
     * This method is used for wait for element to be visible
     * @param by locator value
     */
    protected void waitVisibility(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Used to halt execution for specified number of seconds
     * @param timeoutsInSeconds : integer value
     */
    protected void sleep(int timeoutsInSeconds) {
        try {
            Thread.sleep(1000 * timeoutsInSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to Click
     * @param by locator type
     */
    protected void waitAndClick(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    /**
     * Used to click
     * @param by locator type
     */
    protected void click(By by) {
        waitVisibility(by);
        driver.findElement(by).click();
    }

    /**
     * Used to get the status of radio button
     * @param by locator type
     * @return boolean value
     */
    protected boolean getRadioBtnStatus(By by) {
        boolean status = false;
        sleep(2);
        try {
            if (driver.findElement(by).isSelected())
                status = true;
        } catch (Exception e){}

        return  status;
    }

    /**
     * Used to check element is present or not
     * @param by locator type
     * @return boolean value
     */
    protected boolean isElementPresent(By by) {
        boolean elementStatus = false;
        sleep(2);
        try {
                elementStatus = driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            elementStatus = false;
        }

        return elementStatus;
    }

    /**
     * Used to find the element
     * @param by locator type
     * @return WebElement
     */
    protected WebElement waitAndFindElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Used to find out the list of element
     * @param by locator type
     * @return List of Webelement
     */
    protected List<WebElement> waitAndFindElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    /**
     * Used to get the text
     * @param by locator type
     * @return String value
     */
    protected String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    /**
     * Used to get the text from element
     * @param element Webelement
     * @return String value
     */
    protected String getText(AndroidElement element) {
        return element.getText();
    }

    /**
     * Used to send the value to textbox
     * @param by locator type
     * @param message String msg
     */
    protected void sendText(By by, String message) {
        waitAndFindElement(by).sendKeys(message);
    }

    /**
     * Used to check the assert condition
     * @param status boolean value
     * @param msg String message
     */
    protected void assertTrue(boolean status, String msg) {
        Assert.assertTrue(status, "Condition is " + msg +" true");
    }

    /**
     * Used to check the assert equal valye
     * @param actual String actual value
     * @param expected String expected value
     */
    protected void assertEquals(String actual, String expected) {
        Assert.assertEquals(actual, expected, actual + " and " + expected + " text are not matched");
    }

    /**
     * Used to check the assert equal valye
     * @param actual String actual value
     * @param expected String expected value
     * @param msg String message
     */
    protected void assertEquals(String actual, String expected, String msg) {
        Assert.assertEquals(actual, expected, actual + " and " + expected +" " + msg + " are not matched");
    }

    /**
     * How many times you want to swipe..
     * @param numberOfTimesSwipe : integer value
     */
    protected void swipe(int numberOfTimesSwipe) {
        int counter = 0;
        Dimension dimension = driver.manage().window().getSize();
        int startX = dimension.getWidth() / 2;
        int endX = startX;
        int startY = (int) (dimension.getHeight() * .60);
        int endY = (int) (dimension.getHeight() * .20);

        TouchAction action = new TouchAction(driver);
        while (numberOfTimesSwipe > counter) {
            action.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000))).moveTo(PointOption.point(endX, endY)).release().perform();
            sleep(2);
            counter++;
        }
    }
}
