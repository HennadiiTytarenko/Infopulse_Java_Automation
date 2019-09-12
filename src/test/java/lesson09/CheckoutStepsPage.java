package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepsPage {



    @FindBy(xpath = "//p[@class=\"cart_navigation clearfix\"]//a[@title=\"Proceed to checkout\"]")
    private WebElement SummaryCheckoutButton;

    @FindBy(xpath = "//button[@name=\"processAddress\"]")
    private WebElement AddressCheckoutButton;

    @FindBy(xpath = "//button[@name=\"processCarrier\"]")
    private WebElement ShippingCheckoutButton;

    @FindBy(xpath = "//input[@id=\"cgv\"]")
    private WebElement AgreeCheckbox;

    @FindBy(xpath = "//a[@class=\"bankwire\"]")
    private WebElement PayByBankWireButton;

    @FindBy(xpath = "//span[contains(text(),\"I confirm my order\")]")
    private WebElement ConfirmOrderButton;

   @FindBy(xpath = "//div[@class=\"box\"]")
    private WebElement orderInfo;



    public WebDriver driver;
    private static final Logger LOG = LogManager.getLogger(CheckoutStepsPage.class);
    public CheckoutStepsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public CheckoutStepsPage goToAddressStep() {
        LOG.info("Navigating to Address step");
        SummaryCheckoutButton.click();
        return this;
    }

    public CheckoutStepsPage goToShippingStep(){
        LOG.info("Navigating to Shipping step");
        AddressCheckoutButton.click();
        return this;
    }

    public CheckoutStepsPage clickAgreeCheckbox(){
        LOG.info("Agree with terms");
        AgreeCheckbox.click();
        return this;
    }

    public CheckoutStepsPage goToPaymentStep(){
        LOG.info("Navigating to Payment step");
        ShippingCheckoutButton.click();
        return this;
    }

    public CheckoutStepsPage selectPayByBankWire(){
        LOG.info("Selecting payment option");
        PayByBankWireButton.click();
        return this;
    }

    String getOrderinfo(){
        return orderInfo.getText();
    }


    public AccountPage clickConfirm(){
        LOG.info("Confirming order");
        ConfirmOrderButton.click();
        return new AccountPage(driver);
    }
}
