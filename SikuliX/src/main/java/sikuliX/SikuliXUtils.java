package sikuliX;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

import utils.MiscUtils;
import utils.TestingFrameworkFileUtils;

public class SikuliXUtils {
	
	public static void takeAndSaveScreenshot(Region region, Screen screen) {
		ScreenImage screenshot = screen.capture(region);
		screenshot.save(SikuliXFileDirectories.getOutputImagePath());
		System.out.println("Screenshot has been taken and saved to: "+SikuliXFileDirectories.getOutputImagePath());
	}
	
	public static List<Match> findAllMatchesforPattern(String patternImage, Region region){
		Pattern pattern = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+patternImage);
		List <Match> matches = new ArrayList<Match>();
		try {
			Iterator<Match> matchesFound = region.findAll(pattern);
			matches = MiscUtils.iteratorToList(matchesFound);
		} catch (FindFailed e) {
			System.out.println("No matches were found for pattern. Returning empty list.");
			e.printStackTrace();
		}
		return matches;
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
	

	public static Match findClosestPatternToRegion(Region region, String imageName, Region screenOrAppRegion) {
		Match closestMatch = new Match();
		List<Match> matches = findAllMatchesforPattern(imageName, screenOrAppRegion);
		Location regionLocation = region.getCenter();
		for(int i=0;i<matches.size();i++) {
			if(i==0) {
				closestMatch = matches.get(i);
			}
			else {
				int distanceFound = SikuliXUtils.getDistanceBetweenLocations(regionLocation, matches.get(i).getCenter());
				int closestDistance = SikuliXUtils.getDistanceBetweenLocations(regionLocation, closestMatch.getCenter());
				if(distanceFound<closestDistance) {
					closestMatch=matches.get(i);
				}
			}
		}
		return closestMatch;
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
		Match textbox = SikuliXUtils.findClosestPatternToRegion(fieldLabel, patternFilename, region);
		return textbox;
	}
}
