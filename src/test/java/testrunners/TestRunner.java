package testrunners;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@CucumberOptions(
        features="./src/main/resources/features/",
        glue={"pageobjects","testrunners","testhooks","stepdefinitions"},
        plugin={ "pretty"}

)
@RunWith(io.cucumber.junit.Cucumber.class)

public class TestRunner {

}
