package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.rc.common.adaptable.AdapterFactoryRegistry;
import org.eclipse.jubula.rc.common.tester.AbstractUITester;
import org.eclipse.jubula.rc.common.tester.adapter.interfaces.IComponent;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;
import org.eclipse.jubula.toolkit.base.components.TextInputComponent;

public class JubulaUtils {

	
	public static boolean getButtonSelection(ButtonComponent button, AUT aut) {
		return Boolean.valueOf(aut.execute(button.isSelected(), null).getReturnValue());
	}
	
	public static String getTextFromTextInput(TextInputComponent textInput, AUT aut) {
		return aut.execute(textInput.readValue(), null).getReturnValue();
	}
	
	public static void getRealComponent(Object graphicsComponent) {
		
		
	}
	
	public static void takeScreenshot(AUT aut) {
		//Create the screenshots folder in the testoutput directory if it does not exist
		String screenshotDirectoryPath = FileUtils.getProjectFilePath()+"\\test-output\\screenshots";
		File screenshotFolder = FileUtils.createDirectory(screenshotDirectoryPath, false);
		
		BufferedImage image = aut.getScreenshot();
		File imageFile = new File(screenshotFolder.getAbsoluteFile()+"\\"+generateDateForFilename()+".png");
		try {
			ImageIO.write(image, "png", imageFile);
			System.out.println("Screenshot Taken: "+imageFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String generateDateForFilename() {
		String currentDate = new Date().toString();
		currentDate = currentDate.replaceAll(":", ".");
		System.out.println(currentDate);
		return currentDate;
	}
}
