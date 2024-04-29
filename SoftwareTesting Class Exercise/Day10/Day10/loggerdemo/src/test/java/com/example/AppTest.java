package com.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AppTest 
{
    public WebDriver driver;
    
    public Logger logger;
    
    
    @BeforeTest
    public void beforeTest() 
    {
        driver = new ChromeDriver();
        logger = LogManager.getLogger(getClass());
    }

    @Test
    public void TestCase1() throws InterruptedException
    {
        driver.get("http://dbankdemo.com/bank/login");
        
        
        
        logger.info("Redirecting to dbankdemo website");
        Thread.sleep(6000);
        driver.findElement(By.id("username")).sendKeys("S@gmail.com");
        driver.findElement(By.id("password")).sendKeys("P@ssword12");
        Thread.sleep(3000);
        
        driver.findElement(By.id("submit")).click();;
        Thread.sleep(3000);

        if(driver.getCurrentUrl().contains("home"))
        {
            System.out.println("Login Successful Redirected to HomePage");
        }

    }
}
