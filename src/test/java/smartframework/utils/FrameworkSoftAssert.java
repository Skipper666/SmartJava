package smartframework.utils;

import org.testng.asserts.SoftAssert;


public class FrameworkSoftAssert {

    private static FrameworkSoftAssert instance;
    public static SoftAssert softAssert;

    private FrameworkSoftAssert() {
    }

    public static synchronized FrameworkSoftAssert getInstance() {
        if (instance == null) {
            instance = new FrameworkSoftAssert();
            softAssert = new SoftAssert();
        }
        return instance;
    }
}
