package com.coveros.training.map.sample;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Simple test class that verifies proper installation of MTW tools and
 * libraries
 * 
 * @author brian
 *
 */
public class SampleFirefoxTest {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Runs a simple test verifying the title of the amazon.com homepage.
	 * 
	 * @throws Exception
	 */
	@Test
	public void amazon() throws Exception {
		driver.get("https://www.coveros.com/");
		assertEquals("Coveros", driver.getTitle());
	}
}
