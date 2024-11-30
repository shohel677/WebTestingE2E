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

            try {
                // Navigate to the target URL
                driver.get("https://vanity-qa02-stage01-gtn03-superset.qa.gtn.oa.iqvia.com/superset/sqllab/");

                Thread.sleep(60000);
                // Locate the hidden textarea within the ACE editor
                WebElement textarea = driver.findElement(By.cssSelector("#ace-editor .ace_text-input"));

                // Input text using JavaScript Executor
                String inputText = "Your input text";
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1];", textarea, inputText);

                // Dispatch an 'input' event to update the ACE editor's state
                js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", textarea);

                // Optionally validate if the text is updated (depends on the page logic)
                System.out.println("Text input applied successfully.");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Close the browser
               // driver.quit();
            }



    }
}
