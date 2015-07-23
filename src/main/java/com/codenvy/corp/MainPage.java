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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

/**
 * @author Musienko Maxim
 */
public class MainPage {
    WebDriver driver;

    MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private String baseUrl      = "https://jira.codenvycorp.com";
    private String mangeViewUrl = "https://jira.codenvycorp.com/secure/RapidBoard.jspa?rapidView=130&projectKey=IDEX&view=reporting";


    private interface Locators {
        String LOGIN_FIELD_ID               = "login-form-username";
        String PASSWORD_FIELD_ID            = "login-form-password";
        String LOGIN_BTN                    = "login";
        String BURN_DOWN_CHART_CONTAINER_ID = "ghx-chart-wrap";
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
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Agile")));
        Thread.sleep(15000);

    }

    public void gotoManageViewAndGoToIdeBornDown() {
        driver.get(mangeViewUrl);
        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("IDE"))).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(burnDownMAinContainer));

    }


}
