package sikuliX;

import org.sikuli.basics.Settings;
import org.sikuli.hotkey.Keys;
import org.sikuli.script.App;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Screen;

public class ScrollTesting {

	public static void main(String[] args) {
		testTextScroll();
	}

	public static void testTextScroll() {
		Settings.TypeDelay = 0.0;
		
		Screen screen = new Screen();
		App word = new App("C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
		word.open();
		
		SikuliXControls.findAndClickRegionByText("Blank Document", word.window());
		screen.wait(1.0);
		
		OCR.globalOptions().fontSize(3);
		
		word.window().type("Mary Sue");
		
		for(int i=0;i<25;i++) {
			word.window().type(Keys.ENTER);
		}
		word.window().type("John Smith");
		for(int i=0;i<3;i++) {
			word.window().type(Keys.ENTER);
		}
		
		Match scrollMatch = SikuliXControls.scrollAndLookForText("John Smith", 3, word.window());
		scrollMatch.click();
	}
	
	
	
}
