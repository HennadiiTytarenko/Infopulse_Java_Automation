package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    @FindBy(className = "logout")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[@class=\"account\"]")
    private WebElement userNamebutton;

    @FindBy(xpath = "//a[@title=\"Orders\"]")
    private WebElement orderHistoryButton;

    public WebDriver driver;

    private static final Logger LOG = LogManager.getLogger(AccountPage.class);
    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public OrderHistoryPage clickOnOrderList(){
        LOG.info("Navigating to order history list");
        orderHistoryButton.click();
        return new OrderHistoryPage(driver);
    }

    public AccountPage clickOnAccountName(){
        userNamebutton.click();
        return new AccountPage(driver);
    }

    public LoginPage signOut(){
    logoutButton.click();
    LOG.info("Logout was clicked");
    return new LoginPage(driver);
    }

}
