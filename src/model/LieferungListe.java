package model;

import java.io.Serializable;
import java.util.List;

import javax.swing.DefaultListModel;

public class LieferungListe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7492267644781800919L;

	public DefaultListModel<Lieferung> getLieferungsListe() {
		return lieferungsListe;
	}

	public void setLieferungsListe(DefaultListModel<Lieferung> lieferungsListe) {
		this.lieferungsListe = lieferungsListe;
	}

	DefaultListModel<Lieferung> lieferungsListe;
	
	public LieferungListe(DefaultListModel<Lieferung> lieferungsListe){
		this.lieferungsListe = lieferungsListe;
	}
	

}
