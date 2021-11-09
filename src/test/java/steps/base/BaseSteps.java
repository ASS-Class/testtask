package steps.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverManager;
import utils.PropertyLoader;

import java.util.HashMap;
import java.util.Map;

import static utils.DriverManager.getDriver;

public class BaseSteps {

    private static final Logger logger = LoggerFactory.getLogger("BaseSteps");

    static final String TEST_URL = PropertyLoader.loadProperty("test_url");
    private static final int TIME_OUT_IN_SECONDS = 20;
    private static final int MILLIS = 1000;

    static final Map<String, String> links = new HashMap<>();

    static {
        links.put("Экономический календарь", "economic-calendar");
    }

    public BaseSteps() {

    }
    @Step("Старт браузера")
    public static void openBrowser() {
        logger.info("Старт браузера");
        goToHome();
        maximizeBrowserWindow();
    }

    @Step("Открытие главной страницы")
    public static void goToHome() {
        getDriver().get(TEST_URL);
        getDriver().findElement(By.xpath(".//span[@class='float-vertical-panel__cross']")).click();
    }

    public static void maximizeBrowserWindow() {
        getDriver().manage().window().maximize();
        waitingDocumentReadyState();
    }

    public static void waitingDocumentReadyState() {
        try {
            Thread.sleep(MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        (new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS)).until((getDriver) -> {
            try {
                return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
                        .toString().equals("complete");
            } catch (Exception e) {
                return false;
            }
        });
    }
    @Step("Закрытие браузера")
    public static void close() {
        logger.info("Закрытие браузера");
        DriverManager.close();
    }


}
