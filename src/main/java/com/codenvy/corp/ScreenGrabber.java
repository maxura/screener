package com.codenvy.corp;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
        init.JIRA.gotoMainPageWaitAuthorizePageAndLogin(readCredential("user.login"), readCredential("user.password"));
        init.JIRA.gotoManageViewAndGoToIdeBornDown();
        init.JIRA.gotoManageViewAndGrabCLDIDEBornDown();
    }


    /**
     * Read credential from local file with paas credentials. Path to file had been defined in selenium.properties file in property
     * "paas.credentials.file".
     *
     * @param credentialName
     * @return
     */
    private static String readCredential(String credentialName) throws IOException {
        Properties paasCredentials = new Properties();
        FileInputStream propertiesFile = new FileInputStream(new File("").getAbsolutePath() + "/credjira");
        paasCredentials.load(propertiesFile);
        propertiesFile.close();
        return paasCredentials.getProperty(credentialName);
    }


}
