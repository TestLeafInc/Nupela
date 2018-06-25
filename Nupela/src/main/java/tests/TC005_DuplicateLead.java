package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.PreAndPost;
import pages.LoginPage;

public class TC005_DuplicateLead extends PreAndPost{

	@BeforeClass
	public void setValues() {
		browserName="chrome";
		testCaseName="Duplicate Lead";
		testDescription="Search and Duplicate an existing lead";
		category="smoke";
		dataSource="Json";
		fileName="TC005";
		authors="Babu";
	}
	
	@Test(dataProvider="fetchData")
	public void runDupsLead(String username, String password, String fName)  {
		
		new LoginPage()
		.login(username,password)
		.clickCrmsfa()
		.clickLeads()
		.clickFindLeads()
		.typeFirstName(fName)
		.clickFindLeadsButton()
		.clickFirstResultingLead()
		.clickDuplicateLead()
		.clickCreateLead()
		.verifyFName(fName);
		
		
	}
	
}
