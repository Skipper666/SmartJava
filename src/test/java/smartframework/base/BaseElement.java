package smartframework.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import smartframework.utils.BrowserFactory;
import smartframework.utils.Configuration;

import java.util.List;


public abstract class BaseElement {

    private By labelLocator;
    public WebDriver driver = BrowserFactory.getInstance().getDriver();
    public JavascriptExecutor executor = (JavascriptExecutor) driver;
    private int wait = Integer.parseInt(Configuration.resourceBundle.getString("waitForElement"));

    public BaseElement(By labelLocator) {
        this.labelLocator = labelLocator;
    }

    public WebElement waitForElementPresent() {
        WebElement element = (new WebDriverWait(driver, wait)).ignoring(StaleElementReferenceException.class)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        if (webDriver.findElements(labelLocator).size() == 1) {
                            if (webDriver.findElement(labelLocator).isDisplayed()) {
                                return webDriver.findElement(labelLocator);
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }
                });
        return element;
    }

    public WebElement waitForFirstElementPresent() {
        WebElement element = (new WebDriverWait(driver, wait))
                .until(new ExpectedCondition<WebElement>() {
                           @Override
                           public WebElement apply(WebDriver webDriver) {
                               if (webDriver.findElement(labelLocator).isDisplayed()) {
                                   return webDriver.findElement(labelLocator);
                               } else {
                                   return null;
                               }
                           }
                       }
                );
        return element;
    }

    public List<WebElement> waitForElementsPresent() {
        List<WebElement> element = (new WebDriverWait(driver, wait))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(labelLocator));
        return element;
    }

    public void click() {
        waitForElementPresent().click();
    }

    public void jsClick() {
        String xpath = labelLocator.toString().substring(labelLocator.toString().indexOf(':') + 1).trim();
        String jsScript = String.format("document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();", xpath);
        executor.executeScript(jsScript);
    }

    public String getAttribute(String attr) {
        return waitForElementPresent().getAttribute(attr);
    }

    public static WebElement searchInnerElement(WebElement baseElement, By xpathSearchingElement) {
        return baseElement.findElement(xpathSearchingElement);
    }
}
