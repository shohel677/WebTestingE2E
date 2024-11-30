package pages;

import abstractComponents.GenericWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static abstractComponents.AbstractComponent.waitForSeconds;
import static abstractComponents.AbstractComponent.waitUntilVisible;

public class CheckoutPage extends GenericWebPage {

    private enum inputText{
        cardHolderName("Cardholder name"),
        cardNumber("Card number"),
        continueText("Continue"),
        confirm("Confirm"),
        cardCode("Card code");
        private String text;

        inputText(String text) {
            this.text = text;

        }
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
    LocalDate today = LocalDate.now();
    String formattedDay = today.format(formatter);
    LocalDate twoYearsLater = today.plusYears(2);
    int yearTwoYearsLater = twoYearsLater.getYear();
    String formattedYear = String.format("%04d", yearTwoYearsLater);
    private final static String shippingMethodXpath = "//label[contains(text(),'Next Day Air')]/preceding-sibling::input";
    private final static String cardSelectXpath = "//label[contains(text(),'Credit Card')]/preceding-sibling::input";
    private final static String continueXpath = "//div[@id='1%s']//button[text()='2%s']";
    private final static String paymentInputXpath = "//label[contains(text(), '%s')]/parent::td/following-sibling::td/input";
    private final static String selectXpath = "//select[@id='%s']";
    private final static String orderConfirmXpath = "//button[text()='%s']";



    private WebElement orderConfirm(){
        return instanceDriver.findElement(By.xpath(String.format(orderConfirmXpath, inputText.confirm.text)));
    }
    private WebElement monthSelect(){
        return instanceDriver.findElement(By.xpath(String.format(selectXpath, "ExpireMonth")));
    }
    private WebElement yearSelect(){
        return instanceDriver.findElement(By.xpath(String.format(selectXpath, "ExpireYear")));
    }
    private WebElement cardHolderNameField(){
        return instanceDriver.findElement(By.xpath(String.format(paymentInputXpath, inputText.cardHolderName.text)));
    }
    private WebElement cardNumberField(){
        return instanceDriver.findElement(By.xpath(String.format(paymentInputXpath, inputText.cardNumber.text)));
    }
    private WebElement cardCodeField(){
        return instanceDriver.findElement(By.xpath(String.format(paymentInputXpath, inputText.cardCode.text)));
    }
    private WebElement shippingMethod(){
        return instanceDriver.findElement(By.xpath(shippingMethodXpath));
    }
    private WebElement paymentMethod(){
        return instanceDriver.findElement(By.xpath(cardSelectXpath));
    }
    private WebElement shippingContinue(){
        return instanceDriver.findElement(By.xpath(String.format(continueXpath, "shipping-method-buttons-container", inputText.continueText.text)));
    }
    private WebElement paymentContinue(){
        return instanceDriver.findElement(By.xpath(String.format(continueXpath, "payment-method-buttons-container", inputText.continueText.text)));
    }
    private WebElement cardContinue(){
        return instanceDriver.findElement(By.xpath(String.format(continueXpath, "payment-info-buttons-container", inputText.continueText.text)));
    }

    public void selectShippingMethodAndContinue(){
        waitForSeconds(2);
        waitUntilVisible(shippingMethodXpath);
        shippingMethod().click();
        logger.info("Selecting shipping method");
        shippingContinue().click();
        logger.info("Click continue");


    }
    public void selectPaymentTypeAndContinue(){
        waitForSeconds(2);
        waitUntilVisible(cardSelectXpath);
        paymentMethod().click();
        logger.info("Selecting payment method credit card");
        paymentContinue().click();
        logger.info("Click continue");


    }
    private void selectField(WebElement element, String value){
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }
    private void selectDateAndYear(){
        selectField(monthSelect(), formattedDay);
        logger.info("Select card expiry day");
        selectField(yearSelect(), formattedYear);
        logger.info("Select card expiry year");
    }
    public void cardInformationAndContinue(){

        waitForSeconds(5);
        cardHolderNameField().sendKeys("Alla Donald");
        logger.info("Input card holder name");
        waitForSeconds(1);
        cardNumberField().sendKeys("4001 5900 0000 0001");
        logger.info("Input card number");
        waitForSeconds(1);
        selectDateAndYear();
        waitForSeconds(1);
        cardCodeField().sendKeys("123");
        logger.info("Input card code");
        waitForSeconds(1);
        cardContinue().click();
        logger.info("Click continue");

    }
    public void clickOrderConfirm(){
        waitForSeconds(3);
        orderConfirm().click();
        logger.info("Click order confirm button");
    }




}
