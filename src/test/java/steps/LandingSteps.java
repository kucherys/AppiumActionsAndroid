package steps;

import capabilities.BaseClassAndroid;
//import cucumber.api.DataTable;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;
import pages.LaunchPage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LandingSteps extends BaseClassAndroid {

    LaunchPage androidLaunchPage;

    @Given("Appium server is running")
    public void appiumServerIsRunning() {
        service = startServer();
    }

    @Given("Appium server and simulator with application started")
    public void appiumServerAndApplicationiOSRunning() throws IOException, InterruptedException {
        setupClassGlobal();
    }

    @When("I verify landing page is loaded")
    public void verifyLandingPageLoaded() {
            androidLaunchPage = new LaunchPage(driver);
            Assert.assertTrue(androidLaunchPage.isLoaded(driver));

    }

    @Then("I verify elements on landing page")
    public void verifyElementsLandingDataPage(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

            for (Map<String, String> row : rows) {
                androidLaunchPage.verifyLogInButton(row.get("logInButton"));
//                androidLaunchPage.verifyWelcomeLabel(row.get("logoLabel"));
                androidLaunchPage.verifySignUpButton(row.get("signUpButton"));
            }

    }

    @Then("^I confirm tests completed with result (.+)$")
    public void confitmTets(String result) {
            System.out.println("CUCUMBER TESTS PASSED WITH RESILT: " + result);

    }

    @After
    public void stopAppiumServer(){
        System.out.println("Stop appium server");
        service.stop();
    }
}
