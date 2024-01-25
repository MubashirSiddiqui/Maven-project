package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.example.Methods.*;

public class TestScript {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.edge.driver", "/chromedriver/edgedriver.exe");
        driver = new EdgeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void test1login() {
        if (isLoggedIn()) {
            Methods.logout(driver);
            System.out.println("Logged out User");
            login(driver);
            System.out.println("Test Case 1: Login Page-Authenticate Successfully");
            delayeecution(3000);

        } else {
            login(driver);
            System.out.println("Test Case 1: Login Page-Authenticate Successfully");
            delayeecution(5000);

        }

    }

    @Test
    public void test2verifyDOB() {

        if (isalreadyloggedin()) {
            // If already logged in, perform logout
            Methods.verifyingDOB(driver);
            Methods.UpdatingDOB(driver);
            System.out.println("Test Case 2(Part c): Date of Birth has been updated");

        } else {
            // If not logged in, perform login
            login(driver);
            test2verifyDOB();
        }

    }
    @Test
    public void test3logout() {

        if (isalreadyloggedin()) {
            Methods.logout(driver);
            System.out.println("Test Case 3: Log out User Successfully");
        } else {
            login(driver);
           test3logout();
        }
    }

    @Test
    public void test2pageisfullyloaded() {
        if (isPageloaded(driver)) {
            System.out.println("Test Case 2(Part a): My Info page is fully loaded.");
        } else {
            System.out.println("Test Case 2(Part a): My Info page is not fully loaded.");
        }
        delayeecution(5000);
    }


    @AfterTest
    public void tearDown() {
        // Close the browser window
        if (driver != null) {
            driver.quit();
        }
    }

    private boolean isLoggedIn() {
        // Specify a unique element that indicates the user is logged in
        By loggedInIndicator = By.xpath("/html/body/div[1]/div[1]/div[1]/aside/nav/div[2]/ul/li[8]/a/span\n");
        // Check if the element is present on the page
        return !driver.findElements(loggedInIndicator).isEmpty();
    }

    private boolean isalreadyloggedin() {
        // Specify a unique element that indicates the user is logged in
        By loggedOutIndicator = By.cssSelector(".bi-caret-down-fill");
        // Check if the element is present on the page
        return !driver.findElements(loggedOutIndicator).isEmpty();
    }


}
