package com.tonic.stepDefinitions.web;

import com.tonic.factory.PlaywrightFactory;
import com.tonic.pageObjects.web.ConfigurationPage;
import com.tonic.pageObjects.web.ItemsPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Map;
import io.cucumber.datatable.DataTable;

public class ItemsSteps {
    private ItemsPage itemsPage;
    private ConfigurationPage configurationPage;
    private Page page;

    @Given("the user is logged in to the {string} system with credentials username {string} and password {string}")
    public void user_logs_in(String url, String username, String password) {
        page = PlaywrightFactory.getPage();
        // Reuse login logic from existing step definitions
        page.navigate(url);
        // Assume ItemsPage or LoginPage has a login method
        itemsPage = new ItemsPage(page);
        itemsPage.login(username, password);
    }

    @And("User navigates to \"Configuration > menu configuration > Items\"")
    public void user_navigates_to_items() {
        if (itemsPage == null) itemsPage = new ItemsPage(PlaywrightFactory.getPage());
        itemsPage.navigateToItems();
    }

    @And("The user is on the Items screen")
    public void user_is_on_items_screen() {
        Assert.assertTrue("Items screen is not visible", itemsPage.isItemsScreenVisible());
    }

    @When("The user clicks on Add New CTA")
    public void user_clicks_add_new_cta() {
        itemsPage.clickAddNewButton();
    }

    @Then("A new blank row should be added at the top of the table")
    public void new_blank_row_added() {
        Assert.assertTrue("New blank row not added at top", itemsPage.isNewBlankRowAtTop());
    }

    @And("Disable the Add New CTA Button")
    public void disable_add_new_cta_button() {
        Assert.assertTrue("Add New CTA button is not disabled", itemsPage.isAddNewButtonDisabled());
    }

    @And("The rows should have the following columns and placeholders:")
    public void rows_should_have_columns_and_placeholders(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String rowName = row.get("Row Name");
            String placeholder = row.get("Placeholder");
            String mandatory = row.get("Mandatory");
            String validations = row.get("Validations");
            Assert.assertTrue("Column or placeholder missing: " + rowName,
                itemsPage.verifyColumnAndPlaceholder(rowName, placeholder, Boolean.parseBoolean(mandatory), validations));
        }
    }

    @When("the user enters invalid values in the \"Items\" field")
    public void user_enters_invalid_items() {
        itemsPage.enterInvalidItemName();
    }

    @Then("an error message \"Item name is required\" should be displayed below the field")
    public void error_message_item_name_required() {
        Assert.assertTrue("Error message for item name is not displayed", itemsPage.isItemNameRequiredErrorDisplayed());
    }

    @When("the user does not select any dropdown values in the \"Sales Groups\" field")
    public void user_does_not_select_sales_group() {
        itemsPage.leaveSalesGroupUnselected();
    }

    @Then("an error message \"Sales group is required\" should be displayed below the field")
    public void error_message_sales_group_required() {
        Assert.assertTrue("Error message for sales group is not displayed", itemsPage.isSalesGroupRequiredErrorDisplayed());
    }

    @And("you will need to add a name")
    public void you_will_need_to_add_a_name() {

        itemsPage.addItemName("Test Item " + itemsPage.time);
    }

    @And("you will need to choose a Sales Group")
    public void you_will_need_to_choose_a_sales_group() throws InterruptedException {
        itemsPage.chooseSalesGroup("Burgers");
    }

    @And("you will need to choose a modifier sets")
    public void you_will_need_to_choose_a_modifier_sets() throws InterruptedException {
        itemsPage.chooseModifierSets("Test");
    }

    @When("you click on save at the top right corner")
    public void you_click_on_save_at_the_top_right_corner() throws InterruptedException {
        itemsPage.clickSaveButton();
    }

    @Then("you receive a message Item Added")
    public void you_receive_a_message_item_added() throws InterruptedException {
        Assert.assertTrue("Item Added message not displayed", itemsPage.isItemAddedMessageDisplayed());
    }
} 