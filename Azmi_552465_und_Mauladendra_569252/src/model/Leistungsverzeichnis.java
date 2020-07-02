package model;

/**
 * @author Haidar Azmi(552465)
 * @author Imdi Melvana Mauladendra(569252)
 *
 * 
 */
public class Leistungsverzeichnis {

	/**
	 * Variable
	 */
	String ordinalZahl;
	String menge;
	String einheit;
	String kurzText;

	/**
	 * Konstruktor
	 * 
	 * @param ordinalZahl
	 * @param menge
	 * @param einheit
	 * @param kurzText
	 */
	public Leistungsverzeichnis(String ordinalZahl, String menge, String einheit, String kurzText) {
		this.ordinalZahl = ordinalZahl;
		this.menge = menge;
		this.einheit = einheit;
		this.kurzText = kurzText;
	}

	/**
	 * getOrdinalZahl
	 * 
	 * @return Ordinalzahl
	 */
	public String getOrdinalZahl() {
		return this.ordinalZahl;
	}

	/**
	 * getMenge
	 * 
	 * @return Menge
	 */
	public String getMenge() {
		return this.menge;
	}

	/**
	 * getEinheit
	 * 
	 * @return Einheit
	 */
	public String getEinheit() {
		return this.einheit;
	}

	/**
	 * getKurzText
	 * 
	 * @return Kurztext
	 */
	public String getKurzText() {
		return this.kurzText;
	}
}
