package com.inetbanking.testcases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends Baseclass{
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username is provided");
		lp.setPassword(pwd);
		logger.info("password is provided");
		lp.clickSubmit();
		logger.info("submit button is clicked");

		if(isAlertPresent()==true) {
			
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");

		}
		else {
			
			Assert.assertTrue(true);
			lp.clicklogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("Login passed");
		}
	}
	
	public boolean isAlertPresent() { // user defined method
		
		try {
			
			driver.switchTo().alert();
			return true;
		
		} catch(NoAlertPresentException e) {
			
			return false;
		}
	}
	
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]= new String[rownum][colcount];
		
		for(int i=1;i<rownum;i++) {
			
			for(int j=0;j<colcount;j++) {
				
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return logindata;
	}
	

	
	
	
}
