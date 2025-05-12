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

public class RemoveFromCartTest {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://naveenautomationlabs.com/opencart/");
    }

    @Test
    public void testRemoveProductFromCart() throws InterruptedException {
        // البحث عن MacBook
        driver.findElement(By.name("search")).sendKeys("MacBook");
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

        // الضغط على Add to Cart
        driver.findElement(By.xpath("//span[text()='Add to Cart']")).click();
        Thread.sleep(2000);

        // فتح السلة من الأعلى
        driver.findElement(By.id("cart")).click();
        Thread.sleep(1000);

        // الضغط على زر X لإزالة المنتج
        WebElement removeBtn = driver.findElement(By.cssSelector("button.btn.btn-danger.btn-xs"));
        removeBtn.click();
        Thread.sleep(2000); // انتظر إزالة العنصر

        // تحقق من أن السلة فارغة
        WebElement cartTotal = driver.findElement(By.id("cart-total"));
        Assert.assertTrue(cartTotal.getText().contains("0 item(s)"), "❌ السلة ليست فارغة بعد إزالة المنتج.");

        System.out.println("✅ تم إزالة المنتج من عربة التسوق بنجاح");
    }

    @AfterClass public void tearDown(){
        driver.quit();
    }
}
