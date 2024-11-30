package pages;

import abstractComponents.GenericWebPage;
import elements.guiObject.Button;
import elements.guiObject.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static abstractComponents.AbstractComponent.waitForSeconds;

public class HomePage extends GenericWebPage {
    private enum homeText{
        logo("nopCommerce demo store"),
        register("Register"),
        category("Electronics"),
        categoryOption("Cell phones");

        private final String text;

        homeText(String text) {
            this.text = text;

        }

    }
    private final static String nopCommerceLogoPath = "//a/img[@alt='%s']";
    private final static String registerButtonPath = "//a[text()='%s']";
    private final static String categoryXpath = "//div[@class='header-menu']/ul[contains(@class, 'notmobile')]/li/a[normalize-space()='%s']";
    private final static String categoryOptionXpath = "//div[@class='header-menu']/ul[contains(@class, 'notmobile')]/li/a[normalize-space()='%1s']/following-sibling::ul//a[contains(text(), '%2s')]";
    private static final Label categoryLabel = new Label(By.xpath(String.format(categoryXpath, homeText.category.text)), "Category label");
    private static final Button registerButton = new Button(By.xpath(String.format(registerButtonPath, homeText.register.text)), "Register button");
    private static final Label nopCommerceLogo = new Label(By.xpath(String.format(nopCommerceLogoPath,homeText.logo.text)), "Noopcommerce logo");
    private static final Label category = new Label(By.xpath(String.format(categoryXpath, homeText.category.text)), "Category");
    private static final Label categoryOption = new Label(By.xpath(String.format(categoryOptionXpath, homeText.category.text,  homeText.categoryOption.text)), "Category option");


    public void categoryOptionClick(){
        Actions actions = new Actions(instanceDriver);
        category.isDisplayed(Duration.ofSeconds(30));
        actions.moveToElement(categoryLabel.getWrappedElement()).build().perform();
        actions.moveToElement(categoryOption.getWrappedElement()).click().build().perform();
        logger.info("Clicking cell phones category option");
        waitForSeconds(3);



    }

    public boolean isHomePageLoaded(){
        return nopCommerceLogo.isDisplayed(Duration.ofSeconds(30));
    }

    public void navigateToRegistrationPage(){
        registerButton.seleniumClick();
        logger.info("user clicked on register button");

    }

}
