package framework.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class PlaywrightWrapper {

    private final BrowserFactory browserFactory = BrowserFactory.getInstance();
    private Playwright playwright;
    private Browser browser;
    private Page page;

    public PlaywrightWrapper() {
        init();
    }

    public void close() {
        page.close();
        log.debug("Page closed");
        browser.close();
        log.debug("Browser closed");
        playwright.close();
        log.debug("Playwright closed");
    }

    private void init() {
        playwright = Playwright.create();
        log.debug("Playwright created");
        browser = browserFactory.createBrowser(playwright);
        log.debug("Browser created");
        page = browser.newPage();
        log.debug("Page created");
    }

}
