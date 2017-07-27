package steam.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import smartframework.base.BasePage;
import smartframework.elements.Label;
import smartframework.utils.Configuration;
import smartframework.utils.FrameworkSoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GamePage extends BasePage {

    private static final By pageLocator = By.xpath("//div[@class='apphub_AppName']");
    private static final String comparableText = CategoryPage.gameWithMaxDiscount.getNameGame();

    private Label discount = new Label(By.xpath("//div[contains(@class,'purchase')]/div[@class='discount_pct']"));
    private Label originPrice = new Label(By.xpath("//div[contains(@class,'purchase')]//div[contains(@class,'original_price')]"));
    private Label finalPrice = new Label(By.xpath("//div[contains(@class,'purchase')]//div[contains(@class,'final_price')]"));
    private Label btnInstall = new Label(By.xpath("//a[contains(@class,'installsteam')]"));

    private Label gallery = new Label(By.xpath("//div[@class='highlight_ctn']"));
    private Label draggableLabel = new Label(By.xpath("//div[contains(@id,'slider')]/div[@class='handle']"));
    private Label targetLabel = new Label(By.xpath("//div[@class='slider_ctn']//div[contains(@id,'right')]"));
    private Label lastImgInGallery = new Label(By.xpath("//div[contains(@id,'strip_scroll')]/div[last()]"));
    private Label linkLastImgInGallery = new Label(By.xpath("//div[@id='highlight_player_area']/div[last()]//a"));

    public GamePage() {
        super(pageLocator, comparableText);
    }

    public void checkCorrectPriceAndDiscount() {
        FrameworkSoftAssert.softAssert.assertEquals(discount.waitForFirstElementPresent().getText(), CategoryPage.gameWithMaxDiscount.getDiscount());
        FrameworkSoftAssert.softAssert.assertEquals(originPrice.waitForFirstElementPresent().getText(), CategoryPage.gameWithMaxDiscount.getOriginPrice());
        FrameworkSoftAssert.softAssert.assertEquals(finalPrice.waitForFirstElementPresent().getText(), CategoryPage.gameWithMaxDiscount.getFinalPrice() + " USD");
        log.info("Проверка соответсвия цен пройдена");
    }

    public void clickInstallSteam() {
        btnInstall.waitForElementPresent().click();
        log.info("Нажата кнопка 'Загрузить Steam'");
    }

    public void scrollGallery() {
        try {
            gallery.waitForElementPresent();
            new Actions(driver).clickAndHold(draggableLabel.waitForElementPresent()).moveToElement(targetLabel.waitForElementPresent()).release().perform();
            lastImgInGallery.waitForElementPresent().click();
        } catch (Exception e) {
            log.info("Галерея отсутствует");
        }
    }

    /*
    Хочу отметить, что в chrome всё работает как требуется по заданию(открывается новая вкладка), а вот в ff
    скрипт почему-то открывает в новом окне. Как вариант, я читал, что ffdriver не поддерживает вкладки и всегда
    используются окна.
    */
    public void openImgNewTabAndMakeScreen() throws IOException {
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", linkLastImgInGallery.waitForElementPresent());
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window((String) tabs.get(1));
        new WebDriverWait(driver, wait).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img")));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = Configuration.resourceBundle.getString("screenshotsPath").replace('/', File.separatorChar) + screenshot.getName();
        FileUtils.copyFile(screenshot, new File(path));
        driver.close();
        driver.switchTo().window((String) tabs.get(0));
        log.info("Скриншот последний картинки из галереи сделан и сохранён в srt/test/resources как " + screenshot.getName());
    }
}