package framework.basesteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

public class PageLoadingSteps extends AbstractSteps {

    @Когда("^открыли страницу \"([^\"]*)\"$")
    @When("^open page \"([^\"]*)\"$")
    public void openPage(String pageName){
        app.openPage(pageName);
    }

    @Когда("^страница \"([^\"]*)\" загрузилась$")
    @When("^page \"([^\"]*)\" loaded")
    public void loadPage(String pageName){
        app.loadPage(pageName);
    }

    @Когда("^страница \"([^\"]*)\" загрузилась в состоянии \"([^\"]*)\"$")
    @When("^page \"([^\"]*)\" loaded in state \"([^\"]*)\"$")
    public void loadPageState(String pageName, String state){
        getApp().loadPageState(pageName, state);
    }

    @Когда("^блок \"([^\"]*)\" (?:загрузился|отображается)")
    @When("^block \"([^\"]*)\" (?:loaded|visible)")
    public void loadBlock(String blockName){
        app.loadBlock(blockName);
    }

    @Когда("^блок \"([^\"]*)\" (?:загрузился|отображается) в состоянии \"([^\"]*)\"")
    @When("^block \"([^\"]*)\" (?:loaded|visible) in state \"([^\"]*)\"")
    public void loadBlockState(String blockName, String state){
        getApp().loadBlockState(blockName, state);
    }

    @Тогда("^блок \"([^\"]*)\" (?:пропал|не отображается)")
    @Then("^block \"([^\"]*)\" (?:unloaded|not visible)")
    public void unloadBlock(String blockName) {
        app.unloadBlock(blockName);
    }

}
