package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;




public class sparkextent extends TestListenerAdapter {
	
	
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		
	//String RName="NSpark-Report.html";
	sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/target/");
	//sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/spark-config.xml");
	sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/spark-config.xml");
	extent=new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Host name","localhost");
	extent.setSystemInfo("Environemnt","QA");
	extent.setSystemInfo("user","Sukanya");
	}
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		// send the passed information to the report with GREEN color highlighted
		System.out.println("OnTestSuccess from Listeners");
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with RED color highlighted
		System.out.println("OnTestFail from Listeners");

		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath); 
		
		if(f.exists())
		{
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		System.out.println("OnTestSKip from Listeners");

	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}