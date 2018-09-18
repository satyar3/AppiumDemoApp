package com.demo;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.LocksDevice;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class FlipKartSwipeDemo
{
	public static AppiumDriver<AndroidElement> driver;
	static int phonescreenwidth;
	static int elementwidth;
	static int totalelementvisible;
	static int count = 0;

	public static void main(String[] args) throws Exception
	{

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(CapabilityType.VERSION, "5.1.1");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true); //Won't start from the scratch without reseting the data.
		capabilities.setCapability("deviceName", "192.168.0.4:5555");
		capabilities.setCapability("appPackage", "com.flipkart.android");
		capabilities.setCapability("appActivity", "com.flipkart.android.activity.HomeFragmentHolderActivity");
		
		driver  = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(((LocksDevice) driver).isDeviceLocked())
			((LocksDevice) driver).unlockDevice();
		
		System.out.println("Flipkart App Launched");
		Thread.sleep(5000);
		
		AndroidElement carousel = driver.findElement(By.id("com.flipkart.android:id/carousel_view_pager"));
		
		/*int xAxis = carousel.getLocation().getX();
		int yAxis = carousel.getLocation().getY();		
		int xAxisNew = carousel.getLocation().getX() + carousel.getSize().getWidth()/2;
		int yAxisNew = carousel.getLocation().getY() + carousel.getSize().getHeight()/2;
		*/
		
		//new TouchAction(driver).tap(point(xAxisNew,yAxisNew)).perform().release();		
		//new TouchAction(driver).press(point(xAxisNew, yAxis)).waitAction(waitOptions(ofSeconds(2))).moveTo(point(xAxisNew/2, yAxis)).release().perform();
		
		System.out.println("Total ELements nos presnent is : "+driver.findElements(By.xpath("//android.widget.FrameLayout/android.widget.ImageView")).size());
		
		phonescreenwidth = driver.manage().window().getSize().getWidth();
		System.out.println("Screen width is : "+phonescreenwidth);
		
		List<AndroidElement> carouselimg = driver.findElements(By.xpath("//android.widget.FrameLayout/android.widget.ImageView"));
		elementwidth = carouselimg.get(0).getSize().getWidth();
		
		for(int i = 0; i<carouselimg.size(); i++)
		{
			int xAxis = carouselimg.get(i).getLocation().getX();
			int yAxis = carouselimg.get(i).getLocation().getY();
			//Thread.sleep(2000);
			//new TouchAction(driver).tap(point(xAxis,yAxis)).perform().release();
			//Thread.sleep(2000);
			//driver.navigate().back();
			count++;
			if(count == 4)
			{
				count = 0;
				new TouchAction(driver).press(point(phonescreenwidth-2, yAxis+50)).waitAction(waitOptions(ofSeconds(2))).moveTo(point(carouselimg.get(count+1).getSize().getWidth()/2, yAxis+50)).release().perform();
			}
			 
		}
		
		totalelementvisible = Math.round(phonescreenwidth/elementwidth);
		System.out.println("Total visible elements are : "+totalelementvisible);
		
	}	
}
