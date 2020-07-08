package sikuliX;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.OCR;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

public class FillFormFields {
	
	
	@Test (enabled = true)
	public static void demoQAFormField() {
		Screen screen = new Screen();
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().maximize();
		wd.get("https://demoqa.com/automation-practice-form/");
		
		
		OCR.globalOptions().fontSize(6);
		Region wdRegion = SikuliXUtils.webDriverRegion(wd);
		wdRegion.wait(1.5);
		SikuliXControls.fillTextField("First name:", "UltimateQATextbox.PNG", "John", wdRegion);
		SikuliXControls.fillTextField("First name:", "UltimateQATextbox.PNG", "Smith", wdRegion);
		
		try {
			wdRegion.click(new Location(200,200));
		} catch (FindFailed e) {
		}
		SikuliXControls.scrollAndLookForText("Date:",3, true, wdRegion);
		SikuliXControls.fillTextField("Date:", "UltimateQATextbox.PNG", "05/02/2019", wdRegion);
	}
	
	@Test (enabled = true)
	public static void mercuryToursFormField() {
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().maximize();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		
		Region wdRegion = SikuliXUtils.webDriverRegion(wd);
		Pattern registerButton = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\RegisterButton.PNG");
		try {
			wdRegion.click(registerButton);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OCR.globalOptions().fontSize(5);
		wdRegion.wait(1.0);
		fillAllMercuryToursFieldsOnPage(wdRegion);
	}
	
	public static void fillAllMercuryToursFieldsOnPage(Region region) {
		SikuliXControls.fillTextField("First Name:", "TextBox.PNG", "John", region);
		SikuliXControls.fillTextField("Last Name:", "TextBox.PNG", "Smith", region);
		SikuliXControls.fillTextField("Phone:", "TextBox.PNG", "987-654-3210", region);
		SikuliXControls.fillTextField("Email:", "TextBox.PNG", "thisisafakeemail@thisisafakedomain.ca", region);
		SikuliXControls.fillTextField("Address:", "TextBox.PNG", "12345 test road", region);
		SikuliXControls.fillTextField("City:", "TextBox.PNG", "Ottawa", region);
		SikuliXControls.fillTextField("Province:", "TextBox.PNG", "Ontario", region);
		SikuliXControls.fillTextField("Postal Code:", "TextBox.PNG", "B8J-9G5", region);
		SikuliXControls.fillTextField("User Name:", "TextBox.PNG", "User name", region);
		SikuliXControls.fillTextField("Password:", "TextBox.PNG","password", region);
		SikuliXControls.fillTextField("Confirm Password:", "TextBox.PNG", "password", region);
	}
	
}


