Feature: Add New Item functionality in Items screen
  As a user with appropriate permissions
  I want to add a new item via the Items screen
  So that I can manage menu items efficiently

  @TONIC7343
  Scenario: Add New Item and validate UI and error messages
    Given the user is logged in to the "https://admin.test.ordyx.com/login" system with credentials username "Prasanna@vrize.com" and password "Password@123"
    And User navigates to "Configuration > menu configuration > Items"
    And The user is on the Items screen
    When The user clicks on Add New CTA
    Then A new blank row should be added at the top of the table
    And Disable the Add New CTA Button
    And The rows should have the following columns and placeholders:
      | Row Name               | Placeholder            | Mandatory | Validations                                                                 |
      | Add New Item           | Add New Item           | Yes       | Data Type - String, Supports Text, Numerics & All Special Characters, Minimum Limit - 1, Maximum Limit - 50 |
      | Add New Sales Group    | Add New Sales Group    | Yes       | Driven from Sales Groups in BoH, updates reflected in real-time             |
      | Add New Modifiers Sets | Add New Modifiers Sets | No        | Driven from Modifier Sets in BoH, updates reflected in real-time            |
    
    When the user enters invalid values in the "Items" field
    Then an error message "Item name is required" should be displayed below the field
    When the user does not select any dropdown values in the "Sales Groups" field
    Then an error message "Sales group is required" should be displayed below the field

  @TONIC7341
  Scenario: Add New Item and save with modal options
    Given the user is logged in to the "https://admin.test.ordyx.com/login" system with credentials username "Prasanna@vrize.com" and password "Password@123"
    And User navigates to "Configuration > menu configuration > Items"
    And The user is on the Items screen
    When The user clicks on Add New CTA
    Then A new blank row should be added at the top of the table
    And Disable the Add New CTA Button
    And The rows should have the following columns and placeholders:
      | Row Name               | Placeholder            | Mandatory |
      | Add New Item           | Add New Item           | Yes       |
      | Add New Sales Group    | Add New Sales Group    | Yes       |
      | Add New Modifiers Sets | Add New Modifiers Sets | No        |
    Then you will need to add a name
    And you will need to choose a Sales Group
    And you will need to choose a modifier sets
    When you click on save at the top right corner
    Then you receive a message Item Added 