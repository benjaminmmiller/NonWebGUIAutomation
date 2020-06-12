package pageObjectModels;

import org.eclipse.jubula.toolkit.base.AbstractComponents;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;

import objectMaps.ObjectMaps;

public class NewStyleTab extends SimpleFormEditorTab {

	private ButtonComponent addTitle;
	private ButtonComponent longTitle;
	private ButtonComponent makeTitleTextSelectable;
	private ButtonComponent errorButton;
	private ButtonComponent infoButton;
	private ButtonComponent cancelButton;
	private ButtonComponent startProgressButton;
	private ButtonComponent warningButton;

	@SuppressWarnings("unchecked")
	public NewStyleTab() {
		addTitle = AbstractComponents.createButtonComponent(ObjectMaps.AddTitle);
		longTitle = AbstractComponents.createButtonComponent(ObjectMaps.Button_LongTitle);
		makeTitleTextSelectable = AbstractComponents.createButtonComponent(ObjectMaps.Button_MakeTitleTextSelectable);
		errorButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_Error);
		infoButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_Info);
		cancelButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_Cancel);
		startProgressButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_StartProgress);
		warningButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_Warning);
	}
	
	
	public ButtonComponent getAddTitle() {
		return addTitle;
	}

	public ButtonComponent getLongTitle() {
		return longTitle;
	}

	public ButtonComponent getMakeTitleTextSelectable() {
		return makeTitleTextSelectable;
	}
	
	public ButtonComponent getErrorButton() {
		return errorButton;
	}

	public ButtonComponent getInfoButton() {
		return infoButton;
	}

	public ButtonComponent getCancelButton() {
		return cancelButton;
	}

	public ButtonComponent getStartProgressButton() {
		return startProgressButton;
	}
	
	public ButtonComponent getWarningButton() {
		return warningButton;
	}
	
}
