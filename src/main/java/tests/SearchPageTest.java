package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePage;
import pageobjects.SearchPage;
import setup.Driver;

import java.util.List;
import java.util.Optional;


public class SearchPageTest {

    private WebDriver driver;

    @Before
    public void createDriver() {
        Driver wdriver = new Driver();
        driver = wdriver.WebDriver();
        driver.manage().window().maximize();
    }

    @Before
    public void searchHotel() {
        HomePage homePage = new HomePage(driver);
        homePage.setCookieAccept();
        String location = "Limerick";
        homePage.findHotel(location);
    }

    @Test
    public void whenFilteringFiveStarHotelsThenHotelsShouldBeFiltered() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.setFiveStar();

        List<String> hotelNames = searchPage.getFilteredAvailableHotels();

        Optional<String> savoyHotel = hotelNames
                .stream()
                .filter(hotelName -> hotelName.equals("The Savoy Hotel"))
                .findFirst();

        Optional<String> georgeHotel = hotelNames
                .stream()
                .filter(hotelName -> hotelName.equals("George Limerick Hotel"))
                .findFirst();

        assert savoyHotel.isPresent();

        assert georgeHotel.isEmpty();
    }

    @Test
    public void whenFilteringHotelsWithSaunaThenHotelsShouldBeFiltered() {
        SearchPage searchPage = new SearchPage(driver);

        searchPage.setSauna();

        List<String> hotelNames = searchPage.getFilteredAvailableHotels();

        Optional<String> limerickStrandHotel = hotelNames
                .stream()
                .filter(hotelName -> hotelName.equals("Limerick Strand Hotel"))
                .findFirst();

        Optional<String> georgeHotel = hotelNames
                .stream()
                .filter(hotelName -> hotelName.equals("George Limerick Hotel"))
                .findFirst();

        assert limerickStrandHotel.isPresent();

        assert georgeHotel.isEmpty();
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

}
