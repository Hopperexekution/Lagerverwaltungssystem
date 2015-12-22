package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.tree.DefaultTreeModel;

import controller.Controller;
import model.Lager;
import model.LagerModel;
import model.Lieferung;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Hauptmenue extends JFrame {
	JTree lagerTree;
	Controller controller;

	public Hauptmenue( Controller controller) {
		setResizable(false);
		setBounds(0,0,800,600);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.controller = controller;
		
		JMenuBar menueLeiste = new JMenuBar();
		menueLeiste.setBounds(0, 0, 794, 24);
		getContentPane().add(menueLeiste);
		
		JMenu menueListe = new JMenu("Men\u00FC");
		menueLeiste.add(menueListe);
		
		JMenuItem menueLaden = new JMenuItem("Laden");
		menueLaden.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				controller.laden();
			}
		});
		menueListe.add(menueLaden);
		
		JMenuItem menueSpeichern = new JMenuItem("Speichern");
		menueSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				controller.speichern();
			}
		});
		menueListe.add(menueSpeichern);
		
		JMenuItem menueBeenden = new JMenuItem("Programm beenden");
		menueBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				controller.programmBeenden();
			}
		});
		menueListe.add(menueBeenden);
		
		JTabbedPane tabLeiste = new JTabbedPane();
		tabLeiste.setBounds(0, 23, 794, 548);
		getContentPane().add(tabLeiste,null);
		
		JSplitPane lageruebersichtTab = new JSplitPane();
		lageruebersichtTab.setDividerLocation(0.5);
		tabLeiste.addTab("Lager\u00FCbersicht", null, lageruebersichtTab, null);
		
		JPanel lagerPane = new JPanel();
		lageruebersichtTab.setRightComponent(lagerPane);
		lagerPane.setLayout(null);
		
		JLabel lagerOptionenUeberschrift = new JLabel("Lager Optionen");
		lagerOptionenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerOptionenUeberschrift.setBounds(220, 11, 107, 23);
		lagerOptionenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lagerPane.add(lagerOptionenUeberschrift);
		
		JTree lagerTree = new JTree();
		lagerTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
			}
		});
		lagerTree.setModel(controller.getLagerModel());
		this.lagerTree = lagerTree;
		lageruebersichtTab.setLeftComponent(lagerTree);
		lageruebersichtTab.setDividerLocation(250);
		
		JButton neuesLagerErstellenButton = new JButton("Neues Lager erstellen");
		neuesLagerErstellenButton.setBounds(20, 48, 170, 23);
		neuesLagerErstellenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					Lager ausgewaehltesLager = (Lager) lagerTree.getLastSelectedPathComponent();
					LagerHinzufuegenView lagerHinzufuegenView = new LagerHinzufuegenView(controller,ausgewaehltesLager);
					lagerHinzufuegenView.setAlwaysOnTop(true);
				}
				
			}
		});
		lagerPane.add(neuesLagerErstellenButton);
		
		JButton lagerLoeschenButton = new JButton("Lager l\u00F6schen");
		lagerLoeschenButton.setBounds(200, 48, 161, 23);
		lagerLoeschenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					controller.loeschen((Lager)(lagerTree.getLastSelectedPathComponent()));
					refreshTree();
				}
			}
		});
		lagerPane.add(lagerLoeschenButton);
		
		
		JSeparator lagerUebersichtSeperator = new JSeparator();
		lagerUebersichtSeperator.setBounds(0, 82, 533, 2);
		lagerPane.add(lagerUebersichtSeperator);
		
		JLabel buchungenUeberschrift = new JLabel("Buchungen");
		buchungenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		buchungenUeberschrift.setBounds(220, 90, 107, 23);
		lagerPane.add(buchungenUeberschrift);
		
		JScrollBar buchungsListeScrollBar = new JScrollBar();
		buchungsListeScrollBar.setBounds(512, 119, 17, 222);
		lagerPane.add(buchungsListeScrollBar);
		
		JList buchungsListe = new JList();
		buchungsListe.setBounds(20, 124, 492, 222);
		buchungsListe.setModel(new AbstractListModel() {
			String[] values = new String[] {"Niedersachsen; 100; 10.12.2015"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lagerPane.add(buchungsListe);
		
		JLabel lagerDetailsUeberschrift = new JLabel("Lager Details");
		lagerDetailsUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerDetailsUeberschrift.setBounds(220, 352, 107, 23);
		lagerPane.add(lagerDetailsUeberschrift);
		
		JLabel lagerKapazitaetUeberschrift = new JLabel("Kapazit\u00E4t");
		lagerKapazitaetUeberschrift.setBounds(20, 425, 79, 14);
		lagerPane.add(lagerKapazitaetUeberschrift);
		
		JLabel lagerKapazitaet = new JLabel("100");
		lagerKapazitaet.setHorizontalAlignment(SwingConstants.CENTER);
		lagerKapazitaet.setBounds(163, 425, 360, 14);
		lagerPane.add(lagerKapazitaet);
		
		JLabel lagerNameUeberschrift = new JLabel("Name");
		lagerNameUeberschrift.setBounds(24, 389, 75, 14);
		lagerPane.add(lagerNameUeberschrift);
		
		JLabel lagerName = new JLabel("Niedersachsen");
		lagerName.setHorizontalAlignment(SwingConstants.CENTER);
		lagerName.setBounds(163, 389, 360, 14);
		lagerPane.add(lagerName);
		
		JLabel lagerBestandUeberschrift = new JLabel("Bestand");
		lagerBestandUeberschrift.setBounds(20, 464, 79, 14);
		lagerPane.add(lagerBestandUeberschrift);
		
		JLabel lagerBestand = new JLabel("50");
		lagerBestand.setHorizontalAlignment(SwingConstants.CENTER);
		lagerBestand.setBounds(163, 464, 360, 14);
		lagerPane.add(lagerBestand);
		
		JSplitPane lieferungsuebersichtTab = new JSplitPane();
		tabLeiste.addTab("Lieferungs\u00FCbersicht", null, lieferungsuebersichtTab, null);
		
		JList<Lieferung> lieferungsListe = new JList<Lieferung>();
		DefaultListModel<Lieferung> lieferungModel = new DefaultListModel<Lieferung>();
		lieferungsListe.setModel(lieferungModel);
		lieferungsuebersichtTab.setLeftComponent(lieferungsListe);
		lieferungsuebersichtTab.setDividerLocation(250);

		
		JPanel lieferungPane = new JPanel();
		lieferungsuebersichtTab.setRightComponent(lieferungPane);
		lieferungPane.setLayout(null);
		
		JLabel lieferungsoptionenUeberschrift = new JLabel("Lieferungsoptionen");
		lieferungsoptionenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lieferungsoptionenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lieferungsoptionenUeberschrift.setBounds(220, 11, 123, 14);
		lieferungPane.add(lieferungsoptionenUeberschrift);
		
		JTextField gesamtmengeEingabe = new JTextField();
		gesamtmengeEingabe.setBounds(257, 89, 97, 20);
		lieferungPane.add(gesamtmengeEingabe);
		
		JButton neueZulieferungButton = new JButton("Neue Zulieferung");
		neueZulieferungButton.setBounds(20, 48, 170, 23);
		neueZulieferungButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				int gesamtMenge = Integer.parseInt(gesamtmengeEingabe.getText());
				if(gesamtMenge <= 0){
					JOptionPane.showMessageDialog(null, "Bitte nur positivie Zahlen verwenden und nicht Null verwenden.");
				}else if(gesamtMenge > 2000000000){
					JOptionPane.showMessageDialog(null, "Bitte verwenden sie nur ganzzahlige Zahlen bis zu einem Maximum von zwei Milliarden.");
				}
				else{
				ZulieferungsView zulieferung = new ZulieferungsView(controller, gesamtMenge);
				}
				}catch(NumberFormatException f){
					JOptionPane.showMessageDialog(null, "Bitte verwenden sie nur ganzzahlige Zahlen bis zu einem Maximum von zwei Milliarden.");
				}
				
			}
		});
		lieferungPane.add(neueZulieferungButton);
		
		JButton neueAuslieferungButton = new JButton("Neue Auslieferung");
		neueAuslieferungButton.setBounds(340, 48, 170, 23);
		neueAuslieferungButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lieferungPane.add(neueAuslieferungButton);
		
		JSeparator lieferungsUebersichtSeperator = new JSeparator();
		lieferungsUebersichtSeperator.setBounds(0, 120, 533, 2);
		lieferungPane.add(lieferungsUebersichtSeperator);
		
		JLabel lieferbezeichnungUeberschrift = new JLabel("Lieferbezeichnung");
		lieferbezeichnungUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lieferbezeichnungUeberschrift.setBounds(24, 133, 121, 14);
		lieferungPane.add(lieferbezeichnungUeberschrift);
		
		JLabel lieferBezeichung = new JLabel("000");
		lieferBezeichung.setHorizontalAlignment(SwingConstants.CENTER);
		lieferBezeichung.setBounds(24, 157, 121, 14);
		lieferungPane.add(lieferBezeichung);
		
		JLabel lieferdatumUeberschrift = new JLabel("Lieferdatum");
		lieferdatumUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lieferdatumUeberschrift.setBounds(211, 133, 101, 14);
		lieferungPane.add(lieferdatumUeberschrift);
		
		JLabel lieferDatum = new JLabel("10.12.2012");
		lieferDatum.setHorizontalAlignment(SwingConstants.CENTER);
		lieferDatum.setBounds(211, 157, 101, 14);
		lieferungPane.add(lieferDatum);
		
		
		JLabel gesamtmenge = new JLabel("Gesamtmenge:");
		gesamtmenge.setBounds(183, 89, 72, 20);
		lieferungPane.add(gesamtmenge);
		
		/*JList<Lieferung> lieferungsListe = new JList<Lieferung>();
		lieferungsListe.setBounds(24, 198, 509, 215);
		lieferungsListe.setModel(new AbstractListModel() {
			String[] values = new String[] {"Lager1 100", "Lager2 50"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		lieferungPane.add(lieferungsListe);
	}*/
	/*private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}*/
}
	public void refreshTree(){
		lagerTree.setModel(null);
		lagerTree.setModel(controller.getLagerModel());
	}
}
