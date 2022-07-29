package framework.basesteps;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import framework.app.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageCollectionSteps extends AbstractSteps {

    @Тогда("^элемент (\\d{1,2}) коллекции \"([^\"]*)\" (?:загрузился|отображается)$")
    @Then("^element (\\d{1,2}) of collection \"([^\"]*)\" is (?:loaded|visible)$")
    public void appearElement(int index, String elementName) {
        getApp().elementIsVisible(getCurrentPage().getElementsCollectionByName(elementName), index);
    }

    @Тогда("^элемент (\\d{1,2}) коллекции \"([^\"]*)\" (?:пропал|не отображается)$")
    @Then("^element (\\d{1,2}) of collection \"([^\"]*)\" (?:disappeared|not visible)$")
    public void disappearElement(int index, String elementName) {
        getApp().elementIsHidden(getCurrentPage().getElementsCollectionByName(elementName), index);
    }

    @Когда("^нажали на (?:элемент|кнопку) (\\d{1,2}) коллекции \"([^\"]*)\"$")
    @When("^click on (?:element|button) (\\d{1,2}) of collection \"([^\"]*)\"$")
    public void clickOnElement(int index, String elementName) {
        getPage().locator(getCurrentPage().getElementsCollectionByName(elementName))
                .elementHandles().get(index).click();
    }

    @Когда("^нажали на (?:элемент|кнопку) коллекции \"([^\"]*)\" со значением \"([^\"]*)\"$")
    @When("^click on (?:element|button) of collection \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void clickOnElement(String elementName, String value) {
        List<ElementHandle> elements = getPage().locator(getCurrentPage().getElementsCollectionByName(elementName))
                .elementHandles();
        for (ElementHandle element : elements) {
            if (element.textContent().equals(value)) {
                element.click();
                break;
            }
            throw new AssertionError(String.format("Element with value %s not found", value));
        }
    }

    @Тогда("^значение (?:элемента|формы) (\\d{1,2}) коллекции \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^value of (?:element|form) (\\d{1,2}) of collection \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementValue(int index, String elementName, String expectedValue) {
        Page page = getPage();
        BasePage currentPage = getCurrentPage();
        poll(() -> {
            String elementText = page.locator(currentPage.getElementsCollectionByName(elementName))
                    .elementHandles().get(index).textContent();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^свойство \"([^\"]*)\" элемента (\\d{1,2}) коллекции \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^css value \"([^\"]*)\" of element (\\d{1,2}) of collection \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementCss(String css, int index, String elementName, String expectedValue) {
        Page page = getPage();
        BasePage currentPage = getCurrentPage();
        poll(() -> {
            String elementText = page.locator(currentPage.getElementsCollectionByName(elementName)).elementHandles().get(index)
                    .evaluate("(element) => window.getComputedStyle(element).getPropertyValue('" + css + "')").toString();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^значение аттрибута \"([^\"]*)\" элемента (\\d{1,2}) коллекции \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^attribute value \"([^\"]*)\" of element (\\d{1,2}) of collection \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementAttribute(String attribute, int index, String elementName, String expectedValue) {
        Page page = getPage();
        BasePage currentPage = getCurrentPage();
        poll(() -> {
            String elementText = page.locator(currentPage.getElementsCollectionByName(elementName))
                    .elementHandles().get(index).getAttribute(attribute);
            assertEquals(expectedValue, elementText);
        });
    }

}
