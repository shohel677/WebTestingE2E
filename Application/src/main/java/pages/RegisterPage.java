package pages;

import abstractComponents.GenericWebPage;
import elements.guiObject.Label;
import org.openqa.selenium.By;


import java.time.Duration;

public class RegisterPage extends GenericWebPage {
    private final static String pageTitlePath = "//div[@class='page-title']/h1[normalize-space()='Register']";
    private final static String registrationConfirmationPath = "//div[text()='Your registration completed']";
    private static final Label pageTitle = new Label(By.xpath(pageTitlePath), "Page Title");
    private static final Label registrationConfirmation = new Label(By.xpath(registrationConfirmationPath), "Page Title");
    public boolean isRegisterPage(){
        return pageTitle.isDisplayed(Duration.ofSeconds(15));
    }
    public boolean isRegistrationConfirmationDisplayed(){
         return registrationConfirmation.isDisplayed(Duration.ofSeconds(30));
    }





}
