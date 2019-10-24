Feature: Application Login

  Scenario Outline: Incorrect Credential Log In
    Given Driver is initialized
    And User navigates to "https://expedia.ca" site
    And User opens the Sign in page
    When User logs in to the application with a incorrect username <username> and password <password>
    Then Error message is displayed
    And Driver is closed

    Examples:
      | username            | password |
      | incorrect@email.com | 123456   |
#      | wrong@email.com     | 987654   |