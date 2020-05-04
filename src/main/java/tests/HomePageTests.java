package tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePage;
import pageobjects.SearchPage;
import setup.Driver;


public class HomePageTests {

    private WebDriver driver;

    @Before
    public void createDriver() {
        Driver wdriver = new Driver();
        driver = wdriver.WebDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void shouldSearchForHotel() {

        String location = "Limerick";
        HomePage homepage = new HomePage(driver);
        SearchPage searchpage = new SearchPage(driver);

        homepage.findHotel(location);

    }

    @Test
    public void shouldBeAtTheRightWebSite() {
        String title = "booking";
        HomePage check = new HomePage(driver);
        check.checkingTitle(title);

    }


    @After
    public void closeDriver() {

        driver.quit();
    }

}






