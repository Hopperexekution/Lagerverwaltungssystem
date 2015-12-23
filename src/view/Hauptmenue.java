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
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.Action;
/**
 * Hauptfenster des Programms
 * @author Birk
 *
 */
public class Hauptmenue extends JFrame implements Observer {
	DefaultListModel<Buchung> listModel = new DefaultListModel<Buchung>();
	private JTree lagerTree;
	private Controller controller;
	private JLabel   lagerKapazitaetUeberschrift, lagerKapazitaet,
		   lagerNameUeberschrift, lagerName, lagerBestandUeberschrift, lagerBestand;
	private static Hauptmenue hauptmenue = null;
	private JList<Lieferung> lieferungsListe;
	private DefaultListModel<Lieferung> lieferungModel;
	private static Observer lagerObserver;
	
	/**
	 * Singleton-Pattern. Hauptmenue darf nur ein mal erzeugt werden.
	 * @param controller
	 * @return
	 */
	public static Hauptmenue getInstance(Controller controller){
			if(hauptmenue == null){
				hauptmenue = new Hauptmenue(controller);
			}
			lagerObserver = hauptmenue;
			controller.getLagerModel().addObserver(lagerObserver);
			return hauptmenue;
		}
		   
	
	

	private Hauptmenue( final Controller controller) {
		setResizable(false);
		setBounds(50,50,800,600);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setController(controller);
		
		JMenuBar menueLeiste = new JMenuBar();
		menueLeiste.setBounds(0, 0, 794, 24);
		getContentPane().add(menueLeiste);
		
		JMenu menueListe = new JMenu("Menü");
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
		lageruebersichtTab.setEnabled(false);
		lageruebersichtTab.setDividerLocation(0.5);
		tabLeiste.addTab("Lagerübersicht", null, lageruebersichtTab, null);
		
		JPanel lagerPane = new JPanel();
		lageruebersichtTab.setRightComponent(lagerPane);
		lagerPane.setLayout(null);
		
		JLabel lagerOptionenUeberschrift = new JLabel("Lager Optionen");
		lagerOptionenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerOptionenUeberschrift.setBounds(209, 14, 107, 23);
		lagerOptionenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lagerPane.add(lagerOptionenUeberschrift);
		JLabel lagerDetailsUeberschrift = new JLabel("Lager Details");
		lagerDetailsUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerDetailsUeberschrift.setBounds(220, 352, 107, 23);
		lagerPane.add(lagerDetailsUeberschrift);
		
		lagerKapazitaetUeberschrift = new JLabel("Kapazität");
		lagerKapazitaetUeberschrift.setHorizontalAlignment(SwingConstants.LEFT);
		lagerKapazitaetUeberschrift.setBounds(20, 425, 133, 14);
		lagerPane.add(lagerKapazitaetUeberschrift);
		
		lagerKapazitaet = new JLabel("0");
		lagerKapazitaet.setHorizontalAlignment(SwingConstants.CENTER);
		lagerKapazitaet.setBounds(163, 425, 360, 14);
		lagerPane.add(lagerKapazitaet);
		
		lagerNameUeberschrift = new JLabel("Name");
		lagerNameUeberschrift.setHorizontalAlignment(SwingConstants.LEFT);
		lagerNameUeberschrift.setBounds(20, 389, 133, 14);
		lagerPane.add(lagerNameUeberschrift);
		
		lagerName = new JLabel("");
		lagerName.setHorizontalAlignment(SwingConstants.CENTER);
		lagerName.setBounds(163, 389, 360, 14);
		lagerPane.add(lagerName);
		
		lagerBestandUeberschrift = new JLabel("Bestand");
		lagerBestandUeberschrift.setHorizontalAlignment(SwingConstants.LEFT);
		lagerBestandUeberschrift.setBounds(20, 464, 133, 14);
		lagerPane.add(lagerBestandUeberschrift);
		final JList buchungsListe = new JList();
		
		lagerBestand = new JLabel("0");
		lagerBestand.setHorizontalAlignment(SwingConstants.CENTER);
		lagerBestand.setBounds(163, 464, 360, 14);
		lagerPane.add(lagerBestand);
		
		lagerTree = new JTree();
		lagerTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					//Auswahl erkennen und Anpassung der View initialisieren, um die korrekten Informationen anzuzeigen  
					Lager ausgewaehltesLager = (Lager) lagerTree.getLastSelectedPathComponent();
					controller.lagerSelected(ausgewaehltesLager);

					for(Buchung buchung: ausgewaehltesLager.getBuchungen()){
						listModel.addElement(buchung);
					}
					buchungsListe.setModel(listModel);
				}
			}
		});
		lagerTree.setModel(controller.getLagerModel());
		JScrollPane treeScrollBar = new JScrollPane(lagerTree);
		lageruebersichtTab.setLeftComponent(treeScrollBar);
		lageruebersichtTab.setDividerLocation(250);
		
		JButton neuesLagerErstellenButton = new JButton("Neues Lager erstellen");
		neuesLagerErstellenButton.setBounds(20, 48, 170, 23);
		neuesLagerErstellenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					//Lager hinzufügen initiieren. Das Lager wird als Kind des ausgewählten Lagers erstellt.
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
		
		JButton lagerLoeschenButton = new JButton("Lager löschen");
		lagerLoeschenButton.setBounds(200, 48, 127, 23);
		lagerLoeschenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lagerTree.getLastSelectedPathComponent() instanceof Lager){
					//Löschen des Lagers auf Basis der Auswahl einleiten
					controller.lagerLoeschen((Lager)(lagerTree.getLastSelectedPathComponent()));
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Sie müssen zuerst ein Lager durch anklicken auswählen." );
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
		
		
		JScrollPane lagerBuchungScrollbBar = new JScrollPane(buchungsListe);
		lagerBuchungScrollbBar.setBounds(20, 124, 492, 222);

		lagerPane.add(lagerBuchungScrollbBar);
		
		JButton lagerUmbenennenButton = new JButton("Lager umbenennen");
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
						//Umbennenen auf Basis der Auswahl einleiten 
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
		
		
		
		JSplitPane lieferungsuebersichtTab = new JSplitPane();
		lieferungsuebersichtTab.setEnabled(false);
		tabLeiste.addTab("Lieferungsübersicht", null, lieferungsuebersichtTab, null);
		
		lieferungsListe = new JList<Lieferung>();
		lieferungModel = new DefaultListModel<Lieferung>();
		lieferungsListe.setModel(lieferungModel);
		JScrollPane lieferungScrollBar = new JScrollPane(lieferungsListe);
		lieferungsuebersichtTab.setLeftComponent(lieferungScrollBar);
		lieferungsuebersichtTab.setDividerLocation(250);
		

		final JList<Buchung> zugehoerigeBuchungen = new JList<Buchung>();
		
		ListSelectionModel listSelectionModel= lieferungsListe.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//Darstellung der Lieferung auf Basis der Auswahl
				Lieferung lieferung = lieferungsListe.getSelectedValue();
				DefaultListModel<Buchung> buchungsModel = new DefaultListModel<>();
				if(lieferung.getZugehoerigeBuchungen() != null)
				{
				for(Buchung buchung : lieferung.getZugehoerigeBuchungen())
				{
					buchungsModel.addElement(buchung);
				}
				zugehoerigeBuchungen.setModel(buchungsModel);
				}
			}
		});

		
		JPanel lieferungPane = new JPanel();
		lieferungsuebersichtTab.setRightComponent(lieferungPane);
		lieferungPane.setLayout(null);
		
		JLabel lieferungsoptionenUeberschrift = new JLabel("Lieferungsoptionen");
		lieferungsoptionenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lieferungsoptionenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lieferungsoptionenUeberschrift.setBounds(220, 11, 123, 14);
		lieferungPane.add(lieferungsoptionenUeberschrift);
		
		final JTextField gesamtmengeEingabe = new JTextField();
		gesamtmengeEingabe.setBounds(257, 89, 97, 20);
		lieferungPane.add(gesamtmengeEingabe);
		
		JButton neueZulieferungButton = new JButton("Neue Zulieferung");
		neueZulieferungButton.setBounds(20, 48, 170, 23);
		neueZulieferungButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					//Einleiten einer neuen Zulieferung. Ungültige Angaben werden abgefangen.
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
		
		JButton neueAuslieferungButton = new JButton("Neue Auslieferung");
		neueAuslieferungButton.setBounds(340, 48, 170, 23);
		neueAuslieferungButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					//Einleiten einer neuen Auslieferung. Ungültige Eingaben werden abgefangen.
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
		
		JSeparator lieferungsUebersichtSeperator = new JSeparator();
		lieferungsUebersichtSeperator.setBounds(0, 120, 533, 2);
		lieferungPane.add(lieferungsUebersichtSeperator);		
		
		JLabel gesamtmenge = new JLabel("Lieferumfang:");
		gesamtmenge.setBounds(174, 89, 95, 20);
		lieferungPane.add(gesamtmenge);
		
		JLabel buchungenLieferung = new JLabel("Buchungen");
		buchungenLieferung.setBounds(230, 150, 150, 20);
		lieferungPane.add(buchungenLieferung);
		
		JScrollPane lieferungBuchungScrollBar = new JScrollPane(zugehoerigeBuchungen);
		lieferungBuchungScrollBar.setBounds(20, 180, 480, 330);
		lieferungPane.add(lieferungBuchungScrollBar);
}
	public JTree getLagerTree() {
		return lagerTree;
	}
	public void setLagerTree(JTree lagerTree) {
		this.lagerTree = lagerTree;
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
	public DefaultListModel<Lieferung> getLieferungsModel() {
		return lieferungModel;
	}
	public void setLieferungsModel(DefaultListModel<Lieferung> lieferungModel) {
		this.lieferungModel = lieferungModel;
	}
	public JList<Lieferung> getLieferungsListe() {
		return lieferungsListe;
	}
	public void setLieferungsListe(JList<Lieferung> lieferungsListe) {
		this.lieferungsListe = lieferungsListe;
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	public static Observer getLagerobserver(){
		return lagerObserver;
	}



	/**
	 * Erneuerung der Lageransicht, falls im Observable ein setChanged() und notifyObervers() ausgelöst wird
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		controller.refreshTree();		
	}
}
