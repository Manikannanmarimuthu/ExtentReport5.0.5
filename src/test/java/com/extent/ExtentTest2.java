package com.extent;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentTest2 {

	@org.testng.annotations.Test
	public void Test() throws IOException {

		ExtentSparkReporter spark = new ExtentSparkReporter("index.html").viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY }).apply();
		;
		ExtentReports extent = new ExtentReports();

		ExtentSparkReporter faliedTest = new ExtentSparkReporter("failed-index.html").viewConfigurer().viewOrder()
				.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY }).apply();

		final File file = new File("extentConfig.xml");
		spark.loadXMLConfig(file);

		/*
		 * spark.config().setTheme(Theme.STANDARD);
		 * spark.config().setDocumentTitle("Mani QA Labs");
		 * spark.config().setReportName("Automation Report");
		 */
		extent.attachReporter(spark, faliedTest);

		com.aventstack.extentreports.ExtentTest test = extent.createTest("Login test");
		test.pass("URL Lunched Successfully");

		com.aventstack.extentreports.ExtentTest test1 = extent.createTest("Login test");
		test1.pass("In HomePage Successfully");
		test1.fail("Test");
		test1.info("Mtest");
		test1.pass(MarkupHelper.createLabel("Test case Pass", ExtentColor.GREEN));

		extent.flush();

		Desktop.getDesktop().browse(new File("index.html").toURI());
	}
}
