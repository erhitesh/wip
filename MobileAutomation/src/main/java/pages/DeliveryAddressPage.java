package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;
import utils.PropertyOperations;

import java.util.HashMap;
import java.util.Map;

public class DeliveryAddressPage extends BasePage {
    public DeliveryAddressPage(AndroidDriver driver) {
        super(driver);
    }

    By addNewAddress = By.xpath("//android.view.View[@text='Add a New Address']");
    By selectDeliverToThisAddressBtn = By.xpath("//android.widget.Button[contains(@text,'Deliver to this address')]");
    String checkAddressAlreadyAdded = "//android.widget.RadioButton[contains(@text,'fullName')]";
    String chooseDeliveryAddress = "//android.widget.RadioButton[contains(@text,'fullName')]//..";
    By fullName = By.id("enterAddressFullName");
    By phoneNumber = By.id("enterAddressPhoneNumber");
    By addressLine1 = By.id("enterAddressAddressLine1");
    By postcode = By.id("enterAddressPostalCode");
    By city = By.id("enterAddressCitySelectContainer");
    String selectCity = "//android.view.View[@text='city' and starts-with(@resource-id,'enterAddressCitySelectNative')]";
    By state = By.id("enterAddressStateOrRegionText");
    By useSameAddressForBillingRadioBtn = By.xpath("//android.widget.CheckBox[@text()='Use same address for billing']");
    By deliverToThisAddressBtn = By.id("address-country-container");
    By continueBtn = By.xpath("//android.widget.Button[@text='Continue']");
    By chooseDeliveryOptionBtn = By.xpath("//android.widget.Button[@text='Continue']");

    /**
     * Used to hold the Delivery address
     * @return Map Value
     */
    public Map<String, String> getDeliveryAddress(){
        Map<String, String> map = new HashMap<>();
        map.put("fullName", PropertyOperations.getPropertyData("Amazon", "fullName"));
        map.put("phoneNumber", PropertyOperations.getPropertyData("Amazon", "phoneNumber"));
        map.put("addressLine1", PropertyOperations.getPropertyData("Amazon", "addressLine1"));
        map.put("postcode", PropertyOperations.getPropertyData("Amazon", "postcode"));
        map.put("city", PropertyOperations.getPropertyData("Amazon", "city"));
        map.put("state", PropertyOperations.getPropertyData("Amazon", "state"));

        return map;
    }

    /**
     * Used to add new address
     * @param map delivery info as map
     */
    public void addNewAddress(Map<String, String> map) {
        waitAndClick(addNewAddress);
        waitVisibility(fullName);
        sendText(fullName, map.get("fullName"));
        sendText(phoneNumber, map.get("phoneNumber"));
        sendText(addressLine1, map.get("addressLine1"));
        sendText(postcode, map.get("postcode"));
        waitAndClick(city);
        waitAndClick(By.xpath(selectCity.replace("city", map.get("city"))));
        if (getText(state).equals(map.get("state"))){
            logger.info("Valid address added");
        }
        waitAndClick(deliverToThisAddressBtn);
    }

    /**
     * Used to add new Delivery address
     * @param map address as map
     */
    public void addDeliveryAddress(Map<String, String> map) {
        waitVisibility(fullName);
        sendText(fullName, map.get("fullName"));
        sendText(phoneNumber, map.get("phoneNumber"));
        sendText(addressLine1, map.get("addressLine1"));
        sendText(postcode, map.get("postcode"));
        waitAndClick(addressLine1);
        sleep(1);
        waitAndClick(city);
        waitAndClick(By.xpath(selectCity.replace("city", map.get("city"))));
        waitAndClick(continueBtn);
    }

    /**
     * Used to select delivery address based on the condition
     * @param map Address as map
     */
    public void selectDeliveryAddress(Map<String, String> map){
        sleep(2);
        String address = checkAddressAlreadyAdded.replace("fullName", map.get("fullName"));
        boolean status = isElementPresent(fullName);
        if (isElementPresent(fullName)){
            addDeliveryAddress(map);
        }else if (isElementPresent(By.xpath(address))){
            By addedAddress = By.xpath(address);
            waitAndClick(By.xpath(address+"//.."));
            waitAndClick(selectDeliverToThisAddressBtn);

        }else if (isElementPresent(addNewAddress)){
            addNewAddress(map);
            // choose delivery option
            waitAndClick(continueBtn);
        }
        ExtentManager.logs(Status.INFO, "Delivery Addredd added successfully");
        logger.info("Delivery Addredd added successfully");
    }

    /**
     * Used to navigate to payment page
     */
    public void navigateToPaymentPage(){
        sleep(3);
        waitAndClick(chooseDeliveryOptionBtn);
        logger.info("Navigate to Payment page");
    }
}
