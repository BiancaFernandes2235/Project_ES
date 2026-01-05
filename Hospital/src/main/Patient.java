package main;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private List<Prescription> activePrescriptions = new ArrayList<>();

    public Patient(String id, String name, String pass) { 
    	super(id, name, pass); 
    }

    public void addPrescription(Prescription p) { 
    	activePrescriptions.add(p); 
    }
    
    public List<Prescription> getActivePrescriptions() { 
    	return activePrescriptions; 
    	}
}