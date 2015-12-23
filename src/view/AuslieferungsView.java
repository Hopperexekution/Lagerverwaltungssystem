package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import model.Buchung;
import model.Lager;

import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class AuslieferungsView extends JFrame {
	private Controller controller;
	private JLabel anzahlverfgbareEinheiten;
	
	
	public AuslieferungsView(Controller controller, int gesamtMenge ) 
	{
		this.controller = controller;
		
		//Frameeinstellungen
		controller.getHauptmenue().setEnabled(false);
		setBounds(400, 200, 695, 480);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		
		
		
		//Windowlistener um Hauptmenue zu blockieren, während das Umbenennen-Fenster geöffnent ist
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				controller.getHauptmenue().setEnabled(true);
			}
		});
		
		
		
		JLabel auslieferungUeberschrift = new JLabel("Auslieferung");
		auslieferungUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		auslieferungUeberschrift.setBounds(10, 11, 102, 20);
		getContentPane().add(auslieferungUeberschrift);
		
		Vector<Lager> auswaehlbareLager = new Vector<Lager>();
		auswaehlbareLager = controller.findeAuswaehlbarLagerAuslieferung(auswaehlbareLager);

		JComboBox<Lager> lagerAuswahl = new JComboBox<Lager>(auswaehlbareLager);
		lagerAuswahl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = controller.findePassendesLager(lagerAuswahl.getSelectedItem().toString(), controller.getLagerModel().getRoot()).getBestand() + "";
				anzahlverfgbareEinheiten.setText(s);
			}
		});
		lagerAuswahl.setBounds(10, 77, 130, 20);
		getContentPane().add(lagerAuswahl);
		
		JLabel lagerUeberschrift = new JLabel("Lager");
		lagerUeberschrift.setBounds(10, 47, 46, 20);
		getContentPane().add(lagerUeberschrift);
		
		JLabel auszulieferndeUeberschrift = new JLabel("auszuliefernde Einheiten");
		auszulieferndeUeberschrift.setBounds(160, 47, 150, 20);
		getContentPane().add(auszulieferndeUeberschrift);
		
		JTextField anzahlAuszulieferndeEinheiten = new JTextField("0");
		anzahlAuszulieferndeEinheiten.setBounds(160, 77, 50, 20);
		getContentPane().add(anzahlAuszulieferndeEinheiten);
		
		JLabel verfgbareEinheitenUeberschrift = new JLabel("Verfügare Einheiten");
		verfgbareEinheitenUeberschrift.setBounds(330, 47, 120, 20);
		getContentPane().add(verfgbareEinheitenUeberschrift);
		
		anzahlverfgbareEinheiten = new JLabel();
		anzahlverfgbareEinheiten.setText(controller.findePassendesLager(lagerAuswahl.getSelectedItem().toString(), controller.getLagerModel().getRoot()).getBestand()+ "");
		anzahlverfgbareEinheiten.setBounds(330, 77, 50, 20);
		getContentPane().add(anzahlverfgbareEinheiten);
		
		JLabel lieferumfangUeberschrift = new JLabel("Lieferumfang");
		lieferumfangUeberschrift.setBounds(470, 47, 145, 20);
		getContentPane().add(lieferumfangUeberschrift);
		
		JLabel lieferumfang = new JLabel(gesamtMenge + "");
		lieferumfang.setBounds(470, 77, 86, 20);
		getContentPane().add(lieferumfang);
		
		JList<Buchung> buchungen = new JList<Buchung>();
		DefaultListModel<Buchung> lieferungsBuchungen = new DefaultListModel<Buchung>();
		buchungen.setModel(lieferungsBuchungen);
		JScrollPane buchungenScrollBar = new JScrollPane(buchungen);
		buchungenScrollBar.setBounds(23, 124, 648, 198);
		getContentPane().add(buchungenScrollBar);
		
		JButton neuesLagerButton = new JButton("Neues Lager");
		neuesLagerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (anzahlAuszulieferndeEinheiten.getText().length() != 0) {
					try{
					int einheit = Integer.parseInt(anzahlAuszulieferndeEinheiten.getText());
					Lager ausgewaehlt = controller.findePassendesLager(lagerAuswahl.getSelectedItem().toString(), controller.getLagerModel().getRoot());
					if (einheit > ausgewaehlt.getBestand()) 
					{
						JOptionPane.showMessageDialog(null,
								"So viele Einheiten sind in dem ausgewählten Lager nicht vorhanden. Bitte wählen sie eine andere Anzahl von Einheiten.");
					} else if (einheit <= 0) 
					{	
							JOptionPane.showMessageDialog(null, "Bitte verwenden Sie nur positive Zahlen und nicht 0.");
					} 
					else if(einheit <= (gesamtMenge - controller.getVerteilteEinheiten()))
					{
								Buchung neueBuchung = controller.erstelleAbbuchung(einheit, lagerAuswahl.getSelectedItem().toString());
								lagerAuswahl.removeItem(ausgewaehlt);
								if(!neueBuchung.equals(null))
								{
									lieferungsBuchungen.addElement(neueBuchung);
									buchungen.setModel(lieferungsBuchungen);
							
								}

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Sie wollen mehr Einheiten verteilen als noch zu verteilen sind. Sie müssen noch " + (gesamtMenge - controller.getVerteilteEinheiten()) + " Einheiten verteilen.");
					}

					}catch(NumberFormatException f){
						JOptionPane.showMessageDialog(null, "Bitte nur Zahlen verwenden.");
					}	
				} else {
					JOptionPane.showMessageDialog(null, "Es ist eine Angabe von ganzzahligen Einheiten für die Verteilung notwendig");
				}

				
			}
		});
		neuesLagerButton.setBounds(20, 359, 140, 23);
		getContentPane().add(neuesLagerButton);
		
		JButton bestaetigenButton = new JButton("Bestätigen");
		bestaetigenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.getVerteilteEinheiten() == gesamtMenge)
				{
					controller.erstelleAuslieferung(gesamtMenge);
					controller.berechneBestand(controller.getLagerModel().getRoot());
					controller.refreshTree();
					controller.getHauptmenue().setEnabled(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Es müssen zuerst alle Einheiten verteilt sein bevor das Bestätigen möglich ist.");
				}
			}
		});
		bestaetigenButton.setBounds(180, 359, 140, 23);
		getContentPane().add(bestaetigenButton);
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loescheUndoListe();
				controller.getHauptmenue().setEnabled(true);
				dispose();
			}
		});
		abbrechenButton.setBounds(340, 359, 140, 23);
		getContentPane().add(abbrechenButton);
		
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.undoMoeglich()){
				Buchung undoBuchung = controller.undo();
				Lager ausgewaehlt = controller.findePassendesLager(undoBuchung.getZugehoerigesLager(),(Lager) controller.getLagerModel().getRoot());
				lagerAuswahl.addItem(ausgewaehlt);
				lieferungsBuchungen.removeElementAt(lieferungsBuchungen.getSize()-1);
				buchungen.setModel(lieferungsBuchungen);
				}
			}
		});
		undo.setBounds(20, 402, 140, 23 );
		getContentPane().add(undo);
		
		JButton redo = new JButton("Redo");
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.redoMoeglich()){
					Buchung redoBuchung = controller.redo();
					Lager ausgewaehlt = controller.findePassendesLager(redoBuchung.getZugehoerigesLager(), (Lager) controller.getLagerModel().getRoot());
					lagerAuswahl.removeItem(ausgewaehlt);
					lieferungsBuchungen.addElement(redoBuchung);
					buchungen.setModel(lieferungsBuchungen);
					}
				
			}
		});
		redo.setBounds(180, 402, 140, 23);
		getContentPane().add(redo);
		
	}
}
