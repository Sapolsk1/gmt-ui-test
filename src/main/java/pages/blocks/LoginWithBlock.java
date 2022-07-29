package pages.blocks;

import framework.app.BasePage;
import framework.app.annotations.Name;

@Name("Login with")
public class LoginWithBlock extends BasePage {

    @Name("Sign up with Google")
    private final String googleSignUp = "a[href='/api/oauth/google/login']";
    @Name("Sign up with Facebook")
    private final String facebookSignUp = "a[href='/api/oauth/facebook/login']";

}
