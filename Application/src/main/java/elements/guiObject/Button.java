package elements.guiObject;

import elements.element.GenericObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button extends GenericObject {

    public Button(By locator, String name){
        super(locator, name);
    }
    public Button(WebElement element){
        super(element);
    }
}
