package controls;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.communication.CAP;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;
import org.eclipse.jubula.toolkit.base.components.GraphicsComponent;
import org.eclipse.jubula.toolkit.base.components.TextInputComponent;
import org.eclipse.jubula.toolkit.concrete.components.TableComponent;
import org.eclipse.jubula.toolkit.enums.ValueSets;
import org.eclipse.jubula.toolkit.enums.ValueSets.InteractionMode;

import uiObjectModels.RadioGroup;
import utils.JubulaUtils;



public class StandardControls {

	public static void clickButton(ButtonComponent button, AUT aut) {
		 aut.execute(button.click(1, InteractionMode.valueOf(1)),null);
	}
	
	public static CAP leftClick(GraphicsComponent graphicsComponent, AUT aut) {
		return graphicsComponent.click(1, InteractionMode.valueOf(1));
	}
	
	public static CAP rightClick(GraphicsComponent graphicsComponent, AUT aut) {
		return graphicsComponent.click(1, InteractionMode.valueOf(2));
	}
	
	public static void fillTextField(TextInputComponent textField, String textToInput, AUT aut) {
		aut.execute(textField.replaceText(textToInput), null);		
	}
	
	public static void setButton(ButtonComponent checkbox, boolean wantSelected, AUT aut) {
		boolean isSelected = JubulaUtils.getButtonSelection(checkbox, aut);
		if(wantSelected) {
			if(!isSelected) {
				aut.execute(checkbox.click(1, InteractionMode.valueOf(1)),null);
			}
		}
		else {
			if(isSelected) {
				aut.execute(checkbox.click(1, InteractionMode.valueOf(1)),null);
			}
		}
	}
	
	public static ButtonComponent getSelectedRadio(RadioGroup radioGroup, AUT aut) {
		for(ButtonComponent radio:radioGroup.getRadios()) {
			if(JubulaUtils.getButtonSelection(radio, aut)) {
				return radio;
			}
		}
		System.out.println("No button has been selected, returning first radio in group");
		return radioGroup.getRadios().get(0);
	}
	
	public static int getSelectedRadioIndex(RadioGroup radioGroup, AUT aut) {
		for(int i=0;i<radioGroup.getRadios().size();i++) {
			if(JubulaUtils.getButtonSelection(radioGroup.getRadios().get(i), aut)) {
				return i;
			}
		}
		System.out.println("No button has been selected, returning 0");
		return 0;
	}
	
	
	public static void selectRadioBasedOnIndex(RadioGroup radioGroup, int index, AUT aut) {
		aut.execute(StandardControls.leftClick(radioGroup.getRadios().get(index), aut), null);
	}
	
	public static void selectCellInTable(int row, int column, TableComponent table, AUT aut) {
		aut.execute(table.selectCell(String.valueOf(row), ValueSets.Operator.equals, String.valueOf(column), ValueSets.Operator.equals, 1, 50, ValueSets.Unit.percent, 50, ValueSets.Unit.percent, ValueSets.BinaryChoice.no, InteractionMode.valueOf(1)), null); 
	}
}
