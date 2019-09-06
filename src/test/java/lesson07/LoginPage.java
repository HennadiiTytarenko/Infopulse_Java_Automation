package lesson07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static lesson07.LoginLogoutTest.driver;

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


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public AccountPage login(String email, String password){

        clickLoginBtn();
        enterEmail(email);
        enterPassword(password);
        clickSignInBtn();
        return new AccountPage(driver);
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





    /*void searchFor(String searchQuery) {
        searchBox.clear();
        searchBox.sendKeys(searchQuery);
    }*/



}
