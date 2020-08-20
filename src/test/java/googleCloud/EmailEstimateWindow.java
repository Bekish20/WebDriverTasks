package googleCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EmailEstimateWindow extends GoogleCloudHomePage {
    @FindBy(xpath = "//*[@id=\"cloud-site\"]/devsite-iframe/iframe")
    private WebElement searchingFirstFrame;

    @FindBy(xpath = "//label[@for=\"input_401\"]/span[text()=\"*\"]")
    private WebElement searchingInputEmailZone;
    @FindBy(xpath = "//button[@aria-label=\"Send Email\"]")
    private WebElement searchingSendEmailButton;
    @FindBy(xpath = "//span[text()=\"Email Your Estimate\"]")
    private WebElement searchingWindowTetle;

    public EmailEstimateWindow(WebDriver driver) {
        super(driver);
    }

    public EmailEstimateWindow inputEmailValueFromBuffer() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Email Your Estimate\"]")));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for=\"input_401\"]/span[text()=\"*\"]")));
        Actions builder = new Actions(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", searchingInputEmailZone);
        executor.executeScript("arguments[0].scrollIntoView();", searchingWindowTetle);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(searchingInputEmailZone));
        builder.keyDown(Keys.LEFT_CONTROL).sendKeys("v").keyUp(Keys.LEFT_CONTROL).build().perform();
        return this;
    }

    public EmailEstimateWindow clickSendEmailButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(searchingSendEmailButton));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", searchingSendEmailButton);
        return this;
    }

    public EmailEstimateWindow selectFrame() {
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(searchingFirstFrame));
        driver.switchTo().frame("myFrame");
        return this;
    }
}
