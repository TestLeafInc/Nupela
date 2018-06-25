package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.PreAndPost;
import pages.LoginPage;

public class TC003_MergeLead extends PreAndPost{

	@BeforeClass
	public void setValues() {
		browserName="chrome";
		testCaseName="Merge Two Lead";
		testDescription="Merge two existing Leads and confirm whether its merged or not";
		category="smoke";
		dataSource="Json";
		fileName="TC003";
		authors="Babu";
	}
	
	@Test(dataProvider="fetchData")
	public void runMergeLead(String username, String password, String firstName, String secondName)  {
		
		new LoginPage()
		.login(username,password)		
		.clickCrmsfa()
		.clickLeads()
		.clickMergeLeads()
		.clickFromLead()
		.typeFirstName(firstName)
		.clickFindLeads()
		.clickFirstResultingLead()
		.clickToLead()
		.typeFirstName(secondName)
		.clickFindLeads()
		.clickFirstResultingLead()
		.clickMergeLead();
		
	}
	
}
