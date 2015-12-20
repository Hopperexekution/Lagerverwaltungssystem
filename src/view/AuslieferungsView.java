package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AuslieferungsView extends JFrame {
	private JTextField textField;
	public AuslieferungsView() {
		getContentPane().setLayout(null);
		
		JLabel lblAuslieferung = new JLabel("Auslieferung");
		lblAuslieferung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAuslieferung.setBounds(10, 11, 102, 14);
		getContentPane().add(lblAuslieferung);
		
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
		
		JLabel lblLager = new JLabel("Lager");
		lblLager.setBounds(10, 47, 46, 14);
		getContentPane().add(lblLager);
		
		JLabel lblVerfgbareEinheiten = new JLabel("Verf\u00FCgbare Einheiten");
		lblVerfgbareEinheiten.setBounds(138, 47, 120, 14);
		getContentPane().add(lblVerfgbareEinheiten);
		
		JLabel lblNewLabel = new JLabel("100");
		lblNewLabel.setBounds(138, 77, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblAuszulieferndeEinheiten = new JLabel("auszuliefernde Einheiten");
		lblAuszulieferndeEinheiten.setBounds(309, 47, 125, 14);
		getContentPane().add(lblAuszulieferndeEinheiten);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		textField.setBounds(319, 74, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNeuesLager = new JButton("Neues Lager");
		btnNeuesLager.setBounds(23, 359, 125, 23);
		getContentPane().add(btnNeuesLager);
		
		JButton btnBesttigen = new JButton("Best\u00E4tigen");
		btnBesttigen.setBounds(210, 359, 89, 23);
		getContentPane().add(btnBesttigen);
		
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
		
		JLabel lblGesamteinheiten = new JLabel("Gesamteinheiten");
		lblGesamteinheiten.setEnabled(false);
		lblGesamteinheiten.setBounds(525, 333, 89, 14);
		getContentPane().add(lblGesamteinheiten);
		
		JLabel label = new JLabel("0");
		label.setEnabled(false);
		label.setBounds(625, 333, 46, 14);
		getContentPane().add(label);
	}
}
