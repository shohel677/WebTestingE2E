package abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static abstractComponents.GenericWebPage.instanceDriver;

public class AbstractComponent {
    public static WebElement waitUntilVisible(int numberOfSecond, String stringXpath){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(numberOfSecond));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath)));
    }
    public static WebElement waitUntilVisible(String stringXpath){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(stringXpath)));
    }
    public static WebElement waitUntilVisible(String stringXpath, String text){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(stringXpath, text))));
    }
    public static void waitUntilInvisible(String stringXpath){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(stringXpath)));
    }
    public static void waitUntilInvisible(int numberOfSecond, String stringXpath){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(numberOfSecond));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(stringXpath)));
    }
    public static WebElement waitUntilElementToBeClickable(int numberOfSecond, String stringXpath){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(numberOfSecond));
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(stringXpath)));
    }
    public static WebElement waitUntilVisibleOf(int numberOfSecond, String  stringXpath){
        WebDriverWait wait = new WebDriverWait(instanceDriver, Duration.ofSeconds(numberOfSecond));
        return wait.until(ExpectedConditions.visibilityOf(instanceDriver.findElement(By.xpath(stringXpath))));
    }
    public static void waitForSeconds(long seconds){

        long mSecond = seconds*1000;

        try {
            Thread.sleep(mSecond);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
