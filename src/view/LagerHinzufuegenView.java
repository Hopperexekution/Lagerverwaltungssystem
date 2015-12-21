package view;

import javax.swing.JFrame;

import controller.Controller;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;

public class LagerHinzufuegenView extends JFrame {
	private JTextField bezeichnungsTextfeld;
	private JTextField kapazitaetsTextfeld;
	public LagerHinzufuegenView(Controller controller) {
		setBounds(150,150,250,200);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel neuesLagerUeberschrift = new JLabel("Neues Lager");
		neuesLagerUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		neuesLagerUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		neuesLagerUeberschrift.setBounds(77, 11, 96, 25);
		getContentPane().add(neuesLagerUeberschrift);
		
		JLabel bezeichungUeberschrift = new JLabel("Bezeichnung:");
		bezeichungUeberschrift.setHorizontalAlignment(SwingConstants.RIGHT);
		bezeichungUeberschrift.setBounds(10, 49, 72, 14);
		getContentPane().add(bezeichungUeberschrift);
		
		JLabel kapazitaetUeberschrift = new JLabel("Kapazit\u00E4t:");
		kapazitaetUeberschrift.setHorizontalAlignment(SwingConstants.RIGHT);
		kapazitaetUeberschrift.setBounds(10, 83, 72, 14);
		getContentPane().add(kapazitaetUeberschrift);
		
		JButton erstellenButton = new JButton("Lager erstellen");
		erstellenButton.setBounds(10, 122, 110, 23);
		getContentPane().add(erstellenButton);
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.setBounds(124, 122, 110, 23);
		getContentPane().add(abbrechenButton);
		
		bezeichnungsTextfeld = new JTextField();
		bezeichnungsTextfeld.setHorizontalAlignment(SwingConstants.LEFT);
		bezeichnungsTextfeld.setBounds(87, 46, 147, 20);
		getContentPane().add(bezeichnungsTextfeld);
		bezeichnungsTextfeld.setColumns(10);
		
		kapazitaetsTextfeld = new JTextField();
		kapazitaetsTextfeld.setHorizontalAlignment(SwingConstants.LEFT);
		kapazitaetsTextfeld.setColumns(10);
		kapazitaetsTextfeld.setBounds(87, 80, 147, 20);
		getContentPane().add(kapazitaetsTextfeld);
		setVisible(true);
	}
}
