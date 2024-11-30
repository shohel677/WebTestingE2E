package pages;

import abstractComponents.GenericWebPage;
import elements.guiObject.Button;
import elements.guiObject.Input;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;

public class ProductsDetailsPage extends GenericWebPage {

    private final static String productQuantityXpath = "//div[@class='add-to-cart-panel']/label/following-sibling::input";
    private final static String addToCartXpath = "//div[@class='add-to-cart-panel']/button[text()='Add to cart']";
    private final static String shoppingCartButton = "//a[text()='shopping cart']";
    private static final Input productQuantity = new Input(By.xpath(productQuantityXpath), "Product quantity input");
    private static final Button addToCart = new Button(By.xpath(addToCartXpath), "Add to cart button");
    private static final Button shoppingCart = new Button(By.xpath(shoppingCartButton), "Shopping cart button");

    public void enterProductQuantity(){
        productQuantity.isDisplayed(Duration.ofSeconds(30));
        productQuantity.clearAndType("2");
        logger.info("Enter quantity of product");
    }
    public void clickAddToCart(){
        addToCart.isDisplayed(Duration.ofSeconds(30));
        addToCart.seleniumClick();
        logger.info("Click add to cart button");
    }
    public void navigateToShoppingCart(){
        shoppingCart.isDisplayed(Duration.ofSeconds(30));
        shoppingCart.seleniumClick();
        logger.info("Navigating to shopping cart");
        Assert.assertEquals("", "");
    }

}
