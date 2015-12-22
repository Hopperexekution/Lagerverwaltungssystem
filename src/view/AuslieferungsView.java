package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;

import controller.Controller;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AuslieferungsView extends JFrame {
	private Controller controller;
	
	
	
	public AuslieferungsView(Controller controller, int gesamtMenge ) 
	{
		this.controller = controller;
		getContentPane().setLayout(null);
		
		JLabel auslieferungUeberschrift = new JLabel("Auslieferung");
		auslieferungUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 15));
		auslieferungUeberschrift.setBounds(10, 11, 102, 19);
		getContentPane().add(auslieferungUeberschrift);
		
		JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
			}
		});
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Niedersachsen", "Frankreich"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 76, 102, 37);
		getContentPane().add(list);
		
		JLabel lagerUeberschrift = new JLabel("Lager");
		lagerUeberschrift.setBounds(10, 47, 46, 14);
		getContentPane().add(lagerUeberschrift);
		
		JLabel verfgbareEinheitenUeberschrift = new JLabel("Verfügbare Einheiten");
		verfgbareEinheitenUeberschrift.setBounds(138, 47, 120, 14);
		getContentPane().add(verfgbareEinheitenUeberschrift);
		
		JLabel anzahlverfgbareEinheiten = new JLabel("0");
		anzahlverfgbareEinheiten.setBounds(138, 77, 46, 14);
		getContentPane().add(anzahlverfgbareEinheiten);
		
		JLabel auszulieferndeEinheitenUeberschrift = new JLabel("auszuliefernde Einheiten");
		auszulieferndeEinheitenUeberschrift.setBounds(319, 47, 145, 14);
		getContentPane().add(auszulieferndeEinheitenUeberschrift);
		
		JTextField anzahlauszulieferndeEinheiten = new JTextField();
		anzahlauszulieferndeEinheiten.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		anzahlauszulieferndeEinheiten.setBounds(319, 74, 86, 20);
		getContentPane().add(anzahlauszulieferndeEinheiten);
		anzahlauszulieferndeEinheiten.setColumns(10);
		
		JButton neuesLagerButton = new JButton("Neues Lager");
		neuesLagerButton.setBounds(23, 359, 125, 23);
		getContentPane().add(neuesLagerButton);
		
		JButton bestaetigenButton = new JButton("Bestätigen");
		bestaetigenButton.setBounds(160, 359, 139, 23);
		getContentPane().add(bestaetigenButton);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Lager 1", "Lager 2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(23, 124, 648, 198);
		getContentPane().add(list_1);
		
		JLabel GesamteinheitenSchriftzug = new JLabel("Gesamteinheiten:");
		GesamteinheitenSchriftzug.setBounds(495, 333, 119, 14);
		getContentPane().add(GesamteinheitenSchriftzug);
		
		JLabel ausgabeGesamteinheiten = new JLabel("0");
		ausgabeGesamteinheiten.setBounds(600, 333, 76, 14);
		ausgabeGesamteinheiten.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(ausgabeGesamteinheiten);
		
		setBounds(400, 200, 695, 450);
		setVisible(true);
		setResizable(false);
	}
}
