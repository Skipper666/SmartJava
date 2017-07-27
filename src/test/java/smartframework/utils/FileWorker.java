package smartframework.utils;

import java.io.File;

public class FileWorker {

    private static final String FILENAME = Configuration.resourceBundle.getString("steamInstall").replace('/', File.separatorChar);

    public static boolean checkExistFile() {
        final File file = new File(FILENAME);
        if (file.exists() && file.length() > 0) {
            return true;
        }
        return false;
    }
}
