package com.inetbanking.testcases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.AddCustomerPage;
import com.inetbanking.pageobjects.LoginPage;

public class TC_AddCustomerTest_003 extends Baseclass{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		logger.info("Loggedin");
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		addcust.custName("Sukanya");
		addcust.custGender();
		addcust.custdob("20", "20", "2020");
		Thread.sleep(3000);
		addcust.custaddress("Chicago");
		addcust.custcity("Bolingbrook");
		addcust.custstate("IL");
		addcust.custpin("60490");
		addcust.custtelephone("123456789");
		
		String email = randomstring()+"gmail.com";
		addcust.custemail(email);
		addcust.btnSubmit();
		
		Thread.sleep(2000);
		
		boolean result = driver.getPageSource().contains("Customer Registered Successfully!!!");
				if(result==true) {
					Assert.assertTrue(true);
				} else {
					
					captureScreeShot(driver,"addNewCustomer");
					Assert.assertTrue(false);
				}
		
	}
	
	
	
	
	
}
