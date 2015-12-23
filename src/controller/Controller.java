package controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observer;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.tree.TreeNode;

import java.io.File; 
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Buchung;
import model.Lager;
import model.LagerModel;
import model.LagerUndListe;
import model.Lieferung;
import model.LieferungListe;
import model.UndoRedoModel;
import view.Hauptmenue;
import view.LagerVerteilenView;


public class Controller {
	private LinkedList<UndoRedoModel> undoListe = new LinkedList<UndoRedoModel>();
	private LinkedList<UndoRedoModel> redoListe = new LinkedList<UndoRedoModel>();
	///private LinkedList<Buchung> buchungListe = new LinkedList<Buchung>();
	Lager gefunden = null;
	private static Hauptmenue hauptmenue;
	private List<Buchung> buchungsListe = new ArrayList<Buchung>();
	private static LagerModel lagerModel;
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					erstelleLagerListe();
					controller.berechneBestand(controller.getLagerModel().getRoot());
					controller.berechneKapazitaet(controller.getLagerModel().getRoot());
					hauptmenue = Hauptmenue.getInstance(controller);
					hauptmenue.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	


	public Controller()
	{
		
	}
	
	public void buchungHinzufügen(int einheiten, String zugehoerigesLager, boolean zubuchung)
	{
		Buchung buchung = new Buchung(einheiten, zugehoerigesLager, zubuchung);
		buchungsListe.add(buchung);
		
	}
	public Hauptmenue getHauptmenue(){
		return hauptmenue;
	}
	
