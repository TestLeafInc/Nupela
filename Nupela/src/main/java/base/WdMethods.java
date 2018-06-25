package base;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import listeners.SeleniumListeners;

public class WdMethods extends SeleniumListeners{	

	public WebElement locateElement(String how, String using) {
		WebElement ele = null;
		switch(how) {
		case("id"):
			ele = getEventDriver().findElement(By.id(using));
		break;
		case("name"):
			ele = getEventDriver().findElement(By.name(using));
		break;
		case("linkText"):
			ele = getEventDriver().findElement(By.linkText(using));
		break;
		case("xpath"):
			ele = getEventDriver().findElement(By.xpath(using));
		break;
		case("partialLinkText"):
			ele = getEventDriver().findElement(By.partialLinkText(using));
		break;
		case("cssSelector"):
			ele = getEventDriver().findElement(By.cssSelector(using));
		break;
		case("tagName"):
			ele = getEventDriver().findElement(By.tagName(using));
		break;
		case("className"):
			ele = getEventDriver().findElement(By.className(using));
		break;
		default:
			reportStep("The given locator "+how+" is not correct", "FAIL");
			break;
		}
		return ele;			
	}

	public void clear(WebElement ele) {
		ele.clear();
	}

	public void type(WebElement ele, String data) {
		ele.sendKeys(data);
	}

	public void click(WebElement ele) {
		ele.click();
	}

	public void selectUsingText(WebElement ele, String text) {
		select(ele, "text", text);
	}

	public void selectUsingValue(WebElement ele, String value) {
		select(ele, "value", value);
	}

	public void selectUsingIndex(WebElement ele, int index) {
		new Select(ele).selectByIndex(index);
	}

	private void select(WebElement ele, String type, String textOrValue) {
		if(ele.getTagName().equals("select")) {
			if(type.equalsIgnoreCase("text"))
				new Select(ele).selectByVisibleText(textOrValue);
			else
				new Select(ele).selectByValue(textOrValue);
		}else {
			ele.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			locateElement("xpath", "//li[text()='"+textOrValue+"']").click();			
		}
	}

	public String getText(WebElement ele){
		return ele.getText();
	}	

	public String getAttributeText(WebElement ele, String value){
		return ele.getAttribute(value);
	}

	public boolean verifyText(WebElement ele, String text) {
		return ele.getText().equals(text);
	}

	public boolean verifyPartialText(WebElement ele, String text) {
		return ele.getText().contains(text);
	}	

	public boolean verifyTitle(String title) {
		return getEventDriver().getTitle().equalsIgnoreCase(title);
	}


	public void switchToFrame(WebElement ele) {
		getEventDriver().switchTo().frame(ele);
	}

	public void switchToFrame(int index) {
		getEventDriver().switchTo().frame(index);
	}

	public void acceptAlert() {
		getEventDriver().switchTo().alert().accept();
	}

	public void dismissAlert() {
		getEventDriver().switchTo().alert().dismiss();
	}

	public void sendTextAlert(String txt) {
		getEventDriver().switchTo().alert().sendKeys(txt);
	}

	public String getTextAlert() {
		return getEventDriver().switchTo().alert().getText();
	}	

	public void switchWindow(int index) {
		List<String> lstWindows = new ArrayList<>();
		lstWindows.addAll(getEventDriver().getWindowHandles());
		getEventDriver().switchTo().window(lstWindows.get(index));
	}


	public void mouseOverOnElement(WebElement ele) {
		new Actions(getEventDriver()).moveToElement(ele).build().perform();
	}
}
