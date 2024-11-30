package pages;

import abstractComponents.GenericWebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends GenericWebPage {

    private final static String productTitlesXpath = "//div/h2/a";

    private List<WebElement> productTitles(){
        return instanceDriver.findElements(By.xpath(productTitlesXpath));
    }
    public void clickOnProductDetails(){
        productTitles().stream().filter(title -> title.getText().equals("Nokia Lumia 1020")).findFirst().ifPresent(WebElement::click);
        logger.info("Click on Nokia Lumia 1020 for details page");

    }

}
