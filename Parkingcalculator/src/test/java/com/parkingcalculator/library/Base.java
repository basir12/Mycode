package com.parkingcalculator.library;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;



public class Base {
	final static Logger logger = Logger.getLogger(Base.class);
	public static WebDriver driver;
	public 	static Library myLibrary;
	private static JavaPropertiesManager readProperty;
	private static String browser;
	private static String demoType;
	@BeforeClass
	public void beforeAllTestStart() {
		 myLibrary = new Library(driver);	
		 readProperty = new JavaPropertiesManager("src/test/resources/config.properties");
		 browser = readProperty.readProperty("browserType");
			demoType = readProperty.readProperty("demoMode");
			if(demoType.contains("true")) {
				myLibrary.setDemo(true);
				logger.info("Test is running in demo mode ON ...");
			//System.out.println("Test is running in demo mode ON ...");
			}else {
				logger.info("Test is running in demo OFF...");
			//System.out.println("Test is running in demo mode off ...");
			}
	}
	@AfterClass
	public void afterAllTestCompleted() {
		
	}
	
	@BeforeMethod
	public void beforeEachTestStart() {
		driver = myLibrary.startChromeBrowser();
		
		/*System.setProperty("webdriver.chrome.driver", "src/test/resources/browers_drivers/chromedriver.exe");
		// start a browser
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();*/
	}

	@AfterMethod
	public void afterEachTestEnd() {
		try {
			Thread.sleep(5 * 1000);
			driver.close();// it close the driver only
			driver.quit();// kills/deletes the driver object
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
