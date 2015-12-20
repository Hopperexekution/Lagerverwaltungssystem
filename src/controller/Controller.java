package controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;

import model.Buchung;

import view.Hauptmenue;


public class Controller {

	static Hauptmenue hauptmenue;
	List<Buchung> buchungsListe = new ArrayList<Buchung>();
	TreeNode lagerListe;
	
	//Variablen-Deklarationen
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
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
	public void buchungHinzufügen(int einheiten, String zugehoerigesLager, Date datum, boolean zubuchung){
		Buchung buchung = new Buchung(einheiten, zugehoerigesLager, datum, zubuchung);
		buchungsListe.add(buchung);
		buchungsListe.sort(new DateComparator());
		
	}
	public void programmBeenden(){
		String[] yesNoOptions = { "Ja", "Nein", "Abbrechen" };
		int auswahl = JOptionPane.showOptionDialog(null, "Wollen Sie das Programm wirklich beenden?", "Wollen Sie das Programm wirklich beenden?", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);
		if(auswahl == 0){
			System.exit(0);
		}
	}
	private static void erstelleLagerListe(){
		
	}
}
