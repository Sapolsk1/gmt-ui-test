package framework.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import framework.property.PropertyManager;

public class BrowserFactory {

    private final LaunchOptionsFactory launchOptionsFactory = LaunchOptionsFactory.getInstance();
    private static final BrowserFactory INSTANCE = new BrowserFactory();
    private final PropertyManager propertyManager = PropertyManager.getInstance();

    private BrowserFactory() {}

    public static BrowserFactory getInstance() {
        return INSTANCE;
    }

    public Browser createBrowser(Playwright playwright) {
        String property = propertyManager.getProperty("playwright.browser");
        switch (property) {
            case ("chromium"):
                return playwright.chromium().launch(launchOptionsFactory.createLaunchOptions());
            case ("firefox"):
                return playwright.firefox().launch(launchOptionsFactory.createLaunchOptions());
            case ("webkit"):
                return playwright.webkit().launch(launchOptionsFactory.createLaunchOptions());
            default:
                throw new RuntimeException("Unknown browser engine " + property);
        }
    }

}
