package abstractComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class GenericWebPage {
    public static WebDriver instanceDriver;
    public static final String USERNAME = "USERNAME";
    public static final String AUTOMATE_KEY = "AUTOMATE_KEY";
    public static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static WebDriver getInstanceDriver(String testOnBrowser, String testOnPlatform) throws MalformedURLException {

        if (instanceDriver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            String url = "http://localhost:4444/wd/hub";

            if (testOnBrowser.contains("chrome")) {
                if(testOnPlatform.contains("docker")){
                    caps.setBrowserName("chrome");
                    caps.setPlatform(Platform.LINUX);
                    instanceDriver = new RemoteWebDriver(new URL(url), caps);
                }
                else if(testOnPlatform.contains("browserStack")){

                    caps.setCapability("browser", "Chrome");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("os", "Windows");
                    caps.setCapability("os_version", "10");
                    caps.setCapability("name", "Chrome Test");

                    instanceDriver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps);
                }

                else{
                    ChromeOptions options = new ChromeOptions();
                    WebDriverManager.chromedriver().setup();
                    if (testOnBrowser.contains("headless")) {
                        options.addArguments("headless");
                    }
                    instanceDriver = new ChromeDriver(options);
                }
            } else if (testOnBrowser.contains("firefox")) {

                 if(testOnPlatform.contains("docker")){
                     caps.setBrowserName("firefox");
                     caps.setPlatform(Platform.LINUX);
                    instanceDriver = new RemoteWebDriver(new URL(url), caps);
                }

                 else if(testOnPlatform.contains("browserStack")){

                     caps.setCapability("browser", "Firefox");
                     caps.setCapability("browser_version", "latest");
                     caps.setCapability("os", "Linux");
                     caps.setCapability("os_version", "Ubuntu");
                     caps.setCapability("name", "Linux Test");

                     instanceDriver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps);
                 }

                else{
                    WebDriverManager.firefoxdriver().setup();
                    instanceDriver = new FirefoxDriver();
                }
            } else if (testOnBrowser.contains("edge")) {
                if(testOnPlatform.contains("docker")){
                    caps.setBrowserName("MicrosoftEdge");
                    caps.setPlatform(Platform.LINUX);
                    instanceDriver = new RemoteWebDriver(new URL(url), caps);
                }
                else if(testOnPlatform.contains("browserStack")){

                    caps.setCapability("browser", "Firefox");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("os", "Linux");
                    caps.setCapability("os_version", "Ubuntu");
                    caps.setCapability("name", "Linux Test");

                    instanceDriver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps);
                }
                else{
                    WebDriverManager.edgedriver().setup();
                    instanceDriver = new EdgeDriver();
                }
            } else if (testOnBrowser.contains("safari")) {
                if(testOnPlatform.contains("browserStack")){

                    caps.setCapability("browser", "Safari");
                    caps.setCapability("browser_version", "latest");
                    caps.setCapability("os", "OS X");
                    caps.setCapability("os_version", "Catalina");
                    caps.setCapability("name", "macOS Test");

                    instanceDriver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps);
                }else {
                    WebDriverManager.safaridriver().setup();
                    instanceDriver = new SafariDriver();
                }
            }
        }
        return instanceDriver;
    }
    public static Logger logger = LogManager.getLogger();


}

