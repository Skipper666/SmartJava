package smartframework.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import smartframework.utils.BrowserFactory;
import smartframework.utils.Configuration;
import steam.menu.Menu;


public abstract class BasePage {

    public WebDriver driver;
    public int wait = Integer.parseInt(Configuration.resourceBundle.getString("waitForElement"));
    public static final By pageNoMenuLocator = By.xpath("//div[contains(@id,'greeting')]//a[@id]");
    public final static Logger log = Logger.getLogger(BasePage.class);


    public BasePage(By pageLocator) {
        driver = BrowserFactory.getInstance().getDriver();

        init(pageLocator);
    }

    public BasePage(By pageLocator, String comparableText) {
        driver = BrowserFactory.getInstance().getDriver();
        init(pageLocator, comparableText);
    }

    private void init(By pageLocator, String comparableText) {
        init(pageLocator);
        Assert.assertTrue(new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOfElementLocated(pageLocator)).getText().equals(comparableText));
    }

    private void init(By pageLocator) {
        Assert.assertTrue(new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pageLocator)).size() == 1);
    }
}
