package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.pageObjects.web.LoginPage;
import com.tonic.pageObjects.web.AdminDashboardPage;
import com.tonic.pageObjects.web.ConfigurationPage;
import com.tonic.pageObjects.web.TerminalsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import com.microsoft.playwright.Page;

public class CommonSteps {
    LoginPage loginPage;
    AdminDashboardPage adminDashboardPage;
    ConfigurationPage configurationPage;
    TerminalsPage terminalsPage;

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        PlaywrightFactory.getPage().navigate("https://admin.test.ordyx.com/login");
        loginPage = new LoginPage(PlaywrightFactory.getPage());
        loginPage.doLogin("Prasanna@vrize.com", "Password@123");
        adminDashboardPage = new AdminDashboardPage(PlaywrightFactory.getPage());
    }

    @When("the user navigates to the terminals page")
    public void the_user_navigates_to_the_terminals_page() {
        adminDashboardPage.goToConfiguration();
        configurationPage = new ConfigurationPage(PlaywrightFactory.getPage());
        configurationPage.goToTerminals();
        terminalsPage = new TerminalsPage(PlaywrightFactory.getPage());
    }

    @Then("the terminals page should be displayed")
    public void the_terminals_page_should_be_displayed() {
        if (terminalsPage == null) {
            terminalsPage = new TerminalsPage(PlaywrightFactory.getPage());
        }
        // Wait for the terminal name field to be visible
        PlaywrightFactory.getPage().waitForSelector("input[placeholder='Terminal Name']", new Page.WaitForSelectorOptions().setTimeout(5000));
        // Take a screenshot for debugging
        String screenshot = PlaywrightFactory.takeScreenshot();
        Assert.assertTrue(terminalsPage.isTerminalsPageVisible());
    }

    @Given("user login {string} with credentials: {string} and {string}")
    public void user_login(String url, String username, String password) {
        Page page = PlaywrightFactory.getPage();
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigateToLogin();
        loginPage.login(username, password);
        AdminDashboardPage adminDashboardPage = new AdminDashboardPage(page);
        Assert.assertTrue("Dashboard not loaded after login", adminDashboardPage.isDashboardLoaded());
    }
} 