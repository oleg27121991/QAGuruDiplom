package by.veremei.ui.config.web;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({
        "classpath:config/${env}.properties",
        "classpath:config/config.properties"
})
public interface WebConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://bezkassira.by/")
    String baseUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    @Key("browserVersion")
    @DefaultValue("118.0")
    String browserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remoteUrl")
    @DefaultValue("https://user1:1234@selenoid.autotests.cloud/wd/hub")
    URL remoteUrl();

    @Key("buyerName")
    String buyerName();

    @Key("buyerPass")
    String buyerPass();
}