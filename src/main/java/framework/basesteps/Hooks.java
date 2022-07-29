package framework.basesteps;

import framework.browser.BrowserContext;
import io.cucumber.java.After;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;

@Slf4j
public class Hooks {

    private static final BrowserContext browserContext = BrowserContext.getInstance();

    @AfterAll
    public static void closePlaywright() {
        browserContext.getWrapper().close();
    }

    @After
    public void refreshContext() {
        browserContext.getWrapper().getBrowser().newContext();
        log.debug("Browser context refreshed");
    }

}
