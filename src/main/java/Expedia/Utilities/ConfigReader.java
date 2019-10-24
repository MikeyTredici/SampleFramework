package Expedia.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static String browser;
    private static String driverPathChrome;
    private static String driverPathFirefox;
    private static String URL;
    private static String retryCount;

    public ConfigReader() {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("Data\\config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read the config.properties file...");
            return;
        }

        browser = properties.getProperty("browser");
        driverPathChrome = properties.getProperty("chrome_driver_path");
        driverPathFirefox = properties.getProperty("firefox_driver_path");
        URL = properties.getProperty("url");
        retryCount = properties.getProperty("retry_count");
    }

    public static String getBrowser() {
        return browser;
    }

    public static String getDriverPathChrome() {
        return driverPathChrome;
    }

    public static String getDriverPathFirefox() {
        return driverPathFirefox;
    }

    public static String getURL() {
        return URL;
    }

    public static String getRetryCount(){
        return retryCount;
    }
}
