/**
 * ****************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Codenvy, S.A. - initial API and implementation
 * *****************************************************************************
 */
package com.codenvy.corp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Musienko Maxim
 */
public class MainPage {
    WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    String burnDownNamePlf;
    String burnDownNameIDE;
    int    agileIdIDE;
    int    agileIdPLF;
    private String baseUrl            = "https://jira.codenvycorp.com";
    private String mangeViewUrlIDE    = "https://jira.codenvycorp.com/secure/RapidBoard.jspa?rapidView=%d&view=reporting&chart=burndown";
    private String mangeViewUrlCLDIDE = "https://jira.codenvycorp.com/secure/RapidBoard.jspa?rapidView=%d&view=reporting&chart=burndown";

    private interface Locators {
        String LOGIN_FIELD_ID               = "login-form-username";
        String PASSWORD_FIELD_ID            = "login-form-password";
        String LOGIN_BTN                    = "login";
        String BURN_DOWN_CHART_CONTAINER_ID = "ghx-chart-wrap";
        String rapidIdForIDE                = "//a[text()='IDE']";
        String rapidIdForPLF                = "//a[text()='PLF']";


    }

    @FindBy
            (id = Locators.LOGIN_FIELD_ID)
    WebElement loginField;


    @FindBy
            (id = Locators.PASSWORD_FIELD_ID)
    WebElement passField;

    @FindBy
            (id = Locators.BURN_DOWN_CHART_CONTAINER_ID)
    WebElement burnDownMAinContainer;


    @FindBy
            (id = Locators.LOGIN_BTN)
    WebElement loginBtnb;

    public void gotoMainPageWaitAuthorizePageAndLogin(String login, String passWord) throws InterruptedException {
        driver.get(baseUrl);
        new WebDriverWait(driver, 40).until(ExpectedConditions.visibilityOfAllElements(Arrays.asList(loginField, passField)));
        loginField.sendKeys(login);
        passField.sendKeys(passWord);
        loginBtnb.click();
        Thread.sleep(15000);
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Agile"))).click();
        initId();

    }

    public void gotoManageViewAndGoToIdeBornDown() throws IOException, InterruptedException {
        driver.get(String.format(mangeViewUrlIDE, agileIdIDE));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(burnDownMAinContainer));
        burnDownNameIDE =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("ghx-items-trigger"))).getText();
        Thread.sleep(10000);
        File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Point p = burnDownMAinContainer.getLocation();

        int width = burnDownMAinContainer.getSize().getWidth();
        int height = burnDownMAinContainer.getSize().getHeight();

        BufferedImage img = ImageIO.read(screen);

        BufferedImage dest = img.getSubimage(p.getX(), p.getY() - 50, width,
                                             height);

        ImageIO.write(dest, "png", screen);
        File file = new File(burnDownNameIDE + ".png");
        FileUtils.copyFile(screen, file);
        addText(file, burnDownNameIDE);
    }


    public void gotoManageViewAndGrabCLDIDEBornDown() throws IOException, InterruptedException {
        driver.get(String.format(mangeViewUrlCLDIDE, agileIdPLF));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(burnDownMAinContainer));
        burnDownNamePlf =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("ghx-items-trigger"))).getText();
        Thread.sleep(10000);
        File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Point p = burnDownMAinContainer.getLocation();
        int width = burnDownMAinContainer.getSize().getWidth();
        int height = burnDownMAinContainer.getSize().getHeight();
        BufferedImage img = ImageIO.read(screen);
        BufferedImage dest = img.getSubimage(p.getX(), p.getY() - 50, width,
                                             height);
        ImageIO.write(dest, "png", screen);
        File file = new File(burnDownNamePlf + ".png");
        FileUtils.copyFile(screen, file);
        addText(file, burnDownNamePlf);

    }


    private void initId() {
        String idAttrIDE =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.rapidIdForIDE)))
                                             .getAttribute("id");
        String idAttrPLF =
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.rapidIdForPLF)))
                                             .getAttribute("id");
        agileIdIDE = Integer.parseInt(idAttrIDE.replace("rapidb_lnk_", "").replace("_lnk", ""));
        agileIdPLF = Integer.parseInt(idAttrPLF.replace("rapidb_lnk_", "").replace("_lnk", ""));
    }

    private void addText(File file, String burnDownInfo) throws IOException {
        BufferedImage image = ImageIO.read(file);
        Graphics g = image.getGraphics();
        g.setColor(Color.RED);
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(burnDownInfo, 550, 45);
        g.dispose();
        ImageIO.write(image, "png", file);
    }
}
