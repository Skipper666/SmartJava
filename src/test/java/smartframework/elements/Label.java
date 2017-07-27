package smartframework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import smartframework.base.BaseElement;


public class Label extends BaseElement {

    public Label(By xpath) {
        super(xpath);
    }

    public String getText() {
        WebElement text = waitForElementPresent();
        return text.getText();
    }
}
