package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.MutableTreeNode;



public class Lager  {
	
	private String name;
	private int kapazit�t;
	private int bestand;
	private Lager vater;
	private List<Buchung> buchungen = new ArrayList<Buchung>();
	private List<Lager> children = new ArrayList<Lager>();
	
	/**
	 * Einf�gen Lager auf unterster Ebene
	 * @param name Lagername
	 * @param kapazit�t Lagerkapazit�t
	 */
	public Lager(String name, int kapazit�t, int bestand){
		this.name = name;
		this.kapazit�t = kapazit�t;
		this.bestand = bestand;
	}
	public Lager(String name, Lager vater){
		this.name = name;
		this.vater = vater;
	}
	public Lager(String name){
		this.name = name;
	}
	
	/**
	 * Ver�nderung des Bestandes nach einer Zu/Abbuchung
	 * @param buchung
	 * @return gibt -1 zur�ck wenn der Bestand plus die hinzugef�gten Einheiten die Kapazit�t �berschreiten w�rden
	 * @return gibt -2 zur�ck wenn mehr Einheiten abgebuch werden sollen als der aktuelle Bestand enth�lt
	 * @return gibt 1 zur�ck wenn die Einheiten eroflgreich zu/abgebucht wurden
	 */
	public int aendereBestand(Buchung buchung){
		if(buchung.getBuchungsStatus()){
			if(this.kapazit�t < (this.bestand + buchung.getEinheiten()))
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
	
	public int getKapazit�t(){
		return this.kapazit�t;
	}
	
	public int getBestand(){
		return this.bestand;
	}
	
	public List<Buchung> getBuchungen(){
		return this.buchungen;
	}
	
	public void hinzufuegenBuchung(Buchung buchung){
		this.buchungen.add(buchung);
	}
	public String toString(){
		return name;
	}
	public Lager getVater() {
		return vater;
	}
	public void setVater(Lager vater) {
		this.vater = vater;
	}
}