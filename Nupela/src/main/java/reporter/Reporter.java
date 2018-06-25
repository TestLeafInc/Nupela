package reporter;

import org.openqa.selenium.WebDriver;

public abstract class Reporter {

	public static String pdfReportPath;
	public static String xlsxReportPath;
	
	public String testCaseName, testDescription, category, authors;

	public abstract long takeSnap();

	public abstract WebDriver getDriver();



}