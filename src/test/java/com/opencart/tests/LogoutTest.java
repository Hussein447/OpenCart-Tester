package com.opencart.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogoutTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // تسجيل الدخول الأول
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        driver.findElement(By.id("input-email")).sendKeys("teste0f626bc-b2ce-4c34-a59d-5dfebb765218@example.com");
        driver.findElement(By.id("input-password")).sendKeys("Password123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }
    @Test
    public void testLogout(){

        // الضغط على زر Logout

        driver.findElement(By.linkText("Logout")).click();

        WebElement continueBtn = driver.findElement(By.linkText("Continue"));
        Assert.assertTrue(continueBtn.isDisplayed(), "Logout failed!");
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
