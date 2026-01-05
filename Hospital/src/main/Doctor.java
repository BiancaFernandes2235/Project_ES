package main;

public class Doctor extends User {
    private String specialty;
    public Doctor(String id, String name, String pass, String spec) {
        super(id, name, pass);
        this.specialty = spec;
    }
    public void overrideSafetyAlert(String reason) {
        AuditLogger.getInstance().log("Override by " + name + ": " + reason);
    }
}
