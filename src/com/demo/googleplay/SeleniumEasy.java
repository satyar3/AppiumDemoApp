package com.demo.googleplay;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;


public class SeleniumEasy {
	WebDriver driver;
	//WebDriverWait wait;
	//String AppURL = "http://www.seleniumeasy.com";

	@Test
	public void setup() throws MalformedURLException {

		// Create an object for Desired Capabilities
		DesiredCapabilities capabilities = DesiredCapabilities.android();

		// Name of mobile web browser to automate. ‘Safari’ for iOS and ‘Chrome’
		// or ‘Browser’ for Android
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		//capabilities.setCapability("browsername", "chrome");
		
		// set the capability to execute our test in Android Platform
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		
		// we need to define platform name
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		
		// The kind of mobile device or emulator to use - iPad Simulator, iPhone
		// Retina 4-inch, Android Emulator, Galaxy S4 etc
		//capabilities.setCapability("deviceName", "520364baf0016431");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"92010457a0ce2315");
		
		// set the android version as well 
		capabilities.setCapability(MobileCapabilityType.VERSION,"5.1.1");

		// Which mobile OS platform to use - iOS, Android, or FirefoxOS
		//capabilities.setCapability("platformName", "Android");

		// Java package of the Android app you want to run- Ex:
		// com.example.android.myApp
		//capabilities.setCapability("appPackage", "com.android.chrome");

		// Activity name for the Android activity you want to launch from your
		// package
		//capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");

		// Initialize the driver object with the URL to Appium Server and
		// passing the capabilities
		//driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		URL url= new URL("http://127.0.0.1:4723/wd/hub");
		WebDriver driver = new AndroidDriver(url, capabilities);
		driver.get("http://www.facebook.com");
		System.out.println("Title "+driver.getTitle());
		//wait = new WebDriverWait(driver, 5);
		
		driver.quit();
	}

	//@Test
	/*public void testSearchAppium() {
		driver.get(AppURL);
		WebElement titleElement = driver.findElement(By.cssSelector("#site-name>a"));
		String homePageTitle = titleElement.getText();
		Assert.assertEquals(homePageTitle, "Selenium Easy");

		WebElement searchElement = driver.findElement(By.name("search_block_form"));
		searchElement.sendKeys("Appium Tutorials");

		WebElement searchBtnEle = driver.findElement(By.id("edit-submit"));
		searchBtnEle.click();

		By byPageTitle = By.id("page-title");
		wait.until(ExpectedConditions.presenceOfElementLocated(byPageTitle));

		String searchPageTitle = driver.findElement(byPageTitle).getText();
		Assert.assertEquals(searchPageTitle, "Search");
	}*/

	/*@AfterTest
	public void tearDown() {
		driver.quit();
	}*/
}