package com.tonic.stepDefinitions.web;

import com.tonic.pageObjects.web.LoginPage;
import com.tonic.pageObjects.web.RolesPage;
import com.tonic.factory.PlaywrightFactory;
import com.tonic.utils.ApplicationUtils;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;

public class RolesStepDef {
    private RolesPage rolesPage;
    private String userName;
    private String hourlyWages;
    private LoginPage loginPage;
    private String hourlyWagesWithoutDollar;
    private String updatedHourlyWages;
    private double incrementValue;
    private double decrementValue;
    private double hourlyWagesDouble;
    private double updatedHourlyWagesDouble;
    private String decrementValueHourlyWages;
    ApplicationUtils apputils = new ApplicationUtils();
    public RolesStepDef() {
        this.rolesPage = new RolesPage(PlaywrightFactory.getPage());
    }

    @And("I am on the Roles page")
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

    @And("get the current hourly wages of specific user")
    public void  getTheCurrentHourlyWagesOfSpecificUser() {
        String hourlyWages=rolesPage.getHourlyWagesOfSpecificUser(userName);
        System.out.println("Hourly wages input " + hourlyWages);
        hourlyWagesWithoutDollar=apputils.removeDollarSymbol(hourlyWages);
        System.out.println("Hourly wages input without dollar " + hourlyWagesWithoutDollar);
        hourlyWagesDouble =apputils.convertStringToDouble(hourlyWagesWithoutDollar);
        System.out.println("Hourly wages input without dollar in Double " + hourlyWagesDouble);
        incrementValue = hourlyWagesDouble+1 ;
        System.out.println("Increment Value " + incrementValue);
        updatedHourlyWages=apputils.convertDoubleToString(incrementValue);

        decrementValue = hourlyWagesDouble-1 ;
        System.out.println("Decrease Value " + decrementValue);
        decrementValueHourlyWages=apputils.convertDoubleToString(decrementValue);

    }

    @And("User increasing the hourly wages Amount")
    public void UserIncreasingTheHourlyWagesAmount() {
        rolesPage.setHourlyWages(updatedHourlyWages);
    }

    @And("User decreasing the hourly wages Amount")
    public void userDecreasingTheHourlyWagesAmount() {
        rolesPage.setHourlyWages(decrementValueHourlyWages);
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


    @And("get the updated hourly wages of specific user")
    public void getTheUpdatedHourlyWagesOfSpecificUser() {
        String updatedHourlyWage=rolesPage.getHourlyWagesOfSpecificUser(userName);
       String updatedHourlyWagesWithoutDollar=apputils.removeDollarSymbol(updatedHourlyWage);
        System.out.println("Hourly wages input without dollar " + updatedHourlyWagesWithoutDollar);
        updatedHourlyWagesDouble =apputils.convertStringToDouble(updatedHourlyWagesWithoutDollar);
        System.out.println("Hourly wages input without dollar in Double " + updatedHourlyWagesDouble);

    }

    @Then("validate hourly wages increased after updated")
    public void validateHourlyWagesIncreasedAfterUpdated() {
        Assert.assertTrue("Updated value should be Greater than old wages", hourlyWagesDouble < updatedHourlyWagesDouble);
    }




    @Then("validate hourly wages decreased after updated")
    public void validateHourlyWagesDecreasedAfterUpdated() {
        Assert.assertTrue("Updated value should be Greater than old wages", hourlyWagesDouble > updatedHourlyWagesDouble);
    }
}