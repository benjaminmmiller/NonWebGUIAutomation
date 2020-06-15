package sikuliX;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import org.sikuli.hotkey.Keys;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

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

	public static Match findAndClickRegionByText(String text, Region region) {
		Match match = new Match();
		try {
			match = region.findText(text);
			region.click(match);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}
	
	
	public static Match findAndClickRegionByImage(String imageName, Region region) {
		Match match = new Match();
		try {
			match = region.find(SikuliXFileDirectories.getImagesFolderPath()+"\\"+imageName);
			region.click(match);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
		return match;
	}

	public static void maximizeWindowsWindow(Region region) {
		region.keyDown(Key.WIN);
		region.type(Key.UP);
		region.keyUp(Key.WIN);
	}

	public static Match scrollAndLookForText(String searchForText, int maxScrolls, Region region) {
		//Using CTRL+HOME to go to the top of the page
		region.keyDown(Keys.CTRL);
		region.type(Key.HOME);
		region.keyUp(Keys.CTRL);
		
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

}
