package Expedia.PageObjects;

import Expedia.Utilities.TestBase;
import Expedia.Utilities.WebUtilities;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends TestBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public int sleepTime = 2000;
    public List<String> footerExpediaLinkTitles = Arrays.asList("Expedia Group | The World's Travel Platform", "Home: Expedia Media Solutions",
            "Canada Newsroom | Expedia Brand Newsroom", "Expedia Group | Careers", "Expedia.ca Privacy Policy",
            "Expedia, Inc. Web Site Terms, Conditions, and Notices", "Page Not Found | Expedia Group",
            "Travel Blog - Expedia.ca"); // Page Not Found will have to be mutated once link is fixed
    public List<String> travelerQueryContainer = new ArrayList<>();
    public static final String logForExpediaLinkInFooter = "Successfully validated the Link Title for: ";
    public static final String logFailExpected = "Expected: ";
    public static final String logFailActual = "Actual: ";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    // Below are different examples of FindBy locators which can be used with selenium, IDs being the
    // best solution, and xpath being my preferred method when elements do not have an ID as it is very flexible
    @FindBy(id = "tab-package-tab-hp")
    private WebElement bundleAndSaveButton;

    @FindBy(xpath = "//label[@for='fhc-fhc-hp-package']")
    private WebElement flightAndHotelAndCarButton;

    @FindBy(xpath = "//p[@class='fh']//strong[contains(text(),'Book together and SAVE!')]")
    private WebElement bookTogetherAndSaveText;

    @FindBy(id = "package-origin-hp-package")
    private WebElement originTextField;

    @FindBy(id = "package-destination-hp-package")
    private WebElement destinationTextField;

    @FindBy(id = "package-departing-hp-package")
    private WebElement departingDate;

    @FindBy(css = "button[class='datepicker-paging datepicker-next btn-paging btn-secondary next']")
    private WebElement nextButton;

    @FindBy(css = "caption.datepicker-cal-month-header")
    private WebElement monthName;

    @FindBy(xpath = "//tbody[@class='datepicker-cal-dates']/tr/td/button")
    private List<WebElement> monthDays;

    @FindBy(id = "package-returning-hp-package")
    private WebElement returningDate;

    @FindBy(xpath = "//div[3]/table/tbody/tr/td/button")
    private List<WebElement> calendarSecondMonthDays;

    @FindBy(id = "traveler-selector-hp-package")
    private WebElement travelers;

    @FindBy(xpath = "//div[@class='cf'][2]")
    private WebElement footerExpediaRow;

    @FindBy(xpath = "//div[@class='traveler-selector-room-data target-clone-field']/div[2]/div[3]/span")
    private WebElement getAdultCountRoom1;

    @FindBy(xpath = "//div[@class='traveler-selector-room-data target-clone-field']/div[2]/div[2]/button")
    private WebElement removeAdultRoom1;

    @FindBy(xpath = "//div[@class='traveler-selector-room-data target-clone-field']/div[2]/div[4]/button")
    private WebElement addAdultRoom1;

    @FindBy(xpath = "(//div[@class='traveler-selector-room-data target-clone-field']/div/div/div[4]/button)[1]")
    private WebElement addChildren;

    @FindBy(xpath = "(//div[@class='traveler-selector-room-data target-clone-field']/div[3]/div[2]/label/select)[1]")
    private WebElement selectChildOneAge;

    @FindBy(xpath = "(//div[@class='traveler-selector-room-data target-clone-field']/div/div/div[4]/button)[2]")
    private WebElement addInfant;

    @FindBy(xpath = "(//div[@class='traveler-selector-room-data target-clone-field']/div[4]/div[2]/label/select)[1]")
    private WebElement selectInfantOneAge;

    @FindBy(id = "package-children-in-seat-hp-package")
    private WebElement infantInSeatRadioButton;

    @FindBy(xpath = "(//div[@class='menu-main']/a[1])[3]")
    private WebElement addAnotherRoom;

    @FindBy(xpath = "//div[@class='traveler-selector-room-data target-clone-field gcw-cloned-field']/div[2]/div[3]/span")
    private WebElement getAdultCountRoom2;

    @FindBy(xpath = "//div[@class='traveler-selector-room-data target-clone-field gcw-cloned-field']/div[2]/div[2]/button")
    private WebElement removeAdultRoom2;

    @FindBy(xpath = "//div[@class='traveler-selector-room-data target-clone-field gcw-cloned-field']/div[2]/div[4]/button")
    private WebElement addAdultRoom2;

    @FindBy(xpath = "//span[@class='title-amount']")
    private WebElement getRoomNumber;

    @FindBy(xpath = "(//span[@class='gcw-amount-field adults'])[5]")
    private WebElement getSetAdultsNumber;

    @FindBy(xpath = "(//span[@class='gcw-amount-field children'])[5]")
    private WebElement getSetChildrenNumber;

    @FindBy(xpath = "(//span[@class='gcw-amount-field rooms'])[3]")
    private WebElement getSetRoomNumber;

    @FindBy(id = "search-button-hp-package")
    private WebElement searchButton;

    // Method to verify all the links in the Expedia Links row of the footer
    public void verifyExpediaLinksInFooter() {
        // Scrolling to footer
        WebUtilities.scrollByVisibilityOfElement(driver, footerExpediaRow);

        // Getting the number of links for the Expedia section of the footer
        int linkCount = footerExpediaRow.findElements(By.tagName("a")).size();

        // Verifying each link opens the correct page by matching the title to the saved title list
        for (int i = 0; i < linkCount - 1; i++) {
            footerExpediaRow.findElements(By.tagName("a")).get(i).click();

            if (driver.getTitle().equalsIgnoreCase(footerExpediaLinkTitles.get(i))) {
                logger.log(LogStatus.PASS, logForExpediaLinkInFooter + footerExpediaLinkTitles.get(i));
            } else {
                logger.log(LogStatus.FAIL, logFailExpected + footerExpediaLinkTitles.get(i) + "\n" +
                        logFailActual + driver.getTitle());
                Assert.fail();
            }

            driver.navigate().back();
        }
    }


    // Method to select Bundle and Save
    public void clickBundleAndSave() {
        wait.until(ExpectedConditions.elementToBeClickable(bundleAndSaveButton));
        bundleAndSaveButton.click();
    }

    // Method to select Flight + Hotel + Car
    public void clickFlightAndHotelWithCar() {
        wait.until(ExpectedConditions.elementToBeClickable(flightAndHotelAndCarButton));
        flightAndHotelAndCarButton.click();
        wait.until(ExpectedConditions.visibilityOf(bookTogetherAndSaveText));
    }

    // Method to enter the Origin and validate the correct Airport is selected
    public void enterOrigin(String originCode, String fullAirportName) {
        wait.until(ExpectedConditions.visibilityOf(originTextField));
        originTextField.clear();
        originTextField.sendKeys(originCode);
        boolean airport = false;
        int i = 0;
        while (!airport) {
            originTextField.sendKeys(Keys.ARROW_DOWN);
            String getAirport = originTextField.getAttribute("value");
            if (getAirport.contains(originCode)) {
                originTextField.sendKeys(Keys.ENTER);
                airport = true;
            } else {
                i++;
                if (i == 10) {
                    System.out.println("Unable to find " + originCode);
                    break;
                }
            }
        }
        Assert.assertEquals(originTextField.getAttribute("value"), fullAirportName);
    }

    // Method to enter Destination and validate the correct Airport is selected
    public void enterDestination(String destination) {
        wait.until(ExpectedConditions.visibilityOf(destinationTextField));
        destinationTextField.clear();
        destinationTextField.sendKeys(destination);
        Assert.assertEquals(destinationTextField.getAttribute("value"), destination);
    }

    // Method to set the Departure date
    public void setDepartureDate(String departingMonth, String departingDay) {
        departingDate.click();
        while (!monthName.getText().contains(departingMonth)) {
            nextButton.click();
        }

        for (WebElement month : monthDays) {
            String dayText = month.getText();
            if (dayText.contains(departingDay)) {
                month.click();
                break;
            }
        }
    }

    // Method to set the Returning date, in the second month of the calendar picker
    public void setReturningDateSecondMonth(String returningDay) {
        returningDate.click();

        for (WebElement month : calendarSecondMonthDays) {
            String dayText = month.getText();
            if (dayText.contains(returningDay)) {
                month.click();
                break;
            }
        }
    }

    // Method to open the Travelers menu
    public void clickOnTravelers() {
        wait.until(ExpectedConditions.elementToBeClickable(travelers));
        travelers.click();
    }

    // Method to set amount of Adults
    public void setAdultsAmount(int amount, WebElement room, WebElement addAdult, WebElement removeAdult) {
        int currentCount = Integer.parseInt(room.getText());
        if (currentCount < amount) {
            while (currentCount != amount) {
                addAdult.click();
                currentCount++;
            }
        } else if (currentCount > amount) {
            while (currentCount != amount) {
                removeAdult.click();
                currentCount--;
            }
        }
    }

    // Method to add one Child and set the age
    public void addOneChildAndSetAge(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(addChildren));
        addChildren.click();

        WebUtilities.selectDropdownByValue(selectChildOneAge, value);
    }

    // Method to add one Infant and set the age
    public void addOneInfantAndSetAge(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(addInfant));
        addInfant.click();

        WebUtilities.selectDropdownByText(selectInfantOneAge, value);
    }

    // Method to add another room
    public void addAnotherRoom() {
        wait.until(ExpectedConditions.elementToBeClickable(addAnotherRoom));
        addAnotherRoom.click();
    }

    // Method to save the traveler query for future validation
    public List<String> storeTravelerQuery() {
        // Get adults counts
        String adultsCount = getSetAdultsNumber.getText();
        if (adultsCount.contains("1")) {
            adultsCount = adultsCount.replace(" Adult,", "");
        } else {
            adultsCount = adultsCount.replace(" Adults,", "");
        }

        // Get children count
        String childrenCount = getSetChildrenNumber.getText();
        if (childrenCount.contains("1")) {
            childrenCount = childrenCount.replace(" Child, ", "");
        } else {
            childrenCount = childrenCount.replace(" Children,", "");
        }

        // Get room count
        String roomCount = getSetRoomNumber.getText();
        if (roomCount.contains("1")) {
            roomCount = roomCount.replace(" Room", "");
        } else {
            roomCount = roomCount.replace(" Rooms", "");
        }

        // Populate array
        travelerQueryContainer.add(roomCount);
        travelerQueryContainer.add(adultsCount);
        travelerQueryContainer.add(childrenCount);

        // Return list
        return travelerQueryContainer;
    }

    // Method to initiate the search and validate the query
    public void bookingFlightAndHotelWithCar(String originCode, String fullAirportName, String originCity, String destination, String destinationCity, String departingMonth,
                                             String departingDay, String returningMonth, String returningDay, int adultAmountRoom1, String childAge,
                                             String infantAge, int adultAmountRoom2) throws InterruptedException {
        // Selecting Bundle and Save in case home page changes default selection
        clickBundleAndSave();

        // Selecting Flight + Hotel + Car and Wait for new element to replace previous unique element
        clickFlightAndHotelWithCar();

        // Entering Origin through the dropdown suggestion and verify Full Airport Name
        enterOrigin(originCode, fullAirportName);

        // Entering Destination without dropdown suggestion
        enterDestination(destination);

        // Setting departure date
        setDepartureDate(departingMonth, departingDay);

        // Selecting returning date
        setReturningDateSecondMonth(returningDay);

        // Setting adult amount (this amount can be modified in the HomePageTest.java file) then,
        clickOnTravelers();
        setAdultsAmount(adultAmountRoom1, getAdultCountRoom1, addAdultRoom1, removeAdultRoom1);

        // Scrolling down to put the bottom of the travelers box into view
        WebUtilities.scrollByPixel(driver);

        // Adding 1 Children and 1 Infant (with In Seat seating) in Room 1, then adding Room 2 w/ 2 Adults
        addOneChildAndSetAge(childAge);
        addOneInfantAndSetAge(infantAge);
        infantInSeatRadioButton.click();

        // Adding the second room and setting the amount of adults
        addAnotherRoom();
        setAdultsAmount(adultAmountRoom2, getAdultCountRoom2, addAdultRoom2, removeAdultRoom2);

        // Save the traveller information to validate later
        storeTravelerQuery();

        // Initiate the search
        searchButton.click();

        // Waiting for the results to load
        searchResultHotelPage.waitForSearchResultPageToLoad();

        // Validate the search query
        searchResultHotelPage.validateOriginCity(originCity);
        searchResultHotelPage.validateDestinationCity(destinationCity);

        // Creating full dates to match the display on the results page, then validating
        String fullDepartingDay = departingDay + " " + departingMonth;
        String fullReturningDay = returningDay + " " + returningMonth;

        searchResultHotelPage.validateDates(fullDepartingDay, fullReturningDay);
        Assert.assertEquals(travelerQueryContainer, searchResultHotelPage.getTravelerResults());
    }
}
