package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.listeners.JiraListener;
import com.tonic.pageObjects.web.LoginPage;
import com.tonic.pageObjects.web.AdminDashboardPage;
import com.tonic.pageObjects.web.ConfigurationPage;
import com.microsoft.playwright.Page;
import com.tonic.utils.JiraPolicy;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.testng.annotations.Listeners;

import java.util.List;
import java.util.Map;

@Listeners(JiraListener.class)
@JiraPolicy(logTicketReady =false)
public class RolesCopyModalSteps {
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

    @When("the user is on the modal screen with a duplicate icon")
    public void user_on_modal_screen_with_duplicate_icon() {
        adminDashboardPage.goToConfiguration();
        configurationPage = new ConfigurationPage(page);
        configurationPage.goToRoles();
        configurationPage.openFirstRolePermissionsModal();
        Assert.assertTrue(configurationPage.isPermissionsModalVisible());
    }

    @When("the user taps on the duplicate icon")
    public void user_taps_on_duplicate_icon() {
        configurationPage.clickDuplicateIcon();
    }

    @Then("the screen expands")
    public void screen_expands() {
        Assert.assertTrue(configurationPage.isCopyModalFieldsVisible());
    }

    @Then("the following fields are displayed:")
    public void the_following_fields_are_displayed(DataTable dataTable) {
        List<Map<String, String>> fields = dataTable.asMaps(String.class, String.class);
        Assert.assertTrue(configurationPage.isStoreNameFieldVisible());
        Assert.assertTrue(configurationPage.isInheritPermissionsFieldVisible());
        Assert.assertTrue(configurationPage.isSelectComboboxVisible());
    }
} 