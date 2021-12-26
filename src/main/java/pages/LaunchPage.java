package pages;

import capabilities.BaseClassAndroid;
import capabilities.CapabilitiesAndroid;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class LaunchPage extends BaseClassAndroid {

    By welcomeLogo = By.id("welcome_logo");
    By welcomeTitle = By.id("welcome_title");
    By welcomeSubTitle = By.id("welcome_subtitle");
    By signInButton = By.id("welcome_register_btn");
    By logInButton = By.id("welcome_login_btn");

    public LaunchPage(AndroidDriver<AndroidElement> driver){
        this.driver = driver;
    }

    public boolean isLoaded(AndroidDriver<AndroidElement> driver){
        return driver.findElement(logInButton).isDisplayed();
    }

    public void verifyLogo(){
        List<AndroidElement> list = new ArrayList<>();
        list = driver.findElements(welcomeLogo);
        Assert.assertTrue(list.size()==1);
    }

    public String getWelcomeTitle(){
        return driver.findElement(welcomeTitle).getText();
    }

    public void verifyLogInButton(String expectedName){
        String actualName = driver.findElement(logInButton).getText();
        Assert.assertEquals(expectedName, actualName);
    }

    public void verifySignUpButton(String expectedName){
        String actualName = driver.findElement(signInButton).getText();
        Assert.assertEquals(expectedName, actualName);
    }

    public void verifyWelcomeLabel(String expectedLabel, String expectedSubLabel){
        String actualLabel = driver.findElement(welcomeTitle).getText();
        String actualSubLabel = driver.findElement(welcomeSubTitle).getText();
        Assert.assertEquals(expectedLabel, actualLabel);
        Assert.assertEquals(expectedSubLabel, actualSubLabel);
    }
}
