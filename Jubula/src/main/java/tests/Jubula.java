package tests;

import java.util.Locale;

import org.eclipse.jubula.autagent.Embedded;
import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.client.AUTAgent;
import org.eclipse.jubula.client.MakeR;
import org.eclipse.jubula.client.launch.AUTConfiguration;
import org.eclipse.jubula.rc.common.components.AUTHierarchy;
import org.eclipse.jubula.rc.common.exception.ComponentNotManagedException;
import org.eclipse.jubula.rc.swt.components.SwtAUTHierarchy;
import org.eclipse.jubula.toolkit.base.components.ButtonComponent;
import org.eclipse.jubula.toolkit.rcp.config.RCPAUTConfiguration;
import org.eclipse.jubula.toolkit.swt.SwtComponents;
import org.eclipse.jubula.tools.AUTIdentifier;
import org.eclipse.jubula.tools.internal.exception.InvalidDataException;
import org.eclipse.jubula.tools.internal.objects.ComponentIdentifier;
import org.eclipse.jubula.tools.internal.objects.IComponentIdentifier;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import controls.AppControls;
import controls.StandardControls;
import customCAP.EclipseTabTester;
import objectMaps.ObjectMaps;
import pageObjectModels.MasterDetailsTab;
import pageObjectModels.MessageManagerTab;
import pageObjectModels.NewStyleTab;


public class Jubula {

	private static AUTAgent agent;
	protected static AUT aut;
	@BeforeClass
	public static void setupTest() {
		agent = Embedded.INSTANCE.agent();
		agent.connect();
		final String autID = "EclipseIDE";
		AUTConfiguration config = new RCPAUTConfiguration(
				"Eclipse",
				autID,
				"eclipse.exe",
				"D:\\WorkFromHome\\JubulaTest\\eclipse",
				null,
				Locale.US);
		AUTIdentifier id = agent.startAUT(config);
		if(id!=null) {
			aut = agent.getAUT(id, SwtComponents.getToolkitInformation());
			aut.connect();
		}
		else {
			System.out.println("Error, no returned AUT ID");
		}
	}
	
	@Test (enabled = true)
	public static void jubulaAPITest(){
		AppControls.navigateToMenuItem("Form Editors/Simple Form Editor", aut);
		NewStyleTab newStyleTab = new NewStyleTab();
		
		//Click checkboxes/radios
		StandardControls.setButton(newStyleTab.getAddTitle(), true, aut);
		StandardControls.setButton(newStyleTab.getLongTitle(), true, aut);
		StandardControls.setButton(newStyleTab.getMakeTitleTextSelectable(), true, aut);
		StandardControls.setButton(newStyleTab.getAddImage(), true, aut);
		StandardControls.setButton(newStyleTab.getAddToolBar(), true, aut);
		
		//Click Buttons
		StandardControls.clickButton(newStyleTab.getErrorButton(), aut);
		StandardControls.clickButton(newStyleTab.getWarningButton(), aut);
		StandardControls.clickButton(newStyleTab.getInfoButton(), aut);
		StandardControls.clickButton(newStyleTab.getCancelButton(), aut);
		StandardControls.clickButton(newStyleTab.getStartProgressButton(), aut);
		
		//Switch to message manager tab
		MessageManagerTab messageManagerTab = newStyleTab.switchToMessageManagerTab(aut);
		
		//Fill text fields
		StandardControls.fillTextField(messageManagerTab.getField1(), "Field 1 testing text", aut);
		StandardControls.fillTextField(messageManagerTab.getField2(), "Field 2 testing text", aut);
		StandardControls.fillTextField(messageManagerTab.getField3(), "Field 3 testing text", aut);
		
		//Check checkboxes
		StandardControls.setButton(messageManagerTab.getAddGeneralError(), true, aut);
		StandardControls.setButton(messageManagerTab.getAddStaticMessage(), true, aut);
		StandardControls.setButton(messageManagerTab.getAutoUpdate(), false, aut);
		
		//Switch to master details tab
		MasterDetailsTab masterDetailsTab = messageManagerTab.switchToMasterDetailsTab(aut);
		
		//Select cell in table
		StandardControls.selectCellInTable(2, 1, masterDetailsTab.getModelObjectTable(), aut);
		//Select radio
		StandardControls.selectRadioBasedOnIndex(masterDetailsTab.getTypeOneDetailsRadioGroup(), 3, aut);
		//Fill text field
		StandardControls.fillTextField(masterDetailsTab.getTextPropertyTextInput(), "Text property testing text", aut);
	}
	
	@Test (enabled = false)
	public static void jubulaCustomUITest() {
		
		//https://github.com/eclipse/jubula.core/blob/507ab5acef07518d163118c5b7a450b01498767c/org.eclipse.jubula.rc.common/src/org/eclipse/jubula/rc/common/tester/AbstractUITester.java#L27
		AppControls.navigateToMenuItem("Form Editors/Simple Form Editor", aut);
		NewStyleTab newStyleTab = new NewStyleTab();
		
		System.out.println(EclipseTabTester.class.getCanonicalName());
		
		
	}
	
	
	
	@AfterClass
	public static void afterTest() {
		aut.disconnect();
		agent.stopAUT(aut.getIdentifier());
		agent.disconnect();
		Embedded.INSTANCE.shutdown();
	}
	
}
