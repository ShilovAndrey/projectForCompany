package tests.hooks;

import com.slotegrator.ui.Steps;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends Steps {

    @Before(value = "@Run")
    public void before(Scenario scenario) {
        openBrowserStep();
    }

    @After(value = "@Run")
    public void after(Scenario scenario) {
        closeBrowserStep();
    }
}
