package steam.pages;

import org.openqa.selenium.By;
import smartframework.base.BasePage;
import smartframework.elements.Label;
import smartframework.utils.Configuration;
import steam.menu.Menu;

import java.util.Locale;
import java.util.ResourceBundle;


public class WelcomePage extends BasePage {

    private static final By pageLocator = By.xpath("//div[@class='home_page_gutter']");
    private Label btnLanguage = new Label(By.xpath("//span[@id='language_pulldown']"));
    public Menu mainMenu;

    public WelcomePage() {
        super(pageLocator);
        String lang = Configuration.resourceBundle.getString("language");
        if (btnLanguage.getText().equals(lang)) {
            Configuration.getInstance().resourceBundle = ResourceBundle.getBundle("content", new Locale("en", "US"));
            log.info("Установлен англ язык");
        } else {
            Configuration.getInstance().resourceBundle = ResourceBundle.getBundle("content", new Locale("ru", "RU"));
            log.info("Установлен русский язык");
        mainMenu = new Menu();
        }
    }
}
