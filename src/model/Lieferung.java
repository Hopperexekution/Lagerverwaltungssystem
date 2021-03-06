package model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
/**
 * Mit dieser Klasse k�nnen Lieferungem erzeugt werden
 * @author Kevin
 *
 */
public class Lieferung  implements Serializable{
	LinkedList<Buchung> zugehoerigeBuchungen = new LinkedList<Buchung>();
	Date datum;
	int gesamtEinheiten;
	String zuOderAuslieferung;
	
	public Lieferung(Date datum, int gesamtEinheiten){
		this.datum = datum;
		this.gesamtEinheiten = gesamtEinheiten;
	}
	public Lieferung(Date datum, int gesamtEinheiten, String zuOderAbbuchung){
		this.datum = datum;
		this.gesamtEinheiten = gesamtEinheiten;
		this.zuOderAuslieferung = zuOderAbbuchung;
	}
	public Lieferung(Date datum, int gesamtEinheiten, LinkedList<Buchung> zugehoerigeBuchungen)
	{
		this.datum = datum;
		this.gesamtEinheiten = gesamtEinheiten;
		this.zugehoerigeBuchungen = zugehoerigeBuchungen;
	}
	
	public int getGesamtEinheiten(){
		return this.gesamtEinheiten;
	}
	
	public Date getDatum(){
		return this.datum;
	}
	
	public LinkedList<Buchung> getZugehoerigeBuchungen(){
		return this.zugehoerigeBuchungen;
	}
	
	public void hinzufuegenBuchung(Buchung buchung){
		this.zugehoerigeBuchungen.add(buchung);
	}
	public String getZuOderAbbuchung()
	{
		return this.zuOderAuslieferung;
	}
	public String toString(){
		return this.getDatum().toString() + "   " + this.getZuOderAbbuchung();
	}
}