package framework.basesteps;

import com.microsoft.playwright.Page;
import framework.app.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageElementSteps extends AbstractSteps {

    @Тогда("^элемент \"([^\"]*)\" (?:загрузился|отображается)$")
    @Then("^element \"([^\"]*)\" is (?:loaded|visible)$")
    public void appearElement(String elementName) {
        getApp().elementIsVisible(getCurrentPage().getElementByName(elementName));
    }

    @Тогда("^элемент \"([^\"]*)\" (?:пропал|не отображается)$")
    @Then("^element \"([^\"]*)\" (?:disappeared|not visible)$")
    public void disappearElement(String elementName) {
        getApp().elementIsHidden(getCurrentPage().getElementByName(elementName));
    }

    @Когда("^нажали на (?:элемент|кнопку) \"([^\"]*)\"$")
    @When("^click on (?:element|button) \"([^\"]*)\"$")
    public void clickOnElement(String elementName) {
        getPage().locator(getCurrentPage().getElementByName(elementName)).click();
    }

    @Когда("^ввели в (?:элемент|форму) \"([^\"]*)\" значение \"([^\"]*)\"$")
    @When("^input in (?:element|form) \"([^\"]*)\" value \"([^\"]*)\"$")
    public void setElementValue(String elementName, String value) {
        getPage().fill(getCurrentPage().getElementByName(elementName), value);
    }

    @Тогда("^значение (?:элемента|формы) \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^value of (?:element|form) \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementValue(String elementName, String expectedValue) {
        Page page = getPage();
        BasePage currentPage = getCurrentPage();
        poll(() -> {
            String elementText = page.locator(currentPage.getElementByName(elementName)).textContent();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^свойство \"([^\"]*)\" элемента \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^css value \"([^\"]*)\" element \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementCss(String css, String elementName, String expectedValue) {
        Page page = getPage();
        BasePage currentPage = getCurrentPage();
        poll(() -> {
            String elementText = page.locator(currentPage.getElementByName(elementName))
                    .evaluate("(element) => window.getComputedStyle(element).getPropertyValue('" + css + "')").toString();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^значение аттрибута \"([^\"]*)\" элемента \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^attribute value \"([^\"]*)\" element \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementAttribute(String attribute, String elementName, String expectedValue) {
        Page page = getPage();
        BasePage currentPage = getCurrentPage();
        poll(() -> {
            String elementText = page.locator(currentPage.getElementByName(elementName)).getAttribute(attribute);
            assertEquals(expectedValue, elementText);
        });
    }

}
