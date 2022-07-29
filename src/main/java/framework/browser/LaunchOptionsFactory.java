package framework.browser;

import com.microsoft.playwright.BrowserType;
import framework.property.PropertyManager;

public class LaunchOptionsFactory {

    private static final LaunchOptionsFactory INSTANCE = new LaunchOptionsFactory();
    private final PropertyManager propertyManager = PropertyManager.getInstance();

    private LaunchOptionsFactory(){}

    public static LaunchOptionsFactory getInstance() {
        return INSTANCE;
    }

    public BrowserType.LaunchOptions createLaunchOptions() {
        var launchOptions = new BrowserType.LaunchOptions();
        double timeout = Double.parseDouble(propertyManager.getProperty("playwright.timeout"));
        launchOptions.setTimeout(timeout);
        boolean headless = Boolean.parseBoolean(propertyManager.getProperty("playwright.headless"));
        launchOptions.setHeadless(headless);
        double slowMo = Double.parseDouble(propertyManager.getProperty("playwright.slowMo"));
        launchOptions.setSlowMo(slowMo);
        return launchOptions;
    }

}
