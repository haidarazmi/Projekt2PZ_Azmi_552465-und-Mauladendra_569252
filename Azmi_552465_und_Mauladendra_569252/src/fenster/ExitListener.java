package fenster;

import java.awt.event.*;

/**
 * @author Haidar Azmi(552465)
 * @author Imdi Melvana Mauladendra(569252)
 *
 */
public class ExitListener extends WindowAdapter {

	/**
	 * Fenster schliessen
	 */
	public void windowClosing(WindowEvent event) {
		System.exit(0);
	}
}