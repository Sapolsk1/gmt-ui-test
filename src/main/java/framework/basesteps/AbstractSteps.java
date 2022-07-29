package framework.basesteps;

import com.microsoft.playwright.Page;
import framework.app.App;
import framework.app.BasePage;
import framework.browser.BrowserContext;
import framework.local.objects.LocalObjects;
import framework.property.PropertyManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.opentest4j.AssertionFailedError;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;

@Getter
@Slf4j
public abstract class AbstractSteps {

    protected final App app = LocalObjects.getLocalApp().initLocal(new App());
    protected final long timeout = Long.parseLong(PropertyManager.getInstance().getProperty("playwright.timeout"));

    private final BrowserContext browserContext = BrowserContext.getInstance();

    protected final void poll(Runnable runnable) {
        await().atMost(timeout, TimeUnit.MILLISECONDS).until(() -> {
            try {
                runnable.run();
            } catch (AssertionFailedError e) {
                throw new AssertionError(e);
            }
        });
    }

    protected final Page getPage() {
        return browserContext.getWrapper().getPage();
    }

    protected final BasePage getCurrentPage() {
        return getApp().getCurrentPage();
    }

    protected final BasePage getBlock(String name) {
        return getApp().getBlock(name);
    }


}
