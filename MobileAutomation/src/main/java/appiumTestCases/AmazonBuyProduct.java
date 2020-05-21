package appiumTestCases;

import org.testng.annotations.Test;
import utils.PropertyOperations;

import java.util.List;

public class AmazonBuyProduct extends BaseTest {

    @Test
    public void placeAnOrder() {
        List<String> productInfo = null;
        loginPage.doLogin(PropertyOperations.getPropertyData("Amazon", "UserName"), PropertyOperations.getPropertyData("Amazon", "Password"));
        homePage.selectCountryValue(PropertyOperations.getPropertyData("Amazon", "CountryName"));
        amazonSearchPage.searchItem(PropertyOperations.getPropertyData("Amazon", "SearchItem"));
        productInfo = amazonSearchPage.addItemToCart();
        cartPage.verifyInfoOnCheckoutPage(productInfo.get(0), productInfo.get(1));
        cartPage.navigateToDeliveryPage();
        deliveryAddressPage.selectDeliveryAddress(deliveryAddressPage.getDeliveryAddress());
        deliveryAddressPage.navigateToPaymentPage();
        paymentPage.doPayment(paymentPage.getCardDetail());
        placeYourOrder.verifyItemPriceDeliveryChargesAndUserName(PropertyOperations.getPropertyData("Amazon", "fullName"), productInfo.get(1), productInfo.get(2));
        placeYourOrder.placeYourOrder();
    }
}
