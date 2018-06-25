package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MyHomePage extends AbstractPage{

	public MyHomePage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.LINK_TEXT, using = "Leads")
	WebElement leads;

	public MyLeadsPage clickLeads() {
		click(leads);
		return new MyLeadsPage();
	}


}