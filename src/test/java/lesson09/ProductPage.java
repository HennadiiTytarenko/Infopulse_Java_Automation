package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    @FindBy(xpath = "//button[@name=\"Submit\"]")
    public WebElement AddToCartButton;

    @FindBy(xpath = "//a[@title=\"Proceed to checkout\"]")
    public WebElement ProceedToCheckout;


    public WebDriver driver;
    private static final Logger LOG = LogManager.getLogger(ProductPage.class);
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductPage addToCart(){
        LOG.info("Adding product to Cart");
        AddToCartButton.click();
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(ProceedToCheckout));
        return this;
    }

  public CheckoutStepsPage goToCheckout(){
      LOG.info("Navigating to Checkout steps");
      ProceedToCheckout.click();
      return new CheckoutStepsPage(driver);
  }
}
