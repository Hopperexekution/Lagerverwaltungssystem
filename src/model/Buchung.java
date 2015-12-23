package model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author Robin
 *
 */
public class Buchung implements Serializable{
	int einheiten;
	String zugehoerigesLager;
	boolean zubuchung;
	String zuOderAbbuchung;
	
	
	public Buchung(int einheiten, String zugehoerigesLager, boolean zubuchung){
		this.einheiten = einheiten;
		this.zugehoerigesLager = zugehoerigesLager;
		this.zubuchung = zubuchung;
		//Überprüfung ob eine Zubuchung oder Abbuchung vorliegt
		if(this.getBuchungsStatus())
		{
			this.zuOderAbbuchung = "Zubuchung";
		}
		else if(!this.getBuchungsStatus())
		{
			this.zuOderAbbuchung = "Abbuchung";
		}
	}
	
	
	public int getEinheiten(){
		return this.einheiten;
	}
	
	public boolean getBuchungsStatus(){
		return this.zubuchung;
	}
	
	public String getZugehoerigesLager(){
		return this.zugehoerigesLager;
	}
	
	/**
	 * Überschriebene toString-Methode um eine korrekte Anzeige in der Buchungsliste zu ermöglichen
	 */
	public String toString()
	{
		return this.getZuOderAbbuchung()+ " Einheiten: " + this.einheiten + " Lager: " + this.zugehoerigesLager;
	}

	public void setEinheiten(int einheiten) {
		this.einheiten = einheiten;
		
	}
	public String getZuOderAbbuchung()
	{
		return this.zuOderAbbuchung;
	}

}