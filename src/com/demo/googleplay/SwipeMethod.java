package com.demo.googleplay;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;
import static io.appium.java_client.touch.offset.PointOption.point;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofSeconds;

public class SwipeMethod {
	
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability(CapabilityType.VERSION, "5.1.1");
	capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Won't start from the scratch without reseting the data.
	capabilities.setCapability("deviceName", "92010457a0ce2315");
	capabilities.setCapability("appPackage", "com.android.vending");
	capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
	public WebDriver driver = new AndroidDriver(capabilities);
	
	public void swipeToSelectQty(String qty) {

		//Mandatory to import
		
		//import static io.appium.java_client.touch.offset.PointOption.point;
		//import static io.appium.java_client.touch.WaitOptions.waitOptions;
		//import static java.time.Duration.ofSeconds;
		
		AndroidElement startElement = (AndroidElement) driver.findElement(By.xpath("//android.widget.Button[@text='2']"));
		AndroidElement endElement = (AndroidElement) driver.findElement(By.className("android.widget.NumberPicker"));
		System.out.println(endElement.getLocation());
		

		int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
		int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

		//int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
		//int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
		
		int endX = 205 + (endElement.getSize().getWidth() / 2);
		int endY = 314 + (endElement.getSize().getHeight() / 2);
		
		while(!(driver.findElement(By.id("android:id/numberpicker_input")).getText().equals("3")))
		{
		new TouchAction(driver)
			.press(point(startX, startY))
			.waitAction(waitOptions(ofSeconds(2)))
			.moveTo(point(endX, endY))
			.release()
			.perform();
		
		}
		
		// driver.findElementByAndroidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\""+"2"+"\"))");
		driver.rotate(ScreenOrientation.LANDSCAPE);
		driver.rotate(ScreenOrientation.PORTRAIT);
	}

}
