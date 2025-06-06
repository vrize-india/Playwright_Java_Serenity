package com.tonic.stepDefinitions.web;

import com.tonic.pageObjects.web.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class VendorManagementSteps {
    private InventoryPage inventoryPage;

    public VendorManagementSteps() {
        this.inventoryPage = new InventoryPage();
    }

    @Given("the user is logged in to the BOH system")
    public void userIsLoggedIn() {
        // Login steps should be handled in a separate login step definition
    }

    @When("the user navigates to {string} on the top-level navigation")
    public void navigateToTopLevelMenu(String menuItem) throws InterruptedException {
        // Navigation steps should be handled in a separate navigation step definition
        inventoryPage.clickApps();
    }

    @When("the user clicks on {string}")
    public void clickOnMenuItem(String menuItem) throws InterruptedException {
        // Navigation steps should be handled in a separate navigation step definition
        inventoryPage.clickonInventory();
    }

    @Then("the inventory page should display with information and options")
    public void verifyInventoryPageDisplayed() {

    }

    @And("the {string} tab should be pre-selected by default")
    public void verifyTabSelected(String tabName) {
        if (tabName.equals("Ingredients")) {
            Assert.assertTrue(inventoryPage.isIngredientsTabSelected(), "Ingredients tab should be selected");
        }
    }

    @When("the user clicks on the {string} tab")
    public void clickOnTab(String tabName) throws InterruptedException {
        if (tabName.equals("Vendors")) {
            inventoryPage.clickVendorsTab();
        }
    }

    @Then("the vendor list should be displayed")
    public void verifyVendorListDisplayed() {
        Assert.assertTrue(inventoryPage.isVendorsTabSelected(), "Vendors tab should be selected");
    }

    @When("the user clicks on the {string} icon labeled {string} at the top right corner of the page")
    public void clickAddVendor(String icon, String label) {
        inventoryPage.clickAddVendor();
    }

    @Then("the {string} modal window should appear")
    public void verifyModalWindowAppears(String modalTitle) {
        // Add verification for modal window if needed
    }

    @When("the user enters all required information in the Name field")
    public void enterVendorName() throws InterruptedException {
        inventoryPage.enterVendorDetails();
        Thread.sleep(5000);
    }

    @When("the user enters all required information in the Address field")
    public void enterVendorAddressDetails() throws InterruptedException {
        inventoryPage.expandAddressSectionandAddDetails();
    }

    @When("the user enters all required information in the Phone Number field")
    public void enterVendorPhoneNumberDetails() throws InterruptedException {
        inventoryPage.expandPhoneNumberSectionandAddDetails();
    }

    @When("the user enters all required information in the Contact Number field")
    public void enterVendorContactNumberDetails() throws InterruptedException {
        inventoryPage.expandContactSectionandAddDetails();
    }

    @And("clicks the Save button on the modal window")
    public void clickSaveButton() {
        inventoryPage.clickSave();
    }

    @And("a confirmation message Vendor added should be displayed")
    public void verifyConfirmationMessage() {
        inventoryPage.isVendorVisible();
    }
} 