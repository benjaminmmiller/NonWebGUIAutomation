package customCAP;

import org.eclipse.jubula.rc.common.exception.StepExecutionException;
import org.eclipse.jubula.rc.common.tester.TabbedPaneTester;
import org.eclipse.swt.custom.CTabFolder;

public class EclipseTabTester extends TabbedPaneTester{

    protected CTabFolder getGroup() {
        return (CTabFolder) getRealComponent();
    }
	
    public void rcCustomTest(String text, String operator) throws StepExecutionException {
        System.out.println("Custom CAP has been run");
	}
	
}
