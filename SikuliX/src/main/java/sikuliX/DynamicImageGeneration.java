package sikuliX;

import static org.testng.Assert.assertTrue;

import java.awt.Point;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.testng.annotations.Test;

import dynamicImages.DynamicImage;

public class DynamicImageGeneration {

	@Test
	public void dynamicImageTest() throws FindFailed {
		String firstName = "John Smith";
		DynamicImage firstNameImage = new DynamicImage.DynamicImageBuilder(SikuliXFileDirectories.getImagesFolderPath()+"\\FirstName.PNG")
				.text(firstName)
				.textLocation(new Point(90,21))
				.build();
		
		File firstNameFile = firstNameImage.writeImage(firstNameImage.generateImage(), SikuliXFileDirectories.getOutputDynamicImagePath()+"\\Test.png");
		
		WebDriver wd = new FirefoxDriver();
		wd.get("http://newtours.demoaut.com/mercurywelcome.php");
		Screen screen = new Screen();
		
		Region wdRegion = SikuliXUtils.webDriverRegion(wd);
		
		Pattern registerButton = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\RegisterButton.PNG");
		wdRegion.click(registerButton);
		
		
		Pattern firstNameField = new Pattern(SikuliXFileDirectories.getImagesFolderPath()+"\\FirstName.PNG");
		wdRegion.click(firstNameField);
		
		wdRegion.type(firstName);
		wdRegion.mouseMove(new Location(0,0));
		
		Pattern verificationImage = new Pattern(firstNameFile.getAbsolutePath());
		wdRegion.click(verificationImage);
		assertTrue(SikuliXUtils.patternExists(verificationImage, wdRegion));
	}
}
