package customCAP;

import org.eclipse.jubula.communication.CAP;
import org.eclipse.jubula.toolkit.CapBuilder;
import org.eclipse.jubula.toolkit.ToolkitInfo;
import org.eclipse.jubula.tools.ComponentIdentifier;

public class EclipseTabComponent{
	public static final String COMPONENT_CLASS_NAME = "org.eclipse.swt.custom.CTabFolder";
	public static final String TESTER_CLASS_NAME = "customCAP.EclipseTabTester";

	private ToolkitInfo toolkitInfo = null;
	
	public EclipseTabComponent(ToolkitInfo toolkit) {
		setToolkitInfo(toolkit);

        String registerTesterClass = getToolkitInfo().registerTesterClass(
                COMPONENT_CLASS_NAME, TESTER_CLASS_NAME);
        
        String extensionClassName = this.getClass().getName();
        if (registerTesterClass != null) {
            if (TESTER_CLASS_NAME.equals(registerTesterClass)) {
                System.out.println(extensionClassName
                        + " extension has already been registered."); //$NON-NLS-1$
            } else {
                System.out.println(extensionClassName
                        + " extension has been registered and replaced the previous extension: " //$NON-NLS-1$
                        + registerTesterClass);
            }
        } else {
            System.out.println(extensionClassName + " extension has been registered."); //$NON-NLS-1$
        }
	}
	
	public ToolkitInfo getToolkitInfo() {
		return this.toolkitInfo;
	}

	public void setToolkitInfo(ToolkitInfo toolkit) {
		this.toolkitInfo = toolkit;
	}
	
	
	public CAP customCAP(ComponentIdentifier<?> ci, int index){
	 	//rcCustomTest
	    return new CapBuilder("rcCustomTest") //$NON-NLS-1$
	        .setDefaultMapping(false)
	        .setComponentIdentifier(ci)
	        .addParameter(index)
	        .build();
	 }
	
}
