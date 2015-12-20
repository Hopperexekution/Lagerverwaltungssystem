package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.tree.DefaultTreeModel;

import controller.Controller;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

public class Hauptmenue extends JFrame {
	public Hauptmenue( Controller controller) {
		setResizable(false);
		setBounds(800,600,800,600);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 870, 46);
		getContentPane().add(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Laden");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmSpeichern = new JMenuItem("Speichern");
		mntmSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		menuBar.add(mntmSpeichern);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Beenden");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 50, 870, 466);
		getContentPane().add(tabbedPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(0.5);
		tabbedPane.addTab("Lager\u00FCbersicht", null, splitPane, null);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLagerOptionen = new JLabel("Lager Optionen");
		lblLagerOptionen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLagerOptionen.setBounds(10, 11, 107, 14);
		panel_1.add(lblLagerOptionen);
		
		JButton btnNeuesLagerErstellen = new JButton("Neues Lager erstellen");
		btnNeuesLagerErstellen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNeuesLagerErstellen.setBounds(10, 36, 143, 23);
		panel_1.add(btnNeuesLagerErstellen);
		
		JButton btnLagerLschen = new JButton("Lager l\u00F6schen");
		btnLagerLschen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnLagerLschen.setBounds(163, 36, 143, 23);
		panel_1.add(btnLagerLschen);
		
		JButton btnLagerVerschieben = new JButton("Lager verschieben");
		btnLagerVerschieben.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnLagerVerschieben.setBounds(316, 36, 143, 23);
		panel_1.add(btnLagerVerschieben);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 82, 757, 2);
		panel_1.add(separator);
		
		JLabel lblLagerDetails = new JLabel("Lager Details");
		lblLagerDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLagerDetails.setBounds(10, 95, 86, 14);
		panel_1.add(lblLagerDetails);
		
		JLabel lblBuchungen = new JLabel("Buchungen");
		lblBuchungen.setBounds(10, 130, 60, 14);
		panel_1.add(lblBuchungen);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(659, 155, 17, 186);
		panel_1.add(scrollBar);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Niedersachsen; 100; 10.12.2015"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 155, 666, 186);
		panel_1.add(list);
		
		JLabel lblLagerDetails_1 = new JLabel("Lager Details");
		lblLagerDetails_1.setBounds(10, 364, 86, 14);
		panel_1.add(lblLagerDetails_1);
		
		JLabel lblKapazitt = new JLabel("Kapazit\u00E4t");
		lblKapazitt.setBounds(259, 389, 60, 14);
		panel_1.add(lblKapazitt);
		
		JLabel label = new JLabel("100");
		label.setBounds(326, 389, 112, 14);
		panel_1.add(label);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(24, 389, 46, 14);
		panel_1.add(lblName);
		
		JLabel lblNiedersachsen = new JLabel("Niedersachsen");
		lblNiedersachsen.setBounds(70, 389, 143, 14);
		panel_1.add(lblNiedersachsen);
		
		JLabel lblBestand = new JLabel("Bestand");
		lblBestand.setBounds(508, 389, 46, 14);
		panel_1.add(lblBestand);
		
		JLabel label_1 = new JLabel("50");
		label_1.setBounds(582, 389, 107, 14);
		panel_1.add(label_1);
		
		JTree tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
			}
		});
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Lager") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Deutschland");
						node_1.add(new DefaultMutableTreeNode("Niedersachsen"));
					//getContentPane().add(node_1);
					node_1 = new DefaultMutableTreeNode("Europa");
						node_1.add(new DefaultMutableTreeNode("Frankreich"));
					//getContentPane().add(node_1);
				}
			}
		));
		splitPane.setLeftComponent(tree);
		
		JSplitPane splitPane_1 = new JSplitPane();
		tabbedPane.addTab("Lieferungs\u00FCbersicht", null, splitPane_1, null);
		
		JTree tree_1 = new JTree();
		tree_1.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			}
		});
		tree_1.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Lieferungen") {
				{
					//getContentPane().add(new DefaultMutableTreeNode("Lieferung1"));
				}
			}
		));
		splitPane_1.setLeftComponent(tree_1);
		
		JPanel panel = new JPanel();
		splitPane_1.setRightComponent(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lieferungsoptionen");
		lblNewLabel.setBounds(10, 11, 103, 14);
		panel.add(lblNewLabel);
		
		JButton btnNeueZulieferung = new JButton("Neue Zulieferung");
		btnNeueZulieferung.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNeueZulieferung.setBounds(10, 36, 123, 23);
		panel.add(btnNeueZulieferung);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 109, 786, 2);
		panel.add(separator_1);
		
		JLabel lblLieferbezeichnung = new JLabel("Lieferbezeichnung");
		lblLieferbezeichnung.setBounds(24, 133, 109, 14);
		panel.add(lblLieferbezeichnung);
		
		JLabel label_2 = new JLabel("000");
		label_2.setBounds(34, 157, 62, 14);
		panel.add(label_2);
		
		JLabel lblLieferdatum = new JLabel("Lieferdatum");
		lblLieferdatum.setBounds(165, 133, 68, 14);
		panel.add(lblLieferdatum);
		
		JLabel label_3 = new JLabel("10.12.2012");
		label_3.setBounds(165, 157, 68, 14);
		panel.add(label_3);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Lager1 100", "Lager2 50"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(24, 198, 730, 215);
		panel.add(list_1);
	}
}
