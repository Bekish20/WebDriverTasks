package googleCloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoogleCloudSearchResults extends GoogleCloudHomePage {


    @FindBy(xpath = "//b[text()=\"Google Cloud Platform Pricing Calculator\"]")
    private WebElement searchingCalculatorPage;

    public GoogleCloudSearchResults(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculator goToCalculatorPage() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions
                      .presenceOfAllElementsLocatedBy(By.xpath("//b[text()=\"Google Cloud Platform Pricing Calculator\"]")));
        Actions builder = new Actions(driver);
        builder.moveToElement(searchingCalculatorPage).click().build().perform();
        return new GoogleCloudPricingCalculator(driver);
    }
}
