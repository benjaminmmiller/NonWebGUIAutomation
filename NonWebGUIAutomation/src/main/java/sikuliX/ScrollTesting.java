package sikuliX;

import org.sikuli.basics.Settings;
import org.sikuli.hotkey.Keys;
import org.sikuli.script.App;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Screen;

import utils.TestingFrameworkFileUtils;

public class ScrollTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Settings.TypeDelay = 0.0;
		
		Screen screen = new Screen();
		App word = new App("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
		word.open();
		
		SikuliXUtils.findAndClickRegionByText("Blank Document", screen, word);
		screen.wait(1.0);
		
		OCR.globalOptions().fontSize(3);
		
		word.window().type("Banking Test Org");
		
		System.out.println(word.window().text());
		
		for(int i=0;i<25;i++) {
			word.window().type(Keys.ENTER);
		}
		word.window().type("John Smith");
		for(int i=0;i<3;i++) {
			word.window().type(Keys.ENTER);
		}
		
		Match scrollMatch = SikuliXUtils.scrollAndLookForText("Banking Test Org", 3, word);
		scrollMatch.click();
	}

}
