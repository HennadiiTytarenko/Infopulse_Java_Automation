package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(className = "login")
    private WebElement loginButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;


    private WebDriver driver;
    private static final Logger LOG = LogManager.getLogger(LoginPage.class);
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LandingPage login(String email, String password){
        clickLoginBtn();
        enterEmail(email);
        enterPassword(password);
        clickSignInBtn();
        return new LandingPage(driver);
    }


    public LoginPage clickLoginBtn(){
        loginButton.click();
        return this;
    }

   public LoginPage enterEmail(String email){
       emailField.sendKeys(email);
       return this;
    }


    public LoginPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInBtn(){
        submitLoginButton.click();
        return this;
    }

}
