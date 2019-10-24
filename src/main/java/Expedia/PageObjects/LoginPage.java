package Expedia.PageObjects;

import Expedia.Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestBase {

  public WebDriver driver;

  //    By email = By.id("user_email");
  //    By password = By.id("user_password");
  //    By loginButton = By.xpath("//input[@type='submit']");
  //
  //    public LoginPage(WebDriver driver) {
  //        this.driver = driver;
  //    }
  //
  //    public WebElement getEmail() {
  //        return driver.findElement(email);
  //    }
  //
  //    public WebElement getPassword() {
  //        return driver.findElement(password);
  //    }
  //
  //    public WebElement getLoginButton() {
  //        return driver.findElement(loginButton);
  //    }

  public WebDriverWait wait;

  @FindBy(id = "user_email")
  private WebElement emailField;

  @FindBy(id = "user_password")
  WebElement passwordField;

  @FindBy(xpath = "//input[@type='submit']")
  WebElement loginButton;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    wait = new WebDriverWait(driver, 5);
  }

  public void doLogin(String email, String password, String userType) {
    wait.until(ExpectedConditions.visibilityOf(emailField));
    emailField.sendKeys(email);
    passwordField.sendKeys(password);
//    Log.info("Logging in to " + userType);
    loginButton.click();
  }
}
