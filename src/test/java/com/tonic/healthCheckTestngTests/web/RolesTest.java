package com.tonic.healthCheckTestngTests.web;

import com.tonic.annotations.TonicAnnotation;
import com.tonic.enums.CategoryType;
import com.tonic.factory.PlaywrightFactory;
import com.tonic.pageObjects.web.RolesPage;
import com.tonic.utils.AllureScreenshotUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Base64;
import java.nio.file.Path;
import java.nio.file.Paths;

@Epic("Configuration Management")
@Feature("Roles Management")
public class RolesTest extends BaseTest {

    @TonicAnnotation(category = {CategoryType.WEB})
    @Test(priority = 1, description = "Verify roles page loads successfully")
    @Story("View Roles")
    @Description("Verify user can access roles page and view roles list")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyRolesPageLoad() {
        try {
            // Login
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Before login");
            loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After login");

            // Navigate to Roles
            adminDashboardPage.goToConfiguration();
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to configuration");
            
            configurationPage.goToRoles();
            RolesPage rolesPage = new RolesPage(PlaywrightFactory.getPage());
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to roles");

            // Verify roles page elements
            Assert.assertTrue(rolesPage.isRolesPageLoaded(), "Roles page should be loaded");
            Assert.assertTrue(rolesPage.isUserRolesListVisible(), "User roles list should be visible");
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Roles page verification");

        } catch (Exception e) {
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Error occurred");
            throw e;
        }
    }

    @TonicAnnotation(category = {CategoryType.WEB})
    @Test(priority = 2, description = "Edit user role hourly wages")
    @Story("Edit Role")
    @Description("Verify user can edit role details and update hourly wages")
    @Severity(SeverityLevel.CRITICAL)
    public void editUserRoleHourlyWages() {
        try {
            // Login
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Before login");
            loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After login");

            // Navigate to Roles
            adminDashboardPage.goToConfiguration();
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to configuration");
            
            configurationPage.goToRoles();
            RolesPage rolesPage = new RolesPage(PlaywrightFactory.getPage());
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to roles");

            // Get the first available user
            String userName = rolesPage.getFirstVisibleUser();
            System.out.println("Editing role for user: " + userName);

            // Click edit for the user
            rolesPage.clickEditForUser(userName);
            Assert.assertTrue(rolesPage.isEditFormVisible(), "Edit form should be visible");
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After clicking edit for user");

            // Update hourly wages
            rolesPage.setHourlyWages("30.00");
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After updating hourly wages");

            // Save changes
            rolesPage.saveChanges();
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After saving changes");

        } catch (Exception e) {
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Error occurred");
            throw e;
        }
    }

    @TonicAnnotation(category = {CategoryType.WEB})
    @Test(priority = 3, description = "Cancel role edit operation")
    @Story("Edit Role")
    @Description("Verify user can cancel role edit operation without saving changes")
    @Severity(SeverityLevel.NORMAL)
    public void cancelRoleEdit() {
        try {
            // Login
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Before login");
            loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After login");

            // Navigate to Roles
            adminDashboardPage.goToConfiguration();
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to configuration");
            
            configurationPage.goToRoles();
            RolesPage rolesPage = new RolesPage(PlaywrightFactory.getPage());
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to roles");

            // Get the first available user
            String userName = rolesPage.getFirstVisibleUser();
            System.out.println("Editing role for user: " + userName);

            // Click edit for the user
            rolesPage.clickEditForUser(userName);
            Assert.assertTrue(rolesPage.isEditFormVisible(), "Edit form should be visible");
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After clicking edit for user");

            // Update hourly wages
            rolesPage.setHourlyWages("30.00");
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After updating hourly wages");

            // Cancel changes
            rolesPage.cancelChanges();
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After canceling changes");

            // Verify edit form is closed
            Assert.assertFalse(rolesPage.isEditFormVisible(), "Edit form should be closed after canceling");

        } catch (Exception e) {
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Error occurred");
            throw e;
        }
    }

    @TonicAnnotation(category = {CategoryType.WEB})
    @Test(priority = 4, description = "Search for user role")
    @Story("Search Roles")
    @Description("Verify user can search for roles using the search functionality")
    @Severity(SeverityLevel.NORMAL)
    public void searchUserRole() {
        try {
            // Login
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Before login");
            loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After login");

            // Navigate to Roles
            adminDashboardPage.goToConfiguration();
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to configuration");
            
            configurationPage.goToRoles();
            RolesPage rolesPage = new RolesPage(PlaywrightFactory.getPage());
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After navigation to roles");

            // Get the first available user
            String userName = rolesPage.getFirstVisibleUser();
            System.out.println("Searching for user: " + userName);

            // Search for the user
            rolesPage.searchUser(userName);
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "After searching for user");

            // Verify user is visible in search results
            Assert.assertTrue(rolesPage.isUserVisible(userName), "User should be visible in search results");

        } catch (Exception e) {
            AllureScreenshotUtil.takeScreenshot(PlaywrightFactory.getPage(), "Error occurred");
            throw e;
        }
    }
} 