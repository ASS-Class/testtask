package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static utils.DriverManager.getDriver;

public class EventPage extends PageBase {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @FindBy(xpath = ".//div[@class='ec-event__header__item' and ./span[contains(.,'Country')]]//a")
    private WebElement country;
    @FindBy(xpath = ".//div[@class='economic-calendar__event']//*[contains(@class, 'importance')]")
    private WebElement importance;
    @FindBy(xpath = ".//ul[@id='calendar-tabs']/li[text()='History']")
    private WebElement historyTab;

    private String lastHistoryXpath = ".//*[@class='event-table-history__item' and ./div[@data-date > %s]]";

    @Step("Забрать значение поля Country")
    public String getCountry() {
        return country.getText();
    }

    @Step("Забрать значение поля Importance")
    public String getImportance() {
        return importance.getText();
    }

    @Step("Переход на вкладку History")
    public EventPage goToHistoryTab() {
        historyTab.click();
        return this;
    }

    public void printLastHistory(int lastMonths) {
        String dateXpath = "./div[@class='event-table-history__date']/span";
        String actualXpath = "./div[contains(@class,'event-table-history__actual')]/span";
        String forecastXpath = "./div[@class='event-table-history__forecast']";
        String previousXpath = "./div[@class='event-table-history__previous']/span";
        StringBuilder sb = new StringBuilder("\n| Date             | Actual | Forecast | Previous |\n");
        getLastHistory(lastMonths).forEach(webElement -> {
            String date = webElement.findElement(By.xpath(dateXpath)).getText();
            String actual = webElement.findElement(By.xpath(actualXpath)).getText();
            String forecast = webElement.findElement(By.xpath(forecastXpath)).getText();
            String previous = webElement.findElement(By.xpath(previousXpath)).getText();
            sb.append(String.format("|%-18s|%-8s|%-10s|%-10s|\n", date, actual, forecast, previous));
        });
        logger.info(sb.toString());
    }

    private List<WebElement> getLastHistory(int lastMonths) {
        String timestampFrom = Timestamp.valueOf(LocalDateTime.now().minus(lastMonths, ChronoUnit.MONTHS)).getTime() + "";
        return getDriver().findElements(By.xpath(String.format(lastHistoryXpath, timestampFrom)));
    }


}
