package ov;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.*;

public class ovFuncties {
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
				String[] array = chipkaart.HaalKaart(ovingebruik);
				
				if (array != null) {
					if (array[3].equalsIgnoreCase("false")) {
						System.out.println("1. Saldo Checken"+"\n"+ "2. Opwaarderen"+"\n"+ "3. Inchecken");
					} else if (array[3].equalsIgnoreCase("true")) {
						while (true) {
							boolean kanuit = paal.checkreistijd(ovingebruik);
							
							if (kanuit == false) {
								System.out.print(".");
							} else if (kanuit == true) {
								System.out.println("");
								System.out.println("4. Uitchecken");
								break;
							}
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
					int keuze = sc.nextInt();
					
					switch (keuze) {
						case 1:
							paal.saldo(ovingebruik);
							break;
						case 2:
							System.out.println("Hoeveel wilt u opwaarderen? (Schrijf het zo: 0.00)");
							
							while (!sc.hasNextDouble())
							{
							    System.out.println("Ongeldig bedrag. Probeer opnieuw.");
							    sc.next();
							}
							
							double cash = sc.nextDouble();
							
							paal.opwaarderen(ovingebruik, cash);
							
							break;
						case 3:
							System.out.println("Huidige locatie: " + array[4]);
							System.out.println("Waar wilt u heen reizen:");
							
							int index = 0;
							
							for (int i = 0; i < paal.haltes.length; i++) {
								boolean isnieuw = paal.haltes[i].equalsIgnoreCase(array[4]);
								
								if (isnieuw == false) {
									index++;
									System.out.println(index+". "+paal.haltes[i]);
								}
							}
							
							int choice1 = sc.nextInt();
							
							paal.checkin(ovingebruik, (choice1 - 1));
							
							break;
						case 4:
							paal.checkuit(ovingebruik);
							
							break;
					}
				}
			}
		}
	}
}