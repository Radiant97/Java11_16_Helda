package by.tc.like_it.dao.pool.manager;

import java.util.ResourceBundle;

public class DBResourceManager {
    private static final String DB_BUNDLE_NAME = "db";

    private static final DBResourceManager instance = new DBResourceManager();

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(DB_BUNDLE_NAME);

    private DBResourceManager() {
    }

    public static DBResourceManager getInstance() {
        return instance;
    }
    public String getValue(String key) {
        return resourceBundle.getString(key);
    }
}
