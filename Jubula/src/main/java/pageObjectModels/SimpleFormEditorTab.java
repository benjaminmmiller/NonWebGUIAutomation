package pageObjectModels;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.toolkit.base.AbstractComponents;
import org.eclipse.jubula.toolkit.concrete.ConcreteComponents;
import org.eclipse.jubula.toolkit.concrete.components.TabComponent;
import org.eclipse.jubula.toolkit.enums.ValueSets.InteractionMode;

import objectMaps.ObjectMaps;

public class SimpleFormEditorTab extends BaseRCPPage{
	
	private TabComponent simpleFormTabs;
	
	@SuppressWarnings("unchecked")
	public SimpleFormEditorTab() {
		simpleFormTabs = ConcreteComponents.createTabComponent(ObjectMaps.Tab_SimpleFormEditor);
	}
	
	
	public TabComponent getSimpleFormTabs() {
		return simpleFormTabs;
	}
	
	public NewStyleTab switchToNewStyleTab(AUT aut) {
		aut.execute(simpleFormTabs.selectTabByIndex(1),null);
		return new NewStyleTab();
	}
	
	public MessageManagerTab switchToMessageManagerTab(AUT aut) {
		aut.execute(simpleFormTabs.selectTabByIndex(2),null);
		return new MessageManagerTab();
	}
	
	public MasterDetailsTab switchToMasterDetailsTab(AUT aut) {
		aut.execute(simpleFormTabs.selectTabByIndex(7), null);
		return new MasterDetailsTab();
	}
}
