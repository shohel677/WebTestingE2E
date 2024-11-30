package tests.steps;

import pages.*;
import testComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static abstractComponents.AbstractComponent.waitForSeconds;
import static abstractComponents.FrameworkAssertionLibrary.stepAssertionShouldBeTrue;
import static filler.DataReader.fieldsFiller;

public class PlaceOrderAsGuestSteps extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final ProductsPage productsPage = new ProductsPage();
    private final ProductsDetailsPage productsDetailsPage = new ProductsDetailsPage();
    private final ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();
    private final ThankYouPage thankYouPage = new ThankYouPage();

    @Given("User go to the NopCommerce Home page")
    public void userGoToTheNopCommerceHomePage() {

        try {
            stepAssertionShouldBeTrue(homePage.isHomePageLoaded(),"User landed on NopCommerce homepage");

        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User landed on NopCommerce homepage");

        }
    }

    @When("User click Cell phones option from Electronics category")
    public void userClickOptionFromCategory() {

        try {
            homePage.categoryOptionClick();
            stepAssertionShouldBeTrue(true,"User click Cell phones option from Electronics category");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User click Cell phones option from Electronics category");

        }
    }

    @And("User click on the Nokia Lumia 1020 for product details")
    public void userClickOnProductForDetails() {

        try {
            productsPage.clickOnProductDetails();
            stepAssertionShouldBeTrue(true,"User click on the Nokia Lumia 1020 for product details");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User click on the Nokia Lumia 1020 for product details");

        }
    }

    @And("User set the quantity number 2 in the quantity field")
    public void userSetTheQuantityNumberInQuantityField() {

        try {
            productsDetailsPage.enterProductQuantity();
            stepAssertionShouldBeTrue(true,"User set the quantity number 2 in the quantity field");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User set the quantity number 2 in the quantity field");

        }
    }

    @And("User click on the ADD TO CART button")
    public void userClickOnButton() {

        try {
            productsDetailsPage.clickAddToCart();
            stepAssertionShouldBeTrue(true,"User click on the ADD TO CART button");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User click on the ADD TO CART button");

        }
    }

    @And("User go to the shipping cart page")
    public void userGoToTheShippingCartPage() {

        try {
            productsDetailsPage.navigateToShoppingCart();
            stepAssertionShouldBeTrue(true,"User go to the shipping cart page");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"User go to the shipping cart page");

        }
    }

    @And("User accept terms conditions and click checkout button")
    public void userAcceptTermsConditionsAndClickCheckoutButton() {

        try {
            shoppingCartPage.acceptTermsAndCondition();
            shoppingCartPage.clickCheckoutButton();
            stepAssertionShouldBeTrue(true,"Accept terms and condition and click checkout button");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Accept terms and condition and click checkout button");

        }
    }

    @And("User click checkout as guest button")
    public void userClickCheckoutAsGuestButton() {

        try {
            shoppingCartPage.checkoutAsGuest();
            stepAssertionShouldBeTrue(true,"Click checkout as guest button");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Click checkout as guest button");

        }
    }

    @And("User input all the billing details and click continue")
    public void userInputAllTheBillingDetailsAndClickContinue() {


        try {
            waitForSeconds(5);
            fieldsFiller("placeOrderBillingAddress.csv");
            stepAssertionShouldBeTrue(true,"Enter details for shipping");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Enter details for shipping");

        }
    }

    @And("User select shipping method Next Day Air and click continue")
    public void userSelectShippingMethodAndClickContinue() {

        try {
            checkoutPage.selectShippingMethodAndContinue();
            stepAssertionShouldBeTrue(true,"Select next day air shipping method");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Select next day air shipping method");

        }
    }

    @And("User select payment method Credit Card and click continue")
    public void userSelectPaymentMethodAndClickContinue() {

        try {
            checkoutPage.selectPaymentTypeAndContinue();
            stepAssertionShouldBeTrue(true,"Select card for payment method");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Select card for payment method");

        }
    }

    @And("User select Visa card and input card information")
    public void userSelectCardAndInputCardInformation() {

        try {
            checkoutPage.cardInformationAndContinue();
            stepAssertionShouldBeTrue(true,"Visa card information added");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Visa card information added");

        }
    }

    @And("User click confirm button to place the order")
    public void userClickConfirmButtonToPlaceTheOrder() {

        try {
            checkoutPage.clickOrderConfirm();
            stepAssertionShouldBeTrue(true,"Confirm button clicked for place order");
        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Confirm button clicked for place order");

        }
    }

    @Then("Verify that the order place message")
    public void verifyOrderPlaceMessage() {
        try {
            stepAssertionShouldBeTrue(thankYouPage.verifyOrderConfirmationMessage(),"Order confirmation message is displayed");

        }catch (Exception | AssertionError ae){
            stepAssertionShouldBeTrue(false,"Order confirmation message is displayed");

        }
    }
}
