package controls;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.toolkit.concrete.components.MenuBarComponent;
import org.eclipse.jubula.toolkit.enums.ValueSets.Operator;
import org.eclipse.jubula.toolkit.swt.SwtComponents;

public class AppControls {

	
	
	public static void navigateToMenuItem(String menuPath, AUT aut){
		MenuBarComponent menuBar = SwtComponents.createMenu();
		aut.execute(menuBar.selectMenuEntryByTextpath(menuPath, Operator.equals),null);
	}
}
