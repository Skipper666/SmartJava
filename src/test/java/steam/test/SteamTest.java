package steam.test;

import org.testng.annotations.Test;
import smartframework.base.BaseTest;
import smartframework.steps.AgeVerificationSteps;
import smartframework.utils.Configuration;
import smartframework.utils.FrameworkSoftAssert;
import steam.pages.CategoryPage;
import steam.pages.DownloadPage;
import steam.pages.GamePage;
import steam.pages.WelcomePage;

import java.io.IOException;


public class SteamTest extends BaseTest {

    @Test
    public void testSteam() throws IOException {

        logger.logStep(1, "Go to steam pages");
        WelcomePage welcomePage = new WelcomePage();

        logger.logStep(2, "Go to item menu");
        welcomePage.mainMenu.navigateMenu(Configuration.resourceBundle.getString("menuItem"), Configuration.resourceBundle.getString("item"));

        logger.logStep(3, "Go to tab Specials");
        CategoryPage categoryPage = new CategoryPage();
        categoryPage.clickDiscounts();
        logger.logStep(4, "Select game with max discount");
        categoryPage.selectGameWithMaxDiscount();
        AgeVerificationSteps ageVerification = new AgeVerificationSteps();
        ageVerification.checkAgeLimit();

        logger.logStep(5, "Check Correct Price And Discount");
        GamePage gamePage = new GamePage();
        gamePage.checkCorrectPriceAndDiscount();

        logger.logStep(6, "Scrolling Gallery and make screenshot last image in new tab");
        gamePage.scrollGallery();
        gamePage.openImgNewTabAndMakeScreen();

        logger.logStep(7, "Go to install Steam pages");
        gamePage.clickInstallSteam();

        logger.logStep(8, "Download setup file");
        DownloadPage downloadPage = new DownloadPage();
        downloadPage.download();

        logger.logStep(9, "End script");

        FrameworkSoftAssert.softAssert.assertAll();
    }
}
