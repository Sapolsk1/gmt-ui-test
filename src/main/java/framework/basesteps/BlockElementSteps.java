package framework.basesteps;

import com.microsoft.playwright.Page;
import framework.app.BasePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockElementSteps extends AbstractSteps {

    @Тогда("^элемент \"([^\"]*)\" блока \"([^\"]*)\" (?:загрузился|отображается)$")
    @Then("^element \"([^\"]*)\" of block \"([^\"]*)\" is (?:loaded|visible)$")
    public void appearElement(String elementName, String blockName) {
        getApp().elementIsVisible(getBlock(blockName).getElementByName(elementName));
    }

    @Тогда("^элемент \"([^\"]*)\" блока \"([^\"]*)\" (?:пропал|не отображается)$")
    @Then("^element \"([^\"]*)\" of block \"([^\"]*)\" (?:disappeared|not visible)$")
    public void disappearElement(String elementName, String blockName) {
        getApp().elementIsHidden(getBlock(blockName).getElementByName(elementName));
    }

    @Когда("^нажали на (?:элемент|кнопку) \"([^\"]*)\" блока \"([^\"]*)\"$")
    @When("^click on (?:element|button) \"([^\"]*)\" of block \"([^\"]*)\"$")
    public void clickOnElement(String elementName, String blockName) {
        getPage().locator(getBlock(blockName).getElementByName(elementName)).click();
    }

    @Когда("^ввели в (?:элемент|форму) \"([^\"]*)\" блока \"([^\"]*)\" значение \"([^\"]*)\"$")
    @When("^input in (?:element|form) \"([^\"]*)\" of block \"([^\"]*)\" value \"([^\"]*)\"$")
    public void setElementValue(String elementName, String blockName, String value) {
        getPage().fill(getBlock(blockName).getElementByName(elementName), value);
    }

    @Тогда("^значение (?:элемента|формы) \"([^\"]*)\" блока \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^value of (?:element|form) \"([^\"]*)\" of block \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementValue(String elementName, String blockName, String expectedValue) {
        Page page = getPage();
        BasePage block = getBlock(blockName);
        poll(() -> {
            String elementText = page.locator(block.getElementByName(elementName)).textContent();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^свойство \"([^\"]*)\" элемента \"([^\"]*)\" блока \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^css value \"([^\"]*)\" of element \"([^\"]*)\" of block \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementCss(String css, String elementName, String blockName, String expectedValue) {
        Page page = getPage();
        BasePage block = getBlock(blockName);
        poll(() -> {
            String elementText = page.locator(block.getElementByName(elementName))
                    .evaluate("(element) => window.getComputedStyle(element).getPropertyValue('" + css + "')").toString();
            assertEquals(expectedValue, elementText);
        });
    }

    @Тогда("^значение аттрибута \"([^\"]*)\" элемента \"([^\"]*)\" блока \"([^\"]*)\" равно \"([^\"]*)\"$")
    @Then("^attribute value \"([^\"]*)\" of element \"([^\"]*)\" of block \"([^\"]*)\" equals \"([^\"]*)\"$")
    public void checkElementAttribute(String attribute, String elementName, String blockName, String expectedValue) {
        Page page = getPage();
        BasePage block = getBlock(blockName);
        poll(() -> {
            String elementText = page.locator(block.getElementByName(elementName)).getAttribute(attribute);
            assertEquals(expectedValue, elementText);
        });
    }

}
