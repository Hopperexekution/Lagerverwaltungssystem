package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import controller.Controller;
import model.Lager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class LagerUmbenennenView extends JFrame {
	
	
	public LagerUmbenennenView(Controller controller, Lager ausgewaehltesLager) {
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lagerUmbenennenUeberschrift = new JLabel("Lager Umbenennen");
		lagerUmbenennenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lagerUmbenennenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerUmbenennenUeberschrift.setBounds(92, 24, 270, 22);
		getContentPane().add(lagerUmbenennenUeberschrift);
		
		JButton okButton = new JButton("\u00C4nderungen \u00FCbernehmen");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		okButton.setBounds(32, 129, 222, 23);
		getContentPane().add(okButton);
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		abbrechenButton.setBounds(260, 129, 182, 23);
		getContentPane().add(abbrechenButton);
		
		JLabel ausgabeAktuelleBezeichnung = new JLabel(ausgewaehltesLager.getName());
		ausgabeAktuelleBezeichnung.setHorizontalAlignment(SwingConstants.LEFT);
		ausgabeAktuelleBezeichnung.setBounds(160, 67, 285, 20);
		getContentPane().add(ausgabeAktuelleBezeichnung);
		
		JLabel aktuelleBezSchriftzug = new JLabel("Aktuelle Bezeichnung:");
		aktuelleBezSchriftzug.setHorizontalAlignment(SwingConstants.RIGHT);
		aktuelleBezSchriftzug.setBounds(22, 70, 132, 14);
		getContentPane().add(aktuelleBezSchriftzug);
		
		JTextField eingabeNeueBezeichnung = new JTextField();
		eingabeNeueBezeichnung.setHorizontalAlignment(SwingConstants.LEFT);
		eingabeNeueBezeichnung.setColumns(10);
		eingabeNeueBezeichnung.setBounds(157, 98, 285, 20);
		getContentPane().add(eingabeNeueBezeichnung);
		
		JLabel neueBezSchriftzug = new JLabel("Neue Bezeichnung:");
		neueBezSchriftzug.setHorizontalAlignment(SwingConstants.RIGHT);
		neueBezSchriftzug.setBounds(22, 101, 131, 14);
		getContentPane().add(neueBezSchriftzug);
		
		
		setBounds(400, 200, 480, 190);
		setVisible(true);
		setResizable(false);
	}
}
