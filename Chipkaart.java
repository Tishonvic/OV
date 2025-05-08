package ov;

import java.util.*;

public class Chipkaart {
    public List<String[]> kaarten = new ArrayList<>();
    
    public void NieuwKaart(String type) {
    	String id = "ov" + (kaarten.size() + 1);
        String[] nieuweKaart = new String[] {"0.00", id, type, "false", "Venlo"};
        
        kaarten.add(nieuweKaart);
    }
    
    public String[] HaalKaart(String id) {
    	for (int i = 0; i < kaarten.size(); i++) {
    		String[] array = kaarten.get(i);
    		
    		if (array[1].equalsIgnoreCase(id)) {
    			return array;
    		}
    	}
		return null;
    }
}
