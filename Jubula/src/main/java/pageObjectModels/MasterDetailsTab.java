package pageObjectModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jubula.toolkit.base.AbstractComponents;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;
import org.eclipse.jubula.toolkit.base.components.TextInputComponent;
import org.eclipse.jubula.toolkit.concrete.ConcreteComponents;
import org.eclipse.jubula.toolkit.concrete.components.TableComponent;

import objectMaps.ObjectMaps;
import uiObjectModels.RadioGroup;

public class MasterDetailsTab extends SimpleFormEditorTab{

	private TableComponent modelObjectTable;
	
	private ButtonComponent choice1RadioButton;
	private ButtonComponent choice2RadioButton;
	private ButtonComponent choice3RadioButton;
	private ButtonComponent choice4RadioButton;
	private ButtonComponent valueOfFlagPropertyCheckbox;
	
	private TextInputComponent textPropertyTextInput;
	
	private RadioGroup typeOneDetailsRadioGroup;
	
	public MasterDetailsTab() {
		modelObjectTable = ConcreteComponents.createTableComponent(ObjectMaps.Table_MasterDetails);
		
		choice1RadioButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_TypeOneRadios_Choice1);
		choice2RadioButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_TypeOneRadios_Choice2);
		choice3RadioButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_TypeOneRadios_Choice3);
		choice4RadioButton = AbstractComponents.createButtonComponent(ObjectMaps.Button_TypeOneRadios_Choice4);
		
		valueOfFlagPropertyCheckbox = AbstractComponents.createButtonComponent(ObjectMaps.Button_TextPropertyCheckbox);
		
		textPropertyTextInput = AbstractComponents.createTextInputComponent(ObjectMaps.TextInput_TypeOneTextProperty);
		
		//Create radio list based on each individual radio
		List<ButtonComponent> typeOneRadioList = Arrays.asList(choice1RadioButton, choice2RadioButton, choice3RadioButton, choice4RadioButton);
		typeOneDetailsRadioGroup = new RadioGroup(typeOneRadioList);
	}
	
	public TableComponent getModelObjectTable() {
		return modelObjectTable;
	}

	public ButtonComponent getValueOfFlagPropertyCheckbox() {
		return valueOfFlagPropertyCheckbox;
	}

	public TextInputComponent getTextPropertyTextInput() {
		return textPropertyTextInput;
	}

	public RadioGroup getTypeOneDetailsRadioGroup() {
		return typeOneDetailsRadioGroup;
	}
}
