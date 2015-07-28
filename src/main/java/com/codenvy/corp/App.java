package com.codenvy.corp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    static WebDriver driver;
    private static int countRun = 24;

    public static void main(String[] args) throws IOException, InterruptedException {
        while (countRun != 0) {
            try {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                ScreenGrabber screenGrabber = new ScreenGrabber(driver);
                screenGrabber.screenChart();
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
                driver.quit();

            } finally {
                driver.quit();
            }
            driver.quit();
            countRun--;
            Thread.sleep(3600 * 1000);
        }

    }
}
