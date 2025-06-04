package com.tonic.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/web",
    glue = {"com.tonic.stepDefinitions", "com.tonic.hooks"},
    plugin = {"pretty", "summary"}
)
public class TestRunner extends TestNGBase {}