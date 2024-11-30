package elements.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static abstractComponents.GenericWebPage.instanceDriver;
import static abstractComponents.GenericWebPage.logger;

public abstract class GenericObject {
    private FluentWait<WebDriver> waiter;
    private static final Duration DEFAULT_TIMEOUT_FOR_IS_NOT_DISPLAYED = Duration.ofMinutes(3);
    private WebElement element;
    private By locator;
    private String name;
    private static final Duration POLLING = Duration.ofSeconds(5);
    protected static final Duration DEFAULT_TIMEOUT_TO_WAIT = Duration.ofSeconds(60);
    private static final long IMPLICIT = 0;
    public GenericObject(By locator, String name){
        this.locator = locator;
        this.name = name;
    }
    public GenericObject(WebElement element){
        this.element = element;
    }
    public String getName(){return this.name;}

    public WebElement getWrappedElement() {
        if (element != null) {
            return element;
        }
        WebElement toReturn = findElement(DEFAULT_TIMEOUT_TO_WAIT);
        if (toReturn == null) {
            throw new NoSuchElementException("Element " + name + " is not found");
        }
        return toReturn;
    }

    public WebElement findElement(Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(instanceDriver, timeoutInSeconds);
        wait.pollingEvery(POLLING);
        wait.withMessage("Element " + name + " is not found");
        wait.ignoreAll(Collections.singleton(StaleElementReferenceException.class));
        try {
            return wait.until((driver) -> {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    return elements.get(0);
                } else {
                    return null;
                }
            });
        } catch (Exception e){
            logger.info(e);
            return null;
        }
    }

    public void seleniumClick() {
        logger.info(String.format("click on element: %s", this.name));
        try {
            WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(getWrappedElement()));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException("\n" + this.name + " should be clickable before click.\n");
        }
        logger.info(String.format("Clicking element: %s", this.name));
        getWrappedElement().click();
    }
    public boolean isDisplayed() {
        logger.info(String.format("Checking element isDisplayed: %s", this.name));
        try {
            getWrappedElement().isDisplayed();
            return true;
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException ignored) {
            return false;
        }
    }

    public boolean isNotDisplayed() {
        logger.info(String.format("Checking element isDisplayed: %s", this.name));
        try {
            return !findElement(DEFAULT_TIMEOUT_FOR_IS_NOT_DISPLAYED).isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException ignored) {
            return true;
        }
    }

    public boolean isDisplayed(Duration timeoutInS) {
        logger.info(String.format("Check visibility of element - '%s'", locator.toString()));
        try {
            return findElement(timeoutInS).isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException | TimeoutException ignored) {
            return false;
        }
    }
    public void click() {
        logger.info(String.format("click on element: %s", this.name));
        try {
            getWebDriverWait().until(ExpectedConditions.elementToBeClickable(getWrappedElement()));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new IllegalStateException("\n" + this.name + " should be clickable before click.\n");
        }

        logger.info(String.format("Clicking element: %s", this.name));
        clickJS(getWrappedElement()); // Changed to clickJS as Click on failed to click on the element
    }
    protected FluentWait<WebDriver> getWebDriverWait() {
        if (waiter == null) {
            waiter = new FluentWait<WebDriver>((instanceDriver));
        }
        return waiter.withTimeout(DEFAULT_TIMEOUT_TO_WAIT)
                .ignoring(StaleElementReferenceException.class);
    }
    public static void clickJS(WebElement element) {
        getJavascriptExecutor(element).executeScript("arguments[0].click();", element);
    }
    private static JavascriptExecutor getJavascriptExecutor(WebElement element) {
        return (JavascriptExecutor) unpackWebDriverFromSearchContext(element);
    }
    private static WebDriver unpackWebDriverFromSearchContext(SearchContext searchContext) {
        if (searchContext instanceof WebDriver) {
            return (WebDriver)searchContext;
        } else if (searchContext instanceof WrapsDriver) {
            return unpackWebDriverFromSearchContext(((WrapsDriver)searchContext).getWrappedDriver());
        } else {
            return searchContext instanceof WrapsElement ? unpackWebDriverFromSearchContext(((WrapsElement)searchContext).getWrappedElement()) : null;
        }
    }
}
