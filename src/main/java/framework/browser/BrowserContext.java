package framework.browser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BrowserContext {

    private final Map<String, PlaywrightWrapper> browsers = new ConcurrentHashMap<>();
    private static final BrowserContext INSTANCE = new BrowserContext();

    private BrowserContext(){}

    public static BrowserContext getInstance() {
        return INSTANCE;
    }

    public PlaywrightWrapper getWrapper() {
        if (browsers.containsKey(getThreadName())) {
            return browsers.get(getThreadName());
        }
        browsers.put(getThreadName(), new PlaywrightWrapper());
        return browsers.get(getThreadName());
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

}
