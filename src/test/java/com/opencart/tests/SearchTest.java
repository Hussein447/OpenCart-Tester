package com.opencart.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchTest {
    WebDriver driver;
    @BeforeTest
    public void seUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://naveenautomationlabs.com/opencart/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchProduct() {
        // البحث عن MacBook
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("MacBook");

        WebElement searchBtn = driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg"));
        searchBtn.click();

        // تحقق من وجود MacBook في النتائج
        WebElement product = driver.findElement(By.linkText("MacBook"));
        Assert.assertTrue(product.isDisplayed(), "❌ المنتج مش ظاهر في نتائج البحث");

        System.out.println("✅ تم العثور على MacBook في نتائج البحث بنجاح");
    }
}
