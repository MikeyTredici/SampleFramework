package Expedia.PageObjects;

import Expedia.Utilities.TestBase;
import Expedia.Utilities.WebUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SearchResultHotelPage extends TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public int waitTime = 45;
    public int pollingTime = 5;

    public SearchResultHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(xpath = "//span[@class='loader loader-primary loader-light loader-animated loading pageInterstitialLoader']")
    private WebElement loadingWheel;

    @FindBy(xpath = "//div[@class='section-header-content']/span[1]")
    private WebElement resultPageFinder;

    @FindBy(xpath = "//button[@class='origin fakeLink']")
    private WebElement searchedOrigin;

    @FindBy(xpath = "(//button[@class='destination fakeLink'])[2]")
    private WebElement searchedDestination;

    @FindBy(xpath = "(//button[@class='dates fakeLink']/span[2])[2]")
    private WebElement searchedDepatureDate;

    @FindBy(xpath = "//button[@class='dates fakeLink']/span[6]")
    private WebElement searchedReturnDate;

    @FindBy(xpath = "//button[@class='rooms fakeLink']")
    private WebElement searchedRooms;

    @FindBy(xpath = "(//span[@class='wizardTravellerSummaryAdults'])[2]")
    private WebElement searchedAdults;

    @FindBy(xpath = "(//span[@class='wizardTravellerSummaryChild'])[2]")
    private WebElement searchedChildren;

    public void waitForSearchResultPageToLoad() {
//        WebUtilities.fluentWait(driver, waitTime, pollingTime, resultPageFinder);
        WebUtilities.implicitWait(driver, waitTime);
        System.out.println(resultPageFinder.getText());

    }

    public void validateOriginCity(String originCity) {
        Assert.assertTrue(searchedOrigin.getText().contains(originCity));
    }

    public void validateDestinationCity(String destinationCity) {
        Assert.assertTrue(searchedDestination.getText().contains(destinationCity));
    }

    public void validateDates(String departureDate, String returnDate) {
        Assert.assertTrue(searchedDepatureDate.getText().contains(departureDate));
        Assert.assertTrue(searchedReturnDate.getText().contains(returnDate));
    }

    // Method to save the traveler results for validation
    public List<String> getTravelerResults() {

        // Initiliaze Array
        List<String> travelerQueryContainer = new ArrayList<>();

        // Get adults counts
        String adultsCount = searchedAdults.getText();
        if (adultsCount.contains("1")) {
            adultsCount = adultsCount.replace(" adult,", "");
        } else {
            adultsCount = adultsCount.replace(" adults,", "");
        }

        // Get children count
        String childrenCount = searchedChildren.getText();
        if (childrenCount.contains("1")) {
            childrenCount = childrenCount.replace(" child", "");
        } else {
            childrenCount = childrenCount.replace(" children", "");
        }

        // Get room count
        String roomCount = searchedRooms.getText();

        // Populate Array
        travelerQueryContainer.add(roomCount);
        travelerQueryContainer.add(adultsCount);
        travelerQueryContainer.add(childrenCount);

        // Return list
        return travelerQueryContainer;
    }
}
