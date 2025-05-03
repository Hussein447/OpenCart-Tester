package com.opencart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegisterTest {
    WebDriver driver;
    public static String email;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRegister() throws InterruptedException {
        email = "test" + UUID.randomUUID() + "@example.com";

        // إدخال بيانات التسجيل
        driver.findElement(By.id("input-firstname")).sendKeys("Test");
        driver.findElement(By.id("input-lastname")).sendKeys("User");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys("0123456789");
        driver.findElement(By.id("input-password")).sendKeys("Password123");
        driver.findElement(By.id("input-confirm")).sendKeys("Password123");

        // الموافقة على الشروط
        WebElement agreeCheckbox = driver.findElement(By.name("agree"));
        if (!agreeCheckbox.isSelected()) {
            agreeCheckbox.click();
        }

        // الضغط على زر التسجيل
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(3000); // انتظار حتى يتم تسجيل الحساب

        // التحقق من نجاح التسجيل
        boolean isRegistered = driver.getPageSource().contains("Your Account Has Been Created!");
        Assert.assertTrue(isRegistered, "Registration Failed!");

        System.out.println("✅ تم تسجيل الحساب بنجاح باستخدام الإيميل: " + email);
    }
}
