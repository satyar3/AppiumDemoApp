package com.demo;

import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import static io.appium.java_client.touch.offset.PointOption.point;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class DragAndDrop
{
	
	public static AndroidDriver<AndroidElement> driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(CapabilityType.VERSION, "5.1.1");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Won't start from the scratch without reseting the data.
		capabilities.setCapability("deviceName", "92010457a0ce2315");
		capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
		capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");	
		
		driver  = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(driver.isDeviceLocked())
			driver.unlockDevice();
		
		driver.findElement(By.xpath("//android.widget.LinearLayout[@index='0']/android.widget.TextView[@index='1']")).click();
		AndroidElement elem1 = driver.findElement(By.xpath("//android.view.View[@index='2']/descendant::android.widget.ImageView"));
		AndroidElement elem2 = driver.findElement(By.xpath("//android.view.View[@index='5']/descendant::android.widget.ImageView"));
		
		int elm1X = elem1.getLocation().getX() + elem1.getSize().getWidth()/2;
		int elm1Y = elem1.getLocation().getY() + elem1.getSize().getHeight()/2;
		//System.out.println(elm1X);
		//System.out.println(elm1X);
		
		
		int elm2X = elem2.getLocation().getX() + elem1.getSize().getWidth()/2;
		int elm2Y = elem2.getLocation().getY() + elem1.getSize().getHeight()/2;
		//System.out.println(elm2X);
		//System.out.println(elm2X);
		
		TouchAction action = new TouchAction(driver);
		action.longPress(point(elm1X,elm1Y)).waitAction(waitOptions(ofSeconds(2))).moveTo(point(elm2X, elm2Y)).release().perform();
		System.out.println("Dragged and dropped");
	}

}
