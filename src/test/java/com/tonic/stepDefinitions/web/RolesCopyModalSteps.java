package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.pageObjects.web.LoginPage;
import com.tonic.pageObjects.web.AdminDashboardPage;
import com.tonic.pageObjects.web.ConfigurationPage;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

public class RolesCopyModalSteps {
    private Page page;
    private LoginPage loginPage;
    private AdminDashboardPage adminDashboardPage;
    private ConfigurationPage configurationPage;

    // TONIC4797: Modal with duplicate icon
    @When("the user is on the modal screen with a duplicate icon")
    public void user_on_modal_screen_with_duplicate_icon() {
        page = PlaywrightFactory.getPage();
        adminDashboardPage = new AdminDashboardPage(page);
        configurationPage = new ConfigurationPage(page);
        adminDashboardPage.goToConfiguration();
        configurationPage.goToRoles();
        configurationPage.openFirstRolePermissionsModal();
        Assert.assertTrue(configurationPage.isPermissionsModalVisible());
    }

    @When("the user taps on the duplicate icon")
    public void user_taps_on_duplicate_icon() {
        page = PlaywrightFactory.getPage();
        configurationPage = new ConfigurationPage(page);
        configurationPage.clickDuplicateIcon();
    }

    @Then("the screen expands")
    public void screen_expands() {
        page = PlaywrightFactory.getPage();
        configurationPage = new ConfigurationPage(page);
        Assert.assertTrue(configurationPage.isCopyModalFieldsVisible());
    }

    @Then("the following fields are displayed:")
    public void the_following_fields_are_displayed(DataTable dataTable) {
        page = PlaywrightFactory.getPage();
        configurationPage = new ConfigurationPage(page);
        List<Map<String, String>> fields = dataTable.asMaps(String.class, String.class);
        System.out.println("Checking Store Name Field...");
        Assert.assertTrue("Store Name Field not visible", configurationPage.isStoreNameFieldVisible());
        System.out.println("Checking Inherit Permissions Field...");
        Assert.assertTrue("Inherit Permissions Field not visible", configurationPage.isInheritPermissionsFieldVisible());
        System.out.println("Checking Select Combobox...");
        Assert.assertTrue("Select Combobox not visible", configurationPage.isSelectComboboxVisible());
    }

    // TONIC6653: Duplicate icon next to close button
    @When("the user is on the modal screen")
    public void user_is_on_modal_screen() {
        page = PlaywrightFactory.getPage();
        adminDashboardPage = new AdminDashboardPage(page);
        configurationPage = new ConfigurationPage(page);
        adminDashboardPage.goToConfiguration();
        configurationPage.goToRoles();
        configurationPage.openFirstRolePermissionsModal();
        Assert.assertTrue(configurationPage.isPermissionsModalVisible());
    }

    @Then("the duplicate icon is displayed next to the close button")
    public void duplicate_icon_displayed_next_to_close() {
        page = PlaywrightFactory.getPage();
        configurationPage = new ConfigurationPage(page);
        Assert.assertTrue(configurationPage.isDuplicateIconNextToCloseButton());
    }
} 