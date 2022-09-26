package support;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class Driver {

    public static WebDriver aDriver;

    public Driver(WebDriver aDriver) {
        this.aDriver = aDriver;
    }

    public static WebDriver initialiseDriver() {
        WebDriverManager.chromedriver().setup();
        aDriver = new ChromeDriver();
        aDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return aDriver;
    }

    public static void quiteDriver() {
        try {
            aDriver.close();
            if (aDriver != null) {

                aDriver.quit();
                aDriver = null;
            }
        }
        catch (UnreachableBrowserException e) {
            System.out.println("Browser has already Shutdown");
        }
    }


    public static void screenShotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) try {
            scenario.log(scenario.getName() + " ---- " + scenario.getId()
                    + " ------- " + scenario.getStatus() + "\n"
                    + aDriver.getCurrentUrl());

            byte[] screenshot = ((TakesScreenshot) aDriver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot"+ getCurrentTimestamp());

        }
        catch (WebDriverException screenShotException) {
            System.err.println(screenShotException.getMessage());
        }
    }

    private static long getCurrentTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        Timestamp currentTimestamp = Timestamp.valueOf(now);
        return currentTimestamp.getTime();
    }
}
