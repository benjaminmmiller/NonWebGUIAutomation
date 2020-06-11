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
		
		SikuliXUtils.waitForScreenByImage("EclipseHomepage.png", 30, screen);
	
		SikuliXControls.findAndClickRegionByImage("FormEditors.PNG", screen);
		SikuliXControls.findAndClickRegionByText("Simple Form Editor", eclipse.window());
		
		//TestingFrameworkFileUtils.createAndWriteToTextFile(SikuliXFileDirectories.getOutputTextPath(), "EclipseHomePageText", screen.text());
		
		//Wait by screen
		SikuliXUtils.waitForScreenByImage("EclipseEmptySimpleForm.PNG", 15, screen);
		
		//Click checkboxes by text
		SikuliXControls.findAndClickRegionByText("Add title", screen);
		SikuliXControls.findAndClickRegionByText("Add image", screen);
		SikuliXControls.findAndClickRegionByText("Add tool bar", screen);
		
		//Click buttons by image
		SikuliXControls.findAndClickRegionByImage("EclipseErrorButton.PNG", screen);
		SikuliXControls.findAndClickRegionByImage("EclipseWarningButton.PNG", screen);
		SikuliXControls.findAndClickRegionByImage("EclipseCancelButton.png", screen);
		
		//Switch tab
		SikuliXControls.findAndClickRegionByImage("EclipseMessageManagerTab.PNG", screen);
		
		//SikuliXUtils.waitForScreenByText("", maxWaitTime, region)
		
		
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
	}
	
}
