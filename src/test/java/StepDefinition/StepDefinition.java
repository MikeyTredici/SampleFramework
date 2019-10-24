package StepDefinition;

import Expedia.Utilities.ConfigReader;
import Expedia.Utilities.TestBase;
import com.relevantcodes.extentreports.ExtentTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
public class StepDefinition extends TestBase {

    public WebDriverWait wait;
    public WebDriver driver = null;
    public static ExtentTest logger;
    public static final String logInInvalidError = "You may have entered an unknown email address or an incorrect password";
    public static final String logForClickingOnTheAccountMenuButton = "Clicking on the Account menu button";
    public static final String logForClickingOnSignInMenu = "Clicking on Sign In in the Account menu";
    public static final String logForEnteringTextInEmailField = "Entering the following Email: ";
    public static final String logForEnteringTextInPasswordField = "Entering the following password: ";
    public static final String logForClickingOnSignInModal = "Clicking on Sign In in the Sign In modal";
    public static final String logForIncorrectEmailOrPasswordError = "Successfully validated the error for Incorrect Email or Password";

    @FindBy(xpath = "//button[@id='header-account-menu']")
    public WebElement accountMenuButton;

    @FindBy(xpath = "//a[@id='account-signin']")
    public WebElement signInButton;

    @FindBy(xpath = "//input[@id='gss-signin-email']")
    public WebElement emailField;

    @FindBy(xpath = "//input[@id='gss-signin-password']")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@id='gss-signin-submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//div[@id='gss-signin-incorrect-email-or-password']")
    public WebElement incorrectEmailOrPasswordError;


    @Given("^Driver is initialized$")
    public void driver_is_initialized() {
        System.setProperty("webdriver.chrome.driver", ConfigReader.getDriverPathChrome());
        driver = new ChromeDriver();
    }

    @And("^User navigates to \"([^\"]*)\" site$")
    public void user_navigates_to_something_site(String strArg1) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(strArg1);
    }

    @And("^User opens the Sign in page$")
    public void user_opens_the_sign_in_page() {
        driver.findElement(By.xpath("//button[@id='header-account-menu']")).click();

        driver.findElement(By.xpath("//a[@id='account-signin']")).click();
    }

    @When("^User logs in to the application with a incorrect username (.+) and password (.+)$")
    public void user_logs_in_to_the_application_with_a_incorrect_username_and_password(String username, String password) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='gss-signin-email']")).sendKeys(username);

        driver.findElement(By.xpath("//input[@id='gss-signin-password']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@id='gss-signin-submit']")).click();
    }

    @Then("^Error message is displayed$")
    public void error_message_is_displayed() {
        Assert.assertTrue(incorrectEmailOrPasswordError.getText().contains(logInInvalidError));
    }

    @And("^Driver is closed$")
    public void driver_is_closed() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
