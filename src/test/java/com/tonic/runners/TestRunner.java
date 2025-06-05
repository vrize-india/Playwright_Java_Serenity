package com.tonic.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    features = "src/test/resources/features",
    glue = {"com.tonic.stepDefinitions", "com.tonic.hooks"},
    plugin = {"pretty", "summary"},tags = "@TONIC-7229"
)
public class TestRunner extends TestNGBase {}

