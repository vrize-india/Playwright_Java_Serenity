package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.listeners.JiraListener;
import com.tonic.pageObjects.web.*;
import com.tonic.utils.JiraPolicy;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.testng.annotations.Listeners;

@Listeners(JiraListener.class)
@JiraPolicy(logTicketReady =false)
public class TONIC11579Steps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AttendancePage attendancePage;
    private ReportsPage reportsPage;

    @Given("the user is logged in to the {string} system with credentials username={string} and password={string}")
    public void user_logs_in(String url, String username, String password) {
        loginPage = new LoginPage(PlaywrightFactory.getPage());
        loginPage.navigateToLogin();
        loginPage.login(username, password);
    }

    @When("the user navigates to Apps → Attendance → Reports")
    public void user_navigates_to_attendance_reports() {
        dashboardPage = new DashboardPage(PlaywrightFactory.getPage());
        dashboardPage.goToAttendanceReports();
    }

    @When("the user clicks on the {string} option")
    public void user_clicks_on_option(String option) {
        attendancePage = new AttendancePage(PlaywrightFactory.getPage());
        attendancePage.openReportsTab();
    }

    @Then("the page should update to display a filter for report selection")
    public void page_displays_filter() {
        Assert.assertTrue(attendancePage.isFilterDisplayed());
    }

    @When("the user selects {string} as the {string}")
    public void user_selects_report_type(String reportType, String selectType) {
        reportsPage = new ReportsPage(PlaywrightFactory.getPage());
        reportsPage.selectAttendanceDetailReport();
    }

    @When("chooses {string} under {string} section")
    public void user_chooses_date_range(String dateRange, String section) {
        reportsPage.selectCustomDates();
        PlaywrightFactory.getPage().locator("span:has-text('Date Range')").first().click();
    }

    @When("enters a specific period of date")
    public void user_enters_date_period() {
        reportsPage.enterDateRange("1/01/2025", "5/29/2025");
    }

    @When("clicks on {string} section")
    public void user_clicks_filter_by(String section) {
        // This step may be implicit if the filter is already open
    }

    @Then("three sections titled {string}, {string}, and {string} with dropdown options should appear")
    public void verify_sections(String employees, String group, String jobTitle) {
        // Add assertions for dropdowns if needed
    }

    @When("the user selects options from each section needed for the report")
    public void user_selects_report_options() {
        reportsPage.selectEmployee("Barbara Manager");
        reportsPage.selectGroup("FOH");
    }

    @When("clicks on the {string} button")
    public void user_clicks_run(String button) {
        reportsPage.runReport();
    }

    @Then("the page should display the Attendance Detail Report with data corresponding to the selected filters")
    public void verify_report_displayed() {
        Assert.assertTrue(reportsPage.isReportDisplayed());
    }

    @Then("the Wage, Total Wages, and Rates displayed in the report should be accurate")
    public void verify_wages_and_rates() {
        // Implement wage/rate validation
    }

    @Then("verify that the values shown in BOH Preview report match exactly with those in the Legacy report for the same filter and date range")
    public void verify_boh_vs_legacy() {
        // Implement BOH vs Legacy report comparison
    }
}
