package library;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Leistungsverzeichnis;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Haidar Azmi(552465)
 * @author Imdi Melvana Mauladendra(569252)
 * 
 */
public class readXml {

	/**
	 * Variable
	 */
	String nummer;
	String inputFile;
	ArrayList<Leistungsverzeichnis> lv;
	int rep = 0;

	/**
	 * setInputFile
	 * 
	 * @param inputFile
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * lies Xml-Elemente
	 * 
	 * @param parent
	 * @param level
	 * @param level_bef
	 */
	public void reqursive(Node parent, int level, int level_bef) {
		int row = 0;
		NodeList nodelist = parent.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				row++;
				if (node.getNodeName().equalsIgnoreCase("BoQCtgy")) {
					nummer = level_bef == 0 ? (row + "") : (level_bef + "." + row);
					Element el = (Element) node;
					String LblTx = el.getElementsByTagName("LblTx").item(0).getTextContent();
					// System.out.print("\n"+prefix+" "+" ".repeat(level) + " "+LblTx.trim());
					rep = level - (row - 1);
					lv.add(new Leistungsverzeichnis(nummer, "", "",
							"  ".repeat(level - (row - 1)) + " " + LblTx.trim()));
				}

				if (node.getNodeName().equalsIgnoreCase("BoQBody")) {
					int sub_row = 0;
					NodeList nl = node.getChildNodes();
					for (int j = 0; j < nl.getLength(); j++) {
						Node n = nl.item(j);
						if (n.getNodeName().equalsIgnoreCase("Itemlist")) {

							NodeList nll = n.getChildNodes();
							for (int k = 0; k < nll.getLength(); k++) {
								Node naa = nll.item(k);
								if (naa.getNodeType() == Node.ELEMENT_NODE) {
									sub_row++;
									Element el = (Element) naa;

									String Qty = "";
									try {
										Qty = el.getElementsByTagName("Qty").item(0).getTextContent();
									} catch (Exception e) {
										// e.printStackTrace();
									}

									String QU = "";
									try {
										QU = el.getElementsByTagName("QU").item(0).getTextContent();
									} catch (Exception e) {
										// e.printStackTrace();
									}

									String TextOutlTxt = el.getElementsByTagName("TextOutlTxt").item(0)
											.getTextContent();
									// System.out.print("\n"+level+" "+" ".repeat(level) + Qty + " | " + QU + " |
									// "+TextOutlTxt.trim());
									lv.add(new Leistungsverzeichnis(nummer + "." + sub_row, Qty, QU,
											"  ".repeat(rep + 3) + TextOutlTxt.trim()));

								}
							}

						}
					}
				}
				level++;
				reqursive(node, level, level - 2);
			}

		}

	}

	/**
	 * lies Xml-Elemente
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Leistungsverzeichnis> read() throws Exception {
		lv = new ArrayList<Leistungsverzeichnis>();
		nummer = "";

		File fXmlFile = new File(inputFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		Node root = doc.getDocumentElement();
		root.normalize();

		// LEVEL 0
		NodeList nl_level_0 = root.getChildNodes();
		for (int l0 = 0; l0 < nl_level_0.getLength(); l0++) {
			if (nl_level_0.item(l0).getNodeType() == Node.ELEMENT_NODE) {

				// LEVEL 1
				NodeList nl_level_1 = nl_level_0.item(l0).getChildNodes();
				for (int l1 = 0; l1 < nl_level_1.getLength(); l1++) {
					if (nl_level_1.item(l1).getNodeType() == Node.ELEMENT_NODE) {
						if (nl_level_1.item(l1).getNodeName().equalsIgnoreCase("BoQ")) {

							// LEVEL 2
							NodeList nl_level_2 = nl_level_1.item(l1).getChildNodes();
							for (int l2 = 0; l2 < nl_level_2.getLength(); l2++) {
								if (nl_level_2.item(l2).getNodeType() == Node.ELEMENT_NODE) {
									if (nl_level_2.item(l2).getNodeName().equalsIgnoreCase("BoQBody")) {

										reqursive(nl_level_2.item(l2), 0, 0);
										break;

									}

								}
							}

						}

					}
				}

			}
		}
		System.out.println("----------------------------");
		for (Leistungsverzeichnis item : lv) {
			System.out.println(item.getOrdinalZahl() + "\t" + item.getMenge() + " " + item.getEinheit() + "\t"
					+ item.getKurzText());
		}

		return lv;
	}

}
