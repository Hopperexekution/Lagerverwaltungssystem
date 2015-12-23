package model;

/**
 * Model für den Redo/Undo-Mechanismus
 * @author Robin
 *
 */
public class UndoRedoModel {
	Buchung beinhalteteBuchung;
	double prozent;
	
	public UndoRedoModel(Buchung buchung, double prozent){
		this.beinhalteteBuchung = buchung;
		this.prozent = prozent;
	}

	public UndoRedoModel(Buchung buchung) {
		this.beinhalteteBuchung = buchung;
	}

	public Buchung getBuchung(){
		return this.beinhalteteBuchung;
	}
	
	public double getProzent(){
		return this.prozent;
	}
	
	public void setBuchung(Buchung buchung){
		this.beinhalteteBuchung = buchung;
	}
	
	public void setProzent(double prozent){
		this.prozent = prozent;
	}
}
