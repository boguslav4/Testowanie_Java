package com.example.seleniumdemo;

import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import seleniumdemo.Pages;



public class PrestashopDemoSiteTest {

	private static WebDriver driver;
	WebElement element;
	
	private final Pages pages;

	public PrestashopDemoSiteTest(Pages pages) {
		this.pages = pages;
	}


	@Given("you are on the homepage")
	public void homePage(){
		pages.home().open();
	}
	
	@When("you are looking for link")
	public void lookingForLink() {
		
	}
	
	@Then("link $linkName is found")
	public void foundLink(String linkName){
		element = pages.home().findElement(By.linkText(linkName));
		assertNotNull(element);
	}


	@Given("you are on the notebooki")
	public void notebookPage(){
		pages.notebooki().open();
	}
	
	@When("you are making screenshot")
	public void makingScreenshot(){
		File screenshot = ((TakesScreenshot)pages.notebooki()).getScreenshotAs(OutputType.FILE);
		assertNotNull(screenshot);
		
		try {
			FileUtils.copyFile(screenshot, new File("c:\\tmp\\MBAirTest_BC.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}
	
	@Then("screenshoot will be taken")
	public void checkingScreenshot(){	
		
	}


	@Given("you are on the homepage again")
	public void homePageAgain(){
		pages.home().open();
	}
	
	@When("you are waiting for image to load")
	public void loadedTest(){
		String name = pages.home().findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/a/img")).getAttribute("alt");
		extracted(name);
	}

	@Then("image will be loaded")
	private void extracted(String name) {
		assertEquals(name,"INFOLINIA 098 765 43 21, 0876 543 210");
	}
	
	
	@Given("you are on the homepage last")
	public void homePageLast(){
		pages.home().open();
	}
	
	@When("you click on checkbox")
	private void choose(){
		element = pages.home().findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/div/form/p/select"));
		element.sendKeys("Shure Incorporated");
	}
	
	@Then("you are on next page")
	private void nextPage(){
		String el;
		el = pages.shure().getCurrentUrl();
		assertNotNull(element);
	}

	
	@Given("you are on the homepage last time")
	public void homePageLastAgain(){
		pages.home().open();
	}
	
	@When("you take css value")
	private void cssFont(){
		element = pages.home().findElement(By.xpath("/html/body/div/div[2]/div[2]/div/h2"));
		String fS = element.getCssValue("font-size");
	}
	
	@Then("you check if proper")
	private void nextPage(String name){
		assertEquals(name,"12.1000003814697px");
	}



}
