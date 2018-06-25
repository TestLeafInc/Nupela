package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class EditLeadPage extends MenuPage{

	public EditLeadPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.ID, using = "updateLeadForm_firstName")
	WebElement elFirstName;

	@FindBy(how = How.ID, using = "updateLeadForm_lastName")
	WebElement elLastName;
	
	@FindBy(how = How.ID, using = "updateLeadForm_companyName")
	WebElement elCompanyName;
	
	@FindBy(how = How.NAME, using = "submitButton")
	WebElement elUpdate;

	public EditLeadPage typeFirstName(String firstName) {
		type(elFirstName,firstName);
		return this;
	}
	
	public EditLeadPage typelastName(String lastName) {
		type(elLastName,lastName);
		return this;
	}
	
	public EditLeadPage typecompanyName(String companyName) {
		type(elCompanyName,companyName);
		return this;
	}
	
	public ViewLeadPage clickUpdate() {
		click(elUpdate);
		return new ViewLeadPage();
	}
}