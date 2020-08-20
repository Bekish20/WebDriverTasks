package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pastebin.PastebinHomePage;



public class PastebinTest {
    private static String code = "git config --global user.name  \"New Sheriff in Town\"" + "\n"
            + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")" + "\n"
            + "git push origin master --force";
    private static String pasteName = "how to gain dominance among developers";
    private static String codeRedColor ="rgba(255, 0, 0, 1)";
    private static String syntaxHighlighting = "Bash";
    private static String pasteExpiration = "10 Minutes";


    @Test
    public void сheackPageTitle() {
        PastebinHomePage page = new PastebinHomePage(new ChromeDriver());
        page.openPage()
                .writingCode(code)
                .selectPasteExpiration(pasteExpiration)
                .selectSyntaxHighlighting(syntaxHighlighting)
                .writingPasteName(pasteName)
                .createNewPaste();
        Assert.assertEquals(page.getTitleThisPage(), pasteName);
        Assert.assertEquals();
        Assert.assertEquals(page.getEnteredCode(), code);
        Assert.assertEquals(page.getCodeColor(), codeRedColor);
        page.closePage();
    }


   /* @Test
    public void bashTextColorCheack() {
        PastebinHomePage page = new PastebinHomePage(new ChromeDriver());
        page.openPage()
                .writingCode(code)
                .selectPasteExpiration()
                .selectSyntaxHighlighting()
                .writingPasteName(pasteName)
                .createNewPaste();
        Assert.assertEquals(page.getCodeColor(),codeRedColor);
        page.closePage();
    }
    @Test
    public void codeCheack() {
        PastebinHomePage page = new PastebinHomePage(new ChromeDriver());
        page.openPage()
                .writingCode(code)
                .selectPasteExpiration()
                .selectSyntaxHighlighting()
                .writingPasteName(pasteName)
                .createNewPaste();
        Assert.assertEquals(page.getEnteredCode(),code);
        page.closePage();
    }*/

}
