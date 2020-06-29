package jubulaConfiguration;

import java.util.Locale;

import org.eclipse.jubula.autagent.Embedded;
import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.client.AUTAgent;
import org.eclipse.jubula.client.launch.AUTConfiguration;
import org.eclipse.jubula.toolkit.ToolkitInfo;
import org.eclipse.jubula.toolkit.rcp.config.RCPAUTConfiguration;
import org.eclipse.jubula.toolkit.swt.SwtComponents;
import org.eclipse.jubula.tools.AUTIdentifier;

public class JubulaConfiguration {
	
	
	public static AUTAgent setupAutAgent(AUTConfiguration autConfiguration) {
		AUTAgent agent = Embedded.INSTANCE.agent();
		agent.connect();
		agent.startAUT(autConfiguration);
		return agent;
	}
	
	public static AUTAgent setupAutAgent(AUTConfiguration autConfiguration, int portNum) {
		AUTAgent agent = Embedded.INSTANCE.agent(portNum);
		agent.connect();
		agent.startAUT(autConfiguration);
		return agent;
	}
	
	public static AUT setupAut(AUTAgent agent, ToolkitInfo toolkitInfo) {
		AUT aut = null;
		AUTIdentifier id = agent.getAllRegisteredAUTIdentifier().get(0);
		if(id!=null) {
			aut = agent.getAUT(id, toolkitInfo);
			aut.connect();
		}
		else {
			System.out.println("Error, no returned AUT ID. Returning null object");
		}
		return aut;
	}
	
	public static AUTConfiguration eclipseConfiguration() {
		final String autID = "EclipseIDE";
		AUTConfiguration config = new RCPAUTConfiguration(
				"Eclipse",
				autID,
				"eclipse.exe",
				"D:\\WorkFromHome\\JubulaTest\\eclipse",
				null,
				Locale.US);
		return config;
	}
	
	public static void eclipseJubulaSetup(AUT aut, AUTAgent agent) {
		
		
		
	}
	
	
	public static void eclipseJubulaTeardown(AUT aut, AUTAgent agent) {
		aut.disconnect();
		agent.stopAUT(aut.getIdentifier());
		agent.disconnect();
		Embedded.INSTANCE.shutdown();
	}

}
