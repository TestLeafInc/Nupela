package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CreateLeadPage extends MenuPage{

	public CreateLeadPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.ID, using = "createLeadForm_companyName")
	WebElement eleCompanyName;
	
	@FindBy(how = How.ID, using = "createLeadForm_firstName")
	WebElement eleFirstName;
	
	@FindBy(how = How.ID, using = "createLeadForm_lastName")
	WebElement eleLastName;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Create Lead']")
	WebElement eleCreateLead;

	public CreateLeadPage typeCompanyName(String compName) {
		type(eleCompanyName,compName);
		return this;
	}
	
	public CreateLeadPage typeFirstName(String firstName) {
		type(eleFirstName,firstName);
		return this;
	}
	
	public CreateLeadPage typeLastName(String lastName) {
		type(eleLastName,lastName);
		return this;
	}
	
	public ViewLeadPage clickCreateLead() {
		click(eleCreateLead);
		return new ViewLeadPage();
	}


}