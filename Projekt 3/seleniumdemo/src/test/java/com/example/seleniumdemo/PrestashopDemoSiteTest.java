//Na podstawie wyk³adów i kodu z
//https://inf.ug.edu.pl/~kuba/testowanie/
package com.example.seleniumdemo;

import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class PrestashopDemoSiteTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		System.setProperty("webdriver.chrome.driver", "c:\\tmp\\chromedriver.exe");
                System.setProperty("webdriver.chrome.logfile", "c:\\tmp\\chromedriver_BC.log");
		driver = new ChromeDriver();
                //pozwolmy uzytkownikowi cos zobaczyc, jak mowi instrukcja selenium :)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		driver.get("http://demo.presta.shop.pl/");
		
            element = driver.findElement(By.linkText("Notebooki"));
            assertNotNull(element);
	}
	
        @Test
        public void notebookPage(){
            driver.get("http://demo.presta.shop.pl/");
        
            driver.findElement(By.linkText("Notebooki")).click();
            element = driver.findElement(By.linkText("MacBook Air"));
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            assertNotNull(screenshot);

            try {
            FileUtils.copyFile(screenshot, new File("c:\\tmp\\MBAirTest_BC.png"));
            } catch (IOException e) {
            e.printStackTrace();
            }
            assertTrue(true);
        }

        @Test
        public void registrationPage() throws InterruptedException{
            driver.get("http://demo.presta.shop.pl/");

            driver.findElement(By.linkText("Log in")).click();
            driver.findElement(By.id("email_create")).sendKeys("testEmail@test.pl");
            driver.findElement(By.id("SubmitCreate")).click();
            String name = driver.findElement(By.id("email")).getAttribute("value");

            assertEquals(name,"testEmail@test.pl");
        }
        
       
        @Test
        public void imageLoadedTest(){
            driver.get("http://demo.presta.shop.pl/");

            String name = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/a/img")).getAttribute("alt");

            assertEquals(name,"INFOLINIA 098 765 43 21, 0876 543 210");
        }
        
        @Test
        public void addingToCartTest(){
            driver.get("http://demo.presta.shop.pl/");

            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div/ul/li[2]/div[2]/a[2]")).click();
            driver.findElement(By.xpath("/html/body/div/div[1]/div/div[4]/ul/li[1]/a")).click();
            element = driver.findElement(By.linkText("iPod shuffle"));

            assertNotNull(element);
        }
        
        @Test
        public void selectorTest(){
        driver.get("http://demo.presta.shop.pl/");
        
        element = driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/form/p/select"));
        element.sendKeys("Shure Incorporated");
        
        element = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/ul/li/div[1]/h3/a"));
        assertNotNull(element);
        }
        
        @Test
        public void cssTest(){
            driver.get("http://demo.presta.shop.pl/");
        
            element = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/h2"));
            String fS = element.getCssValue("font-size");
            
            //w rzadkich przypadkach wynik moze sie roznic w innej konfiguracji testowej
            assertEquals(fS,"12.1000003814697px");
        }
        
        
	@AfterClass
        //równie¿ usuwanie ciasteczek, jesli zostaly utworzone
	public static void cleanp() {
		driver.quit();
	}
}
