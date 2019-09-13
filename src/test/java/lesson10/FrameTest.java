package lesson10;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FrameTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        //System.setProperty("webdriver.chrome.driver","D:\\HARD WORK\\Testing\\Automation\\Java\\Infopulse\\test-automation-5-master\\src\\driver\\chromedriver.exe");
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
    public void verifyFacebookText() {
        WebElement facebookFrame = driver.findElement(By.xpath("//div[@id = \"facebook_block\"]//iframe"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();"
                ,facebookFrame);
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(facebookFrame));


       List<WebElement> scripts = driver.findElements(By.xpath("//html[@id=\"facebook\"]//script"));
        for( WebElement el : scripts){
            Assert.assertTrue(el.getAttribute("text").contains("facebook"));
        }

        driver.switchTo().defaultContent();

    }

}
