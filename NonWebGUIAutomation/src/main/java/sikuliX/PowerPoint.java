package sikuliX;

import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
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
		
		SikuliXUtils.findAndClickRegionByText("Blank Presentation", powerpoint.window());
		screen.wait(3.0);
		
		Match title = addPowerpointTitleText("Testing using SikuliX. This is the title.", powerpoint.window());
		Match subTitle = SikuliXUtils.findAndClickRegionByText("Click to add subtitle", powerpoint.window());
		subTitle.type("Testing using SikuliX. This is the subtitle.");
		
		newPowerpointSlide(powerpoint.window());
		screen.wait(1.0);
		addPowerpointTitleText("Testing using SikuliX. This is the title for a slide", powerpoint.window());
		addPowerpointBodyText("Point 1\r" + 
						  "Point 2\r" + 
						  "Point 3", powerpoint.window());
		
		newPowerpointSlide(powerpoint.window());
		screen.wait(1.0);
		
		addPowerpointTitleText("Image Slide", powerpoint.window());
		
		SikuliXUtils.findAndClickRegionByText("Click to add text", powerpoint.window());
		insertPowerpointImage("checkmark.png", powerpoint.window());
		exitPowerpoint(screen, powerpoint);
	}
	
	public static void insertPowerpointImage(String fileName, Region region) {
		SikuliXUtils.findAndClickRegionByText("Insert", region);
		region.wait(0.5);
		SikuliXUtils.findAndClickRegionByText("Pictures", region);
		region.wait(0.5);
		SikuliXUtils.findAndClickRegionByText("This Device", region);
		region.wait(0.5);
		region.type(imagesFolderPath+"\\"+fileName);
		region.type(Key.ENTER);
	}
	
	public static void newPowerpointSlide(Region region) {
		region.keyDown(Key.CTRL);
		region.type("M");
		region.keyUp(Key.CTRL);
	}
	
	public static Match addPowerpointTitleText(String text, Region region) {
		Match title = SikuliXUtils.findAndClickRegionByText("Click to add title", region);
		title.type(text);
		return title;
	}
	
	public static Match addPowerpointBodyText(String text, Region region) {
		Match body = SikuliXUtils.findAndClickRegionByText("Click to add text", region);
		body.type(text);
		region.wait(1.0);
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
