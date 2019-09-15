package lesson08;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyConditions {

    static WebDriver driver;
    protected MyConditions(WebDriver driver) {
        this.driver = driver;
    }


    public static ExpectedCondition<Boolean> pageIsLoaded(String expUrl, String expTitle) {
        return new ExpectedCondition<Boolean>() {
            private String currentTitle = "";
            private String currentUrl = "";

            @Override
            public Boolean apply(WebDriver driver) {
                this.currentTitle = driver.getTitle();
                this.currentUrl = driver.getCurrentUrl();
                return expTitle.equals(this.currentTitle) && this.currentUrl.equals(expUrl);
            }

            @Override
            public String toString() {
                return String.format("title to be \"%s\". Current title: \"%s\"", expTitle, this.currentTitle);
            }
        };
    }


    public static ExpectedCondition<List<WebElement>> listNthElementHasText(By locator, int elNo, String expText) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = ExpectedConditions.findElement(locator, driver).getText();
                    return elementText.contains(text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element found by %s", text, locator);
            }
        };
    }

    public static ExpectedCondition<Boolean> stalenessOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ignored) {
                try {
                    element.isEnabled();
                    return false;
                } catch (StaleElementReferenceException var3) {
                    return true;
                }
            }

            public String toString() {
                return String.format("element (%s) to become stale", element);
            }
        };
    }







    static <T> T  waitFor(ExpectedCondition<T> condition, long timeout) {
        return (new WebDriverWait(driver, timeout)).until(condition);
    }

    static <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, 10l);
    }
}

