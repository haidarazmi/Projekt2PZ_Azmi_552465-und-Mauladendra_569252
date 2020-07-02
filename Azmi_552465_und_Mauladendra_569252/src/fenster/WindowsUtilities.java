package fenster;

/**
 *  @author Haidar Azmi(552465)
 *	@author Imdi Melvana Mauladendra(569252)
 * 
 */
import javax.swing.*;
import java.awt.*;

/**
 * Einige Dienstprogramme, die die Verwendung von Fenstern in Swing
 * vereinfachen.
 * 
 */

public class WindowsUtilities {

	/**
	 * setNativeLookAndFeel
	 */

	public static void setNativeLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * setJavaLookAndFeel
	 */
	public static void setJavaLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting Java LAF: " + e);
		}
	}

	/**
	 * setMotifLookAndFeel
	 */
	public static void setMotifLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			System.out.println("Error setting Motif LAF: " + e);
		}
	}

	/**
	 * Eine vereinfachte Möglichkeit, ein JPanel oder einen anderen Container
	 * anzuzeigen. Öffnet einen JFrame mit dem angegebenen Container als
	 * Inhaltsbereich.
	 * 
	 */

	public static JFrame openInJFrame(Container content, int width, int height, String title, Color bgColor) {
		JFrame frame = new JFrame(title);
		frame.setBackground(bgColor);
		content.setBackground(bgColor);
		frame.setSize(width, height);
		frame.setContentPane(content);
		frame.addWindowListener(new ExitListener());
		frame.setVisible(true);
		return (frame);
	}

	/** Hintergrundfarbe. */

	public static JFrame openInJFrame(Container content, int width, int height, String title) {
		return (openInJFrame(content, width, height, title, Color.white));
	}

	/**
	 * Verwendet Color.white als Hintergrundfarbe und den Namen der Container-Klasse
	 * als JFrame-Titel.
	 */

	public static JFrame openInJFrame(Container content, int width, int height) {
		return (openInJFrame(content, width, height, content.getClass().getName(), Color.white));
	}
}