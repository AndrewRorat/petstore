package core.util.config;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

import static core.constants.GlobalVariables.RUN_PROPERTIES_FILE_NAME;
import static java.lang.System.getProperty;

@UtilityClass
public class RunConfig {

    private static final ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle(RUN_PROPERTIES_FILE_NAME);
    }

    public static String getEnvironment() {
        return get("environment");
    }

    public static String getRetries() {
        return get("retry");
    }



    private static String get(String property) {
        var propertyValue = getProperty(property, bundle.getString(property));

        if (propertyValue == null || property.isEmpty()) {
            throw new AssertionError("Either system property or configuration file should be set for " + property);
        }

        return propertyValue;
    }
}
