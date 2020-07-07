package sikuliX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.OCR;
import org.testng.annotations.Test;

import utils.FileUtils;

public class OCRTest {
	private final static String dummyParagraphEN = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.";
	private final static String dummyParagraphFR = "Mais je dois vous expliquer comment est née toute cette idée erronée de dénoncer le plaisir et louer la douleur et je vais vous donner un compte rendu complet du système, et exposer les enseignements réels du grand explorateur de la vérité, le maître-constructeur de l'homme bonheur.";
	
	
	@Test(enabled = false)
	public void englishTest(){
		try {
			testText(dummyParagraphEN);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	@Test(enabled = true)
	public void frenchTest(){
		//Change the language to French
		//Need to include French trained data in SikuliX directory (User)\AppData\Roaming\Sikulix\SikulixTesseract\tessdata
		//Trained data found from https://github.com/tesseract-ocr/tessdata
		Settings.OcrLanguage = "fra";
		try {
			testText(dummyParagraphFR);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	
	
	private void testText(String testText) throws FindFailed{
		
		App word = new App("C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
		word.open();
		
		
		
		SikuliXFinders.findAndClickRegionByText("Blank document", word.window());
		word.window().wait(1.0);
		SikuliXControls.pasteText(testText, word.window());
		
		word.window().mouseMove(word.window().getTopLeft());
		//Get the text with default settings
		FileUtils.createAndWriteToTextFile(SikuliXFileDirectories.getOutputTextPath(), "SmallText-Default", word.window().text());
	
		//Get the text with modifying the font size
		OCR.globalOptions().fontSize(12);
		FileUtils.createAndWriteToTextFile(SikuliXFileDirectories.getOutputTextPath(), "SmallText-Size12", word.window().text());
		
		//Reset back to default
		
		
		//Select the text and increase the font size.
		SikuliXControls.multikey(Key.CTRL, "a", word.window());
		List<String> increaseSizeKeys= new ArrayList<String>();
		Collections.addAll(increaseSizeKeys,Key.CTRL, Key.SHIFT);
		for(int i =0;i<8;i++) {
			SikuliXControls.multikey(increaseSizeKeys, ">", word.window());
		}
		//Deselect the text
		word.window().type(Key.RIGHT);
		
		//Get the text with default OCR settings
		FileUtils.createAndWriteToTextFile(SikuliXFileDirectories.getOutputTextPath(), "BigText-Default", word.window().text());
		
		//Get the text with font size adjusted
		OCR.globalOptions().fontSize(26);
		FileUtils.createAndWriteToTextFile(SikuliXFileDirectories.getOutputTextPath(), "BigText-Size26", word.window().text());
	}

}
