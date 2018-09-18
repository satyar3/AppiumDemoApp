package com.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class UIScrollableAndSelector
{

	AndroidDriver<AndroidElement> driver;

	@BeforeMethod
	public void setUp() throws MalformedURLException
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability(CapabilityType.VERSION, "5.1.1");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true); // Won't start from the scratch without
																			// reseting the data.
		capabilities.setCapability("deviceName", "92010457a0ce2315");
		capabilities.setCapability("appPackage", "com.android.vending");
		capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 2)
	public void searchPlayStore() throws InterruptedException
	{

		Thread.sleep(3000);

		// MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().description(\"Search\"))"));
		// element.click();

		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Search']")).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("flipkart");
		List<AndroidElement> recyclerelements = driver.findElements(By.id("com.android.vending:id/suggest_text"));

		System.out.println(recyclerelements.size());

		for (int i = 0; i < recyclerelements.size(); i++)
		{
			String text = recyclerelements.get(i).getText().trim();

			if (text.equals("Flipkart Online Shopping App"))
			{
				recyclerelements.get(i).click();
				break;
			}
		}
	}

	@Test(priority = 1)
	public void scrollToElement() throws InterruptedException
	{

		String appdescription = "Myntra Online";
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().descriptionContains(\"" + appdescription + "\"))")).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Install')]")).click();
		driver.navigate().back();
		Thread.sleep(2000);
	}

	@AfterMethod
	public void End() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
	}
}