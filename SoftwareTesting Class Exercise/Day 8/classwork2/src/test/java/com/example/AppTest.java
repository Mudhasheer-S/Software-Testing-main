package com.example;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    XSSFWorkbook workbook;
    String username;
    String password;

    @BeforeTest
    public void before() throws IOException
    {
        driver = new ChromeDriver();
        FileInputStream fis = new FileInputStream("C:\\Users\\rakes\\Desktop\\Software Testing\\day8\\classwork2\\src\\Excel\\InputData.xlsx");
        workbook = new XSSFWorkbook(fis);
        
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        username = sheet.getRow(1).getCell(0).getStringCellValue();
        password = sheet.getRow(1).getCell(1).getStringCellValue();
    }

    @Test
    public void Test1() throws InterruptedException
    {
        driver.get("https://www.demoblaze.com/");
        Thread.sleep(7000);

        driver.findElement(By.linkText("Laptops")).click();
        Thread.sleep(2000);
        
        String name = driver.findElement(By.xpath("//*[@id='tbodyid']/div[3]/div/div/h4/a")).getText();
        driver.findElement(By.xpath("//*[@id='tbodyid']/div[3]/div/div/h4/a")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='tbodyid']/div[2]/div/a")).click();
        Thread.sleep(2000);
        
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        
        driver.findElement(By.id("cartur")).click();
        Thread.sleep(5000);

        String name1 = driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
        if(name.equals(name1))
        {
            System.out.println("Object Name matches");
        }
    }
    
    @Test
    public void Test2() throws InterruptedException
    {
        driver.navigate().to("https://www.demoblaze.com/");
        driver.findElement(By.id("login2")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("loginusername")).sendKeys(username);
        Thread.sleep(2000);

        driver.findElement(By.id("loginpassword")).sendKeys(password);
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//*[@id='logInModal']/div/div/div[3]/button[2]")).click();
        Thread.sleep(5000);
        
        String name = driver.findElement(By.id("nameofuser")).getText();
        if(name.contains(username))
        {
            System.out.println("User Name Matches");
        }
        Thread.sleep(5000);
    }

    @AfterTest
    public void After() throws IOException
    {
        driver.close();
        workbook.close();
    }
}
