package lesson07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyConditions {
    static WebDriver driver;

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




    static <T> T  waitFor(ExpectedCondition<T> condition, long timeout) {
        return (new WebDriverWait(driver, timeout)).until(condition);
    }

    static <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, 10l);
    }
}

