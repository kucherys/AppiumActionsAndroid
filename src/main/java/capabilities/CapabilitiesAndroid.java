package capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CapabilitiesAndroid {
    public static AppiumDriverLocalService service;

    public AppiumDriverLocalService startServer() {
        boolean flag = checkIfServerIsRunning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public static boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public static void StartEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(System.getProperty("user.dir") + "//src//main//resources//startEmulator.sh");
        Thread.sleep(6000);
    }

    public static AndroidDriver<AndroidElement> capabilitiesAndroid(String appName) throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> androidDriver;
        File appDir = new File("src");
        File app = new File(appDir, appName);
        DesiredCapabilities cap = new DesiredCapabilities();
//        StartEmulator();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidEmulator");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        androidDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return androidDriver;
    }
}
