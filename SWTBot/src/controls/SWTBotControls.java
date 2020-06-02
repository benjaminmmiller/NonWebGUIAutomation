package controls;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotRadio;

import utils.SWTBotUtils;

public class SWTBotControls {
	
	public static SWTBotRadio clickRadioForLabel(String label, int radioIndex, SWTBot bot) {
		return SWTBotUtils.getRadioButtonsForLabel(label, bot).get(radioIndex).click();
	}
	
	// http://habibbabbles.blogspot.com/2013/08/maximize-eclipse-active-window-from.html
	public static void maximizeActiveWindow(SWTBot bot) {
        final Shell activeShell = bot.activeShell().widget;
        VoidResult maximizeShell = new VoidResult() {
            @Override
            public void run() {
                    activeShell.setMaximized(true);
            }
        };
        syncExec(maximizeShell);
    }
	
	
}
