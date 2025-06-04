package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.pageObjects.web.LoginPage;
import com.tonic.pageObjects.web.AdminDashboardPage;
import com.tonic.pageObjects.web.ConfigurationPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class TONIC6653Steps {
    private Page page;
    private LoginPage loginPage;
    private AdminDashboardPage adminDashboardPage;
    private ConfigurationPage configurationPage;

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