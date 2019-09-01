package lesson05;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AccountTest {

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

    @Before
    public void login() {

        driver.findElement(By.className("login"))
                .click();
        driver.findElement(By.id("email"))
                .sendKeys("Gena080393@gmail.com");
        driver.findElement(By.id("passwd"))
                .sendKeys("hazanov2615386");
        driver.findElement(By.id("SubmitLogin"))
                .click();

    }

    @After
    public void logout() {
        driver.findElement(By.className("logout"))
                .click();
    }


    @AfterClass
    public static void tearDown() {
        driver.quit();
    }




    @Test(timeout = 8000l)
    public void verifyOrderHistory() {
        driver.findElement(By.xpath("//span[contains(text(),'Order history and details')]"))
                .click();
        Assert.assertThat(
                driver
                        .findElement(By.className("info-title"))
                        .getText(),
                CoreMatchers.containsString("Here are the orders you've placed since your account was created."));

/*driver.findElement(By.xpath("//div[@class=\"breadcrumb clearfix\"]/a[contains(text(),'My account')]"))
                .click();
*/

    }

    @Test(timeout = 5000l)
    public void verifyMyWishlists() {
        driver.findElement(By.xpath("//span[contains(text(),'My wishlists')]"))
                .click();
        Assert.assertThat(
                driver
                        .findElement(By.className("page-heading"))
                        .getText(),
                CoreMatchers.containsString("MY WISHLISTS"));

    }

    @Test(timeout = 5000l)
    public void verifyMyCreditSlips() {
        driver.findElement(By.xpath("//span[contains(text(),'My credit slips')]"))
                .click();
        Assert.assertThat(
                driver
                        .findElement(By.className("info-title"))
                        .getText(),
                CoreMatchers.containsString("Credit slips you have received after canceled orders."));

    }

    @Test(timeout = 5000l)
    public void verifyMyAddresses() {
        driver.findElement(By.xpath("//span[contains(text(),'My addresses')]"))
                .click();
        Assert.assertThat(
                driver
                        .findElement(By.className("page-heading"))
                        .getText(),
                CoreMatchers.containsString("MY ADDRESSES"));

    }

    @Test(timeout = 5000l)
    public void verifyMyPersonalInformation() {
        driver.findElement(By.xpath("//span[contains(text(),'My personal information')]"))
                .click();
        Assert.assertThat(
                driver
                        .findElement(By.className("page-subheading"))
                        .getText(),
                CoreMatchers.containsString("YOUR PERSONAL INFORMATION"));

    }

}



