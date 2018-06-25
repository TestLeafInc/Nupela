package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.PreAndPost;
import pages.AbstractPage;
import pages.LoginPage;

public class TC004_EditLead extends PreAndPost{
	
	@BeforeClass
	public void setValues() {
		browserName="chrome";
		testCaseName="Edit Lead";
		testDescription="Edit and Update the Excisting Leads";
		category="smoke";
		dataSource="Excel";
		dataSheetName="TC004";
		authors="Gopi";
	}

	@Test(dataProvider="fetchData")
	public void runEditLead(String username, String password, String fName, String uFName,String uVFName) {
		new LoginPage()
		.login(username,password)
		.clickCrmsfa()
		.clickLeads()
		.clickFindLeads()
		.typeFirstName(fName)
		.clickFindLeadsButton()
		.clickFirstResultingLead()
		.clickEdit()
		.typeFirstName(uFName)
		.clickUpdate()
		.verifyFName(uVFName);
	}
	


}
