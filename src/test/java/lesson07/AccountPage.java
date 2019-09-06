package lesson07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    @FindBy(className = "logout")
    private WebElement logoutButton;

    private WebDriver driver;

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }



    public LoginPage signOut(){
    logoutButton.click();
    return new LoginPage(driver);
    }


}
