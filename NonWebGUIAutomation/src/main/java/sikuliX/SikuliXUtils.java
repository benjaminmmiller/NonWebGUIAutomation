package sikuliX;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.sikuli.hotkey.Keys;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
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
	
	public static List<Match> findAllMatchesforPattern(String patternImage, Screen screen){
		Pattern pattern = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+patternImage);
		List <Match> matches = new ArrayList<Match>();
		try {
			Iterator<Match> matchesFound = screen.findAll(pattern);
			matches = MiscUtils.iteratorToList(matchesFound);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matches;
	}
	
	public static Pattern typeTextForPattern(String imageName, String fieldText, Screen screen) {
		Pattern fieldPattern = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+imageName);
		try {
			screen.type(fieldPattern, fieldText);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fieldPattern;
	}
	
	
	public static Match findAndClickRegionByText(String text, Screen screen) {
		Match match = new Match();
		try {
			match = screen.findText(text);
			screen.click(match);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}
	
	public static Match findAndClickRegionByText(String text, Screen screen, App app) {
		Match match = new Match();
		try {
			match = app.window().findText(text);
			screen.click(match);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}
	

	public static boolean textExistsInWindow(String text, Screen screen, App app) {
		OCR.globalOptions().fontSize(60);
		String textFound = "";
		textFound = app.window().text();
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
	
	public static void maximizeWindowsWindow(App app) {
		app.window().keyDown(Key.WIN);
		app.window().type(Key.UP);
		app.window().keyUp(Key.WIN);
	}
	
	public static void waitForScreen(String imageScreen, int maxWaitTime, Screen screen) {
		Pattern pattern= new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+imageScreen);
		try {
			screen.wait(pattern, maxWaitTime);
		} catch (FindFailed e) {
			System.out.println(screen);
			e.printStackTrace();
		}
		System.out.println("Screen Found");
	}
	
	
	public static Match findClosestPatternToRegion(Region region, String imageName, Screen screen) {
		Match closestMatch = new Match();
		List<Match> matches = findAllMatchesforPattern(imageName, screen);
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
	
	
	public static Match scrollAndLookForText(String searchForText, int maxScrolls, App app) {
		app.window().keyDown(Keys.CTRL);
		app.window().type(Key.HOME);
		app.window().keyUp(Keys.CTRL);
		
		Match textFound = null;
		for(int i=0;i<maxScrolls;i++) {
			app.window().wait(0.5);
			try {
				textFound = app.window().findText(searchForText);
			} catch (FindFailed e) {
				System.out.println("Text not found. Continuing page scroll.");
			}
			if(textFound!=null) {
				return textFound;
			}
			app.window().type(Key.PAGE_DOWN);
		}
		if(textFound==null) {
			System.out.println("No text was found while scrolling");
			textFound= new Match();
		}
		return textFound;
	}
	
	public static void fillTextField(String textFieldLabel, String value, Screen screen) {
		Match match = new Match();
		try {
			OCR.globalOptions().fontSize(5);
			match = screen.findText(textFieldLabel);
			match.click();
			SikuliXUtils.findClosestPatternToRegion(match, "TextBox.PNG", screen).click();
			screen.type(value);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
