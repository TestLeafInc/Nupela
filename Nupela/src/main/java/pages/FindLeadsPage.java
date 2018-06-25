package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class FindLeadsPage extends MenuPage{

	public FindLeadsPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.XPATH, using = "(//input[@name='firstName'])[3]")
	WebElement elFirstName;
	
	@FindBy(how = How.XPATH, using = "(//input[@name='lastName'])[3]")
	WebElement elLastName;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Find Leads']")
	WebElement elFindLeadsButton;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]")
	WebElement elFirstResultingLead;
	
	@FindBy(how = How.NAME, using = "id")
	WebElement elLeadId;

	public FindLeadsPage typeFirstName(String firstName) {		
		type(elFirstName,firstName);
		return this;
	}
	
	public FindLeadsPage typeLasttName(String lastName) {		
		type(elLastName,lastName);
		return this;
	}
	
	public FindLeadsPage clickFindLeadsButton() {
		click(elFindLeadsButton);		
		return this;
	}

	public ViewLeadPage clickFirstResultingLead() {
		click(elFirstResultingLead);
		return new ViewLeadPage();
	}

	public ViewLeadPage typeLeadID(String leadID) {
		type(elLeadId, leadID);		
		return new ViewLeadPage();
	}

}