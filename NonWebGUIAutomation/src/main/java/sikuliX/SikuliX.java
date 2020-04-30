package sikuliX;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.OCR;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import utils.MiscUtils;
import utils.TestingFrameworkFileUtils;

public class SikuliX {
	final static String imagesFolderPath = TestingFrameworkFileUtils.getProjectFilePath() +"\\src\\main\\resources\\images";
	public static void main(String[] args) throws FindFailed, InterruptedException {
		
		
		WebDriver wd = new FirefoxDriver();
		wd.manage().window().maximize();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		Screen screen = new Screen();
		
		Region wdRegion = SikuliXUtils.webDriverRegion(wd);
		
		
		Pattern registerButton = new Pattern(SikuliXUtils.imagesFolderPath+"\\RegisterButton.PNG");
		try {
			wdRegion.click(registerButton);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fillAllFieldsOnPage(screen);
	}
	
	public static void fillAllFieldsOnPage(Screen screen) {
		SikuliXUtils.fillTextField("First Name:", "John", screen);
		SikuliXUtils.fillTextField("Last Name:", "Smith", screen);
		SikuliXUtils.fillTextField("Phone:", "987-654-3210", screen);
		SikuliXUtils.fillTextField("Email:", "thisisafakeemail@thisisafakedomain.ca", screen);
		
		
		SikuliXUtils.fillTextField("Address:", "12345 test road", screen);
		SikuliXUtils.fillTextField("City:", "Ottawa", screen);
		SikuliXUtils.fillTextField("Province:", "Ontario", screen);
		SikuliXUtils.fillTextField("Postal Code:", "B8J-9G5", screen);
		
		
		SikuliXUtils.fillTextField("User Name:", "User name", screen);
		SikuliXUtils.fillTextField("Password:", "password", screen);
		SikuliXUtils.fillTextField("Confirm Password:", "password", screen);
	}
	
	

}
