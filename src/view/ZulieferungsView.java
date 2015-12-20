package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ZulieferungsView extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	public ZulieferungsView() {
		getContentPane().setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(572, 162, 10, 121);
		getContentPane().add(scrollBar);
		
		JLabel lblNeueZulieferung = new JLabel("Neue Zulieferung");
		lblNeueZulieferung.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNeueZulieferung.setBounds(10, 11, 138, 14);
		getContentPane().add(lblNeueZulieferung);
		
		JLabel lblGesamtmenge = new JLabel("Gesamtmenge");
		lblGesamtmenge.setBounds(20, 45, 85, 14);
		getContentPane().add(lblGesamtmenge);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
			}
		});
		textField.setBounds(121, 42, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLager = new JLabel("Lager");
		lblLager.setBounds(20, 90, 46, 14);
		getContentPane().add(lblLager);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Niedersachsen", "Frankreich"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(20, 115, 55, 36);
		getContentPane().add(list);
		
		JLabel lblProzent = new JLabel("Prozent");
		lblProzent.setBounds(126, 90, 46, 14);
		getContentPane().add(lblProzent);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
			}
		});
		textField_1.setBounds(121, 115, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEinheiten = new JLabel("Einheiten");
		lblEinheiten.setBounds(367, 90, 55, 14);
		getContentPane().add(lblEinheiten);
		
		JLabel label = new JLabel("0");
		label.setEnabled(false);
		label.setBounds(367, 116, 46, 14);
		getContentPane().add(label);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Lager1", "Lager2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(20, 162, 562, 121);
		getContentPane().add(list_1);
		
		JLabel lblUndo = new JLabel("Undo");
		lblUndo.setBounds(29, 294, 46, 14);
		getContentPane().add(lblUndo);
		
		JLabel lblRedo = new JLabel("Redo");
		lblRedo.setBounds(121, 294, 46, 14);
		getContentPane().add(lblRedo);
		
		JLabel lblBesttigen = new JLabel("Best\u00E4tigen");
		lblBesttigen.setBounds(135, 338, 72, 14);
		getContentPane().add(lblBesttigen);
		
		JLabel lblNchstesLager = new JLabel("N\u00E4chstes Lager");
		lblNchstesLager.setBounds(29, 338, 96, 14);
		getContentPane().add(lblNchstesLager);
	}
}
