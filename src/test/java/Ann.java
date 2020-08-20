import jdk.jfr.events.ExceptionThrownEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pastebin.PastebinHomePage;

import java.util.concurrent.TimeUnit;

public class Ann {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://pastebin.com/");
        WebElement inputZone = driver.findElement(By.xpath("//textarea[@id=\"paste_code\"]"));
        //div[text()='Step 1. Set the direction for self-development']/preceding::div[@class='pdp-action-item-header__checkbox pdp-action-item-header__checkbox--unchecked']
        //div[text()='Step 1. Set the direction for self-development']/preceding::div[@class='pdp-action-item-header__checkbox pdp-action-item-header__checkbox--unchecked']

        driver.close();

    }

}
