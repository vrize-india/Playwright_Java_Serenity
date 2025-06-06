package com.tonic.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "src/test/resources/features/web/items_add_new.feature",
    glue = {"com.tonic.stepDefinitions", "com.tonic.hooks"},
    plugin = {"pretty", "summary"},tags = " @TONIC7341"
)
public class TestRunner extends TestNGBase {}

