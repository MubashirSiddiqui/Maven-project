package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Methods {
    public static void logout(WebDriver driver) {

        driver.findElement(By.cssSelector(".bi-caret-down-fill")).click();
        driver.findElement(By.linkText("Logout")).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void login(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Login = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input\n"));
        wait.until(ExpectedConditions.visibilityOf(Login));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector(".oxd-button")).click();
    }


    public static void verifyingDOB(WebDriver driver) {
        driver.findElement(By.cssSelector(".oxd-main-menu-item-wrapper:nth-child(6) .oxd-text")).click();
        delayeecution(5000);
        String dateFieldId = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[3]/div[2]/div[1]/div/div[2]/div/div/input\n";
        if (Methods.isDOBFieldFilled(driver, dateFieldId)) {
            System.out.println("Test Case 2(Part b): Date of Birth is already filled");
        } else {
            System.out.println("Test Case 2(Part b): Date of Birth is not filled");

        }

    }

    public static void UpdatingDOB(WebDriver driver) {
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".oxd-grid-item:nth-child(1) .oxd-date-wrapper .oxd-icon")).click();
        driver.findElement(By.cssSelector(".oxd-calendar-selector-month-selected")).click();
        driver.findElement(By.cssSelector(".oxd-calendar-dropdown--option:nth-child(9)")).click();
        driver.findElement(By.cssSelector(".oxd-calendar-selector-year-selected > .oxd-text")).click();
        driver.findElement(By.cssSelector(".oxd-calendar-dropdown--option:nth-child(23)")).click();
        driver.findElement(By.cssSelector(".oxd-calendar-date-wrapper:nth-child(23) > .oxd-calendar-date")).click();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0,0)");

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[5]/button\n")).click();
        delayeecution(5000);
    }




    public static boolean isPageloaded(WebDriver driver) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public static void delayeecution(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error during delay: " + e.getMessage());
        }

    }
    public static boolean isDOBFieldFilled(WebDriver driver, String dobFieldId) {
        WebElement dobField = driver.findElement(By.xpath(dobFieldId));
        String dobValue = dobField.getAttribute("value");
        return dobValue != null && !dobValue.isEmpty();
    }

}
