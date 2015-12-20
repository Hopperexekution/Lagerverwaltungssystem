package controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Buchung;

import view.Hauptmenue;


public class Controller {

	//Objekt-Deklarationen
	Hauptmenue hauptmenue;
	List<Buchung> buchungsListe = new ArrayList<Buchung>();
	
	//Variablen-Deklarationen
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					Hauptmenue hauptmenue = new Hauptmenue(controller);
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
}
