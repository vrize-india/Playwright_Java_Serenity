package com.tonic.pageObjects.web;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import java.util.Objects;

public class ItemsPage {
    private final Page page;

    // Selectors (update as per actual UI)
    private final String addNewButton = "button:has-text('Add New')";
    private final String itemsTable = "div.tonic-container";
    private final String newRow = "tr.editing ng-star-inserted";
    private final String itemNameField = "input[placeholder='Add New Item']";
    private final String salesGroupDropdown = "[placeholder='Add New Sales Group']";
    private final String modifiersSetsDropdown = "[placeholder='Add New Modifiers Sets']";
    private final String itemNameError = "text=Item name is required";
    private final String salesGroupError = "text=Sales group is required";
    public long time = System.currentTimeMillis();

    public ItemsPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        // Assumes login fields/selectors are present on the login page
        page.fill("input[name='email']", username);
        page.fill("input[name='password']", password);
        page.click("button[type='submit']");
        }

    public void navigateToItems() {
        // Example navigation: update selectors as per actual UI
        page.click("text=Configuration");
        page.click("text=Items");
        page.waitForSelector(itemsTable, new Page.WaitForSelectorOptions().setTimeout(5000));
    }

    public boolean isItemsScreenVisible() {
        return page.isVisible(itemsTable);
    }

    public void clickAddNewButton() {
        page.click(addNewButton);
    }

    public boolean isNewBlankRowAtTop() {
        // The new blank row has class 'editing' and is the first row in the table
        // Wait for the row to appear for up to 5 seconds
        page.waitForSelector("tr.editing", new Page.WaitForSelectorOptions().setTimeout(5000));
        return page.isVisible("tr.editing");
    }

    public boolean isAddNewButtonDisabled() {
        return page.isDisabled(addNewButton);
    }

    public boolean verifyColumnAndPlaceholder(String rowName, String placeholder, boolean mandatory, String validations) {
        if (placeholder.equals("Add New Modifiers Sets")) {
            // Check for a button with the text 'Add New Modifier Sets'
            return page.isVisible("button:has-text('Add New Modifier Sets')");
        } else {
            // Check for the input or field placeholder for the given field
            return page.isVisible("[placeholder='" + placeholder + "']");
        }
    }

    public void enterInvalidItemName() {
        // Leave blank or enter invalid value
        page.fill(itemNameField, "");
        // Optionally, trigger blur or save
        page.locator(itemNameField).blur();
    }

    public boolean isItemNameRequiredErrorDisplayed() {
        return page.isVisible(itemNameError);
    }

    public void leaveSalesGroupUnselected() {
        // Do not select any value in the dropdown
        // Optionally, trigger save or blur
        page.locator(salesGroupDropdown).blur();
    }

    public boolean isSalesGroupRequiredErrorDisplayed() {
        return page.isVisible(salesGroupError);
    }

    // Checks if the modal window with item options is visible
    public boolean isItemOptionsModalVisible(String name, String salesGroup, String modifierSets) {
        // Wait for a generic modal/dialog container to appear
        boolean modalVisible = page.waitForSelector(".mat-dialog-container, .modal, .cdk-overlay-pane", new Page.WaitForSelectorOptions().setTimeout(5000)) != null;
        // Check for the three fields by their most likely selectors
        boolean nameField = page.isVisible("input[placeholder='Add New Item']");
        boolean salesGroupField = page.isVisible("[placeholder='Add New Sales Group']") || page.isVisible("mat-select[placeholder='Add New Sales Group']");
        boolean modifierSetsField = page.isVisible("button:has-text('Add New Modifier Sets')");
        return modalVisible && nameField && salesGroupField && modifierSetsField;
    }

    // Adds a name to the item modal
    public void addItemName(String name) {
        // Update selector as per actual modal input
        page.fill("input[placeholder='Add New Item']", name);
    }

    // Chooses a sales group from the dropdown (modal version)
    public void chooseSalesGroup(String salesGroup) throws InterruptedException {
//        // Take a screenshot before interacting for debugging
//        page.screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("modal-sales-group-before-click.png")));
//        // Debug: print all [role='combobox'] elements and their attributes
//        int comboboxCount = page.locator("[role='combobox']").count();
//        System.out.println("[role='combobox'] count in modal: " + comboboxCount);
//        for (int i = 0; i < comboboxCount; i++) {
//            String outerHTML = page.locator("[role='combobox']").nth(i).evaluate("el => el.outerHTML").toString();
//            System.out.println("[role='combobox'] #" + i + ": " + outerHTML);
//        }
//        // Attempt to click the Sales Group combobox (existing logic)
//        page.click("[role='combobox'][aria-label='Select']");
//        // Wait for the dropdown options to be visible
//        page.waitForSelector("[role='option']", new Page.WaitForSelectorOptions().setTimeout(5000));
//        // Click the desired option by its visible text
//        page.click("[role='option'][name='" + salesGroup + "']");
        Thread.sleep(10000);
        page.getByText("Add New Sales Group").click();
        Thread.sleep(10000);
        page.locator("span.mat-option-text").nth(1).click();
        Thread.sleep(10000);
    }

    // Chooses a modifier set from the dropdown
    public void chooseModifierSets(String modifierSet) throws InterruptedException {
        // Click the button to open the modifier sets dropdown
        page.click("button:has-text('Add New Modifier Sets')");
        Thread.sleep(10000);
        page.locator("#mat-menu-panel-0").getByText(modifierSet, new Locator.GetByTextOptions().setExact(true)).click();
        page.locator(".cdk-overlay-backdrop").click();
    }

    // Clicks the save button at the top right corner
    public void clickSaveButton() throws InterruptedException {
        // Compose the dynamic row name using the variable and time
        String rowName = "Test Item "+time +" Burgers Add New";
        page.locator("button.save-btn").click();
        Thread.sleep(5000);
    }

    // Checks if the 'Item Added' message is displayed
    public boolean isItemAddedMessageDisplayed() throws InterruptedException {
        // Update selector as per actual toast/message
        return page.getByText("Successfully added the Item").isVisible();
    }
} 