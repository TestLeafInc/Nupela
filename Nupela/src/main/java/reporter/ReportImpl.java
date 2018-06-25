package reporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.PreAndPost;

public abstract class ReportImpl extends Reporter {
	public static ExtentReports extent;
	public static XCelReporter xlsx;

	private static Map<WebDriver,ExtentTest> testDriver;
	private static Map<WebDriver,PdfReporter> pdfDriver;
	private static Map<WebDriver,XCelReporter> xlsxDriver;


	public void reportStep(String desc, String status) {

		long snapNumber = 100000l;

		if(!status.equalsIgnoreCase("INFO")) {
			try {
				snapNumber= takeSnap();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		if(PreAndPost.config.getProperty("Extent").equalsIgnoreCase("y")) {
			// Write if it is successful or failure or information
			if(status.equalsIgnoreCase("PASS")){
				testDriver.get(getDriver()).log(LogStatus.PASS, desc+testDriver.get(getDriver()).
						addScreenCapture("./../images/"+snapNumber+".jpg"));
			}else if(status.equalsIgnoreCase("FAIL")){
				testDriver.get(getDriver()).log(LogStatus.FAIL, desc+testDriver.get(getDriver()).addScreenCapture("./../images/"+snapNumber+".jpg"));
				throw new RuntimeException("FAILED");
			}else if(status.equalsIgnoreCase("INFO")){
				testDriver.get(getDriver()).log(LogStatus.INFO, desc);
			}else if(status.equalsIgnoreCase("WARN")){
				testDriver.get(getDriver()).log(LogStatus.WARNING, desc+testDriver.get(getDriver()).addScreenCapture("./../images/"+snapNumber+".jpg"));
			}
		} 

		if(PreAndPost.config.getProperty("Pdf").equalsIgnoreCase("y")) { 
			pdfDriver.get(getDriver()).reportStep(desc, status, snapNumber);
		}
		if(PreAndPost.config.getProperty("Excel").equalsIgnoreCase("y")) { 
			xlsxDriver.get(getDriver()).reportStep(desc, status, snapNumber);
		}
	}

	public void startResult(){
		SimpleDateFormat sdf = new SimpleDateFormat("Y.M.d");
		pdfReportPath  = "./reports/PDF/"+sdf.format(new Date());
		xlsxReportPath = "./reports/XLS/"+sdf.format(new Date());

		new File(pdfReportPath).mkdir();
		new File(xlsxReportPath).mkdir();

		if(PreAndPost.config.getProperty("Pdf").equalsIgnoreCase("y")) 
			pdfDriver = new HashMap<WebDriver, PdfReporter>();

		if(PreAndPost.config.getProperty("Extent").equalsIgnoreCase("y")) {
			testDriver = new HashMap<WebDriver, ExtentTest>();
			extent = new ExtentReports("./reports/HTML/result.html", false);
			extent.loadConfig(new File("./src/main/resources/extent-config.xml"));
		}

		if(PreAndPost.config.getProperty("Excel").equalsIgnoreCase("y")) 
			xlsxDriver = new HashMap<WebDriver, XCelReporter>();
	}

	public void startTestCase(String testCaseName, String testDescription, String category, String authors){
		if(PreAndPost.config.getProperty("Extent").equalsIgnoreCase("y")) {	
			testDriver.put(getDriver(),extent.startTest(testCaseName, testDescription));
			testDriver.get(getDriver()).assignCategory(category);
			testDriver.get(getDriver()).assignAuthor(authors);
		}

		if(PreAndPost.config.getProperty("Pdf").equalsIgnoreCase("y")) {
			pdfDriver.put(getDriver(),new PdfReporter().startTestCase(testCaseName, testDescription, category, authors));
		}

		if(PreAndPost.config.getProperty("Excel").equalsIgnoreCase("y")){ 
			xlsxDriver.put(getDriver(),new XCelReporter().startTestCase(testCaseName, testDescription, category, authors));
		}	
	}

	public void endResult(){		
		if(PreAndPost.config.getProperty("Extent").equalsIgnoreCase("y"))	
			extent.flush();
	}

	public void endTestcase(){
		if(PreAndPost.config.getProperty("Extent").equalsIgnoreCase("y")) 
			extent.endTest(testDriver.get(getDriver()));
		if(PreAndPost.config.getProperty("Pdf").equalsIgnoreCase("y")) 
			pdfDriver.get(getDriver()).endTestcase(pdfReportPath+"/"+testCaseName);
		if(PreAndPost.config.getProperty("Excel").equalsIgnoreCase("y")) 
			xlsxDriver.get(getDriver()).endTestcase(xlsxReportPath+"/"+testCaseName);
	}


}

