package library;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * @author Haidar Azmi(552465)
 * @author Imdi Melvana Mauladendra(569252)
 *
 */
public class ExtensionFileFilter extends FileFilter {

	/**
	 * Variable
	 */
	String description;
	String extensions[];

	/**
	 * Konstruktor
	 * 
	 * @param description
	 * @param extension
	 */
	public ExtensionFileFilter(String description, String extension) {
		this(description, new String[] { extension });
	}

	/**
	 * File filtern
	 * 
	 * @param description
	 * @param extensions
	 */
	public ExtensionFileFilter(String description, String extensions[]) {
		if (description == null) {
			this.description = extensions[0];
		} else {
			this.description = description;
		}
		this.extensions = (String[]) extensions.clone();
		toLower(this.extensions);
	}

	/**
	 * 
	 * @param array
	 */
	private void toLower(String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}

	/**
	*
	*/
	public String getDescription() {
		return description;
	}

	/**
	 * File Ueberpruefen
	 */
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}
