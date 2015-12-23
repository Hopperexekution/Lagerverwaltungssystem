package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.MutableTreeNode;



public class Lager implements Serializable
	{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -88137515948885056L;
	private String name;
	private int kapazitaet;
	private int bestand;
	private Lager vater;
	private List<Buchung> buchungen = new ArrayList<Buchung>();
	private List<Lager> children = new ArrayList<Lager>();
	
	/**
	 * Einfügen Lager auf unterster Ebene
	 * @param name Lagername
	 * @param kapazität Lagerkapazität
	 */
	public Lager(String name, int kapazitaet, int bestand, Lager vater)
	{
		this.name = name;
		this.kapazitaet = kapazitaet;
		this.bestand = bestand;
		this.vater = vater;
	}
	public Lager(String name, int kapazitaet, int bestand)
	{
		this.name = name;
		this.kapazitaet = kapazitaet;
		this.bestand = bestand;
	}
	public Lager(String name, Lager vater)
	{
		this.name = name;
		this.vater = vater;
	}
	public Lager(String name)
	{
		this.name = name;
	}
	

	public void erhoeheBestand(int einheitenBuchung)
	{
		this.bestand += einheitenBuchung;
	}
	
	public void vermindereBestand(int einheitenBuchung)
	{
		this.bestand -= einheitenBuchung;
	}
	
	public List<Lager> getChildList()
	{
		return children;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getKapazitaet()
	{
		return this.kapazitaet;
	}
	
	public int getBestand()
	{
		return this.bestand;
	}
	
	public List<Buchung> getBuchungen()
	{
		return this.buchungen;
	}
	
	public void hinzufuegenBuchung(Buchung buchung){
		this.buchungen.add(buchung);
	}
	public String toString()
	{
		return name;
	}
	public Lager getVater()
	{
		return vater;
	}
	public void setVater(Lager vater) 
	{
		this.vater = vater;
	}
	public void setKapazitaet(int kapazitaet)
	{
		this.kapazitaet = kapazitaet;
	}
	public void setBestand(int bestand)
	{
		this.bestand = bestand;
	}
	public void setName(String name)
	{
		this.name = name;
	}
}