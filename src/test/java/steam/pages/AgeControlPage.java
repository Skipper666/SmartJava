package steam.pages;

import org.openqa.selenium.By;
import smartframework.base.BasePage;
import smartframework.elements.Label;


public class AgeControlPage extends BasePage {

    private static final By pageLocator = By.xpath("//div[@id='app_agegate']");
    private Label btnSubmit = new Label(By.xpath("//a[@href='#']/span"));

    public AgeControlPage() {
        super(pageLocator);
    }

    public void verificationAge() {
        btnSubmit.click();
        log.info("Верификация пройдена");
    }
}
