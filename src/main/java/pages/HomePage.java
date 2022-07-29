package pages;

import framework.app.BasePage;
import framework.app.annotations.Name;
import framework.app.annotations.URL;

@Name("Home")
@URL("/en")
public class HomePage extends BasePage {

    @Name("Main header")
    private final String mainHeader = "//h1[contains(text(), 'Bitcoin mining made simple')]";

}
