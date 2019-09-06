package lesson07;

import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class LoginLogoutTest {
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        // System.setProperty("webdriver.chrome.driver","D:\\HARD WORK\\Testing\\Automation\\Java\\Infopulse\\test-automation-5-master\\src\\driver\\chromedriver.exe");
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

    @Test(timeout = 8000l)
    public void LoginTest(){
    LoginPage loginPage = new LoginPage(driver);
    AccountPage accountPage = loginPage.login("Gena080393@gmail.com","hazanov2615386");

        Assert.assertThat(
                driver.findElement(By.xpath("//a[@class=\"account\"]")).getText(),
                CoreMatchers.containsString("Hennadii Tytarenko"));

    }

    @Ignore
    @Test(timeout = 8000l)
    public void LoginTest2(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginBtn().enterEmail("Gena080393@gmail.com").enterPassword("hazanov2615386").clickSignInBtn();
        Assert.assertThat(
                driver.findElement(By.xpath("//a[@class=\"account\"]")).getText(),
                CoreMatchers.containsString("Hennadii Tytarenko"));

    }

    @Test(timeout = 8000l)
    public void SignOutTest(){
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = accountPage.signOut();

        Assert.assertThat(
                driver.findElement(By.xpath("//a[@class=\"login\"]")).getText(),
                CoreMatchers.containsString("Sign in"));

    }


}
