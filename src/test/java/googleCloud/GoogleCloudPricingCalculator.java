package googleCloud;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleCloudPricingCalculator extends GoogleCloudHomePage {
    @FindBy(xpath = "//*[@id=\"cloud-site\"]/devsite-iframe/iframe")
    private WebElement searchingFirstFrame;


    @FindBy(xpath = "//md-tab-item[1]/div[1]/div/div[2][text()=\"Compute Engine\"]")
    private WebElement searchingComputeEngine;

    @FindBy(xpath = "//input[@ng-model=\"listingCtrl.computeServer.quantity\"]")
    private WebElement searchingNumberOfInstances;

    @FindBy(xpath = "//md-checkbox[@aria-label=\"Add GPUs\"]")
    private WebElement searchingAdd_GPUs;

    @FindBy(xpath = "//div[@layout-align=\"end start\"]/button")
    private WebElement searchingAddToEstimate;

    @FindBy(xpath = "//md-list/md-list-item[@class]")
    private WebElement searchingEstimateResult;

    @FindBy(xpath = "//button[@id=\"email_quote\"]")
    private WebElement searchingEmailEstimate;


    public GoogleCloudPricingCalculator(WebDriver driver) {
        super(driver);
    }


    public GoogleCloudPricingCalculator workWithDropLists(String selectedIcon, String typeOfOptionXpath, String requiredValueInTheList) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selectedIcon)));
        WebElement icon = driver.findElement(By.cssSelector(selectedIcon));
        executor.executeScript("arguments[0].click()", icon);
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(typeOfOptionXpath)));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> options = driver
                .findElements(By.xpath(typeOfOptionXpath));
      //
        for (int i = 0; i <= 5; i++) {
            options = driver
                    .findElements(By.xpath(typeOfOptionXpath));
        }
        for (WebElement element : options) {
            String dropListContent = element.getText();
            System.out.println(dropListContent);
            if (dropListContent.equals(requiredValueInTheList)) {
                executor.executeScript("arguments[0].click()", element);
                System.out.println("<<<<<<" + dropListContent + ">>>>>>>>");
            }
        }
        return this;
    }

    public GoogleCloudPricingCalculator selectFrame() {
        driver.switchTo().defaultContent();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .frameToBeAvailableAndSwitchToIt(searchingFirstFrame));
        driver.switchTo().frame("myFrame");
        return this;
    }

    public GoogleCloudPricingCalculator selectComputeEngine() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(searchingComputeEngine));
        executor.executeScript("arguments[0].click()", searchingComputeEngine);
        return this;
    }

    public GoogleCloudPricingCalculator writingNumberOfInstances(String numberOfInstances) {
        searchingNumberOfInstances.sendKeys(numberOfInstances);
        return this;
    }

    public GoogleCloudPricingCalculator addGPU_Features() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(searchingAdd_GPUs));

        executor.executeScript("arguments[0].click()", searchingAdd_GPUs);
        return this;
    }

    public GoogleCloudPricingCalculator addToEstimate() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//div[@layout-align=\"end start\"]/button")));
        List<WebElement> buttonsAddToEstimate = driver.findElements(By.xpath("//div[@layout-align=\"end start\"]/button"));
        executor.executeScript("arguments[0].click()", buttonsAddToEstimate.get(0));
        return this;
    }

    public boolean getEstimateResult(String cheackedValue) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//md-list/md-list-item[@class]")));
        List<WebElement> results = driver.findElements(By.xpath("//md-list/md-list-item[@class]"));
        for (int i = 1; i <= 2; i++) {
            results = driver.findElements(By.xpath("//md-list/md-list-item[@class]"));
        }
        List<String> textResults = new ArrayList<String>();
        for (WebElement element : results) {
            textResults.add(element.getText());
            System.out.println(element.getText());
        }
        boolean cheakingResults = textResults.contains(cheackedValue);
        return cheakingResults;
    }

    public EmailEstimateWindow goToEmailEstimateWindow() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchingEmailEstimate));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", searchingEmailEstimate);
        return new EmailEstimateWindow(driver);
    }

}
