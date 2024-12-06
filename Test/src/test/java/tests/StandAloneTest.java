package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class StandAloneTest {
    public static void main(String [] args){

            // Set the path to your ChromeDriver
            WebDriverManager.chromedriver().setup();

            // Initialize WebDriver
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();


    }
}
