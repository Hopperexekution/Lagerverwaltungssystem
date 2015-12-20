package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.MutableTreeNode;



public class Lager  {
	
	private String name;
	private int kapazität;
	private int bestand;
	private LinkedList<Buchung> buchungen = new LinkedList<Buchung>();
	private List<Lager> children = new ArrayList<Lager>();
	
	/**
	 * Einfügen Lager auf unterster Ebene
	 * @param name Lagername
	 * @param kapazität Lagerkapazität
	 */
	public Lager(String name, int kapazität, int bestand){
		this.name = name;
		this.kapazität = kapazität;
		this.bestand = bestand;
	}
	public Lager(String name){
		this.name = name;
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
	public List<Lager> getChildList(){
		return children;
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