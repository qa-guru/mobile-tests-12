package drivers;

@org.aeonbits.owner.Config.Sources({"classpath:${env}.properties"})

public interface Config extends org.aeonbits.owner.Config {
    @Key("browserstack.user")
    String getBrowserstackUser();

    @Key("browserstack.key")
    String getBrowserstackKey();

    @Key("appUrl")
    String getAppUrl();

    @Key("deviceName")
    String getDeviceName();

    @Key("os_version")
    String getOsVersion();

    @Key("project")
    String getProject();

    @Key("build")
    String getBuild();

    @Key("name")
    String getName();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("platformName")
    String getPlatformName();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();

    @Key("Url")
    String getUrl();

}
