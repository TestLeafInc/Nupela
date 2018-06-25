package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.PreAndPost;
import pages.AbstractPage;
import pages.LoginPage;

public class TC002_DeleteLead extends PreAndPost{
	
	@BeforeClass
	public void setValues() {
		browserName="chrome";
		testCaseName="Delete Lead";
		testDescription="Search and Delete a Lead";
		category="smoke";
		dataSource="DB";
		sqlStatement="Select FIRST_NAME FROM OPENTAPS.PERSON WHERE PARTY_ID = 21447 ";
		authors="Babu";
	}

	@Test(dataProvider="fetchData")
	public void runDeleteLead(String firstName) {
		new LoginPage()
		.login()
		.clickCrmsfa()
		.clickLeads()
		.clickFindLeads() 
		.typeFirstName(firstName)
		.clickFindLeadsButton()
		.clickFirstResultingLead()
		.clickDelete();
		
	}
	


}
