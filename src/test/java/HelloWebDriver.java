import org.openqa.selenium.chrome.ChromeDriver;
import pastebin.PastebinHomePage;

import java.util.concurrent.TimeUnit;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {

        String pasteExpiration = "10 Minutes";
        PastebinHomePage page = new PastebinHomePage(new ChromeDriver());
        page.openPage()
                .writingCode("Hello WebDriver")
                .selectPasteExpiration(pasteExpiration)
                .writingPasteName("helloweb")
                .createNewPaste();
        System.out.println(page.getTitleThisPage());

        TimeUnit.SECONDS.sleep(10);
        page.closePage();
    }
}
