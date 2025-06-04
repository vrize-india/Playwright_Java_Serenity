package com.tonic.pageObjects.web;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.util.List;

public class RolesPage {
    private Page page;

    // Locators for navigation and page verification
    private String rolesTitle = "h1:has-text('Roles')";
    private String configurationMenu = "text=Configuration";
    private String rolesMenu = "text=Roles";

    // Locators for user list and search
    private String userRolesList = "//td[contains(@class,'name')]//span";
    private String searchInput = "input[placeholder='Search']";
    private String userLocatorFormat = "//span[contains(text(), '%s')]";

    // Locators for edit form
    private String editButtonXPath = "//span[text()='%s']/ancestor::tr//button[@mattooltip='Edit']";
    private String hourlyWagesInput = "input[type='number'][matinput][appdecimalinput][placeholder='0']";
    private String saveButton = "//button[@mattooltip='Save']";
    private String cancelButton = "button:has-text('Cancel')";

    public RolesPage(Page page) {
        this.page = page;
    }

    // Navigation methods
    public void navigateToRoles() {
        try {
            // Wait for navigation to complete
            page.waitForLoadState();
            
            // Click on Configuration
            page.waitForSelector(configurationMenu, new Page.WaitForSelectorOptions().setTimeout(10000));
            page.click(configurationMenu);
            
            // Click on Roles
            page.waitForSelector(rolesMenu, new Page.WaitForSelectorOptions().setTimeout(10000));
            page.click(rolesMenu);
            
            // Wait for roles page to load
            page.waitForLoadState();
            
            // Verify roles page is loaded
            if (!isRolesPageLoaded()) {
                throw new RuntimeException("Failed to navigate to Roles page");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to Roles page", e);
        }
    }

    public boolean isRolesPageLoaded() {
        try {
            // Wait for both the title and the user list to be visible
            page.waitForSelector(rolesTitle, new Page.WaitForSelectorOptions().setTimeout(10000));
            page.waitForSelector(userRolesList, new Page.WaitForSelectorOptions().setTimeout(10000));
            return page.isVisible(rolesTitle) && page.locator(userRolesList).count() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // User list and search methods
    public boolean isUserRolesListVisible() {
        try {
            page.waitForSelector(userRolesList, new Page.WaitForSelectorOptions().setTimeout(20000));
            return page.locator(userRolesList).count() > 0;
        } catch (Exception e) {
            throw new RuntimeException("User roles list not visible within timeout", e);
        }
    }

    public void searchUser(String userName) {
        try {
            // Wait for navigation and page load
            page.waitForLoadState();
            
            // Wait for the search input with increased timeout
            page.waitForSelector(searchInput, new Page.WaitForSelectorOptions().setTimeout(20000));
            
            // Get the search input locator
            Locator searchBox = page.locator(searchInput);
            
            // Clear and type if visible
            if (searchBox.isVisible()) {
                searchBox.clear();
                searchBox.type(userName);
                
                // Wait for search results to update
                page.waitForTimeout(2000);
                System.out.println("Searching for user: " + userName);
                
                // Wait for the user to appear in results
                String userLocator = String.format(userLocatorFormat, userName);
                page.waitForSelector(userLocator, new Page.WaitForSelectorOptions().setTimeout(5000));
            } else {
                throw new RuntimeException("Search input not found or not visible");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to search for user: " + userName, e);
        }
    }

    public boolean isUserVisible(String userName) {
        try {
            String userLocator = String.format(userLocatorFormat, userName);
            page.waitForSelector(userLocator, new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator(userLocator).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstVisibleUser() {
        try {
            // Wait for at least one user name to be visible
            page.waitForSelector(userRolesList, new Page.WaitForSelectorOptions().setTimeout(10000));
            Locator userNames = page.locator(userRolesList);
            
            // Get all user names
            List<String> names = userNames.allTextContents();
            if (names.isEmpty()) {
                throw new RuntimeException("No users found in the list");
            }
            
            // Get the first non-empty name
            String userName = names.stream()
                .filter(name -> !name.trim().isEmpty())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No valid user names found"));
            
            System.out.println("Found first user: " + userName);
            return userName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get first visible user", e);
        }
    }

    // Edit user methods
    public void clickEditForUser(String userName) {
        try {
            // First try to find the user
            page.waitForSelector(userRolesList, new Page.WaitForSelectorOptions().setTimeout(10000));
//            searchUser(userName);
            
            // Wait for the user to be visible after search
            String userLocator = String.format(userLocatorFormat, userName);
            page.waitForSelector(userLocator, new Page.WaitForSelectorOptions().setTimeout(10000));
            
            // Use the correct XPath to find and click the edit button
            String editButtonLocator = String.format(editButtonXPath, userName);
            page.waitForSelector(editButtonLocator, new Page.WaitForSelectorOptions().setTimeout(10000));
            page.click(editButtonLocator);
            Locator editButton = page.locator(editButtonLocator);
//            if (editButton.isVisible()) {
//                editButton.click();
                // Wait for the edit form to appear
//                page.waitForSelector(hourlyWagesInput, new Page.WaitForSelectorOptions().setTimeout(5000));
//            } else {
//                throw new RuntimeException("Edit button not found or not visible for user '" + userName + "'");
//            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to click edit for user '" + userName + "'", e);
        }
    }

    public void setHourlyWages(String wages) {
        try {
            // Wait for the hourly wages input to be visible
            page.waitForSelector(hourlyWagesInput, new Page.WaitForSelectorOptions().setTimeout(10000));
            Locator wagesInput = page.locator(hourlyWagesInput);
            
            if (wagesInput.isVisible()) {
                wagesInput.clear();
                wagesInput.type(wages);
                // Add a small delay to ensure the input is filled
                page.waitForTimeout(500);
            } else {
                throw new RuntimeException("Hourly wages input not found or not visible");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set hourly wages to " + wages, e);
        }
    }

    public void saveChanges() {
        try {
            // Wait for the edit form to be fully loaded
            page.waitForLoadState();
            
            // Wait for the save button with increased timeout and state
            page.waitForSelector(saveButton, new Page.WaitForSelectorOptions()
                .setTimeout(20000)
                .setState(WaitForSelectorState.VISIBLE));
            
            // Get the save button locator
            Locator saveBtn = page.locator(saveButton);
            
            // Ensure the button is both visible and enabled
            if (saveBtn.isVisible() && saveBtn.isEnabled()) {
                // Click the save button
                saveBtn.click();
                
                // Wait for any loading states to complete
                page.waitForLoadState();
                
                // Wait for the save button to disappear (indicating save was successful)
                page.waitForSelector(saveButton, new Page.WaitForSelectorOptions()
                    .setTimeout(10000)
                    .setState(WaitForSelectorState.HIDDEN));
                
                // Additional wait to ensure all UI updates are complete
                page.waitForTimeout(2000);
            } else {
                throw new RuntimeException("Save button is not visible or enabled");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to save changes: " + e.getMessage(), e);
        }
    }

    public void cancelChanges() {
        try {
            page.waitForSelector(cancelButton, new Page.WaitForSelectorOptions().setTimeout(5000));
            Locator cancelBtn = page.locator(cancelButton);
            
            if (cancelBtn.isVisible()) {
                cancelBtn.click();
                // Wait for the cancel operation to complete
                page.waitForLoadState();
                // Additional wait to ensure the form is closed
                page.waitForTimeout(1000);
            } else {
                throw new RuntimeException("Cancel button not found or not visible");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to cancel changes", e);
        }
    }

    public boolean isEditFormVisible() {
        try {
            page.waitForSelector(hourlyWagesInput, new Page.WaitForSelectorOptions().setTimeout(10000));
            return page.locator(hourlyWagesInput).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
} 