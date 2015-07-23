package com.codenvy.corp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    static WebDriver driver;


    public static void main(String[] args) throws IOException {
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
    }
}
