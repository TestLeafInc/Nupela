package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MergeFindLeads extends MenuPage{

	public MergeFindLeads(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.NAME, using = "firstName")
	WebElement elFirstName;
	
	@FindBy(how = How.NAME, using = "lastName")
	WebElement elLastName;
	
	@FindBy(how = How.XPATH, using = "//button[.='Find Leads']")
	WebElement elFindLeadsButton;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")
	WebElement elFirstResultingLead;
	
	@FindBy(how = How.NAME, using = "id")
	WebElement elLeadId;

	public MergeFindLeads typeFirstName(String firstName) {
		type(elFirstName,firstName);
		return this;
	}
	
	public MergeFindLeads typeLastName(String lastName) {
		type(elLastName,lastName);
		return this;
	}
	
	public MergeFindLeads clickFindLeads() {
		click(elFindLeadsButton);
		return this;
	}

	public MergeLeadsPage clickFirstResultingLead() {
		click(elFirstResultingLead);
		switchWindow(0);
		return new MergeLeadsPage();
	}

	public MergeFindLeads typeLeadID(String leadID) {
		type(elLeadId, leadID);		
		return this;
	}

}