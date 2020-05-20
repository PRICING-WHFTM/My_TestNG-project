package com.business.Tests;

import com.business.Utilities.Base;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Actions_mouse extends Base {

    @Test
    public void doubleClick_RightClick() {
        extentLogger = report.createTest("Double click on element");
        driver.get("https://www.amazon.com/");
        String maintTitle = driver.getTitle();
        // Do double click to element
        WebElement nextSign = driver.findElement(By.xpath("//i[@class='a-icon a-icon-next-rounded']"));
        actions.moveToElement(nextSign).doubleClick().build().perform();
        // Do right click
        WebElement searchEditbox = driver.findElement(By.id("twotabsearchtextbox"));
        actions.moveToElement(searchEditbox).contextClick().build().perform();
        searchEditbox.click();
        // Move to specific element
        WebElement customerService = driver.findElement(By.xpath("//a[contains(text(),'Customer Service')]"));
        actions.moveToElement(customerService).click().build().perform();
        String cstmrTitle = driver.getTitle();
        extentLogger.info("Verifying th result");
        Assert.assertFalse(maintTitle.equalsIgnoreCase(cstmrTitle));

    }


    /**
     * Here we use KEYS for down/up
     */
    @Test
    public void mouseHower_MoveToElement() {
        extentLogger = report.createTest("Mouse hover test");
        driver.get("https://www.amazon.com/");
        WebElement prime = driver.findElement(By.xpath("//span[contains(text(),'Try Prime')]"));
        extentLogger.info("Verifying the result");
        actions.moveToElement(prime).build().perform();
    }

    @Test
    public void scrollDown_scrollUP() throws InterruptedException {
        extentLogger = report.createTest("Scroll up amd down");
        driver.get("https://www.airtelxstream.in/");
        int i = 0;
        int j = 0;
        while (i < 300) {
            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
            i++;
        }
        Thread.sleep(2000);
        while (j < 300) {
            actions.sendKeys(Keys.PAGE_UP).build().perform();
            j++;
        }

    }

    @Test
    public void dragAndDrop() {
        extentLogger = report.createTest("Drag and drop testing");
        driver.get("https://jqueryui.com/droppable/");
        WebElement mainFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(mainFrame);
        extentLogger.info("Verifying the result");
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(draggable, draggable).build().perform();

    }
}


