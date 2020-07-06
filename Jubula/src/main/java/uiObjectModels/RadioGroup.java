package uiObjectModels;

import java.util.List;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;
import org.eclipse.jubula.toolkit.enums.ValueSets.InteractionMode;

import controls.StandardControls;
import utils.JubulaUtils;

public class RadioGroup {

	private List<ButtonComponent> radios;
	
	public List<ButtonComponent> getRadios() {
		return radios;
	}

	public RadioGroup(List<ButtonComponent> radios) {
		this.radios=radios;
	}
}
