/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.solent.spring.map.integration.test;

/**
 *
 * @author Hamza
 */
import org.junit.Test;

import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;

public class MapApplicationIT {
  private WebDriver driver;
  private String baseUrl;
  private Map<String, Object> vars;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  
  public void setUp() throws Exception {
        // Set the path to the chromedriver executable
    String driverLocation = System.getProperty("webdriver.chrome.driver");

    // Check if the system property is already set
    if (driverLocation == null) {
        // Set the path based on the operating system
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            driverLocation = "chromedriver.exe";
        } 

        System.setProperty("webdriver.http.factory", "jdk-http-client");

        // Set the system property
        System.setProperty("webdriver.chrome.driver", driverLocation);
    }
        ChromeOptions chromeOptions = new ChromeOptions();
        // Set up any desired Chrome options
        chromeOptions.addArguments("--port=9515"); // Use a different port number
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--remote-allow-origins=*");    




        driver = new ChromeDriver(chromeOptions);
        if (driver != null) {
    // Your code here
        } else {
            System.out.println("WebDriver is null");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
  }

  @Test
  public void testUntitledTestCase() throws Exception {
     driver.get("https://com619-devops.uksouth.cloudapp.azure.com/home");
    driver.findElement(By.linkText("Login or create new account")).click();
    driver.get("https://com619-devops.uksouth.cloudapp.azure.com/login");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("hamzamohibe");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("hamzatest2");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.linkText("Admin")).click();
    driver.findElement(By.linkText("Manage Map Points")).click();
    driver.get("https://com619-devops.uksouth.cloudapp.azure.com/poiList");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='No Image Uploaded'])[1]/following::button[1]")).click();
    driver.get("https://com619-devops.uksouth.cloudapp.azure.com/viewModifyPoi?pointId=25");
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("TEST2");
    driver.findElement(By.linkText("Home")).click();
    driver.get("https://com619-devops.uksouth.cloudapp.azure.com/home");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

