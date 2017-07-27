package smartframework.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import smartframework.utils.BrowserFactory;
import smartframework.utils.Configuration;
import smartframework.utils.FrameworkLogger;
import smartframework.utils.FrameworkSoftAssert;

import java.io.File;


public class BaseTest {

    public WebDriver driver;
    private Configuration configuration;
    public FrameworkLogger logger;
    private FrameworkSoftAssert frameworkSoftAssert;

    @BeforeClass
    public void setUp() {
        logger = FrameworkLogger.getInstance();
        frameworkSoftAssert = FrameworkSoftAssert.getInstance();
        configuration = Configuration.getInstance();
        String baseUrl = configuration.getBaseUrl();
        String browser = configuration.getBrowser();

        File file = new File(Configuration.resourceBundle.getString("steamInstall").replace('/', File.separatorChar));
        if (file.exists()) {
            file.delete();
        }
        driver = BrowserFactory.getInstance().getDriver(browser);
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.getInstance().closeAllDriver();
    }
}
