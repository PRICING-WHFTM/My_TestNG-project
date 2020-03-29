package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Grouping_tests extends Base {

    @Test(groups = "regression")
    public void verifyWrongLogin() {
        // create a new test in the report and give name : Wrong email and assword testing
        extentLogger = report.createTest("Wrong email and Password testing");
        // use the logger to log the steps
        extentLogger.info("Navigating The website");
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
        //using page objects
        extentLogger.info("entering username");
        pages.homepage.username.sendKeys(ConfigurationReader.getProperty("username"));
        extentLogger.info("entering password");
        pages.homepage.passWord.sendKeys(ConfigurationReader.getProperty("password"));
        extentLogger.info("clicking submit button");
        pages.homepage.submitBtn.click();
        // using the method which created from  page objects
        pages.homepage.login("zorayde", "xosemi");
        extentLogger.info("Validating the error message");
        String errorMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(errorMessage, "Invalid Login or Password.");
        extentLogger.pass("Wrong email and password");
    }

    @Test(groups = "regression")
    public void doubleClick_RightClick() {
        driver.get("https://www.amazon.com/");
        String maintTitle = driver.getTitle();
        // Do double click to element
        WebElement nextSign = driver.findElement(By.xpath("//i[@class='a-icon a-icon-next-rounded']"));
        actions.doubleClick(nextSign).build().perform();
        // Do right click
        WebElement searchEditbox = driver.findElement(By.id("twotabsearchtextbox"));
        actions.contextClick(searchEditbox).build().perform();
        searchEditbox.click();
        // Move to specific element
        WebElement customerService = driver.findElement(By.xpath("//a[contains(text(),'Customer Service')]"));
        actions.moveToElement(customerService).click().build().perform();
        String cstmrTitle = driver.getTitle();
        Assert.assertFalse(maintTitle.equalsIgnoreCase(cstmrTitle));
    }


    @Test(groups = "smoke")
    public void verifyBlankUsrNamme() {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
        pages.homepage.login("", "xosemi");
        String errorMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(errorMessage, "Invalid Login or Password.");
    }


}
