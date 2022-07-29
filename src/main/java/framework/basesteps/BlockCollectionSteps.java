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

public class BlockCollectionSteps extends AbstractSteps {

    @Тогда("^элемент (\\d{1,2}) коллекции \"([^\"]*)\" блока \"([^\"]*)\" (?:загрузился|отображается)$")
    @Then("^element (\\d{1,2}) of collection \"([^\"]*)\" of block \"([^\"]*)\" is (?:loaded|visible)$")
    public void appearElement(int index, String elementName, String blockName) {
        getApp().elementIsVisible(getBlock(blockName).getElementsCollectionByName(elementName), index);
    }

    @Тогда("^элемент (\\d{1,2}) коллекции \"([^\"]*)\" блока \"([^\"]*)\" (?:пропал|не отображается)$")
    @Then("^element (\\d{1,2}) of collection \"([^\"]*)\" of block \"([^\"]*)\" (?:disappeared|not visible)$")
    public void disappearElement(int index, String elementName, String blockName) {
        getApp().elementIsHidden(getBlock(blockName).getElementsCollectionByName(elementName), index);
    }

    @Когда("^нажали на (?:элемент|кнопку) (\\d{1,2}) коллекции \"([^\"]*)\" блока \"([^\"]*)\"$")
    @When("^click on (?:element|button) (\\d{1,2}) of collection \"([^\"]*)\" of block \"([^\"]*)\"$")
    public void clickOnElement(int index, String elementName, String blockName) {
        getPage().locator(getBlock(blockName).getElementsCollectionByName(elementName))
                .elementHandles().get(index).click();
    }

    @Когда("^нажали на (?:элемент|кнопку) коллекции \"([^\"]*)\" со значением \"([^\"]*)\" блока \"([^\"]*)\"$")
    @When("^click on (?:element|button) of collection \"([^\"]*)\" with value \"([^\"]*)\" of block \"([^\"]*)\"$")
    public void clickOnElement(String elementName, String value, String blockName) {
        Page page = getPage();
        poll(() -> {
            page.locator(getBlock(blockName).getElementsCollectionByName(elementName)).elementHandles().stream()
                    .filter(e -> e.textContent().equals(value))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError(String.format("Element with value %s not found", value)))
                    .click();
        });
    }

    @Тогда("^значение (?:элемента|формы) (\\d{1,2}) коллекции \"([^\"]*)\" блока \"([^\"]*)\" равно \"([^\"]*)\"$")
    @When("^value of (?:element|form) (\\d{1,2}) of collection \"([^\"]*)\" of block  \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementValue(int index, String elementName, String blockName, String expectedValue) {
        Page page = getPage();
        BasePage block = getBlock(blockName);
        poll(() -> {
            String elementText = page.locator(block.getElementsCollectionByName(elementName))
                    .elementHandles().get(index).textContent();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^свойство \"([^\"]*)\" элемента (\\d{1,2}) коллекции \"([^\"]*)\" блока \"([^\"]*)\" равно \"([^\"]*)\"$")
    @When("^css value \"([^\"]*)\" of element (\\d{1,2}) of collection \"([^\"]*)\" of block \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementCss(String css, int index, String elementName, String blockName, String expectedValue) {
        Page page = getPage();
        BasePage block = getBlock(blockName);
        poll(() -> {
            String elementText = page.locator(block.getElementsCollectionByName(elementName)).elementHandles().get(index)
                    .evaluate("(element) => window.getComputedStyle(element).getPropertyValue('" + css + "')").toString();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^значение аттрибута \"([^\"]*)\" элемента (\\d{1,2}) коллекции \"([^\"]*)\" блока \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Тогда("^attribute value \"([^\"]*)\" of element (\\d{1,2}) of collection \"([^\"]*)\" of block \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementAttribute(String attribute, int index, String elementName, String blockName, String expectedValue) {
        Page page = getPage();
        BasePage block = getBlock(blockName);
        poll(() -> {
            String elementText = page.locator(block.getElementsCollectionByName(elementName))
                    .elementHandles().get(index).getAttribute(attribute);
            assertEquals(expectedValue, elementText);
        });
    }

}
