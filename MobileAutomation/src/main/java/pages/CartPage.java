package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;

public class CartPage extends BasePage {
    public CartPage(AndroidDriver driver) {
        super(driver);
    }

    By subTotal = By.xpath("(//android.view.View[contains(@text,'$')])[1]");
    By proceedToCheckoutBtn = By.xpath("//android.widget.Button[@text='Proceed to Checkout']");
    By productInfo = By.xpath("//android.view.View[contains(@text,'65')]");

    /**
     * Used to get price value from cart page
     * @return String value
     */
    public String getPriceValue() {
        return getText(subTotal);
    }

    /**
     * Used to get the product info(Description)
     * @return String value
     */
    public String productInfo() {
        return getText(productInfo);
    }

    /**
     * This method is used verify the info on the checkoutpage
     * @param productInfo String value as product info
     * @param price String price value
     */
    public void verifyInfoOnCheckoutPage(String productInfo, String price) {
       assertEquals(getPriceValue(), price, "Price");
       assertEquals(productInfo(), productInfo, "Product Head Line");
       ExtentManager.logs(Status.INFO, "Item Price match successfully");
       logger.info("Verify Item price on cart page");
    }

    /**
     * This method is used to navigate to the Delivery page
     */
    public void navigateToDeliveryPage(){
        waitAndClick(proceedToCheckoutBtn);
        logger.info("Navigate to delivery page");
    }
}
