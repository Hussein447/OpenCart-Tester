package com.opencart.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }

    @Test(dependsOnMethods = "com.opencart.tests.RegisterTest.testRegister") // التسجيل أولا
    public void testLogin() throws InterruptedException {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

        // إدخال بيانات تسجيل الدخول
        driver.findElement(By.id("input-email")).sendKeys(RegisterTest.email);
        driver.findElement(By.id("input-password")).sendKeys("Password123");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(3000); // انتظار تحميل الصفحة

        // التحقق من نجاح تسجيل الدخول
        boolean isLoggedIn = driver.getPageSource().contains("My Account");
        Assert.assertTrue(isLoggedIn, "Login Failed!");

        System.out.println("✅ تسجيل الدخول ناجح بالإيميل: " + RegisterTest.email);
    }
}
