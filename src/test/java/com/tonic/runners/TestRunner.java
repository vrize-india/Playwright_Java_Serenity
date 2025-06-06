package com.tonic.runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

@CucumberOptions(

    features = "src/test/resources/features",
    glue = {"com.tonic.stepDefinitions", "com.tonic.hooks"},
    plugin = {"pretty", "summary", "html:target/cucumber-report.html", "json:target/cucumber-report.json"},
        tags = "@TONIC11579"
)
@Listeners({AllureTestNg.class})
public class TestRunner  extends io.cucumber.testng.AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

