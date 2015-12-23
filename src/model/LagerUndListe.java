package model;

import java.io.Serializable;
/**
 * Objekt um die Models abspeichern zu können
 * @author Robin
 *
 */
public class LagerUndListe implements Serializable{
	LagerModel lagerModel;
	LieferungListe lieferungListe;
	public LagerUndListe(LagerModel lagerModel, LieferungListe lieferungListe)
	{
		this.lagerModel = lagerModel;
		this.lieferungListe = lieferungListe;
	}
	
	public LagerModel getLagerModel()
	{
		return this.lagerModel;
	}
	public LieferungListe getLieferungListe()
	{
		return this.lieferungListe;
	}
}
