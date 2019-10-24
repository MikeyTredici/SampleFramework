package Expedia.Listeners;

import Expedia.Utilities.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final ConfigReader configReader = new ConfigReader();
    private static final String retryCount = ConfigReader.getRetryCount();
    private int counter = 0;
    private Integer retryLimit = Integer.parseInt(retryCount);

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}
