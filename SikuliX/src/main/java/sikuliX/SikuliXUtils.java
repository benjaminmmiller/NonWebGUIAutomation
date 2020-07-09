package sikuliX;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import utils.MiscUtils;

public class SikuliXUtils {
	
	public static void takeAndSaveScreenshot(Region region, Screen screen) {
		ScreenImage screenshot = screen.capture(region);
		screenshot.save(SikuliXFileDirectories.getOutputImagePath());
		System.out.println("Screenshot has been taken and saved to: "+SikuliXFileDirectories.getOutputImagePath());
	}
	
	public static void highlightAllMatchesForPattern(String imageFilename, Region region) {
		List<Match> matches = SikuliXFinders.findAllMatchesforPattern(imageFilename, region);
		int color = 0;
		for(Match m:matches) {
			System.out.println("Match Score: "+m.getScore());
			if(color==0) {
				m.highlight("red");
			}
			else if(color==1) {
				m.highlight("green");
			}
			else {
				m.highlight("blue");
			}
			color++;
			if(color>2) {
				color=0;
			}
		}
	}
	
	public static boolean textExistsInWindow(String text, Region region) {
		OCR.globalOptions().fontSize(60);
		String textFound = "";
		textFound = region.text();
		System.out.println("Original Text");
		System.out.println(textFound);
		textFound = textFound.replaceAll("\n+", " ");
		System.out.println("Modified Text");
		System.out.println(textFound);
		return textFound.contains(text);
	}
	
	public static boolean patternExists(Pattern pattern, Region region) {
		Match match = region.exists(pattern);
		if(match!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean waitForScreenByImage(String imageFilename, int maxWaitTime, Region region) {
		Pattern pattern= new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+imageFilename);
		try {
			region.wait(pattern, maxWaitTime);
			System.out.println("Screen found. Returning true");
			return true;
		} catch (FindFailed e) {
			System.out.println("No screen has been found. Returning false.");
			return false;
		}
	}
	
	public static boolean waitForScreenByText(String waitForText, int maxWaitTime, Region region) {
		try {
			region.waitText(waitForText);
			return true;
		} catch (FindFailed e) {
			System.out.println("No text has been found. Returning false.");
			return false;
		}
	}
	

	/**
	 * Returns the distance between two SikuliX locations as an int
	 * Uses generic getDistancesBetweenPoints method
	 * @param loc1
	 * @param loc2
	 * @return
	 */
	public static int getDistanceBetweenLocations(Location loc1, Location loc2) {
		return MiscUtils.getDistanceBetweenPoints(loc1.getPoint(), loc2.getPoint());
	}
	
	public static Region webDriverRegion(WebDriver wd) {
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		int wdWidth = wd.manage().window().getSize().width;
		if(wdWidth>device.getDisplayMode().getWidth()) {
			wdWidth = device.getDisplayMode().getWidth();
		}

		int wdHeight = wd.manage().window().getSize().height;
		if(wdHeight>device.getDisplayMode().getHeight()) {
			wdHeight = device.getDisplayMode().getHeight();
		}
		
		Point wdPoint = wd.manage().window().getPosition();
		
		return new Region(wdPoint.x, wdPoint.y, wdWidth, wdHeight);
	}
	
	
	public static Match retrievePatternForText(String textFieldLabel, String patternFilename, Region region) {
		Match fieldLabel = new Match();
		try {
			fieldLabel = region.findText(textFieldLabel);
		} catch (FindFailed e) {
			System.out.println("Text field label was not found. Default match object will be returned.");
			return fieldLabel;
		}
		Match textbox = SikuliXFinders.findClosestPatternToRegion(fieldLabel, patternFilename, region);
		return textbox;
	}
	
	public static String getClipboardText() {
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		String clipboardText = "";
        try {
			clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return clipboardText;
	}
	
	public static void clearClipboardContent() {
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(""), null);
	}
}
