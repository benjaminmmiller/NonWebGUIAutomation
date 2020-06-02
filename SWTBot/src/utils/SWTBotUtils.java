package utils;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.asyncExec;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withStyle;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.ChildrenControlFinder;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.hamcrest.Matcher;

public class SWTBotUtils {

	
	public static int getSelectedRadioButtonForLabel(String label, SWTBot bot) {
		List<SWTBotRadio> radioButtons = getRadioButtonsForLabel(label, bot);
		for(int i=0;i<radioButtons.size();i++) {
			if(radioButtons.get(i).isSelected()) {
				return i;
			}
		}
		System.out.println("No radio button has been selected, returning -1");
		return -1;
	}
	
	public static List<SWTBotRadio> getRadioButtonsForLabel(String label, SWTBot bot){
		List<SWTBotRadio> radioButtons = new ArrayList<>();
		syncExec(new VoidResult() {
			public void run() {
				ChildrenControlFinder childControlFinder = new ChildrenControlFinder(bot.label(label).widget.getParent());
				List<Control> childControls = childControlFinder.findControls(allOf(widgetOfType(Button.class), withStyle(SWT.RADIO, "SWT.RADIO")));
				for(Control control:childControls) {
					if(control instanceof Button) {
						radioButtons.add(new SWTBotRadio((Button) control));
					}
				}
			}
		});
		return radioButtons;
	}
	
	//TODO remove this when other solution is working
	public static List<Control> getControlsForLabel(String label, SWTBot bot){
		SWTBotLabel swtBotLabel = bot.label(label);
		final List <Control> controls = new ArrayList<>();
		syncExec(new VoidResult() {
			public void run() {
				Composite labelComposite = getLabelComposite(swtBotLabel);
				getAllChildsForComposite(labelComposite, controls);
			}
		});
		return controls;
	}
	
	
	public static void getAllChildsForComposite(Composite topLevelComposite, List<Control> controls) {
		Control[] compositeChilds = topLevelComposite.getChildren();
		for(Control child:compositeChilds) {
			controls.add(child);
			if(child instanceof Composite) {
				getAllChildsForComposite((Composite) child, controls);
			}
		}
	}
	
	
	public static Composite getLabelComposite(SWTBotLabel label) {
		return label.widget.getParent();
	}
	
	
	public static <T extends Control> List<AbstractSWTBotControl> allControlsOfType(Class<T> type) {
		Matcher<T> matcher = WidgetMatcherFactory.widgetOfType(type);
		List<T> widgets = (List<T>) new SWTBot().widgets(matcher);
		List<AbstractSWTBotControl> swtBotControls = new ArrayList<AbstractSWTBotControl>();
		for(T widget:widgets) {
			AbstractSWTBotControl swtBotControl = new AbstractSWTBotControl(widget);
			System.out.print("Class: "+widget.getClass());
			asyncExec(new VoidResult() {
				public void run() {
					System.out.print(" || Parent: "+widget.getParent());
				}
			});
			System.out.print(" || Text: "+swtBotControl.getText());
			System.out.println(" || ID: "+swtBotControl.getId());
			
			
		}
		return swtBotControls;
	}
	
	
	public void printViews(SWTWorkbenchBot bot) {
		List<SWTBotView> views = bot.views();
		for(SWTBotView view:views) {
			System.out.println(view.getTitle());
		}
	}
	
	public void printEditors(SWTWorkbenchBot bot) {
		List<? extends SWTBotEditor> editors = bot.editors();
		for(SWTBotEditor editor:editors) {
			System.out.println(editor.getTitle());
		}
	}
	
	public void printButtons(SWTWorkbenchBot bot) {
		List<SWTBotToolbarButton> toolbarButtons = bot.activePart().getToolbarButtons();
		for(SWTBotToolbarButton button: toolbarButtons) {
			System.out.println("Button: "+button.getText());
		}
	}
		
	
	public static List<AbstractSWTBotControl> allControls(){
		return allControlsOfType(Control.class);
	}
}
