package dataLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static dataLoader.TestData.modifiedDirectory;

public class TestEssentials {

    public static String appAddress;
    public static String userName;
    public static String passWord;
    public static String browserName;
    public static String platformName;
    public static Properties dataProperty = new Properties();

    public static FileInputStream propertyFileLoader()  {

        try {
            return new FileInputStream(modifiedDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "DataProp" + File.separator + "GlobalData.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void propertyDataLoader() {
        try {
            dataProperty.load(propertyFileLoader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        appAddress = System.getProperty("url") !=null ? System.getProperty("url") : dataProperty.getProperty("url");
        userName = System.getProperty("username") !=null ? System.getProperty("username") : dataProperty.getProperty("username");
        passWord = System.getProperty("password") !=null ? System.getProperty("password") : dataProperty.getProperty("password");
        browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : dataProperty.getProperty("browser");
        platformName = System.getProperty("platform") !=null ? System.getProperty("platform") : dataProperty.getProperty("platform");

    }
}
