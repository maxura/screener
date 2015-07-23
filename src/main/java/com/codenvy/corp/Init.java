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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * //
 *
 * @author Musienko Maxim
 */
public class Init {
    WebDriver driver;
    public static MainPage JIRA;

    Init (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, JIRA = new MainPage(driver));
    }
}
