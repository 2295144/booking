package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#ss")
    private WebElement destination;
    @FindBy(how = How.CSS, using = ".xp__dates.xp__group")
    private WebElement datePickerGroup;
    @FindBy(how = How.CLASS_NAME, using = ("bui-calendar__control--next"))
    private WebElement nextArrow;
    @FindBy(how = How.CLASS_NAME, using = "sb-searchbox__button")
    private WebElement searchBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"cookie_warning\"]/div/div/div[2]/button")
    private WebElement cookieAccept;


    public HomePage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private String getDateFromNow(int daysToAdd) {

        Date dateNow = new Date();
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(dateNow);
        calendarDate.add(Calendar.DAY_OF_MONTH, daysToAdd);

        Date threeMonthsFromNow = calendarDate.getTime();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormatter.format(threeMonthsFromNow);
    }

    private WebElement getDateCellBy(String referenceDate) {

        List<WebElement> dateCells = driver.findElements(new By.ByCssSelector(".bui-calendar__date"));
        Optional<WebElement> dateCell = dateCells
                .stream()
                .filter(cell -> referenceDate.equals(cell.getAttribute("data-date")))
                .findFirst();


        if (dateCell.isPresent()) {

            return dateCell.get();

        } else {

            nextArrow.click();
        }

        return getDateCellBy(referenceDate);
    }

    public void findHotel(String location) {

        destination.sendKeys(location);
        datePickerGroup.click();

        String checkinDate = getDateFromNow(90);
        String checkoutDate = getDateFromNow(91);
        WebElement checkinDateCell = getDateCellBy(checkinDate);
        WebElement checkoutDateCell = getDateCellBy(checkoutDate);

        checkinDateCell.click();
        checkoutDateCell.click();

        searchBtn.click();
    }

    public void checkingTitle(String referenceTitle) {
        String currentWindowTitle = driver.getTitle().toLowerCase();

        if (currentWindowTitle.contains(referenceTitle)) {
            assert true;
        } else {
            assert false;
        }
    }

    public void setCookieAccept() {
        cookieAccept.click();
    }
}

