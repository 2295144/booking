package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    private WebElement getFiveStar() {

        String referenceFilter = "class-5";

        List<WebElement> filtersAvailable = driver.findElements(new By.ByCssSelector(".filterelement"));
        Optional<WebElement> fiveStarFilter = filtersAvailable
                .stream()
                .filter(itens -> referenceFilter.equals(itens.getAttribute("data-id")))
                .findFirst();

        return fiveStarFilter.get();
    }

    private WebElement getSaunaFilter() {

        String referenceFilter = "popular_activities-10";

        List<WebElement> filtersAvailable = driver.findElements(new By.ByCssSelector(".filterelement"));
        Optional<WebElement> saunaFilter = filtersAvailable
                .stream()
                .filter(itens -> referenceFilter.equals(itens.getAttribute("data-id")))
                .findFirst();

        return saunaFilter.get();
    }

    public List<String> getFilteredAvailableHotels() {


        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        new By.ByCssSelector(".sr-usp-overlay__container")));

        List<WebElement> hotelNameElements = driver.findElements(new By.ByCssSelector(".sr-hotel__name"));
        List<String> hotelNames = hotelNameElements
                .stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());

        return hotelNames;
    }


    public void setFiveStar() {

        getFiveStar().click();


    }

    public void setSauna() {

        getSaunaFilter().click();

    }


}

