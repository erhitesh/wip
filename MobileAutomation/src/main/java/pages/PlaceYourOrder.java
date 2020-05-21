package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;

public class PlaceYourOrder extends BasePage {
    public PlaceYourOrder(AndroidDriver driver) {
        super(driver);
    }

    By placeYourOrder = By.xpath("(//android.widget.Button[contains(@text,'Place your order')])[1]");
    By itemPrice = By.xpath("//android.view.View[@text='Items:']/following-sibling::android.view.View");
    By deliveryPrice = By.xpath("//android.view.View[@text='Delivery:']/following-sibling::android.view.View");
    By deliveryUserFullName = By.xpath("(//android.view.View[@resource-id='shipping-summary']//child::android.widget.ListView//android.view.View)[1]");

    /**
     * Get the Item price value
     * @return String value as price
     */
    public String getItemPrice() {
        return getText(itemPrice);
    }

    /**
     * Get the Item delivery price
     * @return String value as item delivery price
     */
    public String getItemDeliveryPrice() {
        return getText(deliveryPrice);
    }

    /**
     *  Used to get Delivery User Name
     * @return String value as Delivery user Name
     */
    public String getDeliveryUserName() {
        return  getText(deliveryUserFullName).toString().trim();
    }

    /**
     * Verify the Price & Desciption value on Place an order page with product info page
     * @param deliveryUserFullName
     * @param price
     * @param deliveryCharges
     */
    public void verifyItemPriceDeliveryChargesAndUserName(String deliveryUserFullName, String price, String deliveryCharges) {
        assertEquals(getItemPrice(), price, "Price");
        assertEquals(getItemDeliveryPrice(), deliveryCharges, "Delivery Price");
        assertEquals(getDeliveryUserName(), deliveryUserFullName, "Delivery Address");
        ExtentManager.logs(Status.INFO, "Item price & Delivery user Name verified successfully on place an order page");
        logger.info("Item price & Delivery user Name verified successfully on place an order page");
    }

    /**
     * Used to place an order
     */
    public void placeYourOrder() {
        waitAndClick(placeYourOrder);
        logger.info("Order Placed successfully");
    }
}
