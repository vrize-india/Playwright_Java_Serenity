@regression
Feature: Roles Copy Modal
  As a user
  I want to copy a role and see the correct modal fields
  So that I can verify the UI and default values
  @RolesCopyModal @TONIC4797
  Scenario: Copy role and verify modal fields are displayed
    Given user login "https://admin.test.ordyx.com/login" with credentials: "Prasanna@vrize.com" and "Password@123"
    When the user is on the modal screen with a duplicate icon
    When the user taps on the duplicate icon
    Then the screen expands
    And the following fields are displayed:
      | Field                   | Placeholder Text | Default Value         |
      | Store Name Field        | -               | Selected Store Name   |
      | Inherit Permissions From| "Select"        | -                     |


  @TONIC6653
  Scenario: Duplicate roles are displayed
    Given user login "https://admin.test.ordyx.com/login" with credentials: "Prasanna@vrize.com" and "Password@123"
    When the user is on the modal screen
    Then the duplicate icon is displayed next to the close button