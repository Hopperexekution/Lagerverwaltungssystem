package model;

import java.util.Date;

public class Buchung {
	int einheiten;
	String zugehoerigesLager;
	boolean zubuchung;
	
	
	public Buchung(int einheiten, String zugehoerigesLager, boolean zubuchung){
		this.einheiten = einheiten;
		this.zugehoerigesLager = zugehoerigesLager;
		this.zubuchung = zubuchung;
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
	
	
	public String toString()
	{
		return "Einheiten: " + this.einheiten + " Lager: " + this.zugehoerigesLager;
	}

	public void setEinheiten(int einheiten) {
		this.einheiten = einheiten;
		
	}

}