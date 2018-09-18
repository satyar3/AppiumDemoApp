package com.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class HybridAppTest
{

	public static AndroidDriver<AndroidElement> driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(CapabilityType.VERSION, "5.1.1");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Won't start from the scratch without reseting the data.
		capabilities.setCapability("deviceName", "92010457a0ce2315");
		//capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
		//capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");	
		
		driver  = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		//Click on the element which will lead to webview
		driver.findElement(By.id("Element locator of the native view")).click();
		//Web view launched		
		
		//This is used to find the current view if web view or nativeapp view
		String viewname = driver.getContext();
		System.out.println("Current context is : "+viewname);
		
		Set<String> contextnames = driver.getContextHandles();
		
		for(String contextname : contextnames)
		{
			System.out.println(contextname);
			
			if(contextname.contains("enter the webview name here"))
			{
				driver.context(contextname);
				break;
			}
		}
		
		
	}

}
