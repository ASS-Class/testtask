package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public abstract class DriverManager {

    private static final int TIMEOUT = 100;
    private final static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    static {//Если параметр -Ddriver_path не был передан, загружаем путь к драйверу из пропертей
        System.setProperty(
                PropertyLoader.loadProperty("driver"),
                System.getProperty("driver_path", PropertyLoader.loadProperty("driver_path"))
        );
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-agent=\"Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)\"");
            driver.set(new ChromeDriver(options));
            driver.get().manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.MILLISECONDS);
        }
        return driver.get();
    }

    public static void close() {
        getDriver().close();
        driver.set(null);
    }
}