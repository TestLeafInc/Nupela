package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class HomePage extends AbstractPage{

	public HomePage(){
		PageFactory.initElements(getEventDriver(), this);
	}

	@FindBy(how = How.LINK_TEXT, using = "CRM/SFA")
	WebElement crmsfa;

	public MyHomePage clickCrmsfa() {
		click(crmsfa);
		return new MyHomePage();
	}


}