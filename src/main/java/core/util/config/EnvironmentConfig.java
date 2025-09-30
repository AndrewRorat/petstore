package core.util.config;

import lombok.experimental.UtilityClass;

import java.util.ResourceBundle;

import static core.util.config.RunConfig.getEnvironment;
import static java.lang.System.getProperty;

@UtilityClass
public class EnvironmentConfig {
    private static final String ENV_PROPERTIES_FILE_PREFORMATTED = "env/%s";

    private static final ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle(String.format(ENV_PROPERTIES_FILE_PREFORMATTED, getEnvironment()));
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static boolean isLoggingEnabled() {
        return Boolean.parseBoolean(get("log.enabled"));
    }

    private static String get(String property) {
        var propertyValue = getProperty(property, bundle.getString(property));

        if (propertyValue == null || property.isEmpty()) {
            throw new AssertionError("Either system property or configuration file should be set for " + property);
        }

        return propertyValue;
    }
}
