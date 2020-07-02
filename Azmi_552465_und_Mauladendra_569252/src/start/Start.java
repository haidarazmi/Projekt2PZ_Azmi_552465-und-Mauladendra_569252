package start;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import fenster.WindowsUtilities;
import library.ExtensionFileFilter;
import library.readXml;
import model.Leistungsverzeichnis;

/**
 * @author Haidar Azmi(552465)
 * @author Imdi Melvana Mauladendra(569252)
 * 
 */

public class Start extends javax.swing.JFrame {

	// Variable
	ArrayList<Leistungsverzeichnis> lv;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JTextField jTextField1;

	public Start() {

		initComponents();
		this.setLocationRelativeTo(null);
		this.setTitle("X83-Datei Oeffner");
	}

	/**
	 * GUI-Komponenten
	 *
	 */
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel4 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jButton2 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		jLabel4.setText("Open File:");

		jButton2.setText("Browse..");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 485,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton2))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jTable1.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "OZ", "Menge", "Einh.", "Kurztext" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTable1MouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(jTable1);
		if (jTable1.getColumnModel().getColumnCount() > 0) {
			jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
			jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
			jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
			jTable1.getColumnModel().getColumn(3).setPreferredWidth(400);
		}

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
	}

	/**
	 * X83-Datei oeffnen und auswaehlen
	 * 
	 * @param evt
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("OPEN");
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filter1 = new ExtensionFileFilter("x83 only", new String[] { "x83" });
		chooser.setFileFilter(filter1);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				String sourcePath = chooser.getSelectedFile().toString();
				readXml test = new readXml();
				test.setInputFile(sourcePath);

				lv = test.read();
				loadData();

			} catch (IOException ex) {
				Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
			} catch (Exception ex) {
				Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		requestFocusInWindow();
	}

	/**
	 * inhalt lesen
	 */
	public void loadData() {
		while (jTable1.getRowCount() > 0) {
			((DefaultTableModel) jTable1.getModel()).removeRow(0);
		}

		for (int row = 0; row < lv.size(); row++) {
			Object[] values = new Object[4];
			values[0] = lv.get(row).getOrdinalZahl();
			values[1] = lv.get(row).getMenge();
			values[2] = lv.get(row).getEinheit();
			values[3] = lv.get(row).getKurzText();

			((DefaultTableModel) jTable1.getModel()).insertRow(row, values);
		}
	}

	/**
	 * @param evt
	 */
	private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {

	}

	/**
	 * Main
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				WindowsUtilities.setNativeLookAndFeel();
				new Start().setVisible(true);
			}
		});
	}

}
