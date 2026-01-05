package main;

public class Administrator extends User {
    public Administrator(String id, String name, String pass) { 
    	super(id, name, pass); 
    }
    
    public void generateReport() { 
    	AuditLogger.getInstance().log("Report Generated"); 
    }
}
