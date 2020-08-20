package googleCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoogleCloudHomePage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    protected WebDriver driver;

    @FindBy(xpath = "//input[@placeholder=\"Search\"]")
    private WebElement searchingInputFild;


    public GoogleCloudHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public GoogleCloudHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleCloudSearchResults writingInSearchField() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder=\"Search\"]")));
        searchingInputFild.click();
        searchingInputFild.sendKeys("Google Cloud Platform Pricing Calculator");
        searchingInputFild.submit();
        return new GoogleCloudSearchResults(driver);
    }

    public GoogleCloudHomePage closePage() {
        driver.quit();
        return this;
    }
}
