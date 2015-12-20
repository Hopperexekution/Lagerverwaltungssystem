package model;

import java.util.LinkedList;

import javax.swing.tree.MutableTreeNode;



public class Lager  {
	
	String name;
	int kapazität;
	int bestand;
	LinkedList<Buchung> buchungen = new LinkedList<Buchung>();
	
	/**
	 * Einfügen Lager auf unterster Ebene
	 * @param name Lagername
	 * @param kapazität Lagerkapazität
	 */
	public Lager(String name, int kapazität){
		this.name = name;
		this.kapazität = kapazität;
		this.bestand = 0;
	}
	
	/**
	 * Veränderung des Bestandes nach einer Zu/Abbuchung
	 * @param buchung
	 * @return gibt -1 zurück wenn der Bestand plus die hinzugefügten Einheiten die Kapazität überschreiten würden
	 * @return gibt -2 zurück wenn mehr Einheiten abgebuch werden sollen als der aktuelle Bestand enthält
	 * @return gibt 1 zurück wenn die Einheiten eroflgreich zu/abgebucht wurden
	 */
	public int aendereBestand(Buchung buchung){
		if(buchung.getBuchungsStatus()){
			if(this.kapazität < (this.bestand + buchung.getEinheiten()))
				return -1;
			
			else {
				this.bestand += buchung.getEinheiten();
				return 1;
			}
		}
		else {
			if(buchung.getEinheiten() > this.bestand){
				return -2;
		}
			else{
				this.bestand -= buchung.getEinheiten();
				return 1;
			}
				
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getKapazität(){
		return this.kapazität;
	}
	
	public int getBestand(){
		return this.bestand;
	}
	
	public LinkedList<Buchung> getBuchungen(){
		return this.buchungen;
	}
	
	public void hinzufuegenBuchung(Buchung buchung){
		this.buchungen.add(buchung);
	}
}