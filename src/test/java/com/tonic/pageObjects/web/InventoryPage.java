package com.tonic.pageObjects.web;

import com.microsoft.playwright.Page;
import com.tonic.factory.PlaywrightFactory;

public class InventoryPage {
    private Page page;

    private String appspage = "//li[contains(@class,'nav-item')]//a[text()='Apps']";
    private String inventorypage = "//span[contains(text(),'Inventory')]";

    
    // Locators
    private String ingredientsTab = "//mat-icon[text()=' menu_book ']";
    private String vendorsTab = "//span[contains(text(),'Vendors')]";
    private String vendorsTabIsSelected = "//div//mat-icon[text()=' menu_book ']//following-sibling::span//parent::div//following::div[@aria-selected='true']";
    private String addVendorButton = "//button[contains(@mattooltip, 'Add Vendor')]";
    private String vendorNameInput = "//input[@formcontrolname='name']";
    private String accountNumberInput = "//input[@formcontrolname='accountNumber']";
    private String contactNameInput = "//input[@formcontrolname='contactName']";
    private String contactEmailInput = "//input[@formcontrolname='contactEmail']";
    private String notesInput = "//textarea[@name='notes']";
    // Address section
    private String expandAddressSection = "//mat-panel-title[contains(text(),'Address')]";
    private String addressInput = "//input[@formcontrolname='address']";
    private String stateInput="//input[@formcontrolname='state']";
    private String cityInput = "//input[@formcontrolname='city']";
    private String postalCodeInput = "//input[@formcontrolname='postalCode']";

    //Phone Number
    private String expandPhoneNumberSection = "//mat-panel-title[contains(text(),'Phone Number')]";
    private String phoneNumberInput = "//input[@formcontrolname='phoneNumber']";

    //Phone Number
    private String expandContactSection = "//mat-panel-title[contains(text(),'Contact')]";
    private String contactNumberInput = "//input[@formcontrolname='contactName']";


    private String websiteInput = "//input[@name='website']";
    private String saveButton = "//button[@mattooltip='Save']";
    private String successMessage = "//div[contains(@class, 'success-message')]";

    private String specificVendpr ="//td[contains(text(),'%s')]";

    public InventoryPage() {
        this.page = PlaywrightFactory.getPage();
    }

    public void clickApps() throws InterruptedException {
        page.click(appspage);
        Thread.sleep(10000);
    }
    public boolean isVendorVisible() {
        try {
            String userLocator = String.format(specificVendpr, "Ramu1");
            page.waitForSelector(userLocator, new Page.WaitForSelectorOptions().setTimeout(5000));
            return page.locator(userLocator).isVisible();
        } catch (Exception e) {
            return false;
        }
    }


    public void clickonInventory() throws InterruptedException {
        page.click(inventorypage);
        Thread.sleep(10000);

    }

    public void expandAddressSectionandAddDetails() throws InterruptedException {
        page.click(expandAddressSection);
        Thread.sleep(10000);

        page.fill(addressInput, "Street21");
        page.fill(stateInput, "NY");
        page.fill(cityInput, "New");
        page.fill(postalCodeInput, "10005");


    }

    public void expandPhoneNumberSectionandAddDetails() throws InterruptedException {
        page.click(expandPhoneNumberSection);
        Thread.sleep(10000);
        page.fill(phoneNumberInput, "8749304875");

    }

    public void expandContactSectionandAddDetails() throws InterruptedException {
        page.click(expandContactSection);
        Thread.sleep(10000);
        page.fill(contactNameInput, "ramu31");
        page.fill(contactEmailInput, "ramu31@abc.com");

    }


    public void clickIngredientsTab() {
        page.click(ingredientsTab);
    }

    public void clickVendorsTab() throws InterruptedException {
        page.click(vendorsTab);
        Thread.sleep(10000);
    }

    public void clickAddVendor() {
        page.click(addVendorButton);
        page.waitForSelector(vendorNameInput, new Page.WaitForSelectorOptions().setTimeout(20000));
    }

    public void enterVendorDetails() {
        page.fill(vendorNameInput, "Ramu1");
        page.fill(accountNumberInput, "4567");

    }

    public void clickSave() {
        page.click(saveButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return page.isVisible(successMessage);
    }

    public String getSuccessMessage() {
        return page.textContent(successMessage);
    }

    public boolean isVendorsTabSelected() {
        return page.isVisible(vendorsTabIsSelected);
    }

    public boolean isIngredientsTabSelected() {
        return page.isVisible(ingredientsTab + "[@aria-selected='true']");
    }
} 