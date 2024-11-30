package filler;

import elements.guiObject.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static abstractComponents.AbstractComponent.waitForSeconds;
import static abstractComponents.GenericWebPage.*;

public class FillFields {
    private enum dobSelectNameProperty{
        birthDay("DateOfBirthDay"),
        birthMonth("DateOfBirthMonth"),
        birthYear("DateOfBirthYear");

        private final String name;

         dobSelectNameProperty(String name) {
            this.name = name;

        }

    }
    public static void fillTextField(String xpath, String label, String value){


        WebElement input = instanceDriver.findElement(By.xpath(String.format(xpath, label)));
        clickClearAndType(input, value);
        logger.info(label+ " input field filled with text " + value);

    }
    public static void fillEmailField(String xpath, String label, String value){
        value = value+ "@automation.com";
        WebElement input = instanceDriver.findElement(By.xpath(String.format(xpath, label)));
        clickClearAndType(input, value);
        logger.info(label+ " input field filled with text " + value);

    }
    public static void clickButton(String xpath, String label, String value){

       // WebElement button = instanceDriver.findElement(By.xpath(String.format(xpath, value)));
        Button button = new Button(By.xpath(String.format(xpath, value)), value + "button is clicked");
        button.seleniumClick();
        logger.info(label+ " button is clicked ");

    }
    public static void checkBoxField(String xpath, String label, String value){

        WebElement checkbox = instanceDriver.findElement(By.xpath(String.format(xpath, label)));
        if (value.equals("unchecked")){
            checkbox.click();
            waitForSeconds(1);
            logger.info(label+" checkbox is unselected for the user");
        }
        else {
            checkbox.isSelected();
            logger.info(label+" checkbox is selected for the user");

        }
    }
    public static void selectRadioButton(String xpath, String label, String value){

        WebElement genderRadioButton = instanceDriver.findElement(By.xpath(String.format(xpath, label, value.toLowerCase())));
        genderRadioButton.isDisplayed();
        genderRadioButton.click();
        logger.info("Gender selected: " + value);
        waitForSeconds(1);

    }

    public static void selectDateOfBirth(String xpath, String label, String value){

        String[] splitDate =value.split("/");
        String dateOfBrithDay = splitDate[0].trim();
        String dateOfBirthMonth = splitDate[1].trim();
        String dateOfBrithYear = splitDate[2].trim();

        selectBirthDay(xpath,label,dobSelectNameProperty.birthDay.name,dateOfBrithDay);
        selectBirthMonth(xpath,label,dobSelectNameProperty.birthMonth.name,dateOfBirthMonth);
        selectBirthYear(xpath,label,dobSelectNameProperty.birthYear.name,dateOfBrithYear);

    }
    private static void selectBirthDay(String xpath, String label, String namePropertyValue, String dateOfBrithDay){
        selectField(xpath,label,namePropertyValue,dateOfBrithDay);
        logger.info("Selected birthday: " + dateOfBrithDay);
    }
    private static void selectBirthMonth(String xpath, String label, String namePropertyValue, String dateOfBirthMonth){
        switch (dateOfBirthMonth){

            case ("01"):
                selectField(xpath,label,namePropertyValue,"January");
                logger.info("Selected birth month: January" );
                waitForSeconds(1);
                break;
            case ("02"):
                selectField(xpath,label,namePropertyValue,"February");
                logger.info("Selected birth month: February" );
                waitForSeconds(1);
                break;
            case ("03"):
                selectField(xpath,label,namePropertyValue,"March");
                logger.info("Selected birth month: March" );
                waitForSeconds(1);
                break;
            case ("04"):
                selectField(xpath,label,namePropertyValue,"April");
                logger.info("Selected birth month: April" );
                waitForSeconds(1);
                break;
            case ("05"):
                selectField(xpath,label,namePropertyValue,"May");
                logger.info("Selected birth month: May" );
                waitForSeconds(1);
                break;
            case ("06"):
                selectField(xpath,label,namePropertyValue,"June");
                logger.info("Selected birth month: June" );
                waitForSeconds(1);
                break;
            case ("07"):
                selectField(xpath,label,namePropertyValue,"July");
                logger.info("Selected birth month: July" );
                waitForSeconds(1);
                break;
            case ("08"):
                selectField(xpath,label,namePropertyValue,"August");
                logger.info("Selected birth month: August" );
                waitForSeconds(1);
                break;
            case ("09"):
                selectField(xpath,label,namePropertyValue,"September");
                logger.info("Selected birth month: September" );
                waitForSeconds(1);
                break;
            case ("10"):
                selectField(xpath,label,namePropertyValue,"October");
                logger.info("Selected birth month: October" );
                waitForSeconds(1);
                break;
            case ("11"):
                selectField(xpath,label,namePropertyValue,"November");
                logger.info("Selected birth month: November" );
                waitForSeconds(1);
                break;
            case ("12"):
                selectField(xpath,label,namePropertyValue,"December");
                logger.info("Selected birth month: December" );
                waitForSeconds(1);
                break;
            default:
                logger.info("Birth month is not valid");
                waitForSeconds(1);
                break;

        }
    }
    private static void selectBirthYear(String xpath, String label, String namePropertyValue, String dateOfBrithYear){
        selectField(xpath,label,namePropertyValue,dateOfBrithYear);
        logger.info("Selected birth year: " + dateOfBrithYear);
        waitForSeconds(1);

    }
    private static void selectField(String xpath, String label, String namePropertyValue, String value){
        WebElement selectDropdown = instanceDriver.findElement(By.xpath(String.format(xpath, label, namePropertyValue)));

        Select optionSelect = new Select(selectDropdown);
        optionSelect.selectByVisibleText(value);
    }
    public static void selectField(String xpath, String label, String value){
        WebElement selectDropdown = instanceDriver.findElement(By.xpath(String.format(xpath, label)));

        Select optionSelect = new Select(selectDropdown);
        optionSelect.selectByVisibleText(value);
    }
    private static void clickClearAndType(WebElement input, String value){
        input.isDisplayed();
        input.click();
        input.clear();
        input.sendKeys(value);
        waitForSeconds(1);
    }
}
