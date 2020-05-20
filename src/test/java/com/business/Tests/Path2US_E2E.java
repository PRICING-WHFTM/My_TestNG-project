package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.JSUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Path2US_E2E extends Base {

    @Test
    public void path2US() throws InterruptedException {
        extentLogger = report.createTest("Path2USE23");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.path2usa.com/travel-companions");
        WebElement from = driver.findElement(By.id("travel_from"));
        from.sendKeys("Arkansas");
        Thread.sleep(500);
        from.sendKeys(Keys.DOWN);
        from.sendKeys(Keys.ENTER);
        Assert.assertEquals(from.getAttribute("value"), "Northwest Arkansas Regional Airport (XNA) Fayetteville");
        WebElement to = driver.findElement(By.id("travel_to"));
        to.sendKeys("JFK");
        to.sendKeys(Keys.ARROW_DOWN);
        to.sendKeys(Keys.ENTER);
        Thread.sleep(700);
        // Assert.assertTrue(to.getAttribute("value").contains("John F. Kennedy International Airport"));
        //Choose July 15
        WebElement travelDate = driver.findElement(By.id("travel_date"));
        travelDate.click();
        WebElement month = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']"));
        WebElement next = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'»')]"));
        while (!month.getText().contains("July")) {
            next.click();
        }
        List<WebElement> days = driver.findElements(By.xpath("//td[@class='day']"));
        for (WebElement day : days) {
            if (day.getText().contains("15")) {
                day.click();
                break;
            }
        }
        Thread.sleep(1000);
        Assert.assertTrue(travelDate.getAttribute("value").contains("07-15"));
        WebElement weeks = driver.findElement(By.id("datebetween"));
        Select selectWeek = new Select(weeks);
        selectWeek.selectByVisibleText("+ - 2 Weeks");
        Assert.assertEquals(selectWeek.getFirstSelectedOption().getText(), "+ - 2 Weeks");
        WebElement airLine = driver.findElement(By.id("travel_airline"));
        Select selectAirline = new Select(airLine);
        selectAirline.selectByVisibleText("Air Canada");
        Assert.assertEquals(selectAirline.getFirstSelectedOption().getText(), "Air Canada");
        WebElement language = driver.findElement(By.id("travel_language"));
        Select selectLanguage = new Select(language);
        selectLanguage.selectByVisibleText("English");
        Assert.assertEquals(selectLanguage.getFirstSelectedOption().getText(), "English");
        WebElement searchBtn = driver.findElement(By.xpath("//button[contains(text(),'SEARCH')]"));
        // searchBtn.click(); --> ElementClickInterceptedException:
        JSUtil.clickElementByJS(searchBtn, driver);
        //Assert.assertTrue(driver.getPageSource().contains("Travel Companions"));


    }
}
