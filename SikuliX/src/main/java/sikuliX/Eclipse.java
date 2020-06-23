package sikuliX;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.OCR;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.TestingFrameworkFileUtils;

public class Eclipse {

	
	@BeforeTest
	public static void setupTest() {
		OCR.globalOptions().oem(OCR.OEM.LSTM_ONLY);
		OCR.globalOptions().fontSize(8);
	}
	
	@Test(enabled = true)
	public static void eclipseTest() {
		Screen screen = new Screen();
		String eclipsePath = "D:\\WorkFromHome\\SikuliXTest\\RCPTestApp\\eclipse\\eclipsec.exe";
		App eclipse = new App(eclipsePath);
		if(!eclipse.open()) {
			throw new RuntimeException("Application did not open");
		}
		
		//Wait for homepage
		SikuliXUtils.waitForScreenByImage("EclipseHomepage.png", 30, screen);
	
		//Find and click "FormEditor" in the menu
		SikuliXControls.findAndClickRegionByImage("FormEditors.PNG", screen);
		//Wait for dropdown menu to appear
		SikuliXUtils.waitForScreenByText("Simple Form Editor", 15, screen);
		//Find and click "Simple Form Editor" in the dropdown menu by text
		SikuliXControls.findAndClickRegionByText("Simple Form Editor", eclipse.window());
		
	
		//Wait for form editors screen
		SikuliXUtils.waitForScreenByImage("EclipseEmptySimpleForm.PNG", 15, screen);
		
		OCR.globalOptions().fontSize(6);
		
		//Click checkboxes by text
		SikuliXControls.findAndClickRegionByText("Add title", screen);
		SikuliXControls.findAndClickRegionByText("Add image", screen);
		SikuliXControls.findAndClickRegionByText("Add tool bar", screen);
		
		//Click buttons by image
		SikuliXControls.findAndClickRegionByImage("EclipseErrorButton.PNG", screen);
		SikuliXControls.findAndClickRegionByImage("EclipseWarningButton.PNG", screen);
		SikuliXControls.findAndClickRegionByImage("EclipseCancelButton.png", screen);
		
		//Switch to "Message Manager tab"
		SikuliXControls.findAndClickRegionByImage("EclipseMessageManagerTab.PNG", screen);
		
		OCR.globalOptions().fontSize(8);
		SikuliXUtils.waitForScreenByText("Example with message handling", 15, screen);
		
		OCR.globalOptions().fontSize(5);
		
		//Fill text fields
		SikuliXControls.fillTextField("Field1", "EclipseTextField.PNG", "Field 1 text", screen);
		OCR.globalOptions().fontSize(6);
		SikuliXControls.fillTextField("Field2", "EclipseTextField.PNG", "Field 2 text", screen);
		SikuliXControls.fillTextField("Field3", "EclipseTextField.PNG", "Field 3 text", screen);
		
		//Click checkboxes
		SikuliXControls.findAndClickRegionByText("Add general error", screen);
		SikuliXControls.findAndClickRegionByText("Add static message", screen);
		SikuliXControls.findAndClickRegionByText("Auto update", screen);
		
		//TestingFrameworkFileUtils.createAndWriteToTextFile(SikuliXFileDirectories.getOutputTextPath(), "EclipseHomePageText", screen.text());
	}
	
}
