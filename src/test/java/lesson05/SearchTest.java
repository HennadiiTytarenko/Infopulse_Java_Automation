package lesson05;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

       System.setProperty("webdriver.chrome.driver","D:\\HARD WORK\\Testing\\Automation\\Java\\Infopulse\\test-automation-5-master\\src\\driver\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.get("http://automationpractice.com/index.php");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test(timeout = 5000l)
    public void verifyFirstTipIsCorrect() {
        driver.findElement(By.id("search_query_top"))
                .clear();
        driver.findElement(By.id("search_query_top"))
                .sendKeys("Printed Summer Dress");

        List<WebElement> countResults = driver.findElements(By.xpath("//div[@class = \"ac_results\"]//li"));
        Assert.assertTrue("There are 3 results", countResults.size()==3);
        Assert.assertThat(
                countResults.get(0).getText(),
                CoreMatchers.containsString("Printed Summer Dress"));
    }

}

