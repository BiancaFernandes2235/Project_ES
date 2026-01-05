package main;

public class Nurse extends User {
    public Nurse(String id, String name, String pass) { 
    	super(id, name, pass); 
    }

    public String triage(int heartRate, double temp) {
        if (heartRate > 120 || temp > 39.5) 
        	return "Red";
        else
        	return "Green";
    }
}
