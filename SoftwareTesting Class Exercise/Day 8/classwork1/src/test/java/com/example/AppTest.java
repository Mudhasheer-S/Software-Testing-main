package com.example;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public WebDriver driver;
    public XSSFWorkbook workbook;
    public String username;
    public String password;

    @BeforeTest
    public void BeforeTest() throws IOException, InterruptedException
    {
        driver = new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        Thread.sleep(2000);
        
        FileInputStream fis = new FileInputStream("C:\\Users\\rakes\\Desktop\\Software Testing\\day8\\classwork1\\src\\Excel\\InputData.xlsx");
        workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet("Sheet1");
        username = sheet.getRow(1).getCell(0).getStringCellValue();
        password = sheet.getRow(1).getCell(1).getStringCellValue();
        
    }

    @Test
    public void Test1() throws InterruptedException
    {
        driver.navigate().to("http://dbankdemo.com/bank/login");
        Thread.sleep(2000);

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();

        if(driver.getCurrentUrl().contains("home"))
        {
            System.out.println("Successful Login");
        }
    }
    
    @Test
    public void Test2() throws InterruptedException
    {
        driver.navigate().to("http://dbankdemo.com/bank/login");
        Thread.sleep(2000);
        
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.id("deposit-menu-item")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("selectedAccount")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='selectedAccount']/option[3]")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("amount")).sendKeys("5000");
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(2000);
        
        WebElement s = driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]"));
        if(s.getText().equals("$5000.00"))
        {
            System.out.println(s.getText());
            System.out.println("Amount is debicted");
        }
    }
    
    @Test
    public void Test3() throws InterruptedException
    {
        driver.navigate().to("http://dbankdemo.com/bank/login");
        Thread.sleep(2000);
        
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("withdraw-menu-item")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("selectedAccount")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='selectedAccount']/option[3]")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("amount")).sendKeys("3000");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        Thread.sleep(2000);

        WebElement s = driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]"));
        if(s.getText().equals("$-3000.00"))
        {
            System.out.println(s.getText());
            System.out.println("Amount is debicted");
        }
    }

    @AfterTest
    public void After() throws IOException
    {
        driver.close();
        workbook.close();
    }

}
