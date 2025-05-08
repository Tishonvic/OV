package ov;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Paal {
	static Scanner sc = new Scanner(System.in);
	
	private Chipkaart chipkaart;  // Store the shared instance
	private double rate = 0.25;
	private double[] tijden = {15, 30, 28};
	private int richting;
	
	public String[] haltes = {"Nijmegen", "Heyendaal", "Venlo", "Arnhem"};
	
	LocalTime incheckTime;
	LocalDate incheckDate;

    public Paal(Chipkaart chipkaart) {
        this.chipkaart = chipkaart;  // Use the same Chipkaart instance
    }
    
    public void saldo(String id) {
    	String[] array = chipkaart.HaalKaart(id);
		
		if (array != null) {
			if (array[1].equalsIgnoreCase(id)) {
				System.out.println(array[0]);
			}
		}
    }
    
    public void opwaarderen(String id, double cash) {
    	String[] array = chipkaart.HaalKaart(id);
		
		if (array != null) {
			if (array[1].equalsIgnoreCase(id)) {
				double saldo = Double.parseDouble(array[0]);
				saldo += cash;
				
				array[0] = String.valueOf(saldo);
				
				System.out.println("U nieuwe saldo is: $" + array[0]);
			}
		}
    }
	
	public void checkin(String id, int locatie) {
		String[] array = chipkaart.HaalKaart(id);
		
		if (array != null) {
			if (array[3].equalsIgnoreCase("false")) {
				if (array[1].equalsIgnoreCase(id)) {			
					double saldo = Double.parseDouble(array[0]);
					
					if (saldo > 0) {
						incheckTime = LocalTime.now();
						incheckDate = LocalDate.now();
						
						DateTimeFormatter TimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
					    String formattedTime = incheckTime.format(TimeFormat);
					    
					    DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					    String formattedDate = incheckDate.format(DateFormat);
						
						System.out.println("Ingecheckt: " + formattedDate + " " + formattedTime);
						
						array[3] = "true";
					} else if (saldo <= 0) {
						System.out.println("U heeft onvoldoende saldo!");
					}
				}
			} else {
				System.out.println("U bent al ingecheckt.");
			}
		}
	}
	
	public void checkuit(String id) {
		String[] array = chipkaart.HaalKaart(id);
		
		if (array != null) {
			if (array[3].equalsIgnoreCase("true")) {
				if (array[1].equalsIgnoreCase(id)) {			
					double saldo = Double.parseDouble(array[0]);
					
					if (saldo > 0) {
						LocalTime uitcheckTime = LocalTime.now();
						LocalDate uitcheckDate = LocalDate.now();
						
						DateTimeFormatter TimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
					    String formattedTimeUit = incheckTime.format(TimeFormat);
					    
					    DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					    String formattedDateUit = incheckDate.format(DateFormat);
					    
					    long seconds = ChronoUnit.SECONDS.between(incheckTime, uitcheckTime);
					    
						if (array[2].equalsIgnoreCase("Student")) {
							saldo -= ((seconds*rate)*0.4);	
						} else {
							saldo -= (seconds*rate);
						}
						
						array[0] = String.valueOf(saldo);
						
						System.out.println("U nieuwe saldo is: $" + array[0] + "\n" + "$" + (seconds*rate) + " is af u saldo gehaald.");
						System.out.println("Uitgecheckt: " + formattedDateUit + " " + formattedTimeUit);
						
						array[3] = "false";
						array[4] = haltes[richting];
						
						System.out.println("Huidige locatie: " + array[4]);
					}
				}
			} else {
				System.out.println("U bent niet ingecheckt.");
			}
		}
	}
	
	public boolean checkreistijd(String id) {
		String[] array = chipkaart.HaalKaart(id);
		
		if (array != null) {
			if (array[3].equalsIgnoreCase("true")) {
				if (array[1].equalsIgnoreCase(id)) {			
					LocalTime nowTime = LocalTime.now();
					LocalDate nowDate = LocalDate.now();
					
					long seconds = ChronoUnit.SECONDS.between(incheckTime, nowTime);
					
					if (seconds >= (tijden[richting])) {
						return true;
					}
				}
			}
		}
		
		
		return false;
	}
}