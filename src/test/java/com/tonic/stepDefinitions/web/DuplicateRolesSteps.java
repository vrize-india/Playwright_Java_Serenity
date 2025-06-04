package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.pageObjects.web.LoginPage;
import com.tonic.pageObjects.web.AdminDashboardPage;
import com.tonic.pageObjects.web.ConfigurationPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class DuplicateRolesSteps {
    private Page page;
    private LoginPage loginPage;
    private AdminDashboardPage adminDashboardPage;
    private ConfigurationPage configurationPage;

    @Given("user login {string} with credentials: {string} and {string}")
    public void user_login(String url, String username, String password) {
        page = PlaywrightFactory.getPage();
        loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.login(username, password);
        adminDashboardPage = new AdminDashboardPage(page);
        Assert.assertTrue("Dashboard not loaded after login", adminDashboardPage.isDashboardLoaded());
    }

    @When("the user is on the modal screen")
    public void user_is_on_modal_screen() {
        adminDashboardPage.goToConfiguration();
        configurationPage = new ConfigurationPage(page);
        configurationPage.goToRoles();
        configurationPage.openFirstRolePermissionsModal();
        Assert.assertTrue(configurationPage.isPermissionsModalVisible());
    }

    @Then("the duplicate icon is displayed next to the close button")
    public void duplicate_icon_displayed_next_to_close() {
        // TODO: Implement logic to assert the duplicate icon is next to the close button in the modal
        Assert.assertTrue(configurationPage.isDuplicateIconNextToCloseButton());
    }
} 