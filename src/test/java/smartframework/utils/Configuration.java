package smartframework.utils;

import java.io.File;
import java.util.ResourceBundle;


public class Configuration {

    private static Configuration instance = null;

    private String baseUrl;
    private String pathChromeDriver;
    private String keyDriverChrome;
    private String nameChromeBrowser;
    private String nameFirefoxBrowser;
    private String browser;

    public static ResourceBundle resourceBundle;

    private Configuration() {
        resourceBundle = ResourceBundle.getBundle("content");
        try {
            this.baseUrl = resourceBundle.getString("baseUrl");
            this.pathChromeDriver = resourceBundle.getString("pathChromeDriver").replace('/', File.separatorChar);
            this.keyDriverChrome = resourceBundle.getString("keyDriverChrome");
            this.browser = resourceBundle.getString("browser");
            this.nameChromeBrowser = resourceBundle.getString("browserChromeName");
            this.nameFirefoxBrowser = resourceBundle.getString("browserFirefoxName");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getPathChromeDriver() {
        return pathChromeDriver;
    }


    public String getKeyDriverChrome() {
        return keyDriverChrome;
    }

    public String getBrowser() {
        return browser;
    }

    public String getNameChromeBrowser() {
        return nameChromeBrowser;
    }

    public String getNameFirefoxBrowser() {
        return nameFirefoxBrowser;
    }

}
