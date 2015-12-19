package controller;

import view.Hauptmenue;


public class Controller {

	//Objekt-Deklarationen
	Hauptmenue hauptmenue;
	
	//Variablen-Deklarationen
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Controller();
	}
	
	public Controller()
	{
		
		
		hauptmenue = new Hauptmenue(this);
		
	}
}
