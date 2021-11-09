package steps.base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.ru.Дано;

import static utils.DriverManager.getDriver;

public class CSteps {

    @Before
    public void before() {
        BaseSteps.openBrowser();
    }

    @After
    public void after() {
        BaseSteps.close();
    }

    @Дано("Открыть в браузере страницу {string}")
    public void openPage(String string) {
        getDriver().get(BaseSteps.TEST_URL + BaseSteps.links.get(string));
    }

}
