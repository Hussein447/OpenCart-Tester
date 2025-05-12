package com.opencart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ContactUsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://naveenautomationlabs.com/opencart/");
    }

    @Test
    public void testContactUsForm() {
        // 1️⃣ الذهاب إلى صفحة Contact Us
        driver.findElement(By.linkText("Contact Us")).click();

        // 2️⃣ ملء البيانات
        driver.findElement(By.id("input-name")).sendKeys("Test User");
        driver.findElement(By.id("input-email")).sendKeys("testuser@example.com");
        driver.findElement(By.id("input-enquiry")).sendKeys("This is a test message for contact us form.");

        // 3️⃣ الضغط على Submit
        driver.findElement(By.cssSelector("input[type='submit']")).click();

        // 4️⃣ التحقق من رسالة النجاح
        WebElement successMsg = driver.findElement(By.cssSelector("#content > p"));
        Assert.assertTrue(successMsg.getText().contains("Your enquiry has been successfully sent to the store owner!"),
                "❌ رسالة النجاح لم تظهر");

        System.out.println("✅ تم إرسال نموذج التواصل بنجاح");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
