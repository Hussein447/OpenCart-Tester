package com.opencart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddToCartTest {
    WebDriver driver;

    @BeforeTest
    public  void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://naveenautomationlabs.com/opencart/");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testAddProductToCart() throws InterruptedException {
        // البحث عن MacBook
        driver.findElement(By.name("search")).sendKeys("MacBook");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        // الضغط على زر Add to Cart
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();
        Thread.sleep(2000); // ننتظر ظهور رسالة النجاح

        // التحقق من رسالة النجاح
        WebElement successMsg = driver.findElement(By.cssSelector(".alert-success"));
        Assert.assertTrue(successMsg.getText().contains("Success: You have added MacBook"),
                "❌ لم تظهر رسالة النجاح أو المنتج لم يُضف.");

        // فتح عربة التسوق من الأعلى
        driver.findElement(By.id("cart")).click();
        Thread.sleep(1000); // ننتظر القائمة المنسدلة

        // التحقق من ظهور المنتج في القائمة
        WebElement productInCart = driver.findElement(By.xpath("//td[@class='text-left']/a[contains(text(),'MacBook')]"));
        Assert.assertTrue(productInCart.isDisplayed(), "❌ المنتج غير موجود في عربة التسوق");

        System.out.println("✅ تم إضافة المنتج إلى عربة التسوق بنجاح");
    }

}
