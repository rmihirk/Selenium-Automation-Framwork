package testobject;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import baseScript.BaseTest;
import pageobject.Test_Page;

public class TC_00 extends BaseTest {

	Test_Page test_po = new Test_Page();

	@Test
	public void scenario1() {

		test_po.clickOnDraggable();
		String get1 = test_po.getAttribute();
		test_po.dragAndDropToElement();
		String get2 = test_po.getAttribute();
		if (get1.equals(get2)) {
			assertTrue(false);
		}
	}

}
