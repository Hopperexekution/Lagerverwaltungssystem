package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
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
import model.Buchung;
import model.Lager;
import model.LagerModel;
import model.Lieferung;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.AbstractListModel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Hauptmenue extends JFrame {
	private JTree lagerTree;
	private Controller controller;
	private JMenuBar menueLeiste;
	private JMenu menueListe;
	private JMenuItem menueSpeichern, menueLaden, menueBeenden;
	private JTabbedPane tabLeiste;
	private JSplitPane lageruebersichtTab, lieferungsuebersichtTab;
	private JPanel lagerPane, lieferungPane;
	private JLabel lagerOptionenUeberschrift, lagerDetailsUeberschrift, lagerKapazitaetUeberschrift, lagerKapazitaet,
		   lagerNameUeberschrift, lagerName, lagerBestandUeberschrift, lagerBestand, buchungenUeberschrift, 
		   lieferungsoptionenUeberschrift, lieferbezeichnungUeberschrift, lieferBezeichung, lieferdatumUeberschrift, lieferDatum,
		   gesamtmenge, buchungenLieferung;
	private JScrollPane treeScrollBar, lieferungScrollBar, lagerBuchungScrollbBar, lieferungBuchungScrollBar;
	private static Hauptmenue hauptmenue = null;

	public static Hauptmenue getInstance(Controller controller){
			if(hauptmenue == null){
				hauptmenue = new Hauptmenue(controller);
			}
			return hauptmenue;
		}
		   
	public JTree getLagerTree() {
		return lagerTree;
	}
	public void setLagerTree(JTree lagerTree) {
		this.lagerTree = lagerTree;
	}
	public JMenuBar getMenueLeiste() {
		return menueLeiste;
	}
	public void setMenueLeiste(JMenuBar menueLeiste) {
		this.menueLeiste = menueLeiste;
	}
	public JMenu getMenueListe() {
		return menueListe;
	}
	public void setMenueListe(JMenu menueListe) {
		this.menueListe = menueListe;
	}
	public JMenuItem getMenueSpeichern() {
		return menueSpeichern;
	}
	public void setMenueSpeichern(JMenuItem menueSpeichern) {
		this.menueSpeichern = menueSpeichern;
	}
	public JMenuItem getMenueLaden() {
		return menueLaden;
	}
	public void setMenueLaden(JMenuItem menueLaden) {
		this.menueLaden = menueLaden;
	}
	public JMenuItem getMenueBeenden() {
		return menueBeenden;
	}
	public void setMenueBeenden(JMenuItem menueBeenden) {
		this.menueBeenden = menueBeenden;
	}
	public JTabbedPane getTabLeiste() {
		return tabLeiste;
	}
	public void setTabLeiste(JTabbedPane tabLeiste) {
		this.tabLeiste = tabLeiste;
	}
	public JSplitPane getLageruebersichtTab() {
		return lageruebersichtTab;
	}
	public void setLageruebersichtTab(JSplitPane lageruebersichtTab) {
		this.lageruebersichtTab = lageruebersichtTab;
	}
	public JSplitPane getLieferungsuebersichtTab() {
		return lieferungsuebersichtTab;
	}
	public void setLieferungsuebersichtTab(JSplitPane lieferungsuebersichtTab) {
		this.lieferungsuebersichtTab = lieferungsuebersichtTab;
	}
	public JPanel getLagerPane() {
		return lagerPane;
	}
	public void setLagerPane(JPanel lagerPane) {
		this.lagerPane = lagerPane;
	}
	public JPanel getLieferungPane() {
		return lieferungPane;
	}
	public void setLieferungPane(JPanel lieferungPane) {
		this.lieferungPane = lieferungPane;
	}
	public JLabel getLagerOptionenUeberschrift() {
		return lagerOptionenUeberschrift;
	}
	public void setLagerOptionenUeberschrift(JLabel lagerOptionenUeberschrift) {
		this.lagerOptionenUeberschrift = lagerOptionenUeberschrift;
	}
	public JLabel getLagerDetailsUeberschrift() {
		return lagerDetailsUeberschrift;
	}
	public void setLagerDetailsUeberschrift(JLabel lagerDetailsUeberschrift) {
		this.lagerDetailsUeberschrift = lagerDetailsUeberschrift;
	}
	public JLabel getLagerKapazitaetUeberschrift() {
		return lagerKapazitaetUeberschrift;
	}
	public void setLagerKapazitaetUeberschrift(JLabel lagerKapazitaetUeberschrift) {
		this.lagerKapazitaetUeberschrift = lagerKapazitaetUeberschrift;
	}
	public JLabel getLagerKapazitaet() {
		return lagerKapazitaet;
	}
	public void setLagerKapazitaet(JLabel lagerKapazitaet) {
		this.lagerKapazitaet = lagerKapazitaet;
	}
	public JLabel getLagerNameUeberschrift() {
		return lagerNameUeberschrift;
	}
	public void setLagerNameUeberschrift(JLabel lagerNameUeberschrift) {
		this.lagerNameUeberschrift = lagerNameUeberschrift;
	}
	public JLabel getLagerName() {
		return lagerName;
	}
	public void setLagerName(JLabel lagerName) {
		this.lagerName = lagerName;
	}
	public JLabel getLagerBestandUeberschrift() {
		return lagerBestandUeberschrift;
	}
	public void setLagerBestandUeberschrift(JLabel lagerBestandUeberschrift) {
		this.lagerBestandUeberschrift = lagerBestandUeberschrift;
	}
	public JLabel getLagerBestand() {
		return lagerBestand;
	}
	public void setLagerBestand(JLabel lagerBestand) {
		this.lagerBestand = lagerBestand;
	}
	public JLabel getBuchungenUeberschrift() {
		return buchungenUeberschrift;
	}
	public void setBuchungenUeberschrift(JLabel buchungenUeberschrift) {
		this.buchungenUeberschrift = buchungenUeberschrift;
	}
	public JLabel getLieferungsoptionenUeberschrift() {
		return lieferungsoptionenUeberschrift;
	}
	public void setLieferungsoptionenUeberschrift(JLabel lieferungsoptionenUeberschrift) {
		this.lieferungsoptionenUeberschrift = lieferungsoptionenUeberschrift;
	}
	public JLabel getLieferbezeichnungUeberschrift() {
		return lieferbezeichnungUeberschrift;
	}
	public void setLieferbezeichnungUeberschrift(JLabel lieferbezeichnungUeberschrift) {
		this.lieferbezeichnungUeberschrift = lieferbezeichnungUeberschrift;
	}
	public JLabel getLieferBezeichung() {
		return lieferBezeichung;
	}
	public void setLieferBezeichung(JLabel lieferBezeichung) {
		this.lieferBezeichung = lieferBezeichung;
	}
	public JLabel getLieferdatumUeberschrift() {
		return lieferdatumUeberschrift;
	}
	public void setLieferdatumUeberschrift(JLabel lieferdatumUeberschrift) {
		this.lieferdatumUeberschrift = lieferdatumUeberschrift;
	}
	public JLabel getLieferDatum() {
		return lieferDatum;
	}
	public void setLieferDatum(JLabel lieferDatum) {
		this.lieferDatum = lieferDatum;
	}
	public JLabel getGesamtmenge() {
		return gesamtmenge;
	}
	public void setGesamtmenge(JLabel gesamtmenge) {
		this.gesamtmenge = gesamtmenge;
	}
	public JButton getNeuesLagerErstellenButton() {
		return neuesLagerErstellenButton;
	}
	public void setNeuesLagerErstellenButton(JButton neuesLagerErstellenButton) {
		this.neuesLagerErstellenButton = neuesLagerErstellenButton;
	}
	public JButton getLagerLoeschenButton() {
		return lagerLoeschenButton;
	}
	public void setLagerLoeschenButton(JButton lagerLoeschenButton) {
		this.lagerLoeschenButton = lagerLoeschenButton;
	}
	public JButton getLagerUmbenennenButton() {
		return lagerUmbenennenButton;
	}
	public void setLagerUmbenennenButton(JButton lagerUmbenennenButton) {
		this.lagerUmbenennenButton = lagerUmbenennenButton;
	}
	public JButton getNeueZulieferungButton() {
		return neueZulieferungButton;
	}
	public void setNeueZulieferungButton(JButton neueZulieferungButton) {
		this.neueZulieferungButton = neueZulieferungButton;
	}
	public JButton getNeueAuslieferungButton() {
		return neueAuslieferungButton;
	}
	public void setNeueAuslieferungButton(JButton neueAuslieferungButton) {
		this.neueAuslieferungButton = neueAuslieferungButton;
	}
	public JSeparator getLagerUebersichtSeperator() {
		return lagerUebersichtSeperator;
	}
	public void setLagerUebersichtSeperator(JSeparator lagerUebersichtSeperator) {
		this.lagerUebersichtSeperator = lagerUebersichtSeperator;
	}
	public JSeparator getLieferungsUebersichtSeperator() {
		return lieferungsUebersichtSeperator;
	}
	public void setLieferungsUebersichtSeperator(JSeparator lieferungsUebersichtSeperator) {
		this.lieferungsUebersichtSeperator = lieferungsUebersichtSeperator;
	}
	public JList getBuchungsListe() {
		return buchungsListe;
	}
	public void setBuchungsListe(JList buchungsListe) {
		this.buchungsListe = buchungsListe;
	}
	public JList<Lieferung> getLieferungsListe() {
		return lieferungsListe;
	}
	public void setLieferungsListe(JList<Lieferung> lieferungsListe) {
		this.lieferungsListe = lieferungsListe;
	}
	public JTextField getGesamtmengeEingabe() {
		return gesamtmengeEingabe;
	}
	public void setGesamtmengeEingabe(JTextField gesamtmengeEingabe) {
		this.gesamtmengeEingabe = gesamtmengeEingabe;
	}
	
	public DefaultListModel<Lieferung> getLieferungsModel(){
		return lieferungModel;
	}
	public void setLieferungsModel(DefaultListModel<Lieferung> lieferungsModel){
		this.lieferungModel = lieferungsModel;
	}
	JButton neuesLagerErstellenButton, lagerLoeschenButton, lagerUmbenennenButton, neueZulieferungButton,neueAuslieferungButton;
	JSeparator lagerUebersichtSeperator, lieferungsUebersichtSeperator;
	JList buchungsListe, zugehoerigeBuchungen;
	JList<Lieferung> lieferungsListe;
	JTextField gesamtmengeEingabe;
	DefaultListModel<Lieferung> lieferungModel;

	private Hauptmenue( Controller controller) {
		setResizable(false);
		setBounds(50,50,800,600);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setController(controller);
		
		menueLeiste = new JMenuBar();
		menueLeiste.setBounds(0, 0, 794, 24);
		getContentPane().add(menueLeiste);
		
		menueListe = new JMenu("Menü");
		menueLeiste.add(menueListe);
		
		menueLaden = new JMenuItem("Laden");
		menueLaden.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				controller.laden();
			}
		});
		menueListe.add(menueLaden);
		
		menueSpeichern = new JMenuItem("Speichern");
		menueSpeichern.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				controller.speichern();
			}
		});
		menueListe.add(menueSpeichern);
		
		menueBeenden = new JMenuItem("Programm beenden");
		menueBeenden.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				controller.programmBeenden();
			}
		});
		menueListe.add(menueBeenden);
		
		tabLeiste = new JTabbedPane();
		tabLeiste.setBounds(0, 23, 794, 548);
		getContentPane().add(tabLeiste,null);
		
		lageruebersichtTab = new JSplitPane();
		lageruebersichtTab.setDividerLocation(0.5);
		tabLeiste.addTab("Lagerübersicht", null, lageruebersichtTab, null);
		
		lagerPane = new JPanel();
		lageruebersichtTab.setRightComponent(lagerPane);
		lagerPane.setLayout(null);
		
		lagerOptionenUeberschrift = new JLabel("Lager Optionen");
		lagerOptionenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerOptionenUeberschrift.setBounds(209, 14, 107, 23);
		lagerOptionenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lagerPane.add(lagerOptionenUeberschrift);
		lagerDetailsUeberschrift = new JLabel("Lager Details");
		lagerDetailsUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerDetailsUeberschrift.setBounds(220, 352, 107, 23);
		lagerPane.add(lagerDetailsUeberschrift);
		
		lagerKapazitaetUeberschrift = new JLabel("Kapazität");
		lagerKapazitaetUeberschrift.setHorizontalAlignment(SwingConstants.LEFT);
		lagerKapazitaetUeberschrift.setBounds(20, 425, 133, 14);
		lagerPane.add(lagerKapazitaetUeberschrift);
		
		lagerKapazitaet = new JLabel("100");
		lagerKapazitaet.setHorizontalAlignment(SwingConstants.CENTER);
		lagerKapazitaet.setBounds(163, 425, 360, 14);
		lagerPane.add(lagerKapazitaet);
		
		lagerNameUeberschrift = new JLabel("Name");
		lagerNameUeberschrift.setHorizontalAlignment(SwingConstants.LEFT);
		lagerNameUeberschrift.setBounds(20, 389, 133, 14);
		lagerPane.add(lagerNameUeberschrift);
		
		lagerName = new JLabel("Niedersachsen");
		lagerName.setHorizontalAlignment(SwingConstants.CENTER);
		lagerName.setBounds(163, 389, 360, 14);
		lagerPane.add(lagerName);
		
		lagerBestandUeberschrift = new JLabel("Bestand");
		lagerBestandUeberschrift.setHorizontalAlignment(SwingConstants.LEFT);
		lagerBestandUeberschrift.setBounds(20, 464, 133, 14);
		lagerPane.add(lagerBestandUeberschrift);
		
		lagerBestand = new JLabel("50");
		lagerBestand.setHorizontalAlignment(SwingConstants.CENTER);
		lagerBestand.setBounds(163, 464, 360, 14);
		lagerPane.add(lagerBestand);
		
		lagerTree = new JTree();
		lagerTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					Lager ausgewaehltesLager = (Lager) lagerTree.getLastSelectedPathComponent();
					controller.lagerSelected(ausgewaehltesLager);
					DefaultListModel<Buchung> listModel = new DefaultListModel<Buchung>();
					for(Buchung buchung: ausgewaehltesLager.getBuchungen()){
						listModel.addElement(buchung);
					}
					buchungsListe.setModel(listModel);
				}
			}
		});
		lagerTree.setModel(controller.getLagerModel());
		treeScrollBar = new JScrollPane(lagerTree);
		lageruebersichtTab.setLeftComponent(treeScrollBar);
		lageruebersichtTab.setDividerLocation(250);
		
		neuesLagerErstellenButton = new JButton("Neues Lager erstellen");
		neuesLagerErstellenButton.setBounds(20, 48, 170, 23);
		neuesLagerErstellenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					Lager ausgewaehltesLager = (Lager) lagerTree.getLastSelectedPathComponent();
					LagerHinzufuegenView lagerHinzufuegenView = new LagerHinzufuegenView(controller,ausgewaehltesLager);
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Sie müssen zuerst ein Lager durch anklicken auswählen." );
				}
				
			}
		});
		lagerPane.add(neuesLagerErstellenButton);
		
		lagerLoeschenButton = new JButton("Lager löschen");
		lagerLoeschenButton.setBounds(200, 48, 127, 23);
		lagerLoeschenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					controller.lagerLoeschen((Lager)(lagerTree.getLastSelectedPathComponent()));
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Sie müssen zuerst ein Lager durch anklicken auswählen." );
				}
			}
		});
		lagerPane.add(lagerLoeschenButton);
		
		
		lagerUebersichtSeperator = new JSeparator();
		lagerUebersichtSeperator.setBounds(0, 82, 533, 2);
		lagerPane.add(lagerUebersichtSeperator);
		
		buchungenUeberschrift = new JLabel("Buchungen");
		buchungenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		buchungenUeberschrift.setBounds(220, 90, 107, 23);
		lagerPane.add(buchungenUeberschrift);
		
		buchungsListe = new JList();
		lagerBuchungScrollbBar = new JScrollPane(buchungsListe);
		lagerBuchungScrollbBar.setBounds(20, 124, 492, 222);

		lagerPane.add(lagerBuchungScrollbBar);
		
		lagerUmbenennenButton = new JButton("Lager umbenennen");
		lagerUmbenennenButton.setBounds(337, 48, 186, 23);
		lagerUmbenennenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager)
				{
					Lager ausgewaehltesLager = (Lager) lagerTree.getLastSelectedPathComponent();
					if (ausgewaehltesLager.getName().equals(controller.getLagerModel().getRoot().getName()))
					{
						JOptionPane.showMessageDialog(getContentPane(), "Dieser Name darf nicht verändert werden.");
					}
					else
					{
						LagerUmbenennenView lagerUmbenennenView = new LagerUmbenennenView(controller,ausgewaehltesLager);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Sie müssen zuerst ein Lager durch anklicken auswählen." );
				}
				
			}
		});
		lagerPane.add(lagerUmbenennenButton);
		
		
		
		lieferungsuebersichtTab = new JSplitPane();
		tabLeiste.addTab("Lieferungsübersicht", null, lieferungsuebersichtTab, null);
		
		lieferungsListe = new JList<Lieferung>();
		lieferungModel = new DefaultListModel<Lieferung>();
		lieferungsListe.setModel(lieferungModel);
		lieferungScrollBar = new JScrollPane(lieferungsListe);
		lieferungsuebersichtTab.setLeftComponent(lieferungScrollBar);
		lieferungsuebersichtTab.setDividerLocation(250);
		

		
		
		ListSelectionModel listSelectionModel= lieferungsListe.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Lieferung lieferung = lieferungsListe.getSelectedValue();
				DefaultListModel<Buchung> buchungsModel = new DefaultListModel<>();
				for(Buchung buchung : lieferung.getZugehoerigeBuchungen())
				{
					buchungsModel.addElement(buchung);
				}
				zugehoerigeBuchungen.setModel(buchungsModel);
			}
		});

		
		lieferungPane = new JPanel();
		lieferungsuebersichtTab.setRightComponent(lieferungPane);
		lieferungPane.setLayout(null);
		
		lieferungsoptionenUeberschrift = new JLabel("Lieferungsoptionen");
		lieferungsoptionenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lieferungsoptionenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lieferungsoptionenUeberschrift.setBounds(220, 11, 123, 14);
		lieferungPane.add(lieferungsoptionenUeberschrift);
		
		gesamtmengeEingabe = new JTextField();
		gesamtmengeEingabe.setBounds(257, 89, 97, 20);
		lieferungPane.add(gesamtmengeEingabe);
		
		neueZulieferungButton = new JButton("Neue Zulieferung");
		neueZulieferungButton.setBounds(20, 48, 170, 23);
		neueZulieferungButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					if  (!gesamtmengeEingabe.getText().equals(""))
					{//Eingabefeld gefüllt!
						int gesamtMenge = Integer.parseInt(gesamtmengeEingabe.getText());
						if(gesamtMenge <= 0)
						{//Eingabe <= 0!
							JOptionPane.showMessageDialog(getContentPane(), "Bitte nur positivie Zahlen verwenden. \nDie Zahl muss außerdem größer als '0' sein." );
						}	
						else if(gesamtMenge > 2000000000)
						{
							JOptionPane.showMessageDialog(getContentPane(), "Bitte verwenden Sie nur ganzzahlige Zahlen \nbis zu einem Maximum von zwei Milliarden.");
						}
						else
						{
							ZulieferungsView zulieferung = new ZulieferungsView(controller, gesamtMenge);
						}
					}
					else
					{//Eingabefeld leer!
						JOptionPane.showMessageDialog(getContentPane(), "Es ist eine Eingabe des Lieferumfangs erforderlich.");
					}
				}
				catch(NumberFormatException f)
				{
					JOptionPane.showMessageDialog(getContentPane(), "Bitte verwenden Sie nur ganzzahlige Zahlen \nbis zu einem Maximum von zwei Milliarden.");
				}
			}
		});
		lieferungPane.add(neueZulieferungButton);
		
		neueAuslieferungButton = new JButton("Neue Auslieferung");
		neueAuslieferungButton.setBounds(340, 48, 170, 23);
		neueAuslieferungButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if  (!gesamtmengeEingabe.getText().equals(""))
					{//Eingabefeld gefüllt!
						int gesamtMenge = Integer.parseInt(gesamtmengeEingabe.getText());
						if(gesamtMenge <= 0)
						{//Eingabe <= 0!
							JOptionPane.showMessageDialog(getContentPane(), "Bitte nur positivie Zahlen verwenden. \nDie Zahl muss außerdem größer als '0' sein." );
						}	
						else if(gesamtMenge > 2000000000)
						{
							JOptionPane.showMessageDialog(getContentPane(), "Bitte verwenden Sie nur ganzzahlige Zahlen \nbis zu einem Maximum von zwei Milliarden.");
						}
						else if(gesamtMenge > controller.getLagerModel().getRoot().getBestand())
						{
							JOptionPane.showMessageDialog(getContentPane(), "Sie können nicht mehr Einheiten ausliefern als der maximale Bestand beträgt.\nSie können maximal " + controller.getLagerModel().getRoot().getBestand() + " Einheiten ausliefern.");
						}
						else
						{	
							AuslieferungsView auslieferung = new AuslieferungsView(controller, gesamtMenge);
						}
					}
					else
					{//Eingabefeld leer!
						JOptionPane.showMessageDialog(getContentPane(), "Es ist eine Eingabe des Lieferumfangs erforderlich.");
					}
				}
				catch(NumberFormatException f)
				{
					JOptionPane.showMessageDialog(getContentPane(), "Bitte verwenden Sie nur ganzzahlige Zahlen \nbis zu einem Maximum von zwei Milliarden.");
				}
			}
		});
		lieferungPane.add(neueAuslieferungButton);
		
		lieferungsUebersichtSeperator = new JSeparator();
		lieferungsUebersichtSeperator.setBounds(0, 120, 533, 2);
		lieferungPane.add(lieferungsUebersichtSeperator);		
		
		gesamtmenge = new JLabel("Lieferumfang:");
		gesamtmenge.setBounds(174, 89, 95, 20);
		lieferungPane.add(gesamtmenge);
		
		buchungenLieferung = new JLabel("Buchungen");
		buchungenLieferung.setBounds(230, 150, 150, 20);
		lieferungPane.add(buchungenLieferung);
				
		zugehoerigeBuchungen = new JList<Buchung>();
		lieferungBuchungScrollBar = new JScrollPane(zugehoerigeBuchungen);
		lieferungBuchungScrollBar.setBounds(20, 180, 480, 330);
		lieferungPane.add(lieferungBuchungScrollBar);
}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
