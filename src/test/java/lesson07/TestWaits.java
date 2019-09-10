package lesson07;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


import static lesson07.MyConditions.driver;
import static lesson07.MyConditions.waitFor;

public class TestWaits {


    @BeforeClass
    public static void setUp() {

        //System.setProperty("webdriver.chrome.driver", "D:\\HARD WORK\\Testing\\Automation\\Java\\Infopulse\\test-automation-5-master\\src\\driver\\chromedriver.exe");
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


    @Test
    public void openSiteMapPage() {
        driver.findElement(By.xpath("//a[@title =\"Sitemap\"]"))
                .click();
        waitFor(MyConditions.pageIsLoaded("http://automationpractice.com/index.php?controller=sitemap","Sitemap - My Store"));
    }

    @Test
    public void verifyTextPresentForElement() {
        String search = "Blouse";

        driver.findElement(By.id("search_query_top"))
                .clear();
        driver.findElement(By.id("search_query_top"))
                .sendKeys("Dress");
    waitFor(MyConditions.listNthElementHasText((By.xpath("//div[@class=\"ac_results\"]/ul/li")), 10, search));


    }

    @Test
    public void waitUntilElementIsDisappeared() {
        driver.findElement(By.id("search_query_top"))
                .clear();
        driver.findElement(By.id("search_query_top"))
                .sendKeys("Dress");
        WebElement dress = driver.findElement(By.xpath("//div[@class=\"ac_results\"]/ul/li"));
        driver.findElement(By.id("search_query_top"))
                .clear();
        driver.findElement(By.id("search_query_top"))
                .sendKeys("T-shirt");
        waitFor(MyConditions.stalenessOf(dress));
        Assert.assertThat(
                driver
                        .findElement(By.xpath("//*[@id=\"index\"]/div[2]/ul/li[1]"))
                        .getText(),
                CoreMatchers.containsString("T-shirt"));
        waitFor(MyConditions.stalenessOf(dress));

}
}