package by.veremei.ui.config;

import org.aeonbits.owner.ConfigFactory;
import by.veremei.ui.config.web.WebConfig;

public enum ConfigReader {
    Instance;

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

    public WebConfig read() {
        return webConfig;
    }
}
