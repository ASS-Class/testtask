package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.DriverManager.getDriver;

public class EconomicCalendarPage extends PageBase {

    private final String dateFilterXPath = ".//*[@id='economicCalendarFilterDate']//li[contains(.,'%s')]";

    @FindBy(xpath = ".//*[@id='economicCalendarFilterCurrency']/li")
    private List<WebElement> currency;
    @FindBy(xpath = ".//*[@id='economicCalendarFilterImportance']/li")
    private List<WebElement> importance;
    @FindBy(xpath = ".//div[@class='ec-table']//div[@class='ec-table__item'][1]//a")
    private WebElement firstRow;

    @Step("Выбрать фильтр Дата")
    public EconomicCalendarPage selectDateFilter(String value) {
        getDriver().findElement(By.xpath(String.format(dateFilterXPath, value))).click();
        return new EconomicCalendarPage();
    }

    @Step("Выбрать значения в фильте Currencies")
    public EconomicCalendarPage selectCurrencyFilter(String... values) {
        selectCheckbox(currency, values);
        return this;
    }

    @Step("Выбрать значения в фильте Importance")
    public EconomicCalendarPage selectImportanceFilter(String... values) {
        selectCheckbox(importance, values);
        return this;
    }

    @Step("Перейти по ссылке в первой строке таблицы событий")
    public EventPage drillToFistEvent() {
        firstRow.click();
        return new EventPage();
    }

    protected void selectCheckbox(List<WebElement> ulElement, String... values) {
        String xpath = ".//*[contains(.,'%s')]/..";
        ulElement.forEach(webElement -> {
            for (String value : values) {
                WebElement targetWe = webElement;
                boolean expectedCheckBoxStatus = false;
                List<WebElement> we = webElement.findElements(By.xpath(String.format(xpath, value)));
                if (we.size() > 0) {
                    targetWe = we.get(0);
                    expectedCheckBoxStatus = true;
                }
                WebElement inputWe = targetWe.findElement(By.xpath(".//input"));
                if (inputWe.isSelected() != expectedCheckBoxStatus) {
                    targetWe.findElement(By.xpath(".//label")).click();
                }
            }
        });

    }

}
