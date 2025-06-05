package com.tonic.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "src/test/resources/features",
    glue = {"com.tonic.stepDefinitions", "com.tonic.hooks"},
    plugin = {"pretty", "summary"},tags = "@p3"
)
public class TestRunner extends TestNGBase {}

