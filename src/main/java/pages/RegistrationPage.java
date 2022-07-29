package pages;

import framework.app.BasePage;
import framework.app.annotations.Name;
import framework.app.annotations.Optional;
import framework.app.annotations.URL;
import lombok.Getter;

@Name("Registration")
@URL("/register")
@Getter
public class RegistrationPage extends BasePage {

    @Name("Home page")
    private final String homePage = "//span[@class='ms-2']/..";
    @Name("Sign up header")
    private final String signUpHeader = "//span[contains(text(), 'Sign up')]";
    @Name("Email header")
    private final String emailHeader = "label[for='email']";
    @Name("Email form")
    private final String emailForm = "input[id='email']";
    @Name("Confirm")
    private final String confirmButton = "button[data-action='submit']";
    @Name("Login link")
    private final String loginLink = "//div[@class='position-relative']//a[@routerlink='/login']";
    @Optional
    @Name("Email error")
    private final String invalidEmail = "div[class='input-group-info text-danger']";
    @Optional
    @Name("Clean email")
    private final String cleanEmailButton = "div[class='input-group-append ng-star-inserted']";
    @Optional
    @Name("Success image")
    private final String successImage = "//icon-check-circle";
    @Optional
    @Name("Success message")
    private final String successMessage = "//icon-check-circle/..";

}
