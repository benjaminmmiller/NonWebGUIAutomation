package sikuliX;

import static org.testng.Assert.assertTrue;

import java.awt.Point;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.TestNG;
import org.testng.annotations.Test;

import imageGeneration.DynamicImage;

public class DynamicImageGeneration {

	@Test
	public void dynamicImageTest() throws FindFailed {
		String firstName = "John Smith";
		DynamicImage firstNameImage = new DynamicImage.DynamicImageBuilder(SikuliXUtils.imagesFolderPath+"\\FirstName.PNG")
				.text(firstName)
				.textLocation(new Point(90,21))
				.build();
		
		File firstNameFile = firstNameImage.writeImage(firstNameImage.generateImage(), SikuliXUtils.outputDynamicImagePath+"\\Test.png");
		
		WebDriver wd = new FirefoxDriver();
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		Screen screen = new Screen();
		
		Region wdRegion = SikuliXUtils.webDriverRegion(wd);
		
		Pattern registerButton = new Pattern(SikuliXUtils.imagesFolderPath+"\\RegisterButton.PNG");
		wdRegion.click(registerButton);
		
		
		Pattern firstNameField = new Pattern(SikuliXUtils.imagesFolderPath+"\\FirstName.PNG");
		wdRegion.click(firstNameField);
		
		wdRegion.type(firstName);
		wdRegion.mouseMove(new Location(0,0));
		
		Pattern verificationImage = new Pattern(firstNameFile.getAbsolutePath());
		wdRegion.click(verificationImage);
		assertTrue(SikuliXUtils.patternExists(verificationImage, wdRegion));
	}
}
