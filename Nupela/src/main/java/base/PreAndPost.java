package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import dataReader.DbProvider;
import dataReader.ExcelProvider;
import dataReader.JsonProvider;
import listeners.SeleniumListeners;

public class PreAndPost extends SeleniumListeners{

	protected String browserName;
	protected String dataSheetName;
	protected String dataSource;
	protected String fileName;
	protected String sqlStatement;
	private WebDriver driver;
	private SeleniumListeners webDrvrEvntImpl;
	public static Properties config;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, IOException{
		config = new Properties();
		config.load(new FileInputStream(new File("./src/main/resources/config.properties")));
		startResult();
	}

	@BeforeMethod
	public synchronized void beforeMethod(){
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browserName);
		dc.setPlatform(Platform.WINDOWS);
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.DRIVER, Level.ALL);
		dc.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		
		// this is for grid run
		if(config.getProperty("RemoteExecution").equalsIgnoreCase("true"))
			try {
				driver = new RemoteWebDriver(new URL("http://"+config.getProperty("HubIP")+":"+config.getProperty("HPort")+"/wd/hub"), dc);
			} catch (MalformedURLException e) {
				reportStep("The hub at the ip address: "+config.getProperty("HubIP")+" is not available.", "FAIL");
			}
		else{ // this is for local run
			if(browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver(dc);
			}else if(browserName.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver(dc);
			}else {
				System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver(dc);
			}
		}

		webDrvrEvntImpl  = new SeleniumListeners();
		webDrvrEvntImpl.driver = driver;
		webDrvrEvntImpl.e_driver = init(driver);
		setDriver(webDrvrEvntImpl);
		startTestCase(testCaseName, testDescription, category, authors);
	}

	@BeforeMethod
	public void launchApp() {
		getEventDriver().get(config.getProperty("URL"));
		getEventDriver().manage().window().maximize();
		getEventDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void afterSuite(){
		endResult();
	}


	@AfterMethod
	public void afterMethod(){
		getDriver().quit();
		endTestcase();	
		unset(); 
	}

	@DataProvider(name="fetchData",parallel=false)
	public  Object[][] getData(){
		if(dataSource.equalsIgnoreCase("db"))
			return DbProvider.getDataFromDb(sqlStatement);
		if(dataSource.equalsIgnoreCase("json"))
			return JsonProvider.getDataFromJson(fileName);
		else
			return ExcelProvider.getDataFromExcel(dataSheetName);

	}	


}
