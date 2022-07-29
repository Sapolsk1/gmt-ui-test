#language: en

@registration
Feature: registration feature

  Background:
    Given open page "Registration"

  @test
  Scenario: valid email input
    When input in element "Email form" value "nursultan@google.com"
    And click on button "Confirm"
    Then element "Email error" not visible
    And value of element "Success message" equals " An activation link has been sent to your email "
    And element "Success image" is visible

  @test
  Scenario: empty email
    When input in element "Email form" value "nursultan@google.com"
    And click on button "Clean email"
    And click on button "Confirm"
    Then value of element "Email error" equals "This is a required field"
    And element "Success image" not visible

  @test
  Scenario: login link redirection
    When click on element "Login link"
    Then page "Login" loaded

  @test
  Scenario Template: invalid email input
    When input in element "Email form" value "<email>"
    And click on button "Confirm"
    Then value of element "Email error" equals "Invalid e-mail"
    And element "Success image" not visible

    Examples:
      | email       |
      | notEmail    |
      | email@.com  |
      | @google.com |

  @test
  Scenario Template: sign with redirection
    When click on button "<button>" of block "Login with"
    Then redirected to url "<url>"

    Examples:
      | button                | url                                          |
      | Sign up with Google   | https://accounts.google.com/o/oauth2/v2/auth |
      | Sign up with Facebook | https://facebook.com/v14.0/dialog/oauth      |

  @test
  Scenario: home button test
    When click on button "Home page"
    Then page "Home" loaded