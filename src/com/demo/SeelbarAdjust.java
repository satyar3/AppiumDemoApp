package com.demo;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class SeelbarAdjust
{

	public static AndroidDriver<AndroidElement> driver;
	public static void main(String[] args) throws MalformedURLException
	{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(CapabilityType.VERSION, "5.1.1");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Won't start from the scratch without reseting the data.
		capabilities.setCapability("deviceName", "92010457a0ce2315");
		capabilities.setCapability("appPackage", "com.sec.android.app.launcher");
		capabilities.setCapability("appActivity", "com.android.launcher2.Launcher");	
		
		driver  = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.openNotifications();
		
		AndroidElement slider = driver.findElement(By.id("com.android.systemui:id/slider"));
		
		int startX = slider.getLocation().getX() + slider.getSize().getWidth()/10;
		int endX = slider.getLocation().getX() + slider.getSize().getWidth();
		
		int fixY = slider.getLocation().getY();
		
		//System.out.println(elm2X);
		//System.out.println(elm2X);
		
		TouchAction action = new TouchAction(driver);
		action.longPress(point(startX,fixY)).waitAction(waitOptions(ofSeconds(2))).moveTo(point(endX, fixY)).release().perform();
		
		System.out.println("Dragged and dropped");
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
	}

}
