package lesson09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {


    @FindBy(xpath = "//tr[@class=\"first_item \"]/td[1]")
    public WebElement referenceOrder;


    private WebDriver driver;

    private static final Logger LOG = LogManager.getLogger(OrderHistoryPage.class);
    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    String getOrderReference(){
      return referenceOrder.getText();
    }

}
