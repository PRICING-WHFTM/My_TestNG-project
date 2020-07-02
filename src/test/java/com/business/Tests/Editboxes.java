package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Editboxes extends Base {


    @Test
    public void editBoxTesting() throws InterruptedException {
        extentLogger=extentReports.createTest("EditBoxes checking test");
        driver.get("https://the-internet.herokuapp.com/forgot_password");
        WebElement editbox = driver.findElement(By.id("email"));
        Assert.assertTrue(editbox.isDisplayed());
        editbox.sendKeys("anar@gmail.com" + Keys.ENTER);
        //editbox.submit();
        String notification = driver.findElement(By.id("content")).getText();
        extentLogger.info("Verifying the result");
        Assert.assertEquals(notification, "Your e-mail's been sent!");
    }
}
