package model;

import java.util.Date;

public class Buchung {
	int einheiten;
	String zugehoerigesLager;
	Date datum;
	boolean zubuchung;
	
	
	public Buchung(int einheiten, String zugehoerigesLager, Date datum, boolean zubuchung){
		this.einheiten = einheiten;
		this.zugehoerigesLager = zugehoerigesLager;
		this.datum = datum;
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
	
	public Date getDatum(){
		return this.datum;
	}

}