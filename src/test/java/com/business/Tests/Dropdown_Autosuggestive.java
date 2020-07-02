package com.business.Tests;

import com.business.Utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Dropdown_Autosuggestive extends Base {
    private static Logger log = LogManager.getLogger(Dropdown_Autosuggestive.class.getName());

    @Test
    public void autoSuggestiveDropdown() throws InterruptedException {
        extentLogger = extentReports.createTest("Verifying AutoSuggestive Dropdown");
        driver.get("https://www.azal.az/en/");
        WebElement clearText = driver.findElement(By.xpath("//a[@class='btn-clear']//i[@class='fa fa-times']"));
        clearText.click();
        Thread.sleep(1000);
        WebElement from = driver.findElement(By.id("departure1"));
        WebElement to = driver.findElement(By.id("arrival1"));
        from.sendKeys("Mi");
        Thread.sleep(1000);
        from.sendKeys(Keys.DOWN); // will point to 2nd option, because default points to 1st
        from.sendKeys(Keys.DOWN); // will point to 3rd option
        from.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        to.sendKeys("G");
        Thread.sleep(2000);
        for (int i = 0; i <= 2; i++) {
            to.sendKeys(Keys.DOWN); // will point to 4th option, loops 3 times
        }
        to.sendKeys(Keys.ENTER);
        Assert.assertTrue(from.getAttribute("value").contains("Minsk, Belarus"));
        Assert.assertTrue(to.getAttribute("value").contains("Baku, Heydar Aliyev, Azerbaijan"));
        Thread.sleep(2000);
    }


}
