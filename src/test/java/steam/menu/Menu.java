package steam.menu;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import smartframework.elements.Label;
import smartframework.elements.TextBox;
import smartframework.utils.BrowserFactory;
import smartframework.utils.Configuration;


public class Menu {

    final static Logger log = Logger.getLogger(Menu.class);

    private static final String MENU_ITEM_TEMPLATE = "//span[@class='pulldown']//a[contains(@class,'desktop') and text()='%s']/../..";
    private static final String MENU_SPECIAL_ITEM_TEMPLATE = "//span[@class='pulldown' and contains(text(),'%s')]/..";
    private static final String TITLE_ITEM_TEMPLATE = "//div[@id='%s']//div//a[contains(text(),'%s')]";
    private static final String MENU_ONE_ITEM_TEMPLATE = "//a[contains(@class,'tab')]//span[contains(text(),'%s')]/..";
    private TextBox searchInput = new TextBox(By.xpath("//div[@class='searchbox']//input"));

    public void navigateMenu(String menuItem, String item) {
        Label itemMenu;
        if (menuItem.equals(Configuration.resourceBundle.getString("forYou"))) {
            itemMenu = new Label(By.xpath(String.format(MENU_SPECIAL_ITEM_TEMPLATE, menuItem)));
        } else {
            itemMenu = new Label(By.xpath(String.format(MENU_ITEM_TEMPLATE, menuItem)));
        }
        String attrDataFlyout = itemMenu.getAttribute("data-flyout");
        Label titleItem = new Label(By.xpath(String.format(TITLE_ITEM_TEMPLATE, attrDataFlyout, item)));
        new Actions(BrowserFactory.getInstance().getDriver()).moveToElement(itemMenu.waitForElementPresent()).build().perform();
        titleItem.click();
        log.info("Выбран пункт меню '" + menuItem + "' и подпункт '" + item + "'");
    }

    public void navigateMenu(String menuItem) {
        Label itemMenu = new Label(By.xpath(String.format(MENU_ONE_ITEM_TEMPLATE, menuItem)));
        itemMenu.click();
        log.info("Выбран пункт меню '" + menuItem + "'");
    }

    public void search(String searchInformation) {
        searchInput.sendKeys(searchInformation);
        searchInput.submit();
        log.info("Поиск в меню по критерию " + searchInformation);
    }
}
