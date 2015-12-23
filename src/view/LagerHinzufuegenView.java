package view;

import javax.swing.JFrame;

import controller.Controller;
import model.Lager;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * Ansicht um ein neues Lager zu Erstellen
 * @author Birk
 *
 */
public class LagerHinzufuegenView extends JFrame {
	private JTextField bezeichnungsTextfeld;
	private JTextField kapazitaetsTextfeld;
	public LagerHinzufuegenView(Controller controller, Lager ausgewaehltesLager) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				controller.getHauptmenue().setEnabled(true);
			}
		});
		
		
		getContentPane().setLayout(null);
		controller.getHauptmenue().setEnabled(false);
		
		
		JLabel neuesLagerUeberschrift = new JLabel("Neues Lager");
		neuesLagerUeberschrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		neuesLagerUeberschrift.setHorizontalAlignment(SwingConstants.CENTER);
		neuesLagerUeberschrift.setBounds(77, 11, 96, 25);
		getContentPane().add(neuesLagerUeberschrift);
		
		JLabel bezeichungUeberschrift = new JLabel("Bezeichnung:");
		bezeichungUeberschrift.setHorizontalAlignment(SwingConstants.RIGHT);
		bezeichungUeberschrift.setBounds(10, 49, 78, 14);
		getContentPane().add(bezeichungUeberschrift);
		
		JLabel kapazitaetUeberschrift = new JLabel("Kapazit\u00E4t:");
		kapazitaetUeberschrift.setHorizontalAlignment(SwingConstants.RIGHT);
		kapazitaetUeberschrift.setBounds(10, 83, 78, 14);
		getContentPane().add(kapazitaetUeberschrift);
		
		bezeichnungsTextfeld = new JTextField();
		bezeichnungsTextfeld.setHorizontalAlignment(SwingConstants.LEFT);
		bezeichnungsTextfeld.setBounds(100, 46, 168, 20);
		getContentPane().add(bezeichnungsTextfeld);
		bezeichnungsTextfeld.setColumns(10);
		
		kapazitaetsTextfeld = new JTextField();
		kapazitaetsTextfeld.setHorizontalAlignment(SwingConstants.LEFT);
		kapazitaetsTextfeld.setColumns(10);
		kapazitaetsTextfeld.setBounds(100, 80, 168, 20);
		
		JButton erstellenButton = new JButton("Lager erstellen");
		erstellenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
						//Lager erstellen einleiten. Ung�ltige Eingaben werden gefiltert.
						String lagerName = bezeichnungsTextfeld.getText();
						int kapazitaet = Integer.parseInt(kapazitaetsTextfeld.getText());
						lagerName = lagerName.trim();
						if (lagerName.length() == 0){
							JOptionPane.showMessageDialog(getContentPane(), "Die Bezeichnung darf nicht leer sein oder nur aus Leerzeichen bestehen!");
						}
						else if(kapazitaet < 0)
						{
							JOptionPane.showMessageDialog(getContentPane(), "Die Kapazit�t muss eine positive Zahl sein");
						}
						else if(kapazitaet > 2000000000)
						{
							JOptionPane.showMessageDialog(getContentPane(), "Die Kapazit�t muss kleiner als zwei Milliarden sein. Bitt w�hlen sie einen anderen Wert.");
						}
						else if(kapazitaet < ausgewaehltesLager.getBestand() && ausgewaehltesLager.getChildList().isEmpty())
						{
							JOptionPane.showMessageDialog(getContentPane(), "Die Kapazit�t ist zu gering um den Bestand \nvon " + ausgewaehltesLager.getName() + "aufzunehmen.");
						}
						else if(controller.findePassendesLager(lagerName, controller.getLagerModel().getRoot()) == null)
						{   
							controller.lagerHinzufuegen(new Lager(lagerName, kapazitaet,0,ausgewaehltesLager), ausgewaehltesLager);
							setVisible(false);
							controller.getHauptmenue().setEnabled(true);
							dispose();
							
						}
						else
						{
							JOptionPane.showMessageDialog(getContentPane(), "Es gibt bereits ein Lager mit diesem Namen. W�hlen Sie bitte einen anderen Namen.");
						}
					}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(getContentPane(), "Die Kapazit�t muss eine Zahl und kleiner als zwei Milliarden sein.");
				}
			}
		});
		erstellenButton.setBounds(10, 122, 138, 23);
		getContentPane().add(erstellenButton);
		
		JButton abbrechenButton = new JButton("Abbrechen");
		abbrechenButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				controller.getHauptmenue().setEnabled(true);
				setVisible(false);
				dispose();
			}
		});
		abbrechenButton.setBounds(158, 122, 110, 23);
		getContentPane().add(abbrechenButton);
		getContentPane().add(kapazitaetsTextfeld);
		
		setVisible(true);
		setBounds(150,150,285,185);
		setResizable(false);
	}
}
