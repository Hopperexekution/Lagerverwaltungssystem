package model;

import java.util.Date;
import java.util.LinkedList;

public class Lieferung {
	LinkedList<Buchung> zugehoerigeBuchungen = new LinkedList<Buchung>();
	Date datum;
	int gesamtEinheiten;
	
	public Lieferung(Date datum, int gesamtEinheiten){
		this.datum = datum;
		this.gesamtEinheiten = gesamtEinheiten;
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
	public String toString(){
		return this.getDatum().toString();
	}
}