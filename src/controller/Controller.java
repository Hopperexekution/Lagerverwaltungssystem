package controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
import model.UndoRedoModel;
import view.Hauptmenue;


public class Controller {
	private List<UndoRedoModel> undoListe;
	private List<UndoRedoModel> redoListe;
	Lager gefunden = null;
	static Hauptmenue hauptmenue;
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
					hauptmenue.refreshTree();
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

	public void loeschen(Lager lager) {
		if(!lagerModel.isRoot(lager)){
			Lager vater = lager.getVater();
			if(!lager.getChildList().isEmpty()){
				for(Lager kindLager : lager.getChildList()){
					kindLager.setVater(vater);
					vater.getChildList().add(kindLager);
				}
			}
			vater.getChildList().remove(lager);
		}
		else{
			
		}
	}

	public Buchung erstelleBuchung(double prozent, int gesamtMenge, String ausgewaehltesLager) {
		int einheit = (int) (Math.floor((double)(gesamtMenge * (prozent/100))));;
		
		Buchung neueBuchung = new Buchung(einheit, ausgewaehltesLager, true);
		undoListe.add(new UndoRedoModel(neueBuchung, prozent));
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
	
	private Lager findePassendesLager(String lagername, Lager wurzel)
	{

		List<Lager> nachfolger = wurzel.getChildList();
		Iterator it = nachfolger.iterator();
		

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

}
