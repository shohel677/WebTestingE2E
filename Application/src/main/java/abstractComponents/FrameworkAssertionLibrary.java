package abstractComponents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class FrameworkAssertionLibrary extends GenericWebPage{
    private static final Logger logger = LogManager.getLogger();

    private static final List<AssertionInfo> assertions = new ArrayList<>();

    public static void assertionShouldBeTrue(boolean condition, String message) {
        assertions.add(new AssertionInfo(condition, message));
        Assert.assertTrue(condition, message);
        logger.info("Step: " + message);
    }
    public static void assertionShouldBeTrue(boolean condition, String expected, String actual) {
        if(condition){
            assertions.add(new AssertionInfo(true, expected));
            Assert.assertTrue(true, expected);
            logger.info("Step: " + expected);
        }
        else{
            assertions.add(new AssertionInfo(false, actual));
            Assert.fail(actual);
            logger.info("Step: " + actual);
        }

    }

    public static void stepAssertionShouldBeTrue(boolean condition, String message) {
        assertions.add(new AssertionInfo(condition, message));
        Assert.assertTrue(condition, message);
        logger.info("STEP: " + message);
    }

    public static List<AssertionInfo> getAllAssertions() {
        return assertions;
    }

    public static class AssertionInfo {
        private final boolean status;
        private final String message;

        public AssertionInfo(boolean status, String message) {
            this.status = status;
            this.message = message;
        }

        public boolean getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }
}
