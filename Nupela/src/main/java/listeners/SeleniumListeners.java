package listeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import reporter.ReportImpl;
import reporter.Reporter;

public class SeleniumListeners extends ReportImpl implements WebDriverEventListener {
	
	public WebDriver driver;
	public EventFiringWebDriver e_driver;
	protected WebDriverWait wait;
	protected static final ThreadLocal<SeleniumListeners> driverThreadLocal = new ThreadLocal<SeleniumListeners>();

	public void setDriver(SeleniumListeners webdrvrEvntImpl) {
		driverThreadLocal.set(webdrvrEvntImpl);
	}

	public WebDriver getDriver() {
		return driverThreadLocal.get().driver;
	}
	
	public EventFiringWebDriver getEventDriver() {
		return driverThreadLocal.get().e_driver;
	}
	
	public void beforeAlertAccept(WebDriver driver) {
		
		
	}

	public void afterAlertAccept(WebDriver driver) {
		
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, 10);
		reportStep("The browser "+driver.toString().split(":")[1].trim().split(" ")[0].trim()+" is launched with Url "+url, "PASS");		
	}

	public void beforeNavigateBack(WebDriver driver) {
		
		
	}

	public void afterNavigateBack(WebDriver driver) {
		
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		
		
	}

	public void afterNavigateForward(WebDriver driver) {
		
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {		
		wait.until(ExpectedConditions.elementToBeClickable(element));		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		String eleText = element.toString().split("->")[1].replaceAll("]", "");
		if(isAlertExist())
			reportStep("The element "+eleText+" is clicked","INFO");		
		else
			reportStep("The element "+eleText+" is clicked","PASS");		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		String eleText = element.toString().split("->")[1].replaceAll("]", "");
		reportStep("The element "+eleText+" is entered with text :"+keysToSend[0],"PASS");		
		
	}

	public void beforeScript(String script, WebDriver driver) {
		
		
	}

	public void afterScript(String script, WebDriver driver) {
		
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		if(throwable instanceof NoSuchElementException) {
			reportStep("The element :"+throwable.getMessage().split("Element info: ")[1]+" not found", "FAIL");
		}
		throw new RuntimeException();
	}
	
	public boolean isAlertExist() {
		boolean bAlertExists =  false;
		try {
			driver.switchTo().alert();
			bAlertExists = true;
		} catch (Exception e) {
			
		}
		return bAlertExists;
	}

	@Override
	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			reportStep("The browser has been closed.", "FAIL");
		} catch (IOException e) {
			reportStep("The snapshot could not be taken", "WARN");
		}
		return number;
	}

	public EventFiringWebDriver init(WebDriver driver) {
		e_driver = new EventFiringWebDriver(driver);
		e_driver.register(this);
		return e_driver;
	}
	
	public static void unset() { 
        driverThreadLocal.remove(); 
    }

	
}
