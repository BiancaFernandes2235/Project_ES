package main;

import java.util.Date;

public class Appointment {
    private Date date;
    private Doctor doc;
    private Patient pat;
    
    public Appointment(Date d, Doctor doc, Patient pat) {
    	this.date = d; this.doc = doc; this.pat = pat; 
    }
    
    public Date getDate() { 
    	return date; 
    }
    
    public Doctor getDoctor() { 
    	return doc; 
    }
}
