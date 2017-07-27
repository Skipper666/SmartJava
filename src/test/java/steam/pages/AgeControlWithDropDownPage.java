package steam.pages;

import org.openqa.selenium.By;
import smartframework.base.BasePage;
import smartframework.elements.DropDown;
import smartframework.elements.Label;
import smartframework.utils.Configuration;


public class AgeControlWithDropDownPage extends BasePage {

    private static final By pageLocator = By.xpath("//form[@id='agecheck_form']");
    private DropDown dropDown = new DropDown(By.xpath("//select[@id='ageYear']"));
    private Label btnSubmit = new Label(By.xpath("//form/a"));

    public AgeControlWithDropDownPage() {
        super(pageLocator);
    }

    public void verificationAge() {
        DropDown.select.selectByValue(Configuration.resourceBundle.getString("validAge"));
        btnSubmit.click();
        log.info("Верификация пройдена");
    }
}
