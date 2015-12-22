package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import controller.Controller;
import model.Lager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class LagerUmbenennenView extends JFrame {

	private JTextField eingabeNeueBezeichnung;
	
	public LagerUmbenennenView(Controller controller, Lager ausgewaehltesLager) {
		
		//Frameeinstellungen
		controller.getHauptmenue().setEnabled(false);
		setBounds(400, 200, 480, 190);
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
		
		
		
		
		
		//Buttons		
		JButton okButton = new JButton("Änderungen übernehmen");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String neuerName = eingabeNeueBezeichnung.getText();
				if(controller.findePassendesLager(neuerName, controller.getLagerModel().getRoot()) == null)
				{
					if(controller.getLagerModel().getRoot().getName().equals(neuerName))
					{
						JOptionPane.showMessageDialog(getContentPane(), "Der Name des Hauptlagers darf nicht gewählt werden.");						
					}
					else if (!neuerName.equals(""))
					{// wenn nicht leer:
						ausgewaehltesLager.setName(neuerName);
						controller.refreshTree();
						controller.getHauptmenue().setEnabled(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(getContentPane(), "Bitte geben Sie einen neuen Namen ein.");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(getContentPane(), "Dieser Name ist bereits vorhanden. Bitte wählen Sie einen neuen.");
				}
			}
		});
		okButton.setBounds(32, 129, 222, 23);
		getContentPane().add(okButton);
		
		
		
		
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getHauptmenue().setEnabled(true);
				dispose();
			}
		});
		abbrechenButton.setBounds(260, 129, 182, 23);
		getContentPane().add(abbrechenButton);
		
		
		
		
		
		
		
		
		
		//Textfelder
		eingabeNeueBezeichnung = new JTextField();
		eingabeNeueBezeichnung.setHorizontalAlignment(SwingConstants.LEFT);
		eingabeNeueBezeichnung.setColumns(10);
		eingabeNeueBezeichnung.setBounds(157, 98, 285, 20);
		getContentPane().add(eingabeNeueBezeichnung);
		
		
		
		
		
		
		
		
		
		//Labels
		JLabel lagerUmbenennenUeberschrift = new JLabel("Lager Umbenennen");
		lagerUmbenennenUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lagerUmbenennenUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		lagerUmbenennenUeberschrift.setBounds(92, 24, 270, 22);
		getContentPane().add(lagerUmbenennenUeberschrift);
		
		JLabel neueBezSchriftzug = new JLabel("Neue Bezeichnung:");
		neueBezSchriftzug.setHorizontalAlignment(SwingConstants.RIGHT);
		neueBezSchriftzug.setBounds(22, 101, 131, 14);
		getContentPane().add(neueBezSchriftzug);
		
		JLabel aktuelleBezSchriftzug = new JLabel("Aktuelle Bezeichnung:");
		aktuelleBezSchriftzug.setHorizontalAlignment(SwingConstants.RIGHT);
		aktuelleBezSchriftzug.setBounds(22, 70, 132, 14);
		getContentPane().add(aktuelleBezSchriftzug);
		
		JLabel ausgabeAktuelleBezeichnung = new JLabel(ausgewaehltesLager.getName());
		ausgabeAktuelleBezeichnung.setHorizontalAlignment(SwingConstants.LEFT);
		ausgabeAktuelleBezeichnung.setBounds(160, 67, 285, 20);
		getContentPane().add(ausgabeAktuelleBezeichnung);		
	}
}
