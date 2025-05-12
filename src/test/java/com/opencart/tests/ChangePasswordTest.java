package com.opencart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ChangePasswordTest {
    WebDriver driver;
    String email = "test93b803ab-e544-4f77-b4f0-61acc35bc7d4@example.com";
    String currentPassword = "Password123";
    String newPassword = "Password456";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testChangePassword() {
        // 1️⃣ تسجيل الدخول
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(currentPassword);
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // تحقق من الدخول
        Assert.assertTrue(driver.getPageSource().contains("My Account"));

        // 2️⃣ الذهاب إلى صفحة تغيير كلمة السر
        driver.findElement(By.linkText("Change your password")).click();

        // 3️⃣ إدخال الباسورد الجديد وتأكيده
        driver.findElement(By.id("input-password")).sendKeys(newPassword);
        driver.findElement(By.id("input-confirm")).sendKeys(newPassword);

        // 4️⃣ الضغط على Continue
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // 5️⃣ التحقق من رسالة النجاح
        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
        Assert.assertTrue(successMsg.getText().contains("Success: Your password has been successfully updated."));

        System.out.println("✅ تم تغيير كلمة السر بنجاح من " + currentPassword + " إلى " + newPassword);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
