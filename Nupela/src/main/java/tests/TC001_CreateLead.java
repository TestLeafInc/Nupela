package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.PreAndPost;
import pages.AbstractPage;
import pages.LoginPage;

public class TC001_CreateLead extends PreAndPost{
	
	@BeforeClass
	public void setValues() {
		browserName="chrome";
		testCaseName="Create a new Lead";
		testDescription="Create a new Lead";
		category="smoke";
		dataSource="Excel";
		dataSheetName="TC001";
		authors="Babu";
	}

	@Test(dataProvider="fetchData")
	public void runCreateLead(String username, String password, 
			String companyName, String firstName, String lastName) {
		
		new LoginPage()
		.login(username,password)
		.clickCrmsfa()
		.clickLeads()
		.clickCreateLeads()
		.typeCompanyName(companyName)
		.typeFirstName(firstName)
		.typeLastName(lastName)
		.clickCreateLead();
		
	}
	


}
