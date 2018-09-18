package com.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class OpeningWebURL {
	
	WebDriver driver;
	
	@BeforeSuite
	public void setup() throws MalformedURLException
	{
		DesiredCapabilities capabilities = DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"92010457a0ce2315");
		capabilities.setCapability(MobileCapabilityType.VERSION,"5.1.1");
		
		//capabilities.setCapability("appPackage", "com.android.chrome");
		//capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		//capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Won't start from the scratch without reseting the data.

			
		URL url= new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, capabilities);
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://www.system.netsuite.com");		
	}
	
	
	@Test
	public void getCurrentPageTitle()
	{
		String title = driver.getTitle();
		System.out.println("Current page title is : "+title);
	}
	
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}
}
