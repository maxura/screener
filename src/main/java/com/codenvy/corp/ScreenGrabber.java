package com.codenvy.corp;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by maxura on 20.07.2015.
 */
public class ScreenGrabber {
    WebDriver driver = new FirefoxDriver();
    protected  void screenOne() throws IOException {
        driver.get("https://codenvy.com/");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("d:\\tmp\\screenshot.png"));
    }
}
