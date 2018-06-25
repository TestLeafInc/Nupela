package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;



public class ViewLeadPage extends MenuPage{

	public ViewLeadPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.LINK_TEXT, using = "Edit")
	WebElement eleEdit;
	
	@FindBy(how = How.LINK_TEXT, using = "Delete")
	WebElement eleDelete;
	
	@FindBy(how = How.LINK_TEXT, using = "Duplicate Lead")
	WebElement eleDuplicateLead;//viewLead_companyName_sp
	
	@FindBy(how = How.ID, using = "viewLead_companyName_sp")
	WebElement elecompanyName;//viewLead_firstName_sp
	
	@FindBy(how = How.ID, using = "viewLead_firstName_sp")
	WebElement elefirstName;
	
	
	public pages.EditLeadPage clickEdit() {
		click(eleEdit);
		return new pages.EditLeadPage();
	}
	
	public MyLeadsPage clickDelete() {
		click(eleDelete);
		return new MyLeadsPage();
	}
	
	public DuplicateLead clickDuplicateLead() {
		click(eleDuplicateLead);
		return new DuplicateLead();
	}

	public ViewLeadPage verifyCompanyName(String cName) {
		verifyPartialText(elecompanyName, cName);
		return this;
	}
	
	public ViewLeadPage verifyFName(String data) {
		verifyText(elefirstName,data);
		return this;
	}

	
	
}





