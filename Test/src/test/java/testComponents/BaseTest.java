package testComponents;


import pages.InitialPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import static abstractComponents.GenericWebPage.getInstanceDriver;
import static abstractComponents.GenericWebPage.instanceDriver;
import static dataLoader.TestEssentials.*;


public class BaseTest {
    public static Logger logger = LogManager.getLogger();
    protected InitialPage initialPage;
    @Parameters({"Browser", "Platform"})
    @BeforeMethod()
    public void setup(Method method, @Optional String Browser, @Optional String Platform) throws MalformedURLException {
        propertyDataLoader();

        String testOnBrowser;
        String testOnPlatform;

        if(Browser != null){
            testOnBrowser = Browser;
        }
        else{
            testOnBrowser = browserName;
        }
        if(Platform != null){
            testOnPlatform = Platform;
        }
        else{
            testOnPlatform = platformName;
        }

        getInstanceDriver(testOnBrowser, testOnPlatform);
        initialPage = new InitialPage();
        logger.info("Starting testcase: "+method.getName());
        initialPage.appAccess(appAddress);
    }




    @AfterMethod(alwaysRun=true)
    public void tearDown(ITestResult result) {
        int status = result.getStatus();
        String testDescription = result.getMethod().getDescription();

        if (status == ITestResult.SUCCESS) {
            logger.info("'" + testDescription + "' is successfully passed");
        } else if (status == ITestResult.FAILURE) {
            logger.info("'" + testDescription + "' failed");
        } else if (status == ITestResult.SKIP) {
            logger.info("'" + testDescription + "' is skipped");
        }

        logger.info("---------------------------------------------------");

        if (instanceDriver == null) {
            return;
        }
        instanceDriver.quit();
        instanceDriver = null;
    }

}
