package sikuliX;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

import org.sikuli.hotkey.Keys;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import utils.MiscUtils;

public class SikuliXControls {

	public static Pattern typeTextForPattern(String imageName, String fieldText, Region region) {
		Pattern fieldPattern = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\"+imageName);
		try {
			region.type(fieldPattern, fieldText);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fieldPattern;
	}

	public static void maximizeWindowsWindow(Region region) {
		region.keyDown(Key.WIN);
		region.type(Key.UP);
		region.keyUp(Key.WIN);
	}

	public static Match scrollAndLookForText(String searchForText, int maxScrolls, boolean scrollFromTop, Region region) {
		moveMouseToSide(region);
		if(scrollFromTop) {
			//Using HOME to go to the top of the page
			region.type(Key.HOME);
		}
		Match textFound = new Match();
		for(int i=0;i<maxScrolls;i++) {
			//Wait a bit for the scroll to happen
			region.wait(0.5);
			try {
				textFound = region.findText(searchForText);
				return textFound;
			} catch (FindFailed e) {
				System.out.println("Text not found. Continuing page scroll.");
			}
			//Use the Page Down key to scroll down
			region.type(Key.PAGE_DOWN);
		}
		System.out.println("No text was found while scrolling");
		return textFound;
	}

	public static Match scrollAndLookForPattern(String patternFilename, int maxScrolls, Region region) {
		//Using CTRL+HOME to go to the top of the page
		region.keyDown(Keys.CTRL);
		region.type(Key.HOME);
		region.keyUp(Keys.CTRL);
		
		Match patternFound = new Match();
		for(int i=0;i<maxScrolls;i++) {
			//Wait a bit for the scroll to happen
			region.wait(0.5);
			try {
				patternFound = region.find(SikuliXFileDirectories.getImagesFolderPath()+"\\"+patternFilename);
				return patternFound;
			} catch (FindFailed e) {
				System.out.println("Pattern not found. Continuing page scroll.");
			}
			//Use the Page Down key to scroll down
			region.type(Key.PAGE_DOWN);
		}
		System.out.println("No pattern was found while scrolling. Returning default match object.");
		return patternFound;
	}

	public static void multikey(String holdKey, String pressKey, Region region) {
		region.keyDown(holdKey);
		region.type(pressKey);
		region.keyUp(holdKey);
	}
	
	public static void multikey(List<String> holdKeys, String pressKey, Region region) {
		for(String holdKey:holdKeys) {
			region.keyDown(holdKey);
		}
		region.type(pressKey);
		for(String holdKey:holdKeys) {
			region.keyUp(holdKey);
		}
	}

	public static void pasteText(String textToPaste, Region region) {
		StringSelection stringSelection = new StringSelection(textToPaste);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		multikey(Key.CTRL, "v", region);
	}

	public static Match fillTextField(String textFieldLabel, String patternFilename, String value, Region region) {
		Match match = SikuliXUtils.retrievePatternForText(textFieldLabel, patternFilename, region);
		match.click();
		region.type(value);
		return match;
	}
	
	
	public static Match waitForAndClickRegionByImage(String image, int maxWaitInSeconds, Region region) {
		SikuliXUtils.waitForScreenByImage(image, maxWaitInSeconds, region);
		return SikuliXFinders.findAndClickRegionByImage(image, region);
	}
	
	public static Match waitForAndClickRegionByText(String text, int maxWaitInSeconds, Region region) {
		SikuliXUtils.waitForScreenByText(text, maxWaitInSeconds, region);
		return SikuliXFinders.findAndClickRegionByText(text, region);
	}

	
	public static Match findImageWithOffset(String imageName, Region region, Point offset) {
		Match match = new Match();
		try {
			match = region.find(imageName);
			region.mouseMove(new Location(match.x+offset.x,match.y+offset.y));
		}
		catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}
	
	
	public static String getSelectedTextFromClipboard(Region region) {
		SikuliXControls.multikey(Key.CTRL, "c", region);
		MiscUtils.delay(200);
		return SikuliXUtils.getClipboardText();
	}
	
	
	public static boolean verifyClipboardText(Region region, String expectedText) {
        if(getSelectedTextFromClipboard(region).equals(expectedText)) {
        	return true;
        }
        else {
        	return false;
        }
	}
	
	
	public static void moveMouseToSide(Region region) {
		try {
			region.mouseMove(new Location(region.w/15, region.h/2));
			MiscUtils.delay(200);
			region.click(Mouse.at());
			MiscUtils.delay(200);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static Match scrollUntilTextIsTop(String text, int maxNumberOfScrollsSteps, int scrollStepSize, int minDistanceFromTop, Region region, boolean scrollFromTop) {
		boolean textWasFound = false;
		boolean textScrolledOff = false;
		if(scrollFromTop) {
			moveMouseToSide(region);
			region.type(Key.HOME);
		}
		try {
			region.mouseMove(region);
		} catch (FindFailed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Match textFound = new Match();
		for(int i=0;i<maxNumberOfScrollsSteps;i++) {
			try {
				textFound = region.findText(text);
				textWasFound=true;
				int distance = textFound.y-region.y;
				System.out.println("Text was found. Scrolling until text is at top of the screen");
				if(distance<minDistanceFromTop) {
					return textFound;
				}
				if(textScrolledOff) {
					return textFound;
				}
			} catch (FindFailed e) {
				if(textWasFound) {
					System.out.println("Scrolled the found text off the page, due to the scroll step and minDistanceFromTop ratio."
							+ "Scrolling up until text is back on screen");
					textScrolledOff = true;
					Mouse.wheel(Mouse.WHEEL_UP,2);
				}
				else {
					System.out.println("Pattern not found. Continuing page scroll.");
				}
			}
			if(!textScrolledOff) {
				Mouse.wheel(Mouse.WHEEL_DOWN, scrollStepSize);
			}
		}
		return textFound;
	}
}
