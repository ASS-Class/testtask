package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.function.Function;

import static utils.DriverManager.getDriver;

public abstract class PageBase {

    public PageBase() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void selectCheckbox(List<WebElement> ulElement, String... values) {
        String xpath = ".//li[contains(.,'%s')]";
        ulElement.forEach(webElement -> {
            for (String value : values) {
                WebElement we = webElement.findElement(By.xpath(String.format(xpath, value)));
                WebElement inputWe = we.findElement(By.xpath(".//input"));
                if ("checked".equals(inputWe.getAttribute("value"))) {
                    we.click();
                }
            }
        });

    }


}
