#language: en

@visualSettings
Feature: visual settings feature

  Background:
    Given open page "Registration"

  @test
  Scenario: theme switch check
    When click on button "Theme switcher" of block "Visual Settings"
    Then attribute value "class" of element "Mode" of block "Visual Settings" equals ""
    When click on button "Theme switcher" of block "Visual Settings"
    Then attribute value "class" of element "Mode" of block "Visual Settings" equals "light"
    When click on button "Theme switcher" of block "Visual Settings"
    Then attribute value "class" of element "Mode" of block "Visual Settings" equals ""

  @test
  Scenario Template: localization settings
    When click on element "Localization dropdown" of block "Visual Settings"
    And click on element of collection "Localizations" with value <language> of block "Visual Settings"
    Then value of element "Sign up header" equals "<text>"

    Examples:
      | language    | text        |
      | " Español " | inscribirse |