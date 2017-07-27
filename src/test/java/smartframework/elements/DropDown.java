package smartframework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import smartframework.base.BaseElement;


public class DropDown extends BaseElement {

    private By dropDownLocator = null;
    public static Select select;

    public DropDown(By dropDownLocator) {
        super(dropDownLocator);
        this.dropDownLocator = dropDownLocator;
        select = new Select(driver.findElement(dropDownLocator));
    }
}
