package pageObjectModels;

import org.eclipse.jubula.toolkit.base.AbstractComponents;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;
import org.eclipse.jubula.toolkit.base.components.TextComponent;
import org.eclipse.jubula.toolkit.base.components.TextInputComponent;

import objectMaps.ObjectMaps;

public class MessageManagerTab extends SimpleFormEditorTab{

	private TextInputComponent field1;
	private TextInputComponent field2;
	private TextInputComponent field3;
	
	private ButtonComponent addGeneralError;
	private ButtonComponent addStaticMessage;
	private ButtonComponent autoUpdate;
	
	@SuppressWarnings("unchecked")
	public MessageManagerTab() {
		field1 = AbstractComponents.createTextInputComponent(ObjectMaps.TextInput_Field1);
		field2 = AbstractComponents.createTextInputComponent(ObjectMaps.TextInput_Field2);
		field3 = AbstractComponents.createTextInputComponent(ObjectMaps.TextInput_Field3);
		
		addGeneralError = AbstractComponents.createButtonComponent(ObjectMaps.Button_AddGeneralError);
		addStaticMessage = AbstractComponents.createButtonComponent(ObjectMaps.Button_AddStaticMessage);
		autoUpdate = AbstractComponents.createButtonComponent(ObjectMaps.Button_AutoUpdate);
	}
	
	public TextInputComponent getField1() {
		return field1;
	}

	public TextInputComponent getField2() {
		return field2;
	}

	public TextInputComponent getField3() {
		return field3;
	}

	public ButtonComponent getAddGeneralError() {
		return addGeneralError;
	}

	public ButtonComponent getAddStaticMessage() {
		return addStaticMessage;
	}

	public ButtonComponent getAutoUpdate() {
		return autoUpdate;
	}
	
	
}
