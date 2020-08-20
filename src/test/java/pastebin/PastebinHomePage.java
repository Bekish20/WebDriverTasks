package pastebin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL ="https://pastebin.com";
    private WebDriver driver;

    @FindBy (id ="paste_code" )
    private WebElement searchInputFieldForCode;
    @FindBy (xpath ="//input[@name=\"paste_name\"]")
    private WebElement searchPasteName;

    //select/option[text()="10 Minutes"]
    //select[@name="paste_expire_date"]
    @FindBy (xpath ="//select[@name=\"paste_expire_date\"]" )
    private WebElement searchPasteExpirationSelector;

    @FindBy (xpath ="//input[@value=\"Create New Paste\"]" )
    private WebElement searchNewPaste;

    //select/option[text()="Bash"]
    @FindBy (name = "paste_format")
    private WebElement searchSyntaxHighlightingSelector;

    @FindBy (xpath = "//div[@class=\"paste_box_line1\"]")
    private WebElement searchPageTitle;
    @FindBy (xpath = "//span[@class=\"st0\"]")
    private WebElement searchMarkedCode;
    @FindBy (xpath = "//ol[@class=\"bash\"]")
    private WebElement searchForEnteredCode;


    public PastebinHomePage (WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public PastebinHomePage openPage(){
        driver.get(HOMEPAGE_URL);
        return this;
    }
    public PastebinHomePage writingCode(String code){
        searchInputFieldForCode.sendKeys(code);
        return this;
    }
    public PastebinHomePage writingPasteName(String name){
        searchPasteName.sendKeys(name);
        return this;
    }

    public PastebinHomePage selectSyntaxHighlighting (String syntaxHighlighting){
        Select select= new Select(searchSyntaxHighlightingSelector);
        select.selectByVisibleText(syntaxHighlighting);
        return  this;
    }
    public PastebinHomePage selectPasteExpiration(String pasteExpiration){
        Select select=new Select(searchPasteExpirationSelector);
        select.selectByVisibleText(pasteExpiration);
        return this;
    }
    public PastebinHomePage createNewPaste(){
        searchNewPaste.click();
        return this;
    }
    public String getTitleThisPage(){
        return searchPageTitle.getAttribute("title");
    }
    public String getTitleThatPage(){
        return driver.getTitle();
    }
    public String getCodeColor(){
        return searchMarkedCode.getCssValue("color");
    }
    public String getEnteredCode(){
        return searchForEnteredCode.getText();
    }
    public PastebinHomePage closePage(){
        driver.quit();
        return this;
    }
}
