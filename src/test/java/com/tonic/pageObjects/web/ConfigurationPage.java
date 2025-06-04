package com.tonic.pageObjects.web;

import com.microsoft.playwright.Page;

public class ConfigurationPage {
    private Page page;
    private String terminalsMenu = "text=Terminals";
    private String rolesTab = "text=Roles";
    private String permissionsButton = "table button";
    private String permissionsModal = ".modal-dialog,[role=dialog]";
    private String duplicateIcon = ".modal-dialog,[role=dialog] button";
    private String storeNameField = "text=Store Name";
    private String inheritPermissionsField = "text=Inherit Permissions From";
    private String selectCombobox = "[role=combobox],select";

    public ConfigurationPage(Page page) {
        this.page = page;
    }

    public boolean isConfigurationLoaded() {
        return page.isVisible(terminalsMenu);
    }

    public void goToTerminals() {
        page.locator(terminalsMenu).click();
    }

    public void goToRoles() {
        page.locator(rolesTab).click();
    }

    public void openFirstRolePermissionsModal() {
        page.locator(permissionsButton).first().click();
    }

    public boolean isPermissionsModalVisible() {
        return page.locator(permissionsModal).first().isVisible();
    }

    public void clickDuplicateIcon() {
        page.locator(duplicateIcon).nth(1).click();
    }

    public boolean isCopyModalFieldsVisible() {
        return page.locator(storeNameField).isVisible() && page.locator(inheritPermissionsField).isVisible();
    }

    public boolean isStoreNameFieldVisible() {
        return page.locator(storeNameField).isVisible();
    }

    public boolean isInheritPermissionsFieldVisible() {
        return page.locator(inheritPermissionsField).isVisible();
    }

    public boolean isSelectComboboxVisible() {
        return page.locator("[role=combobox],select").locator("text=Select").first().isVisible();
    }

    public boolean isDuplicateIconNextToCloseButton() {
        // TODO: Implement logic to check if the duplicate icon is next to the close button in the modal
        // This may require a more specific selector or DOM structure check
        return true; // Placeholder for now
    }
} 