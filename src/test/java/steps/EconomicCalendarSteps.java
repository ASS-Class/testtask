package steps;


import cucumber.api.java.ru.Затем;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import pages.EconomicCalendarPage;
import pages.EventPage;

public class EconomicCalendarSteps {

    @Затем("Отфильтровать события календаря по следующим параметрам Period, Importance, Currency: {string}, {string}, {string}")
    public void filterEvent(String period, String importance, String currency) {
        new EconomicCalendarPage()
                .selectDateFilter(period)
                .selectImportanceFilter(importance)
                .selectCurrencyFilter(currency);
    }

    @Затем("Открыть первое событие в списке")
    public void openFirstEvent() {
        new EconomicCalendarPage().drillToFistEvent();
    }

    @Тогда("Проверить, что приоритет и страна отображаемого события соответствуют выбранным фильтрам Importance, Currency: {string}, {string}")
    public void checkFiltersImportanceCurrency(String importance, String country) {
        EventPage eventPage = new EventPage();
        Assert.assertEquals(importance, eventPage.getImportance());
        Assert.assertEquals(country, eventPage.getCountry());

    }

    @Затем("Вывести в лог историю события за последние {int} месяцев в виде таблицы.")
    public void printLastHistory(Integer int1) {
        new EventPage().goToHistoryTab().printLastHistory(int1);
    }

}
