package com.tonic.stepDefinitions;

import com.tonic.pageObjects.web.RolesPage;
import com.tonic.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;

public class RolesStepDef {
    private RolesPage rolesPage;
    private String userName;
    private String hourlyWages;

    public RolesStepDef() {
        this.rolesPage = new RolesPage(DriverFactory.getPage());
    }

    @Given("I am on the Roles page")
    public void iAmOnTheRolesPage() {
        rolesPage.navigateToRoles();
        Assert.assertTrue("Roles page should be loaded", rolesPage.isRolesPageLoaded());
    }

    @When("I find a user in the roles list")
    public void iFindAUserInTheRolesList() {
        userName = rolesPage.getFirstVisibleUser();
        Assert.assertNotNull("Should find a user", userName);
    }

    @And("I click edit for the user")
    public void iClickEditForTheUser() {
        rolesPage.clickEditForUser(userName);
        Assert.assertTrue("Edit form should be visible", rolesPage.isEditFormVisible());
    }

    @And("I update the hourly wages to {string}")
    public void iUpdateTheHourlyWages(String wages) {
        this.hourlyWages = wages;
        rolesPage.setHourlyWages(wages);
    }

    @And("I save the changes")
    public void iSaveTheChanges() {
        rolesPage.saveChanges();
    }

    @Then("the changes should be saved successfully")
    public void theChangesShouldBeSavedSuccessfully() {
        // Verify the edit form is no longer visible
        Assert.assertFalse("Edit form should not be visible after saving", rolesPage.isEditFormVisible());
    }
} 