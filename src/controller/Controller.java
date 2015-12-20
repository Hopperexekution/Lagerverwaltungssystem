package controller;

import java.awt.EventQueue;

import view.Hauptmenue;


public class Controller {

	//Objekt-Deklarationen
	Hauptmenue hauptmenue;
	
	//Variablen-Deklarationen
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller controller = new Controller();
					Hauptmenue hauptmenue = new Hauptmenue(controller);
					hauptmenue.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Controller()
	{
		
	}
}
