package tests.steps;


import pages.HomePage;
import testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;

import static abstractComponents.FrameworkAssertionLibrary.stepAssertionShouldBeTrue;
import static filler.DataReader.fieldsFiller;


public class UserRegistrationSteps extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final RegisterPage registerPage = new RegisterPage();

    @Given("User go to the NopCommerce Home page")
    public void userGoesToNopCommerceHomePage() {
        stepAssertionShouldBeTrue(homePage.isHomePageLoaded(),"User landed on NopCommerce homepage");
    }
    @When("User navigate to the Registration page")
    public void userNavigateToRegistrationPage() {
        homePage.navigateToRegistrationPage();
        stepAssertionShouldBeTrue(registerPage.isRegisterPage(),"User landed on NopCommerce registration page");
    }

    @And("User select the {gender} as gender")
    public void userSelectGender() {

        try {
            fieldsFiller("userRegistrationGender.csv");
            stepAssertionShouldBeTrue(true,"Gender field is selected");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Gender field is selected");

        }
    }

    @And("User set first name and last name")
    public void userSetFirstNameAndLastName() {

        try {
            fieldsFiller("userRegistrationFirstAndLastName.csv");
            stepAssertionShouldBeTrue(true,"Entered user last name and first name field");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Entered user last name and first name field");

        }
    }

    @And("User set {dob} as date of birth")
    public void userSetDOB() {

        try {
            fieldsFiller("userRegistrationDOB.csv");
            stepAssertionShouldBeTrue(true,"User date of birth is selected");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User date of birth is selected");

        }
    }

    @And("User set {email} as email")
    public void userSetEmail() {

        try {
            fieldsFiller("userRegistrationSetEmail.csv");
            stepAssertionShouldBeTrue(true,"User email is entered");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User email is entered");

        }
    }

    @And("User set {companyName} as company details")
    public void userSetCompanyName() {

        try {
            fieldsFiller("userRegistrationSetCompanyName.csv");
            stepAssertionShouldBeTrue(true,"Company name of user is set");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Company name of user is set");

        }
    }

    @And("User set Newsletter option as {Status}")
    public void userSetNewsletterOption() {

        try {
            fieldsFiller("userRegistrationNewsletter.csv");
            stepAssertionShouldBeTrue(true,"Newsletter status is set");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Newsletter status is set");

        }
    }

    @And("User set {password} as password and confirm password again")
    public void userSetPassword() {

        try {
            fieldsFiller("userRegistrationSetPassword.csv");
            stepAssertionShouldBeTrue(true,"Entered value on Password and confirm password field");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Entered value on Password and confirm password field");

        }
    }

    @And("User click on the Register button")
    public void userClickOnRegisterButton() {

        try {
            fieldsFiller("userRegistrationRegister.csv");
            stepAssertionShouldBeTrue(true,"user register button is clicked");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"user register button is clicked");

        }
    }

    @Then("Verify that the new account registration message ({message}) is displayed$")
    public void verifyRegistrationMessage() {
        try {
            stepAssertionShouldBeTrue(registerPage.isRegistrationConfirmationDisplayed(),"Registration confirmation message is displayed");

        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Registration confirmation message is displayed");


        }
    }

}
