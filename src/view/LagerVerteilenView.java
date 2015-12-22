package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.Controller;
import model.Buchung;
import model.Lager;

public class LagerVerteilenView extends JFrame {
	private Controller controller;



	public LagerVerteilenView(Controller controller, int gesamtMenge, Lager zuLoeschendesLager) {
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
		if(auswaehlbareLager.contains(zuLoeschendesLager)){
		auswaehlbareLager.remove(zuLoeschendesLager);
		}
		
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
				if(controller.undoMoeglich()){
				Buchung undoBuchung = controller.undo();
				Lager ausgewaehlt = controller.findePassendesLager(undoBuchung.getZugehoerigesLager(),(Lager) controller.getLagerModel().getRoot());
				lagerAuswahl.addItem(ausgewaehlt);
				lieferungsBuchungen.removeElementAt(lieferungsBuchungen.getSize()-1);
				zulieferungLager.setModel(lieferungsBuchungen);
				}
			}
		});
		butUndo.setBounds(60, 291, 90, 23);
		getContentPane().add(butUndo);

		JButton butRedo = new JButton("Redo");
		butRedo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.redoMoeglich()){
				Buchung redoBuchung = controller.redo();
				Lager ausgewaehlt = controller.findePassendesLager(redoBuchung.getZugehoerigesLager(), (Lager) controller.getLagerModel().getRoot());
				lagerAuswahl.removeItem(ausgewaehlt);
				lieferungsBuchungen.addElement(redoBuchung);
				zulieferungLager.setModel(lieferungsBuchungen);
				}

			}
		});
		butRedo.setBounds(156, 291, 90, 23);
		getContentPane().add(butRedo);

		JButton butBesttigen = new JButton("Bestätigen");
		butBesttigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(controller.getProzent() == 100)
				{
					int restEinheiten;
					restEinheiten = gesamtMenge - controller.getVerteilteEinheiten();
					int auswahl = JOptionPane.showConfirmDialog(null, "Die restliche(n) unverteilte(n) " + restEinheiten + " Einheit(en) wird/werden auf das zuletzt hinzugefügte Lager verteilt.\nWollen Sie das tun? Wenn nicht benutzen Sie den Undo Knopf und verteilen die Prozentangaben neu.", "Bestätigen", JOptionPane.YES_OPTION);
					if(auswahl == JOptionPane.YES_OPTION)
					{
					controller.erstelleZulieferung(restEinheiten, gesamtMenge);
					Lager vater = zuLoeschendesLager.getVater();
					vater.setBestand(vater.getBestand() - zuLoeschendesLager.getBestand());
					vater.setKapazitaet(vater.getKapazitaet() - zuLoeschendesLager.getKapazitaet());
					vater.getChildList().remove(zuLoeschendesLager);
					dispose();
					controller.refreshTree();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Es müssen zuerst 100% der Einheiten verteilt sein bevor das Bestätigen möglich ist.");
				}
			}
		});
		butBesttigen.setBounds(463, 291, 100, 23);
		getContentPane().add(butBesttigen);

		JButton butNchstesLager = new JButton("Nächstes Lager");
		butNchstesLager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (lblAngabeProzent.getText().length() != 0) {
					try{
					double prozent = Double.parseDouble(lblAngabeProzent.getText());
					int einheit = (int) (Math.floor((double)(gesamtMenge * (prozent/100))));;
					if (prozent > 100 || prozent <= 0) 
					{
						JOptionPane.showMessageDialog(null,
								"Prozentangabe liegt entweder über 100% oder ist kleiner gleich 0%. Bitte andere Angabe eintragen.");
					} else if ((controller.getProzent() + prozent) > 100) 
					{	if(controller.getProzent() == 100)
						{
							JOptionPane.showMessageDialog(null, "Sie haben alle 100% verteilt. Betätigen Sie den Undo Button für Veränderungen.\nOder Bestätigen Sie die Zulieferung und noch nicht verteilte Einheiten(aufgrund von Abrundungen) werden dem letzten Lager hinzugefügt.\nOder brechen Sie die Zulieferung ab.");
						} else
						{
							double nochZuVerteilendeProzent = (100 - controller.getProzent());
							JOptionPane.showMessageDialog(null, "Wenn diese Prozentzahl der Gesamteinheiten hinzugefügt werden soll, dann würde die insgesamte Prozentanzahl über 100% liegen.\nDies ist nicht möglich wählen Sie bitte einen kleineren Wert. Der aktuelle Prozentwert entspricht " + controller.getProzent()+"%.\nEs sind noch " + nochZuVerteilendeProzent + "% zu verteilen");
						}
					}
					else
					{
						if(einheit != 0)
						{
							Lager ausgewaehlt = controller.findePassendesLager(lagerAuswahl.getSelectedItem().toString(), (Lager) controller.getLagerModel().getRoot());
							int freieEinheiten = ausgewaehlt.getKapazitaet() - ausgewaehlt.getBestand();
							if(einheit <=  freieEinheiten)
							{
								Buchung neueBuchung = controller.erstelleZubuchung(prozent, gesamtMenge, lagerAuswahl.getSelectedItem().toString());
								lagerAuswahl.removeItem(ausgewaehlt);
								if(!neueBuchung.equals(null))
								{
									lieferungsBuchungen.addElement(neueBuchung);
									zulieferungLager.setModel(lieferungsBuchungen);
							
								}
								controller.loescheRedoListe();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Das Lager kann nur noch " + freieEinheiten + " Einheiten aufnehmen.\nSie wollen aber " + einheit + " Einheiten aufnehmen.");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Bei dieser Prozentzahl würden keine Einheiten auf das Lager verteilt werden.\nBitte verwenden Sie eine andere Prozentzahl.");
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
		butNchstesLager.setBounds(256, 291, 180, 23);
		getContentPane().add(butNchstesLager);
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.loescheRedoListe();
				controller.loescheUndoListe();
				dispose();
			}
		});
		abbrechenButton.setBounds(463, 328, 100, 23);
		getContentPane().add(abbrechenButton);

		setBounds(400, 200, 600, 400);
		setResizable(false);
		setVisible(true);
	}


}
