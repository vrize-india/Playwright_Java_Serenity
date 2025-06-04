package com.tonic.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "src/test/resources/features/web",
    glue = {"com.tonic.stepDefinitions", "com.tonic.hooks"},
    plugin = {"pretty", "summary"},tags = ""
)
public class TestRunner extends TestNGBase {}

