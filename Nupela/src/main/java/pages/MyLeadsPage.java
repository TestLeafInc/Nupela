package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MyLeadsPage extends MenuPage{

	public MyLeadsPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.LINK_TEXT, using = "Create Lead")
	WebElement createLead;
	
	@FindBy(how = How.LINK_TEXT, using = "Find Leads")
	WebElement findLeads;
	
	@FindBy(how = How.LINK_TEXT, using = "Merge Leads")
	WebElement elMergeLeads;

	public CreateLeadPage clickCrexateLeads() {
		click(createLead);
		return new CreateLeadPage();
	}
	
	public FindLeadsPage clickFindLeads() {
		click(findLeads);
		return new FindLeadsPage();
	}
	
	public MergeLeadsPage clickMergeLeads() {
		click(elMergeLeads);
		return new MergeLeadsPage();
	}


}