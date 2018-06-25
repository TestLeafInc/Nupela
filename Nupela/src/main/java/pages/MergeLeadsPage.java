package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MergeLeadsPage extends MenuPage{

	public MergeLeadsPage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.XPATH, using = "(//img[@alt='Lookup'])[1]")
	WebElement elFromLead;
	
	@FindBy(how = How.XPATH, using = "(//img[@alt='Lookup'])[2]")
	WebElement elToLead;
	
	@FindBy(how = How.LINK_TEXT, using = "Merge")
	WebElement elMerge;
	
	public MergeFindLeads clickFromLead() {
		click(elFromLead);
		switchWindow(1);
		return new MergeFindLeads();
	}
	
	public MergeFindLeads clickToLead() {
		click(elToLead);
		switchWindow(1);
		return new MergeFindLeads();
	}
	
	public ViewLeadPage clickMergeLead() {
		click(elMerge);
		acceptAlert();
		return new ViewLeadPage();
	}
	

}