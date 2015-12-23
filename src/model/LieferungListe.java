package model;

import java.io.Serializable;
import java.util.List;

public class LieferungListe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7492267644781800919L;

	public List<Lieferung> getLieferungsListe() {
		return lieferungsListe;
	}

	public void setLieferungsListe(List<Lieferung> lieferungsListe) {
		this.lieferungsListe = lieferungsListe;
	}

	List<Lieferung> lieferungsListe;
	
	public LieferungListe(List<Lieferung> lieferungsListe){
		this.lieferungsListe = lieferungsListe;
	}
	

}
