package com.coveros.training.map.sample;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.coveros.training.SauceProperties;

public class TargetAddThreeSpeakers {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void beforeClass() {
		String os = SauceProperties.getString(SauceProperties.OS);
		String geckodriver = "geckodriver";
		String chromedriver = "chromedriver";
		if (os.equals("windows")) {
			geckodriver += ".exe";
			chromedriver += ".exe";
		}
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver/" + os + "/" + chromedriver);
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver/" + os + "/" + geckodriver);
	}

	@Before
	public void setUp() throws Exception {
		// Now you can Initialize marionette driver to launch firefox
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//		capabilities.setCapability("marionette", true);
//		driver = new MarionetteDriver(capabilities);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		driver = new ChromeDriver(capabilities);
		baseUrl = "http://www.target.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testTarget() throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.id("searchLabel")).click();
		driver.findElement(By.id("search")).clear();
		driver.findElement(By.id("search")).sendKeys("speakers");
		driver.findElement(By.xpath("(//button[@id='searchReset'])[2]")).click();
//		for (int second = 0;; second++) {
//			if (second >= 60)
//				fail("timeout");
//			try {
//				if ("".equals(
//						driver.findElement(By.xpath("//div[@id='slp-facet-wrap']/section/div[2]/div/h1")).getText()))
//					break;
//			} catch (Exception e) {
//			}
//			Thread.sleep(1000);
//		}

		driver.findElement(By.linkText("Sonos PLAY:1 Compact Smart Speaker - Black")).click();
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if ("Sonos PLAY:1 Compact Smart Speaker - Black"
						.equals(driver.findElement(By.cssSelector("h2.title-product > span")).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		
		
		new Select(driver.findElement(By.id("sbc-quantity-picker"))).selectByVisibleText("3");
		driver.findElement(By.xpath("//div[@id='AddToCartAreaId']/div/div/button")).click();
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if ("3 added to cart"
						.equals(driver.findElement(By.cssSelector("h2.itemRtText.h-standardSpacingLeft")).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.cssSelector(".cart-ATC")).click();
		//driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
		for (int second = 0;; second++) {
			if (second >= 5)
				fail("timeout");
			try {
				if ("cart total: $634.47"
						.equals(driver.findElement(By.xpath("//div[@id='cart-page']/div/div/h1")).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.xpath("//div[@id='products-1']/div/div[2]/div[2]/button")).click();

		driver.findElement(By.xpath("//div[2]/div/div[2]/button")).click();
		for (int second = 0;; second++) {
			if (second >= 5)
				fail("timeout");
			try {
				if ("your cart is empty".equals(driver.findElement(By.cssSelector("h1.title-text.alpha")).getText()))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		assertEquals("your cart is empty", driver.findElement(By.cssSelector("h1.title-text.alpha")).getText());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
