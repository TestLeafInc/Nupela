package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoginPage extends AbstractPage{
	
	public LoginPage(){
		PageFactory.initElements(getEventDriver(), this);
	}
	
	@FindBy(how = How.ID, using = "username")
	WebElement username;

	@FindBy(how = How.ID, using = "password")
	WebElement password;
	
	@FindBy(how = How.CLASS_NAME, using = "decorativeSubmit")
	WebElement loginBtn;
	
	public HomePage login(String user, String pwd) {
		type(username, user);
		type(password, pwd);
		click(loginBtn);
		return new HomePage();
	}
	
	public HomePage login() {
		type(username, "DemoSalesManager");
		type(password, "crmsfa");
		click(loginBtn);
		return new HomePage();
	}
	

}