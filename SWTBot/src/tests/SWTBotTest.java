package tests;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.asyncExec;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.inGroup;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withLabel;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotPerspective;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.forms.finder.SWTFormsBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.ChildrenControlFinder;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.matchers.WidgetOfType;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import controls.SWTBotControls;
import utils.SWTBotUtils;

class SWTBotTest {

	private static SWTWorkbenchBot bot;
	
	@Test
	void test() {
		SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US"; 
		bot = new SWTWorkbenchBot();
		bot.viewByTitle("Welcome").close();
		bot.menu("Form Editors").menu("Simple Form Editor").click();
		
		delay(50000000);
	}

	public void fillFirstPage(SWTBot bot) {
		bot.cTabItem("First Page").activate().setFocus();

		//Need a FormsBot to click hyperlinks
		SWTFormsBot formsBot = new SWTFormsBot();
		formsBot.hyperlink("Sample hyperlink with longer text.").click();
		
		bot.label("Expandable Section with a longer title").click();
		bot.button("Button").click();
		bot.label("Section title").click();
	}
	
	public void fillNewStyleTab(SWTBot bot) {
		bot.cTabItem("New Style").activate().setFocus();
		bot.checkBox("Add title").click();
		System.out.println(bot.label(4).getText());
		bot.radio("Long title").click();
		System.out.println(bot.label(4).getText());
		
		bot.radio("Long message").click();
		System.out.println(bot.clabel().getText());
		
		bot.radio("Short message").click();
		System.out.println(bot.clabel().getText());
	}
	
	public void fillMessageManagerTab(SWTBot bot) {
		//Switch tab
		bot.cTabItem("Message Manager").activate().setFocus();
		//Set text based on field label
		bot.textWithLabel("Field1").setText("Label set text");
		//Simulate typing the set based on the field label
		//Does not seem to work while PC is locked
		bot.textWithLabel("Field2").typeText("Label type text");
		bot.text(2).setText("Index set text");
	}
	
	public void fillFlowPage(SWTBot bot) {
		bot.cTabItem("Flow Page").activate().setFocus();
		bot.label("Control Section").click();
		bot.label("Control Section").click();
		SWTBotControls.clickRadioForLabel("Control Section", 1, bot);
		System.out.println(SWTBotUtils.getSelectedRadioButtonForLabel("Control Section", bot));
		delay(5000000);
	}
	
	public void fillMasterDetails(SWTBot bot) {
		bot.cTabItem("Master Details").activate().setFocus();
		bot.table().getTableItem(0).click();
		
		SWTBotControls.clickRadioForLabel("Type One Details", 1, bot);
		bot.checkBox("Value of the flag property").deselect();
		
		SWTBotUtils.allControls();
		bot.textWithLabel("Text property:").setText("Text property field test");
	}

	
	public void fillCompositePage(SWTBot bot) {
		bot.cTabItem("Composite Page").activate().setFocus();
		bot.textWithLabel("Form with subpages").setText("This is some testing text for a free form text field");
		
		bot.cTabItem("License Agreement").activate().setFocus();
		bot.textWithLabel("Form with subpages").setText("This is some testing text for a free form text field");
		
		bot.cTabItem("Description").activate().setFocus();
		bot.textWithLabel("Form with subpages").setText("This is some testing text for a free form text field");
	}
	
	public void delay(int timeInMillis) {
		try {
			Thread.sleep(timeInMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


