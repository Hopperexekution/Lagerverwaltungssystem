package controller;

import java.util.Comparator;
import java.util.Date;
import model.Buchung;

public class DateComparator implements Comparator {

	
	public int compare(Object o1, Object o2) {
		long dateOne = ((Buchung) o1).getDatum().getTime();
		long dateTwo = ((Buchung) o2).getDatum().getTime();
		if (dateOne > dateTwo){
			return 1;
		}
		else if(dateOne < dateTwo){
			return -1;
		}
		else{
			return 0;
		}
	}

}
