package Expedia;

import Expedia.Utilities.TestBase;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

    public String originCode = "YUL"; //Sometimes fails as it skips the first character(s)
    public String fullAirportName = "Montreal, QC, Canada (YUL-Pierre Elliott Trudeau Intl.)"; //Sometimes fails as it skips the first character(s)
    public String originCity = "Montreal";
    public String destination = "New York, NY, United States (LGA-LaGuardia)";
    public String destinationCity = "New York";
    public String departingMonth = "Dec";
    public String departingDay = "20";
    public String returningMonth = "Jan";
    public String returningDay = "5";
    public int adultAmountRoom1 = 2; // Min 1 Max 6 (Max traveller is 6, total cannot exceed)
    public String childAge = "13"; // Min 2 Max 17
    public String infantAge = "Under 1"; // Under 1 or 1
    public int adultAmountRoom2 = 2; // Min 1 Max 6

    @Test
    public void homePageFooterExpediaLinksVerification() {
        homePage.verifyExpediaLinksInFooter();
    }

    @Test
    public void searchingForFlightAndHotelWithCar() throws InterruptedException {
        homePage.bookingFlightAndHotelWithCar(originCode, fullAirportName, originCity, destination, destinationCity,
                departingMonth, departingDay, returningMonth, returningDay, adultAmountRoom1, childAge, infantAge,
                adultAmountRoom2);
    }
}
