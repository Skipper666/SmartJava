package smartframework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import smartframework.base.BaseElement;


public class TextBox extends BaseElement {

    private WebElement textBox;

    public TextBox(By labelLocator) {
        super(labelLocator);
        textBox = super.waitForElementPresent();
    }

    public void sendKeys(String searchInformation) {
        textBox.sendKeys(searchInformation);
    }

    public void submit() {
        textBox.submit();
    }
}
