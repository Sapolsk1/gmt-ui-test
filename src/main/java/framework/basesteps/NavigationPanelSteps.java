package framework.basesteps;

import com.microsoft.playwright.Page;
import framework.browser.BrowserContext;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Когда;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationPanelSteps extends AbstractSteps {

    @Когда("^нажали на панели брайзера \"Назад\"$")
    @When("^click \"Backward\" on browser panel$")
    public void back() {
        BrowserContext.getInstance().getWrapper().getPage().goBack();
    }

    @Когда("^нажали на панели брайзера \"Вперед\"$")
    @When("^click \"Вперед\" on browser panel$")
    public void forward() {
        BrowserContext.getInstance().getWrapper().getPage().goForward();
    }

    @Когда("^выполнили обновление страницы$")
    @When("^refresh page$")
    public void refresh() {
        BrowserContext.getInstance().getWrapper().getPage().reload();
    }

    @Когда("^выполнен переход на url \"([^\"]*)\"$")
    @When("^redirected to url \"([^\"]*)\"$")
    public void redirect(String expectedUrl) {
        Page page = BrowserContext.getInstance().getWrapper().getPage();
        poll(() -> assertTrue(page.url().contains(expectedUrl)));
    }

}
