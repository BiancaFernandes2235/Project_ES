package main;

import java.util.Date;

public class Prescription {
    private String name;
    private String dosage;
    private Date expiry;

    public Prescription(String n, String d, Date e) { 
    	name = n; dosage = d; expiry = e; 
    }
    
    public String getName() { 
    	return name; 
    	}
}
