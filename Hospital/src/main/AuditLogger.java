package main;

public class AuditLogger {
    private static AuditLogger instance;
    private AuditLogger() {} 

    public static AuditLogger getInstance() {
        if (instance == null) instance = new AuditLogger();
        return instance;
    }
    public void log(String msg) { System.out.println("[LOG]: " + msg); }
}
