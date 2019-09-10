package lesson07;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyConditions {

    static WebDriver driver;

    public MyConditions(WebDriver driver) {
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
                return (expTitle.equals(this.currentTitle) && this.currentUrl.equals(expUrl));
            }

            @Override
            public String toString() {
                return String.format("title to be \"%s\". Current title: \"%s\".", expTitle, this.currentTitle) +

                        String.format(" Url to be \"%s\". Current url: \"%s\"", expUrl, this.currentUrl);
            }
        };
    }


    public static ExpectedCondition<Boolean> listNthElementHasText(By locator, int elNo, String expText) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    List<WebElement> results = driver.findElements(locator);
                    String elementText = results.get(elNo-1).getText();
                    return elementText.contains(expText);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element found by %s", expText, locator);
            }
        };
    }



    public static ExpectedCondition<Boolean> stalenessOf(final WebElement elToBeDisappeared) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver ignored) {
                try {
                    elToBeDisappeared.isEnabled();
                    return false;
                } catch (StaleElementReferenceException var3) {
                    return true;
                }
            }

            public String toString() {
                return String.format("element (%s) to become stale", elToBeDisappeared);
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

