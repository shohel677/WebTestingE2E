package pages;

import abstractComponents.GenericWebPage;
import elements.guiObject.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static abstractComponents.AbstractComponent.waitUntilVisible;

public class ShoppingCartPage extends GenericWebPage {

    private final static String termsCheckBoxXpath = "//div[@class='terms-of-service']/input[@type='checkbox']";
    private final static String checkoutXpath = "//button[normalize-space()='Checkout']";
    private final static String checkOutAsGuestXpath = "//button[text()='Checkout as Guest']";
    private static final Label checkOutAsGuest = new Label(By.xpath(checkOutAsGuestXpath), "Checkout as guest");
    private static final Label checkout = new Label(By.xpath(checkoutXpath), "Check button");

    private WebElement termsCheckBox(){
        return instanceDriver.findElement(By.xpath(termsCheckBoxXpath));
    }

    public void acceptTermsAndCondition(){
        waitUntilVisible(termsCheckBoxXpath);
        termsCheckBox().click();
        logger.info("Accept terms and condition");

    }
    public void clickCheckoutButton(){
        checkout.isDisplayed(Duration.ofSeconds(30));
        checkout.seleniumClick();
        logger.info("Click checkout button");

    }
    public void checkoutAsGuest(){
        checkOutAsGuest.isDisplayed(Duration.ofSeconds(30));
        checkOutAsGuest.seleniumClick();
        logger.info("Click checkout as guest button");
    }


}
