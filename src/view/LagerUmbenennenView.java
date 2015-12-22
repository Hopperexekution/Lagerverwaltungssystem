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
	private JTextField ausgabeAktuelleBezeichnung;
	private JTextField eingabeNeueBezeichnung;
	public LagerUmbenennenView(Controller controller, Lager ausgewaehltesLager) {
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("New label");
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JLabel lagerBearbeitenUeberschrift = new JLabel("Lager Bearbeiten");
		lagerBearbeitenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lagerBearbeitenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerBearbeitenUeberschrift.setBounds(85, 11, 270, 22);
		getContentPane().add(lagerBearbeitenUeberschrift);
		
		JButton okButton = new JButton("\u00C4nderungen \u00FCbernehmen");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		okButton.setBounds(10, 129, 222, 23);
		getContentPane().add(okButton);
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		abbrechenButton.setBounds(242, 129, 182, 23);
		getContentPane().add(abbrechenButton);
		
		ausgabeAktuelleBezeichnung = new JTextField();
		ausgabeAktuelleBezeichnung.setEnabled(false);
		ausgabeAktuelleBezeichnung.setHorizontalAlignment(SwingConstants.LEFT);
		ausgabeAktuelleBezeichnung.setBounds(139, 67, 285, 20);
		getContentPane().add(ausgabeAktuelleBezeichnung);
		ausgabeAktuelleBezeichnung.setColumns(10);
		
		JLabel aktuelleBezSchriftzug = new JLabel("Aktuelle Bezeichnung:");
		aktuelleBezSchriftzug.setHorizontalAlignment(SwingConstants.RIGHT);
		aktuelleBezSchriftzug.setBounds(22, 70, 115, 14);
		getContentPane().add(aktuelleBezSchriftzug);
		
		eingabeNeueBezeichnung = new JTextField();
		eingabeNeueBezeichnung.setHorizontalAlignment(SwingConstants.LEFT);
		eingabeNeueBezeichnung.setColumns(10);
		eingabeNeueBezeichnung.setBounds(139, 98, 285, 20);
		getContentPane().add(eingabeNeueBezeichnung);
		
		JLabel neueBezSchriftzug = new JLabel("Neue Bezeichnung:");
		neueBezSchriftzug.setHorizontalAlignment(SwingConstants.RIGHT);
		neueBezSchriftzug.setBounds(22, 101, 115, 14);
		getContentPane().add(neueBezSchriftzug);
		
		
		setBounds(124, 122, 110, 23);
		setVisible(true);
	}
}
