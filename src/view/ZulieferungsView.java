package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import controller.Controller;
import model.Buchung;
import model.Lager;
import model.LagerModel;
import model.UndoRedoModel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ZulieferungsView extends JFrame {
	private Controller controller;



	public ZulieferungsView(Controller controller, int gesamtMenge ) {
		this.controller = controller;
		getContentPane().setLayout(null);


		JLabel lblNeueZulieferung = new JLabel("Neue Zulieferung");
		lblNeueZulieferung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNeueZulieferung.setBounds(20, 11, 138, 20);
		getContentPane().add(lblNeueZulieferung);

		JLabel lblGesamtmenge = new JLabel("Gesamtmenge");
		lblGesamtmenge.setBounds(20, 45, 85, 14);
		getContentPane().add(lblGesamtmenge);

		JTextField gesamtMengeEinheiten = new JTextField();
		gesamtMengeEinheiten.setText(gesamtMenge + "");
		gesamtMengeEinheiten.setEditable(false);
		gesamtMengeEinheiten.setBounds(121, 42, 86, 20);
		getContentPane().add(gesamtMengeEinheiten);

		JLabel lblLager = new JLabel("Lager");
		lblLager.setBounds(20, 90, 46, 14);
		getContentPane().add(lblLager);
		
		Vector<Lager> auswaehlbareLager = new Vector<Lager>();
		auswaehlbareLager = controller.findeAuswaehlbarLager(auswaehlbareLager);

		
		JComboBox<Lager> lagerAuswahl = new JComboBox<Lager>(auswaehlbareLager);
		lagerAuswahl.setBounds(20, 115, 130, 20);
		getContentPane().add(lagerAuswahl);
		
		JLabel lblProzent = new JLabel("Prozent");
		lblProzent.setBounds(170, 90, 46, 14);
		getContentPane().add(lblProzent);
		
		JTextField lblAngabeProzent = new JTextField();
		lblAngabeProzent.setBounds(170, 115, 86, 20);
		getContentPane().add(lblAngabeProzent);
		lblAngabeProzent.setColumns(30);

		JLabel lblEinheiten = new JLabel("Einheiten");
		lblEinheiten.setBounds(367, 90, 55, 14);
		getContentPane().add(lblEinheiten);
		
		JLabel labelAnzahlEinheiten = new JLabel("0");
		labelAnzahlEinheiten.setEnabled(false);
		labelAnzahlEinheiten.setBounds(367, 116, 46, 14);
		getContentPane().add(labelAnzahlEinheiten);

		JList<Buchung> zulieferungLager = new JList<Buchung>();
		DefaultListModel<Buchung> lieferungsBuchungen = new DefaultListModel<Buchung>();
		zulieferungLager.setModel(lieferungsBuchungen);
		
		JScrollPane scrollbarLieferung = new JScrollPane(zulieferungLager);
		scrollbarLieferung.setBounds(20, 160, 550, 120);
		getContentPane().add(scrollbarLieferung);

		JButton butUndo = new JButton("Undo");
		butUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo;

			}
		});
		butUndo.setBounds(20, 294, 90, 14);
		getContentPane().add(butUndo);

		JButton butRedo = new JButton("Redo");
		butRedo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo;

			}
		});
		butRedo.setBounds(130, 294, 90, 14);
		getContentPane().add(butRedo);

		JButton butBesttigen = new JButton("Best\u00E4tigen");
		butBesttigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		butBesttigen.setBounds(440, 294, 100, 14);
		getContentPane().add(butBesttigen);

		JButton butNchstesLager = new JButton("N\u00E4chstes Lager");
		butNchstesLager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lblAngabeProzent.getText().length() != 0) {
					try{
					double prozent = Double.parseDouble(lblAngabeProzent.getText());

					if (prozent > 100 || prozent <= 0) 
					{
						JOptionPane.showMessageDialog(null,
								"Prozentangabe liegt entweder über 100% oder ist kleiner gleich 0%. Bitte andere Angabe eintragen.");
					} else {
						Buchung neueBuchung = controller.erstelleBuchung(prozent, gesamtMenge, lagerAuswahl.getSelectedItem().toString());
						if(!neueBuchung.equals(null))
						{
						lieferungsBuchungen.addElement(neueBuchung);
						zulieferungLager.setModel(lieferungsBuchungen);

						}

					}
					}catch(NumberFormatException f){
						JOptionPane.showMessageDialog(null, "Bitte nur Zahlen verwenden.");
					}	
				} else {
					JOptionPane.showMessageDialog(null, "Es ist eine Prozentangabe für die Verteilung notwendig");
				}

				
			}
		});
		butNchstesLager.setBounds(240, 294, 180, 14);
		getContentPane().add(butNchstesLager);

		setBounds(400, 200, 630, 400);
		setVisible(true);
	}


}