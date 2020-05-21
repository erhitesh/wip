package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchPage extends BasePage {

    public AmazonSearchPage(AndroidDriver driver) {
        super(driver);
    }

    // Locator as per pom
    By searchbox = By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
    By selectItemFromSearchList = By.id("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text_layout");
    By filterBtn = By.id("s-all-filters");
    By setTVScreenSize = By.xpath("//android.view.View[@text='60-69 in']");
    By selectFirstSearch = By.xpath("//android.view.View[@resource-id='search']//android.view.View[contains(@text,'65')]");
    By brandName = By.id("productTitleExpanderRow");
    By title = By.id("title");
    By seeAllBuyingOption = By.xpath("//android.widget.Button[@text='See All Buying Options']");
    By productInfo =  By.id("olpProductMobileBack");
    By addToCartBtn = By.xpath("//android.widget.Button[contains(@text,'Add to Cart')]");
    By productPrice = By.xpath("//android.view.View[contains(@text,'$')]");
    By productActualPrice = By.id("priceblock_ourprice");
    By deliveryCharges = By.id("ourPrice_availability");
    By cartValue = By.id("com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count");

    /**
     * Used to apply filter on search
     */
    public void applyFilter(){
        sleep(2);
        waitAndClick(filterBtn);
        sleep(3);
        swipe(1);
        waitAndClick(setTVScreenSize);
    }

    /**
     * Used to search an item
     * @param itemName String value as Item
     */
    public void searchItem(String itemName) {
        sendText(searchbox, itemName);
        waitAndClick(selectItemFromSearchList);
        applyFilter();
        waitAndClick(selectFirstSearch);
        ExtentManager.logs(Status.INFO, "Item " + itemName + " search & selected successfully");
        logger.info(itemName + " search & selected successfully");
    }

    /**
     * Get the Brand name
     * @return String value as Brand Name
     */
    public String getBrandName() {
        return getText(brandName);
    }

    /**
     * Used to get Product Info
     * @return String value as info
     */
    public String getProductInfo() {
        return getText(title);
    }

    /**
     * Navigate to the cart page
     */
    public void navigateToCartPage() {
        sleep(2);
        waitAndClick(cartValue);
    }

    /**
     * Get product price with Delivery
     * @return String value as product price
     */
    public String getProductPriceWithDL() {
        return getText(productPrice);
    }

    /**
     * Get the product Delivery charges
     * @return String value as Delivery charge
     */
    public String productDeliveryCharge() {
        return getText(deliveryCharges).replace("+", "").replace("Delivery", "").trim();
    }

    /**
     * Used to get product price
     * @return String value as product price
     */
    public String getProductPrice() {
        return getText(productActualPrice).trim();
    }

    /**
     * Used to addToCart
     */
    public void addToCart() {
        waitAndClick(addToCartBtn);
        sleep(3);
    }

    /**
     * Used to add item into cart
     * @return List value
     */
    public List<String> addItemToCart() {
        List<String> productInfo = new ArrayList<>();
        productInfo.add(getProductInfo());
        sleep(2);
        productInfo.add(getProductPrice());
        productInfo.add(productDeliveryCharge());
        if (getText(cartValue).contains("1")) {
            logger.info("Product Already Added");
        }else {
            addToCart();
        }

        assertTrue(getText(cartValue).contains("1"), "Not");

        // Navigate to cart Page
        navigateToCartPage();

        ExtentManager.logs(Status.INFO, "Item has beed added to the cart");

        return productInfo;
    }
}
