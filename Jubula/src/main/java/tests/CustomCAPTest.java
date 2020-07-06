package tests;

import java.util.Locale;

import org.eclipse.jubula.autagent.Embedded;
import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.client.AUTAgent;
import org.eclipse.jubula.client.launch.AUTConfiguration;
import org.eclipse.jubula.toolkit.ToolkitInfo;
import org.eclipse.jubula.toolkit.enums.ValueSets.InteractionMode;
import org.eclipse.jubula.toolkit.rcp.config.RCPAUTConfiguration;
import org.eclipse.jubula.toolkit.swt.SwtComponents;
import org.eclipse.jubula.toolkit.swt.SwtToolkit;
import org.eclipse.jubula.tools.AUTIdentifier;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import controls.AppControls;
import controls.StandardControls;
import customCAP.EclipseTabComponent;
import customCAP.EclipseTabTester;
import jubulaConfiguration.JubulaConfiguration;
import objectMaps.ObjectMaps;
import pageObjectModels.NewStyleTab;

public class CustomCAPTest {

	private static AUTAgent agent;
	protected static AUT aut;
	private static EclipseTabComponent eclipseTabComponent;
	
	@BeforeTest
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
			ToolkitInfo toolkitInfo = SwtToolkit
                    .createToolkitInformation();
			
			eclipseTabComponent = new EclipseTabComponent(toolkitInfo);
			aut = agent.getAUT(id, eclipseTabComponent.getToolkitInfo());
			aut.connect();
			
		}
		else {
			System.out.println("Error, no returned AUT ID");
		}
		
		
	}
	
	
	@Test(enabled = true)
	public static void customCAPTest() {
		AppControls.navigateToMenuItem("Form Editors/Simple Form Editor", aut);
		NewStyleTab newStyleTab = new NewStyleTab();
		
		StandardControls.clickButton(newStyleTab.getAddTitle(), aut);
		
		aut.execute(eclipseTabComponent.customCAP(ObjectMaps.Tab_TopTabItems, 1), null);
	}
	
	
	@AfterTest
	public static void afterTest() {
		jubulaConfiguration.JubulaConfiguration.eclipseJubulaTeardown(aut, agent);
	}
	
	
}
