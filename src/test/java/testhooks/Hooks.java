package testhooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static support.Driver.*;

public class Hooks {

    @Before
    public void setUp() {
        initialiseDriver();
        aDriver.manage().window().maximize();

    }

    @After
    public void tearDown(Scenario scenario) {
        screenShotOnFailure(scenario);
        quiteDriver();
    }
}
