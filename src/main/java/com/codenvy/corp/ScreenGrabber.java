package com.codenvy.corp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by maxura on 20.07.2015.
 */
public class ScreenGrabber {
    WebDriver driver;
    Init      init;

    ScreenGrabber(WebDriver driver) {
        this.driver = driver;
        init = new Init(driver);
    }

    protected void screenChart() throws IOException, InterruptedException {
        init.JIRA.gotoMainPageWaitAuthorizePageAndLogin("mmusienko", "vfrcbv_1978");
        init.JIRA.gotoManageViewAndGoToIdeBornDown();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(new Date().toString().replaceAll(" ", "_")+".png"));
    }


}
