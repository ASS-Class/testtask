package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * app.properties
 */
public abstract class PropertyLoader {

    private static final String PROPERTY_PATH = "src/test/resources/app.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (FileInputStream fs = new FileInputStream(PROPERTY_PATH);
             InputStreamReader is = new InputStreamReader(fs, "UTF-8")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadProperty(final String key) {
        return properties.getProperty(key);
    }
}
