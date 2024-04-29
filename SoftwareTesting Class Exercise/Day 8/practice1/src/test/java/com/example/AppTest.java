package com.example;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    String name1;
    String name2;
    String name3;

    @BeforeTest
    public void Before() throws IOException
    {
        driver = new ChromeDriver();

        FileInputStream fis = new FileInputStream("C:\\Users\\rakes\\Desktop\\Software Testing\\day8\\practice1\\src\\Excel\\InputData.xlsx");
        workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet("Sheet1");
        name1=sheet.getRow(1).getCell(0).getStringCellValue();
        name2=sheet.getRow(2).getCell(0).getStringCellValue();
        name3=sheet.getRow(3).getCell(0).getStringCellValue();
    }
    
    @Test
    public void Test1() throws InterruptedException
    {
        driver.get("https://www.firstcry.com/");
        Thread.sleep(3000);
        
        driver.findElement(By.id("search_box")).clear();
        driver.findElement(By.id("search_box")).sendKeys(name1);
        Thread.sleep(2000);

        driver.findElement(By.id("search_box")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        
        String url = driver.getCurrentUrl();
        if(url.contains("kids-toys"))
        {
            System.out.println("Url contains kids toys");
        }
        Thread.sleep(2000);
    }

    @Test
    public void Test2() throws InterruptedException
    {
        driver.navigate().to("https://www.firstcry.com/");
        Thread.sleep(3000);
        
        driver.findElement(By.id("search_box")).clear();
        driver.findElement(By.id("search_box")).sendKeys(name2);
        Thread.sleep(2000);

        driver.findElement(By.id("search_box")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]/a/span")).click();
        Thread.sleep(2000);
        
        String heading = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
        if(heading.equals("Ethnic Wear"))
        {
            System.out.println("Ethnic Wear is filtered");
        }
        Thread.sleep(2000);
    }

    @Test
    public void Test3() throws InterruptedException
    {
        driver.navigate().to("https://www.firstcry.com/");
        Thread.sleep(4000);
        
        driver.findElement(By.id("search_box")).clear();
        driver.findElement(By.id("search_box")).sendKeys(name3);
        Thread.sleep(2000);

        driver.findElement(By.id("search_box")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        String heading1 = driver.findElement(By.xpath("//*[@id='maindiv']/div[1]/div/div[1]/div[2]/a")).getText();

        driver.findElement(By.xpath("//*[@id='maindiv']/div[1]/div/div[1]/div[2]/a")).click();
        Thread.sleep(2000);

        String current_tab = driver.getWindowHandle();// current tab
        for (String s : driver.getWindowHandles()) 
        {
            if (!s.equals(current_tab)) 
            {
                driver.switchTo().window(s);
            }
        }
        Thread.sleep(3000);
        
        String heading2 = driver.findElement(By.id("prod_name")).getText();
        if(heading1.equals(heading2))
        {
            System.out.println("Heading of the Product is matched");
        }
        Thread.sleep(3000);
    }

    @AfterTest
    public void After() throws IOException
    {
        workbook.close();
        driver.quit();
    }
}
