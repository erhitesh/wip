package pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ExtentManager;
import utils.PropertyOperations;

import java.util.HashMap;
import java.util.Map;

public class PaymentPage extends BasePage {
    public PaymentPage(AndroidDriver driver) {
        super(driver);
    }

    By addCC = By.xpath("//android.view.View[@text='Add a credit or debit card']");
    By continueBtn = By.xpath("(//android.widget.Button[contains(@text,'Continue')])[1]");
    String CCInfo = "//android.widget.RadioButton[starts-with(@text,'CCType')]";
    By CCName = By.xpath("//android.view.View[9]/android.widget.EditText");
    By CCNumber = By.xpath("//android.view.View[10]/android.widget.EditText");
    By openMonthView = By.xpath("//android.view.View[11]/android.view.View[5]");
    String selectMonthValue = "(//android.view.View[@text='CCMonth'])[1]";
    By openYearView = By.xpath("//android.view.View[11]/android.view.View[6]");
    String selectYearValue = "(//android.view.View[@text='CCYear'])[1]";
    By addYourCardBtn = By.xpath("//android.widget.Button[contains(@text,'Add your card')]");

    /**
     * Used to hold the fake CC Detail
     * @return Map value
     */
    public Map<String, String> getCardDetail(){
        Map<String, String> cardDetail = new HashMap<>();
        cardDetail.put("CCName", PropertyOperations.getPropertyData("Amazon", "CCName"));
        cardDetail.put("CCNumber", PropertyOperations.getPropertyData("Amazon", "CCNumber"));
        cardDetail.put("CCMonth",PropertyOperations.getPropertyData("Amazon", "CCMonth"));
        cardDetail.put("CCYear",PropertyOperations.getPropertyData("Amazon", "CCYear"));
        cardDetail.put("CCType",PropertyOperations.getPropertyData("Amazon", "CCType"));

        return cardDetail;
    }

    /**
     * Used to add CC
     * @param map CC detail as map
     * @return Return Map as CC details
     */
    public Map<String, String> addCC(Map<String, String> map) {
        sendText(CCName, map.get("CCName"));
        sendText(CCNumber, map.get("CCNumber"));
        waitAndClick(openMonthView);
        waitAndClick(By.xpath(selectMonthValue.replace("CCMonth", map.get("CCMonth"))));
        waitAndClick(openYearView);
        waitAndClick(By.xpath(selectYearValue.replace("CCYear", map.get("CCYear"))));
        waitAndClick(addYourCardBtn);
        sleep(3);
        return map;
    }

    /**
     * Used to do the payment
     * @param cardDeailInfo : as map
     */
    public void doPayment(Map<String, String> cardDeailInfo) {

        if (isElementPresent(addCC)) {
            waitAndClick(addCC);
            addCC(cardDeailInfo);
        }
        if (isElementPresent(By.xpath(CCInfo.replace("CCType", cardDeailInfo.get("CCType"))))){
            waitAndClick(continueBtn);
        }
        else if (!isElementPresent(continueBtn)) {
            waitAndClick(addCC);
            addCC(cardDeailInfo);
        }
        ExtentManager.logs(Status.INFO, "Payment type added successfully");
        logger.info("Payment type added successfully");
    }
}
