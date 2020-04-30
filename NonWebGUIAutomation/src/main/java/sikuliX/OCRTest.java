package sikuliX;

import org.sikuli.script.App;
import org.sikuli.script.Screen;

public class OCRTest {

	public static void main(String[] args) {
		
		Screen screen = new Screen();
		App word = new App("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
		word.open();
		word.window().wait(2.0);
		
		
		
	}

}
