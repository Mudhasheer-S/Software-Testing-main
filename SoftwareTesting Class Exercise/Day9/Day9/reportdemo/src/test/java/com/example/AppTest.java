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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class AppTest 
{
    public WebDriver driver;
    public XSSFWorkbook workbook;
    public String username;
    public String password;
    public ExtentReports reports;
    public ExtentTest test1,test2,test3;




    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException
    {
        String path = "D:\\Testing\\Day9\\reportdemo\\src\\Reports\\reports.html";
        reports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        reports.attachReporter(spark);

        spark.config().setDocumentTitle("Report");
        spark.config().setTheme(Theme.DARK);

        driver = new ChromeDriver();
        
        FileInputStream fis = new FileInputStream("D:\\Testing\\Day9\\reportdemo\\src\\ExcelSheet\\Input.xlsx");
        workbook = new XSSFWorkbook(fis);
        
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        
        username = sheet.getRow(1).getCell(0).getStringCellValue();
        password = sheet.getRow(1).getCell(1).getStringCellValue();
        
    }
    @Test
    public void TestCase1() throws InterruptedException
    {
        driver.get("http://dbankdemo.com/bank/login");
        Thread.sleep(6000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(3000);
        
        driver.findElement(By.id("submit")).click();;
        Thread.sleep(3000);

        test1=reports.createTest("Login Test");
        if(driver.getCurrentUrl().contains("home"))
        {
            test1.log(Status.PASS, "Login Successful Redirected to HomePage");
            System.out.println("Login Successful Redirected to HomePage");
        }
        else
        {
            test1.log(Status.FAIL, "Login Failed");
            System.out.println("Login Failed");

        }

    }
    @Test
    public void TestCase2() throws InterruptedException
    {
        driver.get("http://dbankdemo.com/bank/login");
        Thread.sleep(6000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(3000);
        
        driver.findElement(By.id("submit")).click();;
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='deposit-menu-item']")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='selectedAccount']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='selectedAccount']/option[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='amount']")).sendKeys("5000");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();

        // driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).click();
        String check = driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        test2=reports.createTest("Amount Deposit Test");
        if(check.contains("5000"))
        {
            test2.log(Status.PASS, "Amount Deposited");
            System.out.println("Amount Deposited");
        }
        else
        {
            test2.log(Status.FAIL, "Amount is not Deposited");
            System.out.println("Amount is not Deposited");
        }

    }
    @Test
    public void Testcase3() throws InterruptedException
    {
        driver.get("http://dbankdemo.com/bank/login");
        Thread.sleep(6000);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(3000);
        
        driver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='withdraw-menu-item']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='selectedAccount']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='selectedAccount']/option[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='amount']")).sendKeys("3000");;
        Thread.sleep(3000);
        
        driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]")).click();
        String check = driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")).getText();
        test3=reports.createTest("Amount Withdraw Test");
        if(check.contains("3000"))
        {
            test3.log(Status.PASS, "Amount Withdrawed");
            System.out.println("Amount Withdrawed");
        }
        else
        {
            test3.log(Status.FAIL, "Amount is not Withdrawed");
            System.out.println("Amount is not Withdrawed");
        }
        Thread.sleep(3000);
        
    }

    // @Test
    // public void Test_report()
    // {
    //     ExtentTest test = reports.createTest("Test1");
        
    //     test.log(Status.PASS,"Login is Successful redirected to home page");
    // }

    @AfterTest
    public void afterTest() throws IOException
    {
        reports.flush();
        workbook.close();
        driver.quit();
    }

}
