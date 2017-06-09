package by.tc.like_it.util.lacalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalResourceManager {
    private static final String LOCAL_BUNDLE_NAME = "localization";

    private static final LocalResourceManager instance = new LocalResourceManager();

    private LocalResourceManager() {
    }

    public static LocalResourceManager getInstance() {
        return instance;
    }
    public String getValue(String key, Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, locale);
        return resourceBundle.getString(key);
    }
}
