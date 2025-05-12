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

public class UserJourneyTest {
    WebDriver driver;
    String email;
    String password = "password123";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void userFullJourneyTest() throws InterruptedException{
        // 1️⃣ تسجيل حساب جديد
        email = "test" + UUID.randomUUID() + "@example.com";
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");

        driver.findElement(By.id("input-firstname")).sendKeys("Test");
        driver.findElement(By.id("input-lastname")).sendKeys("User");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys("0123456789");
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(password);

        WebElement agree = driver.findElement(By.name("agree"));
        if (!agree.isSelected()) agree.click();

        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Your Account Has Been Created!"));

        // 2️⃣ تسجيل الخروج بعد التسجيل
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.linkText("Continue")).click();

        // 3️⃣ تسجيل الدخول بالحساب الجديد
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Assert.assertTrue(driver.getPageSource().contains("My Account"));

        // 4️⃣ البحث عن منتج (MacBook)
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("MacBook");
        driver.findElement(By.cssSelector(".btn-default.btn-lg")).click();

        // 5️⃣ إضافة المنتج للسلة
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();
        Thread.sleep(2000);

        // 6️⃣ فتح عربة التسوق والتحقق من المنتج
        driver.findElement(By.id("cart")).click();
        Thread.sleep(1000);
        WebElement cartItem = driver.findElement(By.xpath("//td[@class='text-left']/a[contains(text(),'MacBook')]"));
        Assert.assertTrue(cartItem.isDisplayed());

        // 7️⃣ تسجيل الخروج
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Logout")).click();
        Assert.assertTrue(driver.getPageSource().contains("Account Logout"));
    }
}
