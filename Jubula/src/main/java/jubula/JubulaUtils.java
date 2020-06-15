package jubula;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;

public class JubulaUtils {

	
	public static boolean getButtonSelection(ButtonComponent button, AUT aut) {
		return Boolean.valueOf(aut.execute(button.isSelected(), null).getReturnValue());
	}
}
