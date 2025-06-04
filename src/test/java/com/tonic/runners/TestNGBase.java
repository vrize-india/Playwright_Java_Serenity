package com.tonic.runners;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

public class TestNGBase extends io.cucumber.testng.AbstractTestNGCucumberTests {
    @BeforeSuite(alwaysRun = true)
    @Parameters({"cucumberTags"})
    public void setCucumberTags(@Optional("") String cucumberTags) {
        if (cucumberTags != null && !cucumberTags.isEmpty()) {
            System.setProperty("cucumber.filter.tags", cucumberTags);
        }
    }
} 