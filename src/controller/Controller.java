package controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import model.Lieferung;
import model.UndoRedoModel;
import view.Hauptmenue;


public class Controller {
	private LinkedList<UndoRedoModel> undoListe = new LinkedList<UndoRedoModel>();
	private LinkedList<UndoRedoModel> redoListe = new LinkedList<UndoRedoModel>();
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
					hauptmenue = new Hauptmenue(controller);
					hauptmenue.setVisible(true);
				} catch (Exception e) {
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
		//chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        		"Lagerverwaltungs-Datei: .NSFW", "nsfw");
	    chooser.setFileFilter(filter);
	    //------------------------------------------------------------------

	    int result = chooser.showOpenDialog(hauptmenue);

		if (result == JFileChooser.APPROVE_OPTION) 
    	{
			File savedFile = chooser.getSelectedFile();
			try {
					ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(savedFile));
					lagerModel = (LagerModel)inStream.readObject();
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
	        		"Lagerverwaltungs-Datei: .NSFW", "nsfw");
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
		        	OutStream.writeObject(lagerModel);
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
			vater.getChildList().add(new Lager(kind,vater));
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
					vater.getChildList().add(kindLager);
				}
			}
			vater.getChildList().remove(lager);
			this.berechneBestandKapazitaet(lager);
		}
		else{
			
		}
		
	}

	public void refreshTree(){
		hauptmenue.getLagerTree().setModel(null);
		hauptmenue.getLagerTree().setModel(lagerModel);
	}
	
	public Buchung erstelleBuchung(double prozent, int gesamtMenge, String ausgewaehltesLager) {
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
			if (!aktuelles.toString().equals(lagername)) {
				gefunden = null;
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
	
	private void berechneBestandKapazitaet(Lager wurzel) {
		int kapazitaet = 0, bestand = 0;
		List<Lager> nachfolger = wurzel.getChildList();
		Iterator<Lager> it = nachfolger.iterator();

		while (it.hasNext()) {
			Lager aktuelles = (Lager) it.next();
			berechneBestandKapazitaet(aktuelles);
			if (!aktuelles.getChildList().isEmpty()) {
				List<Lager> kinder = aktuelles.getChildList();
				Iterator<Lager> kinderIt = kinder.iterator();
				while (kinderIt.hasNext())
				{
					Lager kind = kinderIt.next();
					kapazitaet +=  kind.getKapazität();
					bestand += kind.getBestand();
				}
				aktuelles.setKapazitaet(kapazitaet);
				aktuelles.setBestand(bestand);
			}
		}
		
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

	public void erstelleLieferung(int restEinheiten, int gesamtMenge) {
		Lieferung neueZulieferung = new Lieferung(Calendar.getInstance().getTime(), gesamtMenge);
		Lager zuBuchungPassendes;
		if(!undoListe.isEmpty())
		{
			for(UndoRedoModel model : undoListe)
			{
				neueZulieferung.hinzufuegenBuchung(model.getBuchung());
				zuBuchungPassendes = this.findePassendesLager(model.getBuchung().getZugehoerigesLager(), (Lager) this.getLagerModel().getRoot());
				zuBuchungPassendes.hinzufuegenBuchung(model.getBuchung());
			}
		}
		while(!undoListe.isEmpty()){
			undoListe.removeFirst();
		}
		
		if(!redoListe.isEmpty())
		{
			for(UndoRedoModel model : redoListe)
			{
				redoListe.remove(model);
			}
		}
		DefaultListModel<Lieferung> lieferungsModel = this.getHauptmenue().getLieferungsModel();
		lieferungsModel.addElement(neueZulieferung);
		this.getHauptmenue().getLieferungsListe().setModel(lieferungsModel);
		
	}

	public void loescheRedoListe() {
		for(UndoRedoModel model : redoListe)
		{
			redoListe.remove(model);
		}
		
	}

	public void loescheUndoListe() {
		for(UndoRedoModel model : undoListe)
		{
			undoListe.remove(model);
		}
		
	}
	public void lagerHinzufuegen(Lager lager, Lager vater) {
		vater.getChildList().add(lager);
		standardBeschriftung();
		this.berechneBestandKapazitaet(this.getLagerModel().getRoot());
		refreshTree();
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
			hauptmenue.getLagerKapazitaet().setText(Integer.toString(lager.getKapazität()));			
		}
		else{
			hauptmenue.getLagerNameUeberschrift().setText("Lagername:");
			hauptmenue.getLagerBestandUeberschrift().setText("Kummulierter Bestand:");
			hauptmenue.getLagerKapazitaetUeberschrift().setText("Kummulierte Kapaziät:");
			hauptmenue.getLagerName().setText(lager.getName());
			hauptmenue.getLagerBestand().setText(Integer.toString(lager.getBestand()));
			hauptmenue.getLagerKapazitaet().setText(Integer.toString(lager.getKapazität()));
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

}
