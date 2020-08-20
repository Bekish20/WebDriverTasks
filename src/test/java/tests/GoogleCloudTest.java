package tests;
import googleCloud.EmailEstimateWindow;
import googleCloud.GoogleCloudHomePage;
import googleCloud.GoogleCloudPricingCalculator;
import googleCloud.TenMinutesMailHome;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class GoogleCloudTest {
    private static String instances="4";

    private static String operatingSystemIcon="#select_value_label_51";
    private static String operatingSystem="//div[@id=\"select_container_71\"]/md-select-menu/md-content/md-option[@class=\"md-ink-ripple\"]";
    private static String selectedOperatingSystem="Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";

    private static String machineClassIcon="#select_value_label_52";
    private static String machineClass="//div[@id=\"select_container_75\"]/md-select-menu/md-content/md-option[@class=\"md-ink-ripple\"]";
    private static String selectedMachineClass="Regular";

    private static String machineTypeIcon="#select_value_label_55";
    private static String machineType="//div[@id=\"select_container_84\"]/md-select-menu/md-content/md-optgroup/md-option[@class]";
    private static String selectedMachineType="n1-standard-8 (vCPUs: 8, RAM: 30GB)";

    private static String gpusNumbersIcon="#select_value_label_332";
    private static String gpusNumbers="//div[@id=\"select_container_335\"]/md-select-menu/md-content/md-option[@class=\"ng-scope md-ink-ripple\"][@value]";
    private static String select_GPUsNumbers="1";

    private static String gpusTypeIcon="#select_value_label_333";
    private static String gpusType ="//div[@id=\"select_container_337\"]/md-select-menu/md-content/md-option[@class=\"ng-scope md-ink-ripple\"][@value]";
    private static String selectedGPUsType="NVIDIA Tesla V100";

    private static String local_SSD_Icon="#select_value_label_169";
    private static String local_SSD ="//div[@id=\"select_container_171\"]/md-select-menu/md-content/md-option[@class=\"ng-scope md-ink-ripple\"][@value]";
    private static String selectedLocal_SSD ="2x375 GB";

    private static String datacenterLocationIcon="#select_value_label_56";
    private static String datacenterLocation ="//div[@id=\"select_container_86\"]/md-select-menu/md-content/md-option[@class=\"ng-scope md-ink-ripple\"][@value]";
    private static String selectedDatacenterLocation="Frankfurt (europe-west3)";

    private static String commitedUsageIcon="#select_value_label_57";
    private static String commitedUsage ="//div[@id=\"select_container_93\"]/md-select-menu/md-content/md-option[@class=\"md-ink-ripple\"][@value]";
    private static String selectedCommitedUsage="1 Year";

    private static String vmClass="VM class: regular";
    private static String instanceType="Instance type: n1-standard-8";
    private static String region="Region: Frankfurt";
    private static String total_SSD_Space="Total available local SSD space 2x375 GiB";
    private static String commitmentTerm="Commitment term: 1 Year";
    private static String estimatedComponentCost="Estimated Component Cost: USD 1,082.77 per 1 month";
    private static String emailMonthlyCost="1,082.77";

    private WebDriver driver;
    private GoogleCloudPricingCalculator calculatorPage;

    @BeforeClass
    public void browserSetup() {
        driver = new ChromeDriver();
        calculatorPage=goToCalculatorResult();
    }
    public GoogleCloudPricingCalculator goToCalculatorResult(){
        GoogleCloudHomePage page = new GoogleCloudHomePage(driver);
        GoogleCloudPricingCalculator calculatorPage= page.openPage()
                .writingInSearchField()
                .goToCalculatorPage()
                .selectFrame()
                .selectComputeEngine()
                .writingNumberOfInstances(instances)
                .workWithDropLists(operatingSystemIcon,operatingSystem,selectedOperatingSystem)
                .workWithDropLists(machineClassIcon,machineClass,selectedMachineClass)
                .workWithDropLists(machineTypeIcon,machineType,selectedMachineType)
                .addGPU_Features()
                .workWithDropLists(gpusNumbersIcon,gpusNumbers,select_GPUsNumbers)
                .workWithDropLists(gpusTypeIcon,gpusType,selectedGPUsType)
                .workWithDropLists(local_SSD_Icon,local_SSD, selectedLocal_SSD)
                .workWithDropLists(datacenterLocationIcon,datacenterLocation,selectedDatacenterLocation)
                .workWithDropLists(commitedUsageIcon,commitedUsage,selectedCommitedUsage)
                .addToEstimate();
        return calculatorPage;
    }

    @Test
    public void сheackEstimate_VM_Class() {
        boolean cheacking_VM_Class =calculatorPage.getEstimateResult(vmClass);
        Assert.assertTrue(cheacking_VM_Class);
    }
    @Test
    public void сheackEstimateInstanceType() {
        boolean cheackingInstanceType=goToCalculatorResult().getEstimateResult(instanceType);
        Assert.assertTrue(cheackingInstanceType);
    }
    @Test
    public void сheackEstimateRegion() {
        boolean cheackingRegion=goToCalculatorResult().getEstimateResult(region);
        Assert.assertTrue(cheackingRegion);
    }
    @Test
    public void сheackEstimateTotal_SSD_Space() {
        boolean cheackingTotal_SSD_Space=goToCalculatorResult().getEstimateResult(total_SSD_Space);
        Assert.assertTrue(cheackingTotal_SSD_Space);
    }
    @Test
    public void сheackEstimateCommitmentTerm() {
        boolean cheackingCommitmentTerm=goToCalculatorResult().getEstimateResult(commitmentTerm);
        Assert.assertTrue(cheackingCommitmentTerm);
    }
    @Test
    public void сheackEstimateMonthleCost() {
        boolean cheackingEstimatedComponentCost=goToCalculatorResult().getEstimateResult(estimatedComponentCost);
        Assert.assertTrue(cheackingEstimatedComponentCost);
    }

    @Test
    public void сheackEmailMonthleCost() {
        String parentHandle = driver.getWindowHandle();
        EmailEstimateWindow workingWhithEmailEstimateWindow=goToCalculatorResult().goToEmailEstimateWindow();
        TenMinutesMailHome mailPage=new TenMinutesMailHome(driver);
        TenMinutesMailHome workingWithEmailPage= mailPage.openPage();
        ArrayList<String> newHandle = new ArrayList<String>(driver.getWindowHandles());
        newHandle.remove(parentHandle);

        driver.switchTo().window(newHandle.get(0));
        workingWithEmailPage
                .copyingEmail();

        driver.switchTo().window(parentHandle);
        workingWhithEmailEstimateWindow
                .selectFrame()
                .inputEmailValueFromBuffer()
                .clickSendEmailButton();
        driver.switchTo().window(newHandle.get(0));

        boolean cheackingMonthleCostInEmail= workingWithEmailPage
                .cheackingIncomingEmail()
                .cheackMunthlyCost(emailMonthlyCost);
        Assert.assertTrue(cheackingMonthleCostInEmail);


    }
    @AfterMethod(alwaysRun = true)
    public void browserShudown() {
        driver.quit();
    }
}
