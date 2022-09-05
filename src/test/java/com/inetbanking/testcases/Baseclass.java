package com.inetbanking.testcases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;
 
public class Baseclass {

	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getAppURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
	    logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
			
		} else if (br.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
			
		}  
		driver.get(baseURL);
	
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	
	public void captureScreeShot(WebDriver driver,String name) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+ "/Screenshots/"+name+".png");
		FileUtils.copyFile(source, target);
		System.out.println("ScreenShot has taken");
		}
	
	
	public String randomstring() {
		
		String generatedstring= RandomStringUtils.randomAlphabetic(8);
		return generatedstring;
	}
	
	public String randomnum() {
		
		String generatedstring2= RandomStringUtils.randomNumeric(4);
		return generatedstring2;
	}
	
	
	
	
}
