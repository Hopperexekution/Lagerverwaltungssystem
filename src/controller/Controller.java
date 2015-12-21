package controller;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.tree.TreeNode;
import javax.swing.JFileChooser;

import model.Buchung;
import model.Lager;
import model.LagerModel;
import view.Hauptmenue;


public class Controller {

	static Hauptmenue hauptmenue;
	private List<Buchung> buchungsListe = new ArrayList<Buchung>();
	private static LagerModel lagerModel;
	JFileChooser jFileChooser = new JFileChooser();
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					erstelleLagerListe();
					hauptmenue = new Hauptmenue(controller, lagerModel);
					hauptmenue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Controller()
	{
		
	}
	
	public void buchungHinzufügen(int einheiten, String zugehoerigesLager, Date datum, boolean zubuchung)
	{
		Buchung buchung = new Buchung(einheiten, zugehoerigesLager, datum, zubuchung);
		buchungsListe.add(buchung);
		buchungsListe.sort(new DateComparator());
		
	}
	
	public void laden()
	{
		jFileChooser.showOpenDialog(null);
	}	
	
	public void speichern()
	{
		jFileChooser.showSaveDialog(null);
	}	
	
	public void programmBeenden()
	{
		String[] yesNoOptions = { "Ja", "Nein"};
		int auswahl = JOptionPane.showOptionDialog(null, "Wollen Sie das Programm wirklich beenden?", "Wollen Sie das Programm wirklich beenden?", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, yesNoOptions, yesNoOptions[0]);
		if(auswahl == 0){
			System.exit(0);
		}
	}
	
	private static void erstelleLagerListe()
	{
		Lager root = new Lager("Lager");
		addDefaultLager(root, new String[]{"Deutschland", "Europa", "Großbritannien"});
			addDefaultLager(root.getChildList().get(0), new String[]{"Niedersachsen", "NRW", "Bremen", "Hessen", "Sachsen", "Brandenburg", "MV"});	
				addDefaultLager(root.getChildList().get(0).getChildList().get(0), new String[]{"Hannover-Misburg", "Nienburg"});
			addDefaultLager(root.getChildList().get(1), new String[]{"Frankreich", "Italien", "Spanien"});
				addDefaultLager(root.getChildList().get(1).getChildList().get(0),new String[]{"Paris-Nord", "Orleans", "Marseille", "Nimes"});
				addDefaultLager(root.getChildList().get(1).getChildList().get(1), new String[]{"Mailand", "L'Aquila"});
		lagerModel = new LagerModel(root);
	}	
	
	private static void addDefaultLager(Lager vater, String[] kinder)
	{
		for(String kind: kinder)
		{
			vater.getChildList().add(new Lager(kind,vater));
		}
	}
	
	private LagerModel getLagerModel()
	{
		return lagerModel;
	}
}
