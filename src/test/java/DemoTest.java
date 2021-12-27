import capabilities.BaseClassAndroid;
import capabilities.CapabilitiesAndroid;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LaunchPage;

import java.io.IOException;

import static capabilities.BaseClassAndroid.driver;

public class DemoTest extends BaseClassAndroid {

    LaunchPage launchPage;

    @Test
    public void verify_launching_page() {
        launchPage = new LaunchPage(driver);
        launchPage.verifyLogo();

        launchPage.verifySignUpButton("I'm new to Digibank");
        launchPage.verifyLogInButton("Log In");

        System.out.println("TESTS COMPLETED WITH TestNG");

    }

}
