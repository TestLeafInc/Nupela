package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MenuPage extends AbstractPage{

	public MenuPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.LINK_TEXT, using = "Create Lead")
	WebElement createLead;

	public CreateLeadPage clickCreateLeads() {
		click(createLead);
		return new CreateLeadPage();

	}


}