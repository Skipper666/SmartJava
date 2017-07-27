package smartframework.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    private static BrowserFactory instance;
    private static Map<String, WebDriver> drivers = new HashMap<>();
    private WebDriver driver;
    final static Logger log = Logger.getLogger(BrowserFactory.class);

    private BrowserFactory() {
    }

    public static synchronized BrowserFactory getInstance() {
        if (instance == null) {
            instance = new BrowserFactory();
        }
        return instance;
    }

    public synchronized WebDriver getDriver(String browserName) {
        WebDriver driver = null;
        Configuration configuration = Configuration.getInstance();
        String downloadFilepath = System.getProperty("user.dir") + configuration.resourceBundle.getString("pathFolderDownloadBrowser").replace('/', File.separatorChar);
        switch (browserName) {
            case "Chrome":
                driver = drivers.get(configuration.getNameChromeBrowser());
                if (driver == null) {
                    System.setProperty(configuration.getKeyDriverChrome(), configuration.getPathChromeDriver());
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("profile.default_content_settings.popups", 0);
                    prefs.put("download.default_directory", downloadFilepath);
                    prefs.put("safebrowsing.enabled", "true");
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("start-maximized");
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    drivers.put(configuration.getNameChromeBrowser(), driver);
                }
                break;
            case "Firefox":
                driver = drivers.get(configuration.getNameFirefoxBrowser());
                if (driver == null) {
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference("browser.download.folderList", 2);
                    profile.setPreference("browser.download.manager.showWhenStarting", false);
                    profile.setPreference("browser.download.dir", downloadFilepath);
                    profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                    driver = new FirefoxDriver(profile);
                    driver.manage().window().maximize();
                    drivers.put(configuration.getNameFirefoxBrowser(), driver);
                }
                break;
            default:
                log.info("Выбранный браузер не поддерживается!:(");
                System.exit(0);
        }
        this.driver = driver;
        return driver;
    }

    public synchronized WebDriver getDriver() {
        return this.driver;
    }

    public synchronized void closeAllDriver() {
        for (String key : drivers.keySet()) {
            drivers.get(key).quit();
        }
    }
}


