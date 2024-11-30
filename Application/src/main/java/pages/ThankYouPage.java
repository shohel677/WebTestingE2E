package pages;

import abstractComponents.GenericWebPage;
import elements.guiObject.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static abstractComponents.AbstractComponent.waitForSeconds;
import static abstractComponents.AbstractComponent.waitUntilVisible;

public class ThankYouPage extends GenericWebPage {

    private final static String orderConfirmationMessageXpath = "//div/strong[text()='Your order has been successfully processed!']";
    private static final Label orderConfirmationMessage = new Label(By.xpath(orderConfirmationMessageXpath), "Order Confirmation message");

    public boolean verifyOrderConfirmationMessage(){
        waitForSeconds(3);
        return orderConfirmationMessage.isDisplayed(Duration.ofSeconds(15));

    }




}
