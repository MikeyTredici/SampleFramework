package Expedia.Utilities;


import Expedia.PageObjects.HomePage;
import Expedia.PageObjects.LoginPage;
import Expedia.PageObjects.SearchResultHotelPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase extends PageBase {
    private static final ConfigReader configReader = new ConfigReader();
    public static final String chrome = "chrome";
    public static final String firefox = "firefox";
    public WebDriver driver = null;
    public static LoginPage loginPage = null;
    public static HomePage homePage = null;
    public static SearchResultHotelPage searchResultHotelPage = null;

    @BeforeMethod
    public void initializeDriver(ITestResult iTestResult) {
        logger = reports.startTest(iTestResult.getMethod().getMethodName());
//        String browser = System.getProperty("browser");
        String browser = ConfigReader.getBrowser();
        if (browser.equalsIgnoreCase(chrome)) {
            ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", ConfigReader.getDriverPathChrome());

            if (browser.contains("headless")) {
                options.addArguments("headless");
            }

            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase(firefox)) {
            System.setProperty("webdriver.gecko.driver", ConfigReader.getDriverPathFirefox());
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(ConfigReader.getURL());

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResultHotelPage = PageFactory.initElements(driver, SearchResultHotelPage.class);
    }

    @BeforeSuite
    public void initializeBase() {
        initializeExtent();
    }

    @AfterMethod
    public void teardown(ITestResult iTestResult) throws IOException {
        try {
            PageBase.getScreenshot(iTestResult, driver); }
        catch (AssertionError e) {
            e.printStackTrace();
            System.out.println("UI VALIDATATION IS FAILED-NO SCREENSHOT");
        }
        reports.endTest(logger);
        reports.flush();

        driver.manage().deleteAllCookies();
        driver.close();
    }
}
