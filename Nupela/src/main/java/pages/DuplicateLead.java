package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;



public class DuplicateLead extends MenuPage{

	public DuplicateLead(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.NAME, using = "submitButton")
	WebElement createLead;
		
	public ViewLeadPage clickCreateLead() {
		click(createLead);
		return new ViewLeadPage();
	}

	
	
}





