Feature: Roles Management
  As a system administrator
  I want to manage user roles and their hourly wages
  So that I can maintain accurate compensation information

  @web @roles
  Scenario: Edit user role hourly wages
    Given I am on the Roles page
    When I find a user in the roles list
    And I click edit for the user
    And I update the hourly wages to "25.50"
    And I save the changes
    Then the changes should be saved successfully 