package ov;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.*;

import oop1.SavingsAccount;

public class OV {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Chipkaart chipkaart = new Chipkaart();
		Paal paal = new Paal(chipkaart);
		
		String ovingebruik = "";
		
		while (true) {
			if (ovingebruik.equalsIgnoreCase("")) {
				if (chipkaart.kaarten.size() < 1) { 
					System.out.println("Er bestaat nog geen OV. Er wordt nu eentje gemaakt.");
					System.out.println("Geef de type OV-chipkaart aan: Anoniem of Student.");
					String type = sc.nextLine();
					
					chipkaart.NieuwKaart(type);
				} else {					
					System.out.println("Welke OV-chipkaart wilt u gebruiken?");
					
					for (int i = 0; i < chipkaart.kaarten.size(); i++) {
						String[] array = chipkaart.kaarten.get(i);
						
						System.out.println(array[1]);
					}
					
					String ovkeuze = sc.nextLine();
					
					String[] array = chipkaart.HaalKaart(ovkeuze);
					
					if (array != null) {
						if (array[1].equalsIgnoreCase(ovkeuze)) {
							System.out.println("Deze ov bestaat!");
							ovingebruik = array[1];
						} else {
							System.out.println("Deze ov bestaat niet!");
						}
					}
				} 
			} else {
				System.out.println("1. Saldo Checken"+"\n"+ "2. Opwaarderen"+"\n"+ "3. Inchecken"+"\n"+ "4. Uitchecken"+"\n");
				
				int choice = sc.nextInt();
				
				switch (choice) {
					case 1:
						paal.saldo(ovingebruik);
						break;
					case 2:
						System.out.println("Hoeveel wilt u opwaarderen? (Schrijf het zo: 0.00)");
						
						double cash = sc.nextDouble();
						
						paal.opwaarderen(ovingebruik, cash);
						break;
					case 3:
						paal.checkin(ovingebruik);
						break;
					case 4:
						paal.checkuit(ovingebruik);
						break;
				}
			}
		}
	}
}