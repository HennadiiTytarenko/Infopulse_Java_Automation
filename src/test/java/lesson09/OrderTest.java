package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.EventHandler;
import java.util.concurrent.TimeUnit;

public class OrderTest {
    static WebDriver driver;
    private static final Logger LOG = LogManager.getLogger(OrderTest.class);
    LandingPage landingPage = new LandingPage(driver);
    AccountPage accountPage = new AccountPage(driver);
    OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);



    @BeforeClass
    public static void setUp() {

        // System.setProperty("webdriver.chrome.driver","D:\\HARD WORK\\Testing\\Automation\\Java\\Infopulse\\test-automation-5-master\\src\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        EventFiringWebDriver wd = new EventFiringWebDriver(new ChromeDriver());
        wd.register(new EventHandler());
        driver = wd;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @Before
    public void LoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Gena080393@gmail.com","hazanov2615386");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void makeOrder(){
       landingPage.searchAndNavigate("Blouse")
        .addToCart()
        .goToCheckout()
        .goToAddressStep()
        .goToShippingStep()
        .clickAgreeCheckbox()
        .goToPaymentStep()
        .selectPayByBankWire()
        .clickConfirm();

       //Save full information about order which contains unique reference value
        String id=driver.findElement(By.xpath("//div[@class=\"box\"]")).getText();
        accountPage.clickOnAccountName()
        .clickOnOrderList();
        LOG.info("Verifying that reference link in new order exists in full information, which was saved on last step");
        Assert.assertTrue("Not correct reference order", id.contains(orderHistoryPage.getOrderReference()));
    }
}
