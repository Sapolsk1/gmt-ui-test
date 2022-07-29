package pages;

import framework.app.BasePage;
import framework.app.annotations.Name;
import framework.app.annotations.URL;
import lombok.Getter;

@Name("Login")
@URL("/login")
@Getter
public class LoginPage extends BasePage {

    @Name("Login header")
    private final String loginHeader = "//h1/span[contains(text(), 'Log in')]";
    @Name("Email header")
    private final String emailHeader = "label[for='email']";
    @Name("Email form")
    private final String emailForm = "input[id='email']";
    @Name("Password header")
    private final String passwordHeader = "label[for='password']";
    @Name("Password form")
    private final String passwordForm = "input[id='password']";
    @Name("Login")
    private final String loginButton = "button[data-action='submit']";
    @Name("Sign up link")
    private final String loginLink = "//div[@auth-layout--content]//a[@routerlink='/register']";
    @Name("Forgot password link")
    private final String restoreLink = "a[routerlink='/restore']";
    @Name("Show password")
    private final String showPasswordButton = "button[tabindex='-1']";

}
