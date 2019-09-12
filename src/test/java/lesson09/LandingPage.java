package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    @FindBy(id = "search_query_top")
    private WebElement searchBox;

    @FindBy(xpath = "//div[@class = \"ac_results\"]//li[1]")
    private WebElement firstSearchResult;


    public WebDriver driver;

    private static final Logger LOG = LogManager.getLogger(CheckoutStepsPage.class);
    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ProductPage searchAndNavigate (String searchString){
        LOG.info("Searching for product");
        searchBox.sendKeys(searchString);
        firstSearchResult.click();
        return new ProductPage(driver);
    }

}
