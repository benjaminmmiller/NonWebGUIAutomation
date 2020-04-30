package sikuliX;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertTrue;

import org.sikuli.basics.Settings;

import utils.TestingFrameworkFileUtils;

public class PowerPoint {
	final static String screenshotDir = TestingFrameworkFileUtils.getProjectFilePath() +"\\test-output\\screenshots";
	final static String imagesFolderPath = TestingFrameworkFileUtils.getProjectFilePath() +"\\src\\main\\resources\\images";
	
	public static void main(String[] args) throws FindFailed, InterruptedException {
		OCR.globalOptions().oem(OCR.OEM.LSTM_ONLY);
		powerpointTest();
	}
	
	public static void powerpointTest() {
		Settings.TypeDelay = 0.0;
		Screen screen = new Screen();
		App powerpoint = new App("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\POWERPNT.EXE");
		powerpoint.open();
		
		SikuliXUtils.findAndClickRegionByText("Blank Presentation", screen, powerpoint);
		screen.wait(3.0);
		
		Match title = addPowerpointTitleText("Testing using SikuliX. This is the title.", screen, powerpoint);
		Match subTitle = SikuliXUtils.findAndClickRegionByText("Click to add subtitle", screen, powerpoint);
		subTitle.type("Testing using SikuliX. This is the subtitle.");
		
		newPowerpointSlide(powerpoint);
		screen.wait(1.0);
		addPowerpointTitleText("Testing using SikuliX. This is the title for a slide", screen, powerpoint);
		addPowerpointBodyText("Point 1\r" + 
						  "Point 2\r" + 
						  "Point 3", screen, powerpoint);
		
		newPowerpointSlide(powerpoint);
		screen.wait(1.0);
		
		addPowerpointTitleText("Image Slide", screen, powerpoint);
		
		SikuliXUtils.findAndClickRegionByText("Click to add text", screen, powerpoint);
		insertPowerpointImage("checkmark.png", screen, powerpoint);
		exitPowerpoint(screen, powerpoint);
	}
	
	public static void insertPowerpointImage(String fileName, Screen screen, App app) {
		SikuliXUtils.findAndClickRegionByText("Insert", screen, app);
		screen.wait(0.5);
		SikuliXUtils.findAndClickRegionByText("Pictures", screen, app);
		screen.wait(0.5);
		SikuliXUtils.findAndClickRegionByText("This Device", screen, app);
		screen.wait(0.5);
		screen.type(imagesFolderPath+"\\"+fileName);
		screen.type(Key.ENTER);
	}
	
	public static void newPowerpointSlide(App app) {
		app.window().keyDown(Key.CTRL);
		app.window().type("M");
		app.window().keyUp(Key.CTRL);
	}
	
	public static Match addPowerpointTitleText(String text, Screen screen, App app) {
		Match title = SikuliXUtils.findAndClickRegionByText("Click to add title", screen, app);
		title.type(text);
		return title;
	}
	
	public static Match addPowerpointBodyText(String text, Screen screen, App app) {
		Match body = SikuliXUtils.findAndClickRegionByText("Click to add text", screen, app);
		body.type(text);
		screen.wait(1.0);
		return body;
	}
	
	public static void exitPowerpoint(Screen screen, App app) {
		if(app.focus()==true) {
			screen.keyDown(Key.ALT);
			screen.type(Key.F4);
			screen.keyUp(Key.ALT);
			OCR.globalOptions().smallFont();
			screen.wait(1.0);
			try {
				app.window().findText("Don't Save").click();
			} catch (FindFailed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
