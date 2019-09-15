package lesson07;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestWaits extends MyConditions{


    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        //System.setProperty("webdriver.chrome.driver", "D:\\HARD WORK\\Testing\\Automation\\Java\\Infopulse\\test-automation-5-master\\src\\driver\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");

        waitFor(MyConditions.pageIsLoaded("55","55"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }



    @Test(timeout = 5000l)
    public void verifyFirstTipIsCorrect() {
        String search = "Dress";

        driver.findElement(By.id("search_query_top"))
                .clear();
        driver.findElement(By.id("search_query_top"))
                .sendKeys("Dress");

        WebElement firstTip = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]")));



        List<WebElement> results = driver.findElements(By.xpath("//div[@class = \"ac_results\"]//li"));
        Stream<WebElement> searchList = results.stream();



    }


}
