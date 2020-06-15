package tests;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Before;
import org.junit.Test;

import controls.SWTBotControls;
import utils.SWTBotUtils;

public class SWTBotTest {

	private static SWTWorkbenchBot bot;
	private final static int PLAYBACK_DELAY = 200;
	private final static String KEYBAORD_LAYOUT = "EN_US";
	
	
	@Before
	public void setupTests(){
		System.out.println("Starting test");
		SWTBotPreferences.KEYBOARD_LAYOUT = KEYBAORD_LAYOUT;
		SWTBotPreferences.PLAYBACK_DELAY = PLAYBACK_DELAY;
		bot = new SWTWorkbenchBot();
	}
	
	@Test
	public void swtBotTest() {
		SWTBotControls.maximizeActiveWindow(bot);
		bot.viewByTitle("Welcome").close();
		bot.menu("Form Editors").menu("Simple Form Editor").click();
		
		
		//Fill all tabs
		fillNewStyleTab(bot);
		fillMessageManagerTab(bot);
		fillFirstPage(bot);
		fillFlowPage(bot);
		fillMasterDetails(bot);
		fillCompositePage(bot);
	}

	public static void fillNewStyleTab(SWTBot bot) {
		//Switch to "New Style" tab by text label
		bot.cTabItem("New Style").activate().setFocus();
		
		//Click checkboxes
		bot.checkBox("Add title").click();
		bot.checkBox("Add image").click();
		
		//Click radios
		bot.radio("Long title").click();
		bot.radio("Long message").click();
		bot.radio("Short message").click();
		
		//Click buttons
		bot.button("Error").click();
		bot.button("Warning").click();
		bot.button("Cancel").click();
	}
	
	public static void fillMessageManagerTab(SWTBot bot) {
		//Switch to "Message Manager" tab by text label
		bot.cTabItem("Message Manager").activate().setFocus();
		
		//Set text based on field label
		bot.textWithLabel("Field1").setText("Label set text");
		SWTBotControls.delay(PLAYBACK_DELAY );
		
		//Simulate typing the set based on the field label
		//Does not seem to work while PC is locked
		bot.textWithLabel("Field2").typeText("Label type text");
		bot.text(2).setText("Index set text");
		SWTBotControls.delay(PLAYBACK_DELAY );
	}
	
	public static void fillFirstPage(SWTBot bot) {
		//Switch to "First page" tab by text label
		bot.cTabItem("First Page").activate().setFocus();
		//Need a FormsBot to click hyperlinks
		SWTFormsBot formsBot = new SWTFormsBot();
		formsBot.hyperlink("Sample hyperlink with longer text.").click();
		
		//Click labels and button
		bot.label("Expandable Section with a longer title").click();
		bot.button("Button").click();
		bot.label("Section title").click();
	}
	
	
	public static void fillFlowPage(SWTBot bot) {
		//Switch to "Flow Page" tab by text label
		bot.cTabItem("Flow Page").activate().setFocus();
		bot.label("Control Section").click();
		bot.label("Control Section").click();
		SWTBotControls.clickRadioForLabel("Control Section", 1, bot);
	}
	
	public static void fillMasterDetails(SWTBot bot) {
		//Switch to "Master Details" tab by text label
		bot.cTabItem("Master Details").activate().setFocus();
		//Click the first item found in the table
		bot.table().getTableItem(0).click();
		
		//Click radio and checkbox
		SWTBotControls.clickRadioForLabel("Type One Details", 1, bot);
		bot.checkBox("Value of the flag property").deselect();
		
		//Fill text field
		bot.textWithLabel("Text property:").setText("Text property field test");
		SWTBotControls.delay(PLAYBACK_DELAY );
	}

	public static void fillCompositePage(SWTBot bot) {
		//Switch to "Composite Page" tab by text label
		bot.cTabItem("Composite Page").activate().setFocus();
		//Fill all the freeform text fields
		bot.textWithLabel("Form with subpages").setText("This is some testing text for a free form text field");
		SWTBotControls.delay(PLAYBACK_DELAY );
		bot.cTabItem("License Agreement").activate().setFocus();
		bot.textWithLabel("Form with subpages").setText("This is some testing text for a free form text field");
		SWTBotControls.delay(PLAYBACK_DELAY );
		bot.cTabItem("Description").activate().setFocus();
		bot.textWithLabel("Form with subpages").setText("This is some testing text for a free form text field");
		SWTBotControls.delay(PLAYBACK_DELAY );
	}
	
	public void addTask() {
		bot = new SWTWorkbenchBot();
		//Simulates right clicking by directly accessing context menu
		SWTBotTree currentTree = bot.tree();
		currentTree.contextMenu("Add Task...").click();
		
		bot.text().setText("Test");
		bot.button("Add").click();
	}
}


