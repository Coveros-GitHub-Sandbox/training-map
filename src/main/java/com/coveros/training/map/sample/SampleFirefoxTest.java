package com.coveros.training.map.sample;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.MarionetteDriverManager;

/**
 * Simple test class that verifies proper installation of MTW tools and
 * libraries
 * 
 * @author brian
 *
 */
public class SampleFirefoxTest {
	private WebDriver driver;

	static {
		MarionetteDriverManager.getInstance().setup();
		// if you didn't update the Path system variable to add the full
		// directory path to the executable as above mentioned then doing this
		// directly through code

		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver/geckodriver.exe");
	}

	@Before
	public void setUp() throws Exception {
		// Now you can Initialize marionette driver to launch firefox
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new MarionetteDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Runs a simple test verifying the title of the amazon.com homepage.
	 * 
	 * @throws Exception
	 */
	@Test
	public void coveros() throws Exception {
		driver.get("https://www.coveros.com/");
		assertEquals("Coveros", driver.getTitle());
	}
}
