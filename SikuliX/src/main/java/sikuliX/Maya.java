package sikuliX;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import utils.MiscUtils;

public class Maya {

	public static void main(String[] args) {
		Settings.TypeDelay = 0.0;
		Screen screen = new Screen();
		App maya = new App("C:\\Program Files\\Autodesk\\Maya2018\\bin\\maya.exe");
		maya.open();
		
		SikuliXUtils.waitForScreenByImage("Maya.PNG", 60, screen);
		
	}

}