	public void laden()
	{
		JFileChooser chooser = new JFileChooser();
	    //------------------------------------------------------------------
		chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		"NOT SAFE FOR WORK: .NSFW", "nsfw");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
        		"Das ist NICHT der Dateityp den Sie suchen", ".nichtderdateitypdensiesuchen");
        chooser.setFileFilter(filter2);
	    chooser.setFileFilter(filter);
	    //------------------------------------------------------------------

	    int retValue = chooser.showOpenDialog(hauptmenue);

		if (retValue == JFileChooser.APPROVE_OPTION) 
    	{
			File savedFile = chooser.getSelectedFile();
			try {
					ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(savedFile));
					LagerUndListe lagerUndListe = (LagerUndListe) inStream.readObject();
					lagerModel = lagerUndListe.getLagerModel();
					hauptmenue.setLieferungsModel(lagerUndListe.getLieferungListe().getLieferungsListe());
					DefaultListModel<Lieferung> listModel = new DefaultListModel<Lieferung>();
					listModel = lagerUndListe.getLieferungListe().getLieferungsListe();
					hauptmenue.getLieferungsListe().setModel(listModel);
					refreshTree();
					inStream.close();
		
				} 
				catch (Exception e) 
				{	// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(hauptmenue, "Laden nicht möglich!", "Error", JOptionPane.ERROR_MESSAGE);
				}
		}
	}	
	
	public void speichern()
	{		
		{		
			JFileChooser chooser = new JFileChooser();
		    //------------------------------------------------------------------
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        		"NOT SAFE FOR WORK: .NSFW", "nsfw");
	        chooser.setFileFilter(filter);
		    //------------------------------------------------------------------
		    
		    chooser.setDialogTitle("Speichern unter...");
		    
	        int retValue = chooser.showSaveDialog(hauptmenue);
	        
	        if (retValue == JFileChooser.APPROVE_OPTION) 
	        {

		        File file = chooser.getSelectedFile();
		        if(file.toString().toLowerCase().endsWith(".nsfw"))
				{	//Konvertieren der letzten vier Buchstaben in Großbuchstaben
					file = new File(file.toString().substring(0, file.toString().length()-4) + "NSFW");
				}
		        else
		        {   //Anhängen der Dateiendung "NSFW"
		        	file = new File(file.toString() + ".NSFW");
		        }
		        			
				try 
				{

		        	ObjectOutputStream OutStream = new ObjectOutputStream(new FileOutputStream(file));
		        	LieferungListe lieferungListe = new LieferungListe(hauptmenue.getLieferungsModel());
		        	LagerModel lagerModel = this.getLagerModel();
		        	LagerUndListe lagerUndListe = new LagerUndListe(lagerModel, lieferungListe);
		        	OutStream.writeObject(lagerUndListe);
		        	OutStream.flush();
		        	OutStream.close();
				} 
				catch (Exception e) 
				{	// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(hauptmenue, "Speichern nicht möglich!", "Error", JOptionPane.ERROR_MESSAGE);
				} 			
	        }
		}
	}
	
	
	public void programmBeenden()
	{
		String[] yesNoOptions = { "Ja", "Nein"};
		int auswahl = JOptionPane.showOptionDialog(null, "Wollen Sie das Programm wirklich beenden?", "Wollen Sie das Programm wirklich beenden?", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);
		if(auswahl == 0){
			System.exit(0);
		}
	}
	
	private static void erstelleLagerListe()
	{
		Lager root = new Lager("Lager");
		addDefaultLager(root, new String[]{"Deutschland", "Europa", "Großbritannien"});
			addDefaultLager(root.getChildList().get(0), new String[]{"Niedersachsen", "NRW", "Bremen", "Hessen", "Sachsen", "Brandenburg", "MV"});	
				addDefaultLager(root.getChildList().get(0).getChildList().get(0), new String[]{"Hannover-Misburg", "Nienburg"});
			addDefaultLager(root.getChildList().get(1), new String[]{"Frankreich", "Italien", "Spanien"});
				addDefaultLager(root.getChildList().get(1).getChildList().get(0),new String[]{"Paris-Nord", "Orleans", "Marseille", "Nimes"});
				addDefaultLager(root.getChildList().get(1).getChildList().get(1), new String[]{"Mailand", "L'Aquila"});
		lagerModel = new LagerModel(root);
	
	}
	
	
	private static void addDefaultLager(Lager vater, String[] kinder)
	{
		for(String kind: kinder)
		{
			vater.getChildList().add(new Lager(kind,15000, 0, vater));
		}
	}
	
	public LagerModel getLagerModel()
	{
		return lagerModel;
	}

	public void lagerLoeschen(Lager lager) {
		if(!lagerModel.isRoot(lager)){
			Lager vater = lager.getVater();
			if(!lager.getChildList().isEmpty()){
				for(Lager kindLager : lager.getChildList()){
					kindLager.setVater(vater);
					lagerModel.lagerHinzufuegen(vater, kindLager);
				}
				vater.setBestand(vater.getBestand() - lager.getBestand());
				vater.setKapazitaet(vater.getKapazitaet() - lager.getKapazitaet());
				lagerModel.lagerLoeschen(lager);
			}
			else{
				if(vater.getChildList().size() > 1){
					Vector<Lager> childListe = findeAuswaehlbarLager(new Vector<Lager>());
					long gesamtVerfuegbareKapazitaet = 0;
					for(Lager kind: childListe){
						if(!kind.equals(lager))
							gesamtVerfuegbareKapazitaet += kind.getKapazitaet() - kind.getBestand();
					}
					if (gesamtVerfuegbareKapazitaet >= lager.getBestand()){
						if(lager.getBestand() == 0){
							vater.setBestand(vater.getBestand() - lager.getBestand());
							vater.setKapazitaet(vater.getKapazitaet() - lager.getKapazitaet());
							lagerModel.lagerLoeschen(lager);
						}
						else{
								LagerVerteilenView lagerVerteilen = new LagerVerteilenView(hauptmenue.getController(), lager.getBestand(), lager);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Das Lager kann nicht gelöscht werden, da die übrigen Lager über nicht ausreichende Kapazitäten verfügen." );
					}
				}
				else{
					lagerModel.lagerLoeschen(lager);
				}
			}
			this.berechneBestand(lagerModel.getRoot());
			this.berechneKapazitaet(lagerModel.getRoot());
		}
		else{
			JOptionPane.showMessageDialog(null, "Das Hauptlager darf nicht gelöscht werden." );
		}
		
	}

	public void refreshTree(){
		hauptmenue.getLagerTree().setModel(null);
		hauptmenue.getLagerTree().setModel(lagerModel);
	}
	
	public Buchung erstelleZubuchung(double prozent, int gesamtMenge, String ausgewaehltesLager) {
		int einheit = (int) (Math.floor((double)(gesamtMenge * (prozent/100))));;
		
		Buchung neueBuchung = new Buchung(einheit, ausgewaehltesLager, true);
		UndoRedoModel undoRedoModel = new UndoRedoModel(neueBuchung, prozent);
		undoListe.add(undoRedoModel);
		Lager ausgewaehlt = this.findePassendesLager(ausgewaehltesLager, (Lager) this.getLagerModel().getRoot());
		if(ausgewaehlt != null)
		{
			return neueBuchung;
		}
		return null;
	}

	public Vector<Lager> findeAuswaehlbarLager(Vector<Lager> auswaehlbareLager)
	{
	Lager wurzel = (Lager) this.getLagerModel().getRoot();
	if (!wurzel.equals(null)) {
		findeBlaetter(wurzel, auswaehlbareLager);
	} else if (wurzel.getChildList().isEmpty()) {
		auswaehlbareLager.add(wurzel);
	}
	return auswaehlbareLager;
	}
	
	private void findeBlaetter(Lager wurzel, Vector<Lager> auswaehlbarLager) {
		List<Lager> nachfolger = wurzel.getChildList();
		Iterator it = nachfolger.iterator();

		while (it.hasNext()) {
			Lager aktuelles = (Lager) it.next();
			if (aktuelles.getChildList().isEmpty()) {
				auswaehlbarLager.add(aktuelles);
			}
			findeBlaetter(aktuelles, auswaehlbarLager);
		}
	}
	
	public Lager findePassendesLager(String lagername, Lager wurzel)
	{

		List<Lager> nachfolger = wurzel.getChildList();
		Iterator it = nachfolger.iterator();
		
		gefunden = null;
		while (it.hasNext() && gefunden == null) 
		{
			Lager aktuelles = (Lager) it.next();
			if (!aktuelles.toString().equals(lagername)) 
			{
				findePassendesLager(lagername, aktuelles);
			}
			else if(aktuelles.toString().equals(lagername))
			{	
				gefunden = aktuelles;
			}
			
		}
		
		return gefunden;
	}

	public double getProzent() {
		double prozent = 0;
		if(undoListe != null && !undoListe.isEmpty())
		{
			for(UndoRedoModel model: undoListe){
				prozent += model.getProzent();
			}
		}
		return prozent;
	}
	
	public int getVerteilteEinheiten()
	{
		int einheiten = 0;
		if(undoListe != null && !undoListe.isEmpty())
		{
			for(UndoRedoModel model: undoListe){
				einheiten += model.getBuchung().getEinheiten();
			}
		}
		return einheiten;
	}
	
	
	
	public int berechneBestand(Lager wurzel) 
	{
		int bestand = 0;
		List<Lager> nachfolger = wurzel.getChildList();
		Iterator<Lager> it = nachfolger.iterator();
				
		while (it.hasNext()) // nur wenn liste nicht leer
		{
			Lager aktuelles = (Lager) it.next();
			if (aktuelles.getChildList().isEmpty())
			{//Blatt
				bestand += aktuelles.getBestand();			}
			else
			{//Zweig
				bestand += berechneBestand(aktuelles);
			}
		}
		wurzel.setBestand(bestand);
		return bestand;
	}
	
	public int berechneKapazitaet(Lager wurzel) 
	{
		int kapazitaet = 0;
		List<Lager> nachfolger = wurzel.getChildList();
		Iterator<Lager> it = nachfolger.iterator();
				
		while (it.hasNext()) // nur wenn liste nicht leer
		{
			Lager aktuelles = (Lager) it.next();
			if (aktuelles.getChildList().isEmpty())
			{//Blatt
				kapazitaet += aktuelles.getKapazitaet();
			}
			else
			{//Zweig
				kapazitaet += berechneKapazitaet(aktuelles);
			}
		}
		wurzel.setKapazitaet(kapazitaet);
		return kapazitaet;
	}
	
	public Buchung undo(){
		redoListe.add(undoListe.getLast());
		undoListe.removeLast();
		return redoListe.getLast().getBuchung();

	}
	
	public Buchung redo(){
		undoListe.add(redoListe.getLast());
		redoListe.removeLast();
		return undoListe.getLast().getBuchung();
	}

	public boolean redoMoeglich() {
		if(!redoListe.isEmpty())
		{
			return true;
		}
		else
		{	
			return false;
		}
	}
	
	public boolean undoMoeglich() {
		if(!undoListe.isEmpty())
		{
			return true;
		}
		else
		{	
			return false;
		}
	}

	public void erstelleZulieferung(int restEinheiten, int gesamtMenge) 
	{
		Lieferung neueZulieferung;
		if(restEinheiten != 0)
		{
			neueZulieferung = new Lieferung(Calendar.getInstance().getTime(), (gesamtMenge + restEinheiten));
		}
		else
		{
			neueZulieferung = new Lieferung(Calendar.getInstance().getTime(), gesamtMenge);
		}
		Lager zuBuchungPassendes;
		if(!undoListe.isEmpty())
		{
			if(restEinheiten != 0)
			{
				undoListe.getLast().getBuchung().setEinheiten(undoListe.getLast().getBuchung().getEinheiten() + restEinheiten);
			}
			for(UndoRedoModel model : undoListe)
			{
				neueZulieferung.hinzufuegenBuchung(model.getBuchung());
				zuBuchungPassendes = this.findePassendesLager(model.getBuchung().getZugehoerigesLager(), (Lager) this.getLagerModel().getRoot());
				zuBuchungPassendes.hinzufuegenBuchung(model.getBuchung());
				if(model.getBuchung().getBuchungsStatus())
				{
					zuBuchungPassendes.aendereBestand(model.getBuchung().getEinheiten());
				}	

					
			
			}
		}
		this.loescheUndoListe();

		this.loescheRedoListe();
		
		DefaultListModel<Lieferung> lieferungsModel = this.getHauptmenue().getLieferungsModel();
		lieferungsModel.addElement(neueZulieferung);
		this.getHauptmenue().getLieferungsListe().setModel(lieferungsModel);
		
	}
	
		
	

	public void loescheRedoListe() {
		redoListe = new LinkedList<UndoRedoModel>();
	}

	public void loescheUndoListe() {
		undoListe = new LinkedList<UndoRedoModel>();
	}
	
	public void lagerHinzufuegen(Lager lager, Lager vater) 
	{
		if (vater.getChildList().isEmpty())
		{
			lager.setBestand(vater.getBestand());
		}
		lagerModel.lagerHinzufuegen(vater, lager);
		standardBeschriftung();
		this.berechneBestand(this.getLagerModel().getRoot());
		this.berechneKapazitaet(this.getLagerModel().getRoot());
		hauptmenue.setAlwaysOnTop(true);
		hauptmenue.setAlwaysOnTop(false);
	}
	
	public void lagerSelected(Lager lager){
		if(lagerModel.isLeaf(lager)){
			hauptmenue.getLagerNameUeberschrift().setText("Lagername:");
			hauptmenue.getLagerBestandUeberschrift().setText("Bestand:");
			hauptmenue.getLagerKapazitaetUeberschrift().setText("Kapaziät:");
			hauptmenue.getLagerName().setText(lager.getName());
			hauptmenue.getLagerBestand().setText(Integer.toString(lager.getBestand()));
			hauptmenue.getLagerKapazitaet().setText(Integer.toString(lager.getKapazitaet()));			
		}
		else{
			hauptmenue.getLagerNameUeberschrift().setText("Lagername:");
			hauptmenue.getLagerBestandUeberschrift().setText("Kummulierter Bestand:");
			hauptmenue.getLagerKapazitaetUeberschrift().setText("Kummulierte Kapaziät:");
			hauptmenue.getLagerName().setText(lager.getName());
			hauptmenue.getLagerBestand().setText(Integer.toString(lager.getBestand()));
			hauptmenue.getLagerKapazitaet().setText(Integer.toString(lager.getKapazitaet()));
		}
	}
	
	public void standardBeschriftung(){
		hauptmenue.getLagerNameUeberschrift().setText("Lagername:");
		hauptmenue.getLagerBestandUeberschrift().setText("Bestand:");
		hauptmenue.getLagerKapazitaetUeberschrift().setText("Kapaziät:");
		hauptmenue.getLagerName().setText("");
		hauptmenue.getLagerBestand().setText("");
		hauptmenue.getLagerKapazitaet().setText("");
	}

	public Buchung erstelleAbbuchung(int einheit, String ausgewaehltesLager) 
	{
		Buchung neueBuchung = new Buchung(einheit, ausgewaehltesLager, false);
		UndoRedoModel undoRedoModel = new UndoRedoModel(neueBuchung);
		undoListe.add(undoRedoModel);
		return neueBuchung;
	}
	

	public Vector<Lager> findeAuswaehlbarLagerAuslieferung(Vector<Lager> auswaehlbareLager) {
		{
			Lager wurzel = (Lager) this.getLagerModel().getRoot();
			if (!wurzel.equals(null)) {
				findeBlaetterAuslieferung(wurzel, auswaehlbareLager);
			} else if (wurzel.getChildList().isEmpty()) {
				auswaehlbareLager.add(wurzel);
			}
			return auswaehlbareLager;
			}
	}

	private void findeBlaetterAuslieferung(Lager wurzel, Vector<Lager> auswaehlbareLager) {
		List<Lager> nachfolger = wurzel.getChildList();
		Iterator it = nachfolger.iterator();

		while (it.hasNext()) {
			Lager aktuelles = (Lager) it.next();
			if (aktuelles.getChildList().isEmpty() && aktuelles.getBestand() > 0) 
			{
				auswaehlbareLager.add(aktuelles);
			}
			findeBlaetter(aktuelles, auswaehlbareLager);
		}
	}

	public void erstelleAuslieferung(int gesamtMenge) {
		Lieferung neueZulieferung = new Lieferung(Calendar.getInstance().getTime(), gesamtMenge);
		Lager zuBuchungPassendes;
		if(!undoListe.isEmpty())
		{
			for(UndoRedoModel model : undoListe)
			{
				neueZulieferung.hinzufuegenBuchung(model.getBuchung());
				zuBuchungPassendes = this.findePassendesLager(model.getBuchung().getZugehoerigesLager(), (Lager) this.getLagerModel().getRoot());
				zuBuchungPassendes.hinzufuegenBuchung(model.getBuchung());
				if(model.getBuchung().getBuchungsStatus())
				{
					zuBuchungPassendes.aendereBestand(model.getBuchung().getEinheiten());
				}
			}
		}
		this.loescheUndoListe();
		
		DefaultListModel<Lieferung> lieferungsModel = this.getHauptmenue().getLieferungsModel();
		lieferungsModel.addElement(neueZulieferung);
		this.getHauptmenue().getLieferungsListe().setModel(lieferungsModel);
		
	}
}
