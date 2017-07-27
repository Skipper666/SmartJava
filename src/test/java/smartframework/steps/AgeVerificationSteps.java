package smartframework.steps;

import org.apache.log4j.Logger;
import steam.pages.AgeControlPage;
import steam.pages.AgeControlWithDropDownPage;


public class AgeVerificationSteps {

    private AgeControlWithDropDownPage ageControlWithDropDownPage;
    private AgeControlPage ageControlPage;
    final static Logger log = Logger.getLogger(AgeVerificationSteps.class);

    public void checkAgeLimit() {
        if (createAgeControlWithDropDownPage()) {
            ageControlWithDropDownPage.verificationAge();
        } else if (ageControlPage()) {
            ageControlPage.verificationAge();
        } else {
            log.info("Игра не зависит от возраста");
        }
    }

    private boolean createAgeControlWithDropDownPage() {
        try {
            ageControlWithDropDownPage = new AgeControlWithDropDownPage();
            log.info("Создание AgeControlWithDropDownPage");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean ageControlPage() {
        try {
            ageControlPage = new AgeControlPage();
            log.info("Создание AgeControlPage");
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
