package googleCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TenMinutesMailHome extends GoogleCloudHomePage {

    @FindBy(xpath = "//input[@id=\"mail_address\"]")
    private WebElement searchingEmailValue;

    @FindBy(xpath = "//span[@id=\"inbox_count_number\"][text()]")
    //span[@id="inbox_count_number"][text()="0"]
    private WebElement searchingEmailCounter;

    @FindBy(xpath = "//span[text()=\"Google Cloud Platform Price Estimate\"]")
    private WebElement searchingIncomingEmail;
    @FindBy(xpath = "//tr[@id=\"mobilepadding\"]/td/h2")
    private WebElement searchingMonthlyCost;

    public TenMinutesMailHome(WebDriver driver) {
        super(driver);
    }

    public TenMinutesMailHome openPage() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open('https://10minutemail.com')");
        return this;
    }

    public TenMinutesMailHome copyingEmail() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(searchingEmailValue));
        Actions builder = new Actions(driver);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        builder.keyDown(Keys.LEFT_CONTROL).sendKeys("c").keyUp(Keys.LEFT_CONTROL).build().perform();
        return this;
    }

    public TenMinutesMailHome cheackingIncomingEmail() {
        new WebDriverWait(driver, 30)
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        String counterValue = "1";
                        return searchingEmailCounter.getText().equals(counterValue);
                    }
                });


        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(searchingIncomingEmail));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", searchingIncomingEmail);
        Actions builder = new Actions(driver);
        builder.moveToElement(searchingIncomingEmail).click().build().perform();
        return this;
    }

    public boolean cheackMunthlyCost(String expectedMonthlyCost) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(searchingMonthlyCost));
        String monthlyCost = searchingMonthlyCost.getText();
        String[] strings = monthlyCost.split(" ");
        boolean value = false;
        for (String cost : strings) {
            if (cost.equals(expectedMonthlyCost)) {
                value = true;
                break;
            }
        }
        return value;
    }
}
