package framework.app;

import com.microsoft.playwright.Page;
import framework.browser.BrowserContext;
import framework.property.PropertyManager;
import lombok.Getter;
import lombok.Setter;
import org.opentest4j.AssertionFailedError;

import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Emulate application class
 */

public final class App {

    private final Context context = Context.getInstance();
    private final BrowserContext browserContext = BrowserContext.getInstance();
    private final PropertyManager propertyManager = PropertyManager.getInstance();
    private final long timeout = Long.parseLong(PropertyManager.getInstance().getProperty("playwright.timeout"));
    @Getter
    @Setter
    private BasePage currentPage;

    public void openPage(String name) {
        validatePage(name);
        browserContext.getWrapper().getPage()
                .navigate(propertyManager.getProperty("playwright.baseUrl") + context.getPages().get(name).getUrl());
        checkPrimaryElements(name);
    }

    public void loadPage(String name) {
        validatePage(name);
        checkPrimaryElements(name);
    }

    public void loadPageState(String name, String state) {
        loadPage(name);
        checkState(state, currentPage);
    }

    public BasePage getBlock(String name) {
        return context.getBlocks().get(name);
    }

    public void loadBlock(String name) {
        validateBlock(name);
        context.getBlocks().get(name).getPrimaryElements().forEach(this::elementIsVisible);
    }

    public void loadBlockState(String name, String state) {
        loadBlock(name);
        checkState(state, context.getBlocks().get(name));
    }

    public void unloadBlock(String name) {
        validateBlock(name);
        context.getBlocks().get(name).getPrimaryElements().forEach(this::elementIsHidden);
    }

    public void elementIsVisible(String locator) {
        Page page = getPage();
        wait(() -> assertTrue(page.locator(locator).isVisible()));
    }

    public void elementIsVisible(String locator, int index) {
        Page page = getPage();
        wait(() -> assertTrue(page.locator(locator).elementHandles().get(index).isVisible()));
    }

    public void elementIsHidden(String locator) {
        Page page = getPage();
        wait(() -> assertTrue(page.locator(locator).isHidden()));
    }

    public void elementIsHidden(String locator, int index) {
        Page page = getPage();
        wait(() -> assertTrue(page.locator(locator).elementHandles().get(index).isHidden()));
    }

    private void checkPrimaryElements(String name) {
        setCurrentPage(context.getPages().get(name));
        currentPage.getPrimaryElements().forEach(this::elementIsVisible);
    }

    private void checkState(String state, BasePage page) {
        if (!page.getStateElements().containsKey(state)) {
            throw new RuntimeException("State " + state + " not found");
        }
        page.getStateElements().get(state).forEach(this::elementIsVisible);
    }

    private void validatePage(String name) {
        if (!context.getPages().containsKey(name)) {
            throw new AssertionError("Page " + name + " not found.");
        }
    }

    private void validateBlock(String name) {
        if (!context.getBlocks().containsKey(name)) {
            throw new AssertionError("Block " + name + " not found.");
        }
    }

    private Page getPage() {
        return browserContext.getWrapper().getPage();
    }

    private void wait(Runnable runnable) {
        await().atMost(timeout, TimeUnit.MILLISECONDS).until(runnable);
    }

}
