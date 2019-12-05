package com.coveros.training.map.sample;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.coveros.training.SauceProperties;

/**
 * Simple test class that verifies proper installation of MTW tools and
 * libraries
 * 
 * @author brian
 *
 */
public class SampleFirefoxTest {
	private WebDriver driver;

	@BeforeClass
	public static void beforeClass(){
		String os = SauceProperties.getString(SauceProperties.OS);
		String geckodriver = "geckodriver";
		if (os.equals("windows")) {
			geckodriver += ".exe";
		}
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver/geckodriver.exe" );
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver/" + os + "/" + geckodriver);
	}

	@Before
	public void setUp() throws Exception {
		// Now you can Initialize marionette driver to launch firefox
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", true);
		driver = new FirefoxDriver(options);
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
