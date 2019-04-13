package pageobject;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.linkText;

import org.openqa.selenium.By;

import baseclass.BasePage;

public class Test_Page extends BasePage {

	private By draggable=linkText("Draggable");
	private By iframe=className("demo-frame");
	private By dragMeAround=id("draggable");

	public void clickOnDraggable() {
		waitForParticularElement(draggable,1);
		click(draggable);
	}

	public void dragAndDropToElement() {
		driver.switchTo().frame(driver.findElement(iframe));
		dragAndDropByPosition(dragMeAround, 50, 50);
		driver.switchTo().defaultContent();
	}

	public String getAttribute() {
		driver.switchTo().frame(driver.findElement(iframe));
		String value=getAttribute(dragMeAround, "style");
		driver.switchTo().defaultContent();
		return value;
	}
}
